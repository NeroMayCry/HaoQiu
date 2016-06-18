package com.yiluxiangbei.joey.haoqiu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by admin on 2016/2/23.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment>list;
    ArrayList<String>tabTitles;

    public MyViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> list,ArrayList<String>tabTitles) {
        super(fm);
        this.list=list;
        this.tabTitles=tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
