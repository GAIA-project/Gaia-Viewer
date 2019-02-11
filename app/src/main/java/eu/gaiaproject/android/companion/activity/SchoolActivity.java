package eu.gaiaproject.android.companion.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chen0040.sbclient.SpringBootWebSocketClient;
import com.github.chen0040.sbclient.TopicHandler;

import net.sparkworks.cargo.common.dto.PhenomenonDTO;
import net.sparkworks.cargo.common.dto.UnitDTO;
import net.sparkworks.cargo.common.dto.data.LatestDTO;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import eu.gaiaproject.android.companion.R;
import eu.gaiaproject.android.companion.adapter.MyPagerAdapter;
import eu.gaiaproject.android.companion.cargo.dto.GroupDTO;
import eu.gaiaproject.android.companion.cargo.dto.ResourceDTO;
import eu.gaiaproject.android.companion.cargo.dto.WsDTO;
import eu.gaiaproject.android.companion.model.SchoolModel;
import eu.gaiaproject.android.companion.util.Communications;

@EActivity(R.layout.activity_school)
public class SchoolActivity extends AppCompatActivity {
    private static final String TAG = "SchoolActivity";
    public static final String SCHOOL_EXTRA = "SCHOOL";

    private Communications communications;
    public static Map<UUID, String> phenomena = new HashMap<>();
    public static Map<UUID, String> units = new HashMap<>();
    public static Map<String, LatestDTO> latestValues = new HashMap<>();
    public static ObjectMapper mapper = new ObjectMapper();
    private SchoolModel school;
    private MyPagerAdapter adapter;
    private SpringBootWebSocketClient wsClient;
    private ViewPager viewPager;

    @AfterViews
    void init() {
        final TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.rooms).setIcon(R.mipmap.room));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.aggregated_sensors));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.hardware_sensors));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Log.i(TAG, "SchoolExtra:" + getIntent().hasExtra(SCHOOL_EXTRA));
        school = (SchoolModel) getIntent().getExtras().get(SCHOOL_EXTRA);
        Log.i(TAG, "SchoolExtra:" + getIntent().hasExtra(SCHOOL_EXTRA));
        setTitle(school.getName());
        initializeData();
    }

    @Background
    void initializeData() {
        communications = new Communications();
        getResources(school.getUuid(), school.getPath());
    }


    @Background
    void getResources(final UUID uuid, final String path) {
        final Collection<UnitDTO> theUnits = communications.listUnits();
        if (theUnits != null) {
            for (final UnitDTO theUnit : theUnits) {
                units.put(theUnit.getUuid(), theUnit.getName());
            }
        }
        final Collection<PhenomenonDTO> thePhenomena = communications.listPhenomena();
        if (thePhenomena != null) {
            for (final PhenomenonDTO thePhenomenon : thePhenomena) {
                phenomena.put(thePhenomenon.getUuid(), thePhenomenon.getName());
            }
        }

        final Set<SchoolModel> schoolList = new HashSet<>();

        final Collection<GroupDTO> groupDTOS = communications.listSubgroups(uuid);
        for (final GroupDTO room : groupDTOS) {
            schoolList.add(new SchoolModel(room.getName(), room.getUuid(), room.getPath()));
        }
        updateRooms(schoolList);

        final Collection<ResourceDTO> resources = communications.getResourcesOfGroup(uuid);
        final List<ResourceDTO> groupResources = resources.stream().filter(resourceDTO ->
                resourceDTO.getGroupUuid().equals(school.getUuid())).collect(Collectors.toList());
        final List<ResourceDTO> aggregateResources = groupResources.stream().filter(resourceDTO ->
                resourceDTO.getSystemName().startsWith("site-")).collect(Collectors.toList());
        final List<ResourceDTO> hardwareResources = groupResources.stream().filter(resourceDTO ->
                !resourceDTO.getSystemName().startsWith("site-")).collect(Collectors.toList());
        for (ResourceDTO hardwareResource : hardwareResources) {
            Log.i(TAG, "group:" + hardwareResource);
        }
        for (ResourceDTO hardwareResource : hardwareResources) {
            Log.i(TAG, "hard:" + hardwareResource);
        }

        updateAggResources(aggregateResources);
        updateHardwareResources(hardwareResources);

        for (final ResourceDTO groupResource : groupResources) {
            try {
                latestValues.put(groupResource.getSystemName(), communications.getResourceLatestValue(groupResource.getUuid()));
            } catch (Exception e) {

            }
            updateValues();
        }

        wsClient = new SpringBootWebSocketClient();
        wsClient.setId("android-gaiaviewer-001");
        TopicHandler handler = wsClient.subscribe("/path/" + school.getPath());
        handler.addListener(message -> {

            try {
                final WsDTO wsDto = mapper.readValue(message.getContent(), WsDTO.class);
                Log.i(TAG, wsDto.toString());
                if (latestValues != null && latestValues.containsKey(wsDto.getResourceUri())) {
                    final LatestDTO latest = latestValues.get(wsDto.getResourceUri());
                    latest.setLatest(wsDto.getValue());
                    latest.setLatestTime(wsDto.getTimestamp());
                    updateValues();
                }
            } catch (IOException ignore) {
                //ignore
            }
        });
        wsClient.connect("https://ws.sparkworks.net/ws/websocket?access_token=" + communications.getAccountToken().getAccess_token());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wsClient != null) {
            wsClient.disconnect();
        }
    }

    @UiThread
    void updateRooms(final Collection<SchoolModel> schoolList) {
        if (schoolList.isEmpty()) {
            viewPager.setCurrentItem(1);
        }
        adapter.setRooms(schoolList);
    }

    @UiThread
    void updateValues() {
        adapter.updateResources();
    }

    @UiThread
    void updateAggResources(final Collection<ResourceDTO> resources) {
        if (resources.isEmpty() && viewPager.getCurrentItem() == 1) {
            viewPager.setCurrentItem(2);
        }
        adapter.setAggregateResources(resources);
    }

    @UiThread
    void updateHardwareResources(final Collection<ResourceDTO> resources) {
        adapter.setHardwareResources(resources);
    }
}
