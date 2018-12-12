package viewer.android.gaiaproject.eu.gaiaviewer;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import net.sparkworks.cargo.common.dto.PhenomenonDTO;
import net.sparkworks.cargo.common.dto.UnitDTO;
import net.sparkworks.cargo.common.dto.data.LatestDTO;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import viewer.android.gaiaproject.eu.gaiaviewer.cargo.dto.ResourceDTO;

@EActivity(R.layout.activity_school)
public class SchoolActivity extends AppCompatActivity {
    private static final String TAG = "SchoolActivity";
    protected static final String SCHOOL_EXTRA = "SCHOOL";

    @ViewById
    ListView aggResourceList;
    @ViewById
    ListView hardwareResourceList;
    @ViewById
    LinearLayout aggResourceLayout;
    @ViewById
    LinearLayout hardwareResourceLayout;

    private Communications communications;
    private final List<ResourceDTO> aggResourceModelList = new ArrayList<>();
    private final List<ResourceDTO> hardwareResourceModelList = new ArrayList<>();
    private ResourceListAdapter aggResourceAdapter;
    private ResourceListAdapter hardwareResourceAdapter;
    public static Map<UUID, String> phenomena = new HashMap<>();
    public static Map<UUID, String> units = new HashMap<>();
    public static Map<UUID, LatestDTO> latestValues = new HashMap<>();
    private SchoolModel school;

    @AfterViews
    void init() {
        communications = new Communications();
        Log.i(TAG, "SchoolExtra:" + getIntent().hasExtra(SCHOOL_EXTRA));
        school = (SchoolModel) getIntent().getExtras().get(SCHOOL_EXTRA);
        Log.i(TAG, "SchoolExtra:" + getIntent().hasExtra(SCHOOL_EXTRA));
        setTitle(school.getName());

        aggResourceAdapter = new ResourceListAdapter(aggResourceModelList, getApplicationContext());
        aggResourceList.setAdapter(aggResourceAdapter);

        hardwareResourceAdapter = new ResourceListAdapter(hardwareResourceModelList, getApplicationContext());
        hardwareResourceList.setAdapter(hardwareResourceAdapter);

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
        final Collection<ResourceDTO> resources = communications.getResourcesOfGroup(uuid);
        final List<ResourceDTO> groupResources = resources.stream().filter(resourceDTO ->
                resourceDTO.getGroupUuid().equals(school.getUuid())).collect(Collectors.toList());
        final List<ResourceDTO> aggregateResources = groupResources.stream().filter(resourceDTO ->
                resourceDTO.getSystemName().startsWith("site-")).collect(Collectors.toList());
        final List<ResourceDTO> hardwareResources = groupResources.stream().filter(resourceDTO ->
                !resourceDTO.getSystemName().startsWith("site-")).collect(Collectors.toList());
        updateAggResources(aggregateResources);
        updateHardwareResources(hardwareResources);

        for (ResourceDTO groupResource : groupResources) {
            latestValues.put(groupResource.getUuid(), communications.getResourceLatestValue(groupResource.getUuid()));
            updateValues();
        }

    }

    @UiThread
    void updateValues() {
        aggResourceAdapter.notifyDataSetChanged();
        hardwareResourceAdapter.notifyDataSetChanged();
    }

    @UiThread
    void updateAggResources(final Collection<ResourceDTO> resources) {
        aggResourceAdapter.addAll(resources);
        aggResourceAdapter.notifyDataSetChanged();
        aggResourceLayout.setVisibility(resources.isEmpty() ? View.GONE : View.VISIBLE);
    }

    @UiThread
    void updateHardwareResources(final Collection<ResourceDTO> resources) {
        hardwareResourceAdapter.addAll(resources);
        hardwareResourceAdapter.notifyDataSetChanged();
        hardwareResourceLayout.setVisibility(resources.isEmpty() ? View.GONE : View.VISIBLE);
    }
}
