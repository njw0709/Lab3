package com.example.jong.savangerhunt_1;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

/**
 * Created by root on 10/22/15.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        Bundle args = new Bundle();
        android.support.v4.app.Fragment fragment;
        switch (position) {

            case 0:
                fragment = new TabbedFragment_clip();
                args.putInt(TabbedFragment_clip.ARG_SECTION_NUMBER, position + 1);
                fragment.setArguments(args);
                return fragment;

            case 1:
                fragment = new TabbedFragment_map();
                args.putInt(TabbedFragment_map.ARG_SECTION_NUMBER, position + 1);
                fragment.setArguments(args);
                return fragment;

        }
        return null;
    }



    public CharSequence getPageTitle(int position,Resources r) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return r.getString(R.string.title_section1).toUpperCase(l);
            case 1:
                return r.getString(R.string.title_section2).toUpperCase(l);
        }
        return null;
    }
}