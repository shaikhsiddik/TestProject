package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

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
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;

import com.BlueshoreFinancial.clientapp3.R;

public class Watchlist extends AppCompatActivity implements TwoFragment.OnFragmentInteractionListener{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button addSymbol;
    String pos;
    static Handler mHandler;
    Runnable refresh;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null) {
         //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        if(tabLayout!=null) {
            tabLayout.setupWithViewPager(viewPager);
        }
        addSymbol = findViewById(R.id.addPortfolioButton);
        addSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddSymbolWebViewActivity.class);
                startActivity(intent);
            }
        });

        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable,30000);
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run()
        {
            //Toast.makeText(Watchlist.this,"in runnable",Toast.LENGTH_SHORT).show();
            Watchlist.mHandler.postDelayed(m_Runnable, 30000);
        }

    };//runnable


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //adapter.addFragment(new OneFragment(), "Market Watch");
        adapter.addFragment(new TwoFragment(), "Portfolio Watch");
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
