package eu.gaiaproject.android.companion.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import eu.gaiaproject.android.companion.R;
import eu.gaiaproject.android.companion.adapter.RoomsListAdapter;
import eu.gaiaproject.android.companion.activity.SchoolActivity_;
import eu.gaiaproject.android.companion.model.SchoolModel;

import static eu.gaiaproject.android.companion.activity.SchoolActivity.SCHOOL_EXTRA;

@EFragment(R.layout.fragment_rooms)
public class RoomsFragment extends Fragment {
    private static final String TAG = "RoomsFragment";

    @ViewById
    ListView roomsList;

    private final List<SchoolModel> roomsModelList = new ArrayList<>();
    private RoomsListAdapter roomsAdapter;

    @AfterViews
    void init() {
        roomsAdapter = new RoomsListAdapter(roomsModelList, getContext());
        roomsList.setAdapter(roomsAdapter);
        roomsList.setOnItemClickListener((parent, view, position, id) -> {
            final SchoolModel school = (SchoolModel) parent.getAdapter().getItem(position);
            Log.i(TAG, "clicked " + school.getName());
            Intent intent = new Intent(getActivity(), SchoolActivity_.class);
            intent.putExtra(SCHOOL_EXTRA, school);
            startActivity(intent);
        });

    }

    @UiThread
    public void updateRooms(final Collection<SchoolModel> rooms) {
        roomsAdapter.addAll(rooms);
        roomsAdapter.notifyDataSetChanged();
    }

    @UiThread
    public void updateRooms() {
        roomsAdapter.notifyDataSetChanged();
    }
}