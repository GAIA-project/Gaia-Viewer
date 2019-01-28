package eu.gaiaproject.android.companion.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import eu.gaiaproject.android.companion.R;
import eu.gaiaproject.android.companion.adapter.ResourceListAdapter;
import eu.gaiaproject.android.companion.cargo.dto.ResourceDTO;

@EFragment(R.layout.fragment_sensors)
public class SensorsFragment extends Fragment {

    private static final String TAG = "SensorsFragment";
    @ViewById
    ListView aggResourceList;

    private final List<ResourceDTO> aggResourceModelList = new ArrayList<>();
    private ResourceListAdapter aggResourceAdapter;

    @AfterViews
    void init() {
        Log.e(TAG, "SensorsFragment afterViews " + getArguments().getString("title"));
        aggResourceAdapter = new ResourceListAdapter(aggResourceModelList, getContext());
        aggResourceList.setAdapter(aggResourceAdapter);
    }

    public void updateResources(final Collection<ResourceDTO> resources) {
        aggResourceModelList.addAll(resources);
        if (aggResourceAdapter != null) {
            aggResourceAdapter.notifyDataSetChanged();
        } else {
            Log.e(TAG, "updateResources: aggResourceAdapter == null");
        }
        //aggResourceLayout.setVisibility(resources.isEmpty() ? View.GONE : View.VISIBLE);
    }

    public void updateResources() {
        if (aggResourceAdapter != null) {
            aggResourceAdapter.notifyDataSetChanged();
        }
    }
}