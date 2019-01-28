package eu.gaiaproject.android.companion.adapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.Collection;

import eu.gaiaproject.android.companion.cargo.dto.ResourceDTO;
import eu.gaiaproject.android.companion.fragment.RoomsFragment;
import eu.gaiaproject.android.companion.fragment.RoomsFragment_;
import eu.gaiaproject.android.companion.fragment.SensorsFragment_;
import eu.gaiaproject.android.companion.model.SchoolModel;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private RoomsFragment tab1;
    private SensorsFragment_ tab2;
    private SensorsFragment_ tab3;

    public MyPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        tab1 = new RoomsFragment_();
        tab2 = new SensorsFragment_();
        Bundle bE = new Bundle();
        bE.putString("title","aggregate");
        tab2.setArguments(bE);
        tab3 = new SensorsFragment_();
        Bundle bEh = new Bundle();
        bEh.putString("title","hardware");
        tab3.setArguments(bEh);

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