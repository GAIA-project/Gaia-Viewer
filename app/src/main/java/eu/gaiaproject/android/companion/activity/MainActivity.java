package eu.gaiaproject.android.companion.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import viewer.android.gaiaproject.eu.gaiaviewer.R;
import eu.gaiaproject.android.companion.aa.SwAAProfileResponse;
import eu.gaiaproject.android.companion.adapter.SchoolListAdapter;
import eu.gaiaproject.android.companion.cargo.dto.GroupDTO;
import eu.gaiaproject.android.companion.model.SchoolModel;
import eu.gaiaproject.android.companion.util.Communications;

import static eu.gaiaproject.android.companion.activity.SchoolActivity.SCHOOL_EXTRA;

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
        adapter.clear();
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
            schoolModelList.add(new SchoolModel(groupDTO.getName(), groupDTO.getUuid(), groupDTO.getPath()));
        }
        for (SchoolModel schoolModel : schoolModelList) {
            Log.i(TAG, "School:\t" + schoolModel.getName());
        }
        updateSchoolList();
    }

    @UiThread
    void updateSchoolList() {
        adapter.notifyDataSetChanged();
    }

    @UiThread
    void updateWelcomeMessage(SwAAProfileResponse profile) {
        textViewMain.setText(getString(R.string.main_greeting, profile.getFirstname()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            if (new Communications().clearToken()) {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
