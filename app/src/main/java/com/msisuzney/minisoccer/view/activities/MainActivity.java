package com.msisuzney.minisoccer.view.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.presenter.MainPresenter;
import com.msisuzney.minisoccer.presenter.TwinsPresenter;
import com.msisuzney.minisoccer.view.MainView;
import com.roughike.bottombar.BottomBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView {

    private static final String isFirst = "isFirst";
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    private float exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,MainActivity.class.getName());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("联赛");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        setSupportActionBar(toolbar);
        init();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        getPresenter().init();

    }

    private void init() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.main_menu_1:
                        intent = new Intent(MainActivity.this, SpecialNewsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.main_menu_2:
                        intent = new Intent(MainActivity.this, TwinsActivity.class);
                        intent.putExtra(TwinsPresenter.KIND, TwinsPresenter.TWINS_KIND);
                        startActivity(intent);
                        break;
                    case R.id.main_menu_3:
                        intent = new Intent(MainActivity.this, TwinsActivity.class);
                        intent.putExtra(TwinsPresenter.KIND, TwinsPresenter.GIRL_KIND);
                        startActivity(intent);
                        break;
                    case R.id.main_menu_4:
                        intent = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        SharedPreferences sp = getSharedPreferences(TAG, MODE_PRIVATE);
        if (sp.getBoolean(isFirst, true)) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(isFirst, false);
            editor.apply();
            showDialog();
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("声明");
        builder.setMessage("本应用只用于学习目的，数据全部来自懂球帝App\n获取足球资讯请下载懂不懂球都要用的懂球帝App");
        builder.setPositiveButton("下载懂球帝App", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://android.myapp.com/myapp/detail.htm?apkName=com.dongqiudi.news");
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("我知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_search) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        return true;

    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            exit();
        }
    }

    private void exit() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }


    @Override
    public ViewPager getViewPager() {
        return viewPager;

    }

    @Override
    public FragmentManager getFragmentManagerInMvpView() {
        return getSupportFragmentManager();
    }

    @Override
    public BottomBar getBottomBar() {
        return bottomBar;
    }

    @Override
    public TabLayout getTabLayout() {
        return tabLayout;
    }

    @Override
    public String[] getTabsName() {
        return getResources().getStringArray(R.array.tab);
    }

    @Override
    public int getBottomBarPosition(int resId) {
        switch (resId) {
            case R.id.CSLBottomBar:
                return 0;
            case R.id.LFPBottomBar:
                return 1;
            case R.id.PLBottomBar:
                return 2;
            case R.id.SABottomBar:
                return 3;
            case R.id.GLBottomBar:
                return 4;
        }
        return -1;
    }
}
