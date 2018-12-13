package viewer.android.gaiaproject.eu.gaiaviewer;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.Collection;

import viewer.android.gaiaproject.eu.gaiaviewer.cargo.dto.ResourceDTO;
import viewer.android.gaiaproject.eu.gaiaviewer.fragment.RoomsFragment;
import viewer.android.gaiaproject.eu.gaiaviewer.fragment.RoomsFragment_;
import viewer.android.gaiaproject.eu.gaiaviewer.fragment.SensorsFragment;
import viewer.android.gaiaproject.eu.gaiaviewer.fragment.SensorsFragment_;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private RoomsFragment tab1;
    private SensorsFragment tab2;
    private SensorsFragment tab3;

    public MyPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        tab1 = new RoomsFragment_();
        tab2 = new SensorsFragment_();
        tab3 = new SensorsFragment_();
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return tab1;
            case 1:
                return tab2;
            case 2:
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    public void setAggregateResources(final Collection<ResourceDTO> resources) {
        tab2.updateResources(resources);
    }

    public void setHardwareResources(final Collection<ResourceDTO> resources) {
        tab3.updateResources(resources);
    }

    public void updateResources() {
        tab2.updateResources();
        tab3.updateResources();
    }

    public void setRooms(final Collection<SchoolModel> schoolList) {
        tab1.updateRooms(schoolList);
    }
}