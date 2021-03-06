package eu.gaiaproject.android.companion.util;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixplicity.easyprefs.library.Prefs;


import net.sparkworks.cargo.common.dto.PhenomenonDTO;
import net.sparkworks.cargo.common.dto.UnitDTO;
import net.sparkworks.cargo.common.dto.data.LatestDTO;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import eu.gaiaproject.android.companion.BuildConfig;
import eu.gaiaproject.android.companion.aa.SwAACheckTokenResponse;
import eu.gaiaproject.android.companion.aa.SwAAProfileResponse;
import eu.gaiaproject.android.companion.aa.SwAccessTokenResponse;
import eu.gaiaproject.android.companion.cargo.dto.GroupDTO;
import eu.gaiaproject.android.companion.cargo.dto.ResourceDTO;

import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import static net.sparkworks.cargo.common.CargoRoutes.GROUP;
import static net.sparkworks.cargo.common.CargoRoutes.GROUP_RESOURCES;
import static net.sparkworks.cargo.common.CargoRoutes.GROUP_SUBGROUPS;
import static net.sparkworks.cargo.common.CargoRoutes.PHENOMENON;
import static net.sparkworks.cargo.common.CargoRoutes.RESOURCE_LATEST_BY_UUID;
import static net.sparkworks.cargo.common.CargoRoutes.UNIT;
import static eu.gaiaproject.android.companion.util.Constants.ACCOUNTS_PREF_NAME;

public class Communications {
    private static final String TAG = "Communications";
    private static final String BEARER = "Bearer ";

    private static final String SSO_URL = "https://sso.sparkworks.net/aa/";
    private static final String SPARKS_URL = "https://api.sparkworks.net/v2";
    private static final String PROFILE_SSO_URL = SSO_URL + "profile";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTH_BEARER_PREFIX = "Bearer ";

    private static final String GCDB_URL = "https://gcdb.sparkworks.net/";

    private static final String APPLICATION_JSON_CT = "application/json";

    private final ObjectMapper mapper;
    RestTemplate restClient;

    public Communications() {
        this.mapper = new ObjectMapper();
        this.restClient = new RestTemplate();
    }

    private MultiValueMap<String, String> makeAuthHeaders(SwAccessTokenResponse token) {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, BEARER + token.getAccess_token());
        return headers;
    }

    public SwAAProfileResponse getProfile() {
        final SwAccessTokenResponse token = getAccountToken();
        if (token != null) {
            final MultiValueMap<String, String> headers = makeAuthHeaders(token);
            HttpEntity httpEntity = new HttpEntity(headers);
            try {
                return restClient.exchange(PROFILE_SSO_URL, HttpMethod.GET, httpEntity, SwAAProfileResponse.class).getBody();
            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return null;
            }
        }
        return null;
    }


    private boolean refreshToken(SwAccessTokenResponse token) {
        try {
            URL url = new URL(SSO_URL + "oauth/token?refresh_token=" + token.getRefresh_token() + "&client_id=" + BuildConfig.GaiaViewerId + "&client_secret=" + BuildConfig.GaiaViewerSecret + "&grant_type=refresh_token");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            if (urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line).append('\n');
                }
                final SwAccessTokenResponse newToken = mapper.readValue(total.toString(), SwAccessTokenResponse.class);
                if (newToken != null) {
                    storeToken(newToken);
                    return true;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return false;
    }

    private SwAccessTokenResponse storeToken(final SwAccessTokenResponse newToken) {
        try {
            Prefs.putString(ACCOUNTS_PREF_NAME, mapper.writeValueAsString(newToken));
        } catch (JsonProcessingException e) {
            Log.e(TAG, "JsonProcessingException");
            return null;
        }
        Log.d("LOGINTOKEN", newToken.getAccess_token());
        return newToken;
    }

    public SwAccessTokenResponse getAccountToken() {
        String tokenString = Prefs.getString(ACCOUNTS_PREF_NAME, null);
        Log.d("current token", tokenString);
        if (tokenString != null) {
            try {
                return new ObjectMapper().readValue(tokenString, SwAccessTokenResponse.class);
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    public boolean checkToken() {
        return checkToken(getAccountToken());
    }

    private boolean checkToken(final SwAccessTokenResponse token) {
        final MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        if (token == null) {
            return false;
        }
        body.add("token", token.getAccess_token());
        final MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Constants.OAUTH2_BASIC);
        final HttpEntity<?> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<SwAACheckTokenResponse> resp;
        try {
            resp = restClient.exchange(Constants.OATH2_CHECK_TOKEN_URL, HttpMethod.POST, httpEntity, SwAACheckTokenResponse.class);
        } catch (HttpClientErrorException e) {
            return false;
        }
        if (resp.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return false;
        }
        final SwAACheckTokenResponse response = resp.getBody();
        if (response != null) {
            Log.i(TAG, response.toString());
            if (response.getError() != null) {
                return refreshToken(token);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public SwAccessTokenResponse login(final String username, final String password) {
        Log.i(TAG, "login");
        try {
            final HttpEntity httpEntity = new HttpEntity<>("");
            final SwAccessTokenResponse newToken = restClient.exchange(UriComponentsBuilder.
                    fromUriString(Constants.OATH2_TOKEN_URL2).
                    buildAndExpand(username, password, BuildConfig.GaiaViewerId, BuildConfig.GaiaViewerSecret).toUriString(), HttpMethod.POST, httpEntity, SwAccessTokenResponse.class).getBody();
            if (newToken != null) {
                return storeToken(newToken);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    public boolean clearToken() {
        Prefs.clear();
        return true;
    }


    public Collection<GroupDTO> getGroups() {
        HttpEntity httpEntity = new HttpEntity(prepareHeaders(getAccountToken().getAccess_token()));
        ResponseEntity<Collection<GroupDTO>> response = restClient.
                exchange(UriComponentsBuilder.fromUriString(SPARKS_URL + GROUP).toUriString(), HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Collection<GroupDTO>>() {
                });
        if (!response.getStatusCode().is2xxSuccessful())
            throw new RestClientException(response.toString());
        return response.getBody();
    }

    protected MultiValueMap<String, String> prepareHeaders(final String accessToken) {
        MultiValueMap<String, String> headers = new HttpHeaders();
        if (Objects.nonNull(accessToken)) {
            headers.add(AUTHORIZATION_HEADER, AUTH_BEARER_PREFIX + accessToken);
        }
        return headers;
    }


    public Collection<ResourceDTO> getResourcesOfGroup(UUID uuid) {
        HttpEntity httpEntity = new HttpEntity(prepareHeaders(getAccountToken().getAccess_token()));
        ResponseEntity<Collection<ResourceDTO>> response = restClient.
                exchange(UriComponentsBuilder.fromUriString(SPARKS_URL + GROUP_RESOURCES).buildAndExpand(uuid).toUriString(), HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Collection<ResourceDTO>>() {
                });
        if (!response.getStatusCode().is2xxSuccessful())
            throw new RestClientException(response.toString());
        return response.getBody();
    }

    public Collection<PhenomenonDTO> listPhenomena() {
        HttpEntity httpEntity = new HttpEntity(prepareHeaders(getAccountToken().getAccess_token()));
        ResponseEntity<Collection<PhenomenonDTO>> response = restClient.
                exchange(UriComponentsBuilder.fromUriString(SPARKS_URL + PHENOMENON).toUriString(), HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Collection<PhenomenonDTO>>() {
                });
        if (!response.getStatusCode().is2xxSuccessful())
            throw new RestClientException(response.toString());
        return response.getBody();
    }

    public Collection<UnitDTO> listUnits() {
        HttpEntity httpEntity = new HttpEntity(prepareHeaders(getAccountToken().getAccess_token()));
        ResponseEntity<Collection<UnitDTO>> response = restClient.
                exchange(UriComponentsBuilder.fromUriString(SPARKS_URL + UNIT).toUriString(), HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Collection<UnitDTO>>() {
                });
        if (!response.getStatusCode().is2xxSuccessful())
            throw new RestClientException(response.toString());
        return response.getBody();
    }

    public LatestDTO getResourceLatestValue(UUID uuid) {
        HttpEntity httpEntity = new HttpEntity(prepareHeaders(getAccountToken().getAccess_token()));
        ResponseEntity<LatestDTO> response = restClient.
                exchange(UriComponentsBuilder.fromUriString(SPARKS_URL + RESOURCE_LATEST_BY_UUID).buildAndExpand(uuid.toString()).toUriString(), HttpMethod.GET, httpEntity, LatestDTO.class);
        if (!response.getStatusCode().is2xxSuccessful())
            throw new RestClientException(response.toString());
        return response.getBody();
    }

    public Collection<GroupDTO> listSubgroups(UUID uuid) {
        HttpEntity httpEntity = new HttpEntity(prepareHeaders(getAccountToken().getAccess_token()));
        ResponseEntity<Collection<GroupDTO>> response = restClient.
                exchange(UriComponentsBuilder.fromUriString(SPARKS_URL + GROUP_SUBGROUPS).buildAndExpand(uuid, -1).toUriString(), HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Collection<GroupDTO>>() {
                });
        if (!response.getStatusCode().is2xxSuccessful())
            throw new RestClientException(response.toString());
        return response.getBody();
    }
}
