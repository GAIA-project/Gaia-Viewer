package viewer.android.gaiaproject.eu.gaiaviewer;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import net.sparkworks.cargo.common.dto.GroupDTO;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import viewer.android.gaiaproject.eu.gaiaviewer.aa.SwAAProfileResponse;

import java.util.Collection;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Communications communications;
    
    @ViewById
    TextView textViewMain;
    
    @AfterViews
    void init() {
        communications = new Communications();
        getProfile();
    }
    
    @Background
    void getProfile() {
        SwAAProfileResponse profile = communications.getProfile();
        Log.i(TAG, profile.toString());
        updateWelcomeMessage(profile);
        Collection<GroupDTO> groups = communications.getGroups();
        for (GroupDTO group : groups) {
            Log.i(TAG, group.toString());
        }
    }
    
    @UiThread
    void updateWelcomeMessage(SwAAProfileResponse profile) {
        textViewMain.setText(getString(R.string.main_greeting, profile.getFirstname()));
    }
}
