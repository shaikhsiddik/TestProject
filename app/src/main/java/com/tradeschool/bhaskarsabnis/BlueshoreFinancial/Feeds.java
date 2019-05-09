package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.BlueshoreFinancial.clientapp3.R;
import com.tradeschool.bhaskarsabnis.BlueshoreFinancial.Tips_Notification;

import java.util.ArrayList;
import java.util.List;

public class Feeds extends AppCompatActivity implements RssTwitterFragment.OnFragmentInteractionListener,RssNewsFragment.OnFragmentInteractionListener{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String pos;
    static Handler mHandler;
    Runnable refresh;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.screenHeightDp < 616)
        {
            setContentView(R.layout.activity_rss_small);
        }
        else if (config.screenHeightDp >= 616 && config.screenHeightDp <=650)
        {
            setContentView(R.layout.activity_rss_medium);
        }
        else
        {
            setContentView(R.layout.activity_rss);
        }


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager_feed);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs_feed);
        if(tabLayout!=null) {
            tabLayout.setupWithViewPager(viewPager);
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RssTwitterFragment(), "Twitter");
        adapter.addFragment(new RssNewsFragment(), "News");
        if(viewPager!=null) {
            viewPager.setAdapter(adapter);
        }
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(1);
        }

    }

    public void logout_notification(View view) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();

        Intent intent = new Intent(Feeds.this, LoginActivity.class);
        finish();
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(Feeds.this, MainActivity.class);
        startActivity(intent);
    }
    public void learn(View view) {
        Intent intent = new Intent(Feeds.this, Learn.class);
        startActivity(intent);
    }
    public void contact(View view) {
        Intent intent = new Intent(Feeds.this, Contact.class);
        startActivity(intent);
    }
    public void notification(View view) {
        Intent intent = new Intent(Feeds.this, Tips_Notification.class);
        startActivity(intent);
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
