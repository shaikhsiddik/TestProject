package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.BlueshoreFinancial.clientapp3.R;

import java.util.ArrayList;
import java.util.List;

public class EquityDerivative extends AppCompatActivity implements
        FragmentLT.OnFragmentInteractionListener,
        FragmentCO.OnFragmentInteractionListener,
        FragmentDT.OnFragmentInteractionListener,
        FragmentFO.OnFragmentInteractionListener,
        FragmentST.OnFragmentInteractionListener {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equity_derivative);

        toolbar = (Toolbar) findViewById(R.id.toolbar_ed);
        //setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
           // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager_ed);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs_ed);
        if(tabLayout!=null) {
            tabLayout.setupWithViewPager(viewPager);
        }
    }
    private void setupViewPager(ViewPager viewPager) {
        EquityDerivative.ViewPagerAdapter adapter = new EquityDerivative.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentLT(), "LONG TERM");
        adapter.addFragment(new FragmentST(), "SHORT TERM");
        adapter.addFragment(new FragmentDT(), "INTRADAY");
        adapter.addFragment(new FragmentFO(), "F&O");
        adapter.addFragment(new FragmentCO(), "COMMODITY");
        if(viewPager!=null) {
            viewPager.setAdapter(adapter);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
