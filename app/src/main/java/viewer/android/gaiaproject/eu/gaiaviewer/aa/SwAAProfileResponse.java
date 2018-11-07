package viewer.android.gaiaproject.eu.gaiaviewer.aa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwAAProfileResponse {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String timezone;
    private Set<String> roles;
}
