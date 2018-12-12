package viewer.android.gaiaproject.eu.gaiaviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import viewer.android.gaiaproject.eu.gaiaviewer.aa.SwAAProfileResponse;
import viewer.android.gaiaproject.eu.gaiaviewer.cargo.dto.GroupDTO;

import static viewer.android.gaiaproject.eu.gaiaviewer.SchoolActivity.SCHOOL_EXTRA;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @ViewById
    TextView textViewMain;
    @ViewById
    ListView schoolList;

    private Communications communications;
    private final List<SchoolModel> schoolModelList = new ArrayList<>();
    private SchoolListAdapter adapter;


    @AfterViews
    void init() {
        communications = new Communications();

        adapter = new SchoolListAdapter(schoolModelList, getApplicationContext());
        schoolList.setAdapter(adapter);
        schoolList.setOnItemClickListener((parent, view, position, id) -> {
            final SchoolModel school = (SchoolModel) parent.getAdapter().getItem(position);
            Log.i(TAG, "clicked " + school.getName());
            Intent intent = new Intent(this, SchoolActivity_.class);
            intent.putExtra(SCHOOL_EXTRA, school);
            startActivity(intent);
        });

        getProfile();
    }

    @Background
    void getProfile() {
        final SwAAProfileResponse profile = communications.getProfile();
        Log.i(TAG, profile.toString());
        updateWelcomeMessage(profile);
        final Collection<GroupDTO> groups = communications.getGroups();
        final Map<GroupDTO, Set<GroupDTO>> mainGroups = new HashMap<>();
        for (final GroupDTO group : groups) {
            int depth = group.getPath().split("\\.").length;
            if (depth == 4) {
                mainGroups.put(group, new HashSet<>());
            }
        }
        for (final GroupDTO room : groups) {
            int depth = room.getPath().split("\\.").length;
            if (depth > 4) {
                for (final GroupDTO mainGroup : mainGroups.keySet()) {
                    if (room.getPath().contains(mainGroup.getPath())) {
                        mainGroups.get(mainGroup).add(room);
                    }
                }
            }
        }

        for (final GroupDTO groupDTO : mainGroups.keySet()) {
            Log.i(TAG, "School:\t" + groupDTO.getName());
            Log.i(TAG, "\tRooms:\t");
            for (final GroupDTO room : mainGroups.get(groupDTO)) {
                Log.i(TAG, "\t\t-" + room.getName() + "\t");
            }
        }
        for (final GroupDTO groupDTO : mainGroups.keySet()) {
            schoolModelList.add(new SchoolModel(groupDTO.getName(), groupDTO.getUuid(), groupDTO.getPath()));
        }
        updateSchoolList(schoolModelList);
    }

    @UiThread
    void updateSchoolList(List<SchoolModel> schoolModelList) {
        adapter.addAll(schoolModelList);
        adapter.notifyDataSetChanged();
        Log.i(TAG, "items:" + schoolList.getAdapter().getCount());
    }

    @UiThread
    void updateWelcomeMessage(SwAAProfileResponse profile) {
        textViewMain.setText(getString(R.string.main_greeting, profile.getFirstname()));
    }
}
