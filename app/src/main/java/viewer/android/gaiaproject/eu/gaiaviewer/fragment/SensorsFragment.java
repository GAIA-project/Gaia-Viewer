package viewer.android.gaiaproject.eu.gaiaviewer.fragment;

import android.support.v4.app.Fragment;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import viewer.android.gaiaproject.eu.gaiaviewer.R;
import viewer.android.gaiaproject.eu.gaiaviewer.adapter.ResourceListAdapter;
import viewer.android.gaiaproject.eu.gaiaviewer.cargo.dto.ResourceDTO;

@EFragment(R.layout.fragment_sensors)
public class SensorsFragment extends Fragment {

    @ViewById
    ListView aggResourceList;

    private final List<ResourceDTO> aggResourceModelList = new ArrayList<>();
    private ResourceListAdapter aggResourceAdapter;

    @AfterViews
    void init() {
        aggResourceAdapter = new ResourceListAdapter(aggResourceModelList, getContext());
        aggResourceList.setAdapter(aggResourceAdapter);
    }

    public void updateResources(final Collection<ResourceDTO> resources) {
        if (aggResourceAdapter != null) {
            aggResourceAdapter.addAll(resources);
            aggResourceAdapter.notifyDataSetChanged();
        }
        //aggResourceLayout.setVisibility(resources.isEmpty() ? View.GONE : View.VISIBLE);
    }

    public void updateResources() {
        if (aggResourceAdapter != null) {
            aggResourceAdapter.notifyDataSetChanged();
        }
    }
}