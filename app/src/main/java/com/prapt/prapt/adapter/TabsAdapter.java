package com.prapt.prapt.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.prapt.prapt.fragment.TopoffersTab;
import com.prapt.prapt.fragment.AlloffersTab;

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                AlloffersTab all = new AlloffersTab();
                return all;
            case 1:
                TopoffersTab top = new TopoffersTab();
                return top;
            default:
                return null;
        }
    }
}
