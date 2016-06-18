package com.yiluxiangbei.joey.haoqiu.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.heinrichreimersoftware.materialdrawer.DrawerView;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerProfile;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.yiluxiangbei.joey.haoqiu.R;
import com.yiluxiangbei.joey.haoqiu.adapter.ScorerAdapter;
import com.yiluxiangbei.joey.haoqiu.adapter.StickyListLeagueAdapter;
import com.yiluxiangbei.joey.haoqiu.adapter.TableAdapter;
import com.yiluxiangbei.joey.haoqiu.biz.LeagueHttpBiz;
import com.yiluxiangbei.joey.haoqiu.entity.GameSchedule;
import com.yiluxiangbei.joey.haoqiu.entity.Header;
import com.yiluxiangbei.joey.haoqiu.entity.Scorer;
import com.yiluxiangbei.joey.haoqiu.entity.Table;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    public DrawerView drawer;
    private ActionBarDrawerToggle drawerToggle;
    private ViewPager pager;
    public DrawerLayout drawerLayout;
    public SmartTabLayout viewPagerTab;
    private LayoutInflater inflater;
    private int selectedText=0xffffffff;
    private int progressColor=0xffec407a;
    public String leagueName="英超";
    List<GameSchedule>gameSchedules=new ArrayList<GameSchedule>();
    List<Header>headers=new ArrayList<Header>();
    List<Scorer>scorers=new ArrayList<Scorer>();
    List<Table>tables=new ArrayList<Table>();
    LeagueHttpBiz biz;
    StickyListHeadersListView gameScheduleListView;
    ListView tableListView;
    ListView scorerListView;
    private View gameScheduleView;
    private View tableView;
    private View scorerView;
    List<View>viewContainer=new ArrayList<View>();
    List<String>titleContainer=new ArrayList<String>();
    private SwipyRefreshLayout swipyRefreshLayout;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        inflater=LayoutInflater.from(this);

        toolbar.setTitle("首页");
        toolbar.setTitleTextColor(selectedText);
        drawer= (DrawerView) findViewById(R.id.drawer);
        pager= (ViewPager) findViewById(R.id.viewpager);
        viewPagerTab= (SmartTabLayout) findViewById(R.id.viewpagertab);
        swipyRefreshLayout= (SwipyRefreshLayout) findViewById(R.id.main_swipyrefreshlayout);
        swipyRefreshLayout.setColorSchemeColors(progressColor);

        //scheduleListView= (StickyListHeadersListView) findViewById(R.id.schedule_list);
        /*FragmentPagerItems pages=new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("赛程",GameSheduleFragment.class ));
        pages.add(FragmentPagerItem.of("积分榜", TableFragment.class));
        pages.add(FragmentPagerItem.of("射手榜", ScorerFragment.class));
        FragmentManager fm=getSupportFragmentManager();
        FragmentPagerItemAdapter adapter=new FragmentPagerItemAdapter(
                fm,pages);
        pager.setAdapter(adapter);*/

        SwipyRefreshLayout.OnRefreshListener listener=new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                biz=new LeagueHttpBiz(MainActivity.this);
                biz.initScheduleData(leagueName);
            }
        };
        swipyRefreshLayout.setOnRefreshListener(listener);

        swipyRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipyRefreshLayout.setRefreshing(true);
            }
        });
        listener.onRefresh(SwipyRefreshLayoutDirection.TOP);

        Log.i("gameScheduleList",gameSchedules.toString());

        gameScheduleView=inflater.inflate(R.layout.tab_gameschedule,null);
        tableView=inflater.inflate(R.layout.tab_table,null);
        scorerView=inflater.inflate(R.layout.tab_scorer,null);
        viewContainer.add(gameScheduleView);
        viewContainer.add(tableView);
        viewContainer.add(scorerView);
        titleContainer.add("赛程");
        titleContainer.add("积分榜");
        titleContainer.add("射手榜");

        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewContainer.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView(viewContainer.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(viewContainer.get(position));
                return viewContainer.get(position);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleContainer.get(position);
            }
        });


        viewPagerTab.setViewPager(pager);

        InitDrawer();

        setListeners();
    }

    public void setListeners(){

        drawer.setOnItemClickListener(new DrawerItem.OnItemClickListener() {
            @Override
            public void onClick(DrawerItem drawerItem, long l, int i) {
                switch (i) {
                    case 0:
                        leagueName = "英超";
                        drawer.selectItem(0);
                        biz.initScheduleData(leagueName);
                        SwipyRefreshLayout.OnRefreshListener listener0=new SwipyRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                                biz=new LeagueHttpBiz(MainActivity.this);
                                biz.initScheduleData(leagueName);
                            }
                        };
                        swipyRefreshLayout.setOnRefreshListener(listener0);

                        swipyRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                swipyRefreshLayout.setRefreshing(true);
                            }
                        });
                        listener0.onRefresh(SwipyRefreshLayoutDirection.TOP);
                        break;
                    case 1:
                        leagueName = "德甲";
                        drawer.selectItem(1);
                        biz.initScheduleData(leagueName);
                        SwipyRefreshLayout.OnRefreshListener listener1=new SwipyRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                                biz=new LeagueHttpBiz(MainActivity.this);
                                biz.initScheduleData(leagueName);
                            }
                        };
                        swipyRefreshLayout.setOnRefreshListener(listener1);

                        swipyRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                swipyRefreshLayout.setRefreshing(true);
                            }
                        });
                        listener1.onRefresh(SwipyRefreshLayoutDirection.TOP);
                        break;
                    case 2:
                        leagueName = "西甲";
                        drawer.selectItem(2);
                        biz.initScheduleData(leagueName);
                        SwipyRefreshLayout.OnRefreshListener listener2=new SwipyRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                                biz=new LeagueHttpBiz(MainActivity.this);
                                biz.initScheduleData(leagueName);
                            }
                        };
                        swipyRefreshLayout.setOnRefreshListener(listener2);

                        swipyRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                swipyRefreshLayout.setRefreshing(true);
                            }
                        });
                        listener2.onRefresh(SwipyRefreshLayoutDirection.TOP);
                        break;
                    case 3:
                        leagueName = "意甲";
                        drawer.selectItem(3);
                        biz.initScheduleData(leagueName);
                        SwipyRefreshLayout.OnRefreshListener listener3=new SwipyRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                                biz=new LeagueHttpBiz(MainActivity.this);
                                biz.initScheduleData(leagueName);
                            }
                        };
                        swipyRefreshLayout.setOnRefreshListener(listener3);

                        swipyRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                swipyRefreshLayout.setRefreshing(true);
                            }
                        });
                        listener3.onRefresh(SwipyRefreshLayoutDirection.TOP);
                        break;
                    case 4:
                        leagueName = "法甲";
                        drawer.selectItem(4);
                        biz.initScheduleData(leagueName);
                        SwipyRefreshLayout.OnRefreshListener listener4=new SwipyRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                                biz=new LeagueHttpBiz(MainActivity.this);
                                biz.initScheduleData(leagueName);
                            }
                        };
                        swipyRefreshLayout.setOnRefreshListener(listener4);

                        swipyRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                swipyRefreshLayout.setRefreshing(true);
                            }
                        });
                        listener4.onRefresh(SwipyRefreshLayoutDirection.TOP);
                        break;
                    case 5:
                        leagueName = "中超";
                        drawer.selectItem(5);
                        biz.initScheduleData(leagueName);
                        SwipyRefreshLayout.OnRefreshListener listener5=new SwipyRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                                biz=new LeagueHttpBiz(MainActivity.this);
                                biz.initScheduleData(leagueName);
                            }
                        };
                        swipyRefreshLayout.setOnRefreshListener(listener5);

                        swipyRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                swipyRefreshLayout.setRefreshing(true);
                            }
                        });
                        listener5.onRefresh(SwipyRefreshLayoutDirection.TOP);
                        break;
                }
                drawerLayout.closeDrawer(drawer);
            }

        });


        drawer.setOnProfileClickListener(new DrawerProfile.OnProfileClickListener() {
            @Override
            public void onClick(DrawerProfile drawerProfile, long l) {
                Toast.makeText(MainActivity.this, "Clicked profile *" + l, Toast.LENGTH_SHORT).show();
            }
        });
        drawer.setOnProfileSwitchListener(new DrawerProfile.OnProfileSwitchListener() {
            @Override
            public void onSwitch(DrawerProfile drawerProfile, long l, DrawerProfile drawerProfile1, long l1) {
                Toast.makeText(MainActivity.this, "Switched from profile *" + l + " to profile *" + l1, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class ReceiveBroadCast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            leagueName=intent.getStringExtra("leagueName");
        }
    }

    public void InitDrawer(){

        drawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDarker));
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.closeDrawer(drawer);

        drawer.addItem(new DrawerItem()
                .setRoundedImage((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.barclays))
                .setTextPrimary(getString(R.string.premier)));
        //drawer.addDivider();
        drawer.addItem(new DrawerItem()
                .setRoundedImage((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.bundesliga))
                .setTextPrimary(getString(R.string.Bundesliga)));
        //drawer.addDivider();
        drawer.addItem(new DrawerItem()
                .setRoundedImage((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.liga))
                .setTextPrimary(getString(R.string.Laliga)));
        //drawer.addDivider();
        drawer.addItem(new DrawerItem()
                .setRoundedImage((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.seriea))
                .setTextPrimary(getString(R.string.Seriea)));
        //drawer.addDivider();
        drawer.addItem(new DrawerItem()
                .setRoundedImage((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.ligue1))
                .setTextPrimary(getString(R.string.Ligue1)));
        //drawer.addDivider();
        drawer.addItem(new DrawerItem()
                .setRoundedImage((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.csl))
                .setTextPrimary(getString(R.string.CSL)));
        //drawer.addDivider();
        drawer.selectItem(0);

        drawer.addProfile(new DrawerProfile()
                        .setId(1)
                        .setRoundedAvatar((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.cat_1))
                        .setBackground(ContextCompat.getDrawable(this, R.mipmap.chelsea_011_1200_675))
                        .setName("Joey")
                        .setDescription("Fan of Chelsea")
        );

        drawer.addProfile(new DrawerProfile()
                        .setId(2)
                        .setRoundedAvatar((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.cat_2))
                        .setBackground(ContextCompat.getDrawable(this, R.mipmap.cat_wide_2))
                        .setName("Lampard")
                        .setDescription("Legend of Chelsea")
        );

    }






    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode==KeyEvent.KEYCODE_BACK){

            if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(drawer);

            }else {
                final MaterialDialog dialog=new MaterialDialog.Builder(MainActivity.this)
                        .title("提示")
                        .content("确定要退出？")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                                finish();
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {

                            }
                        })
                        .positiveText("确定")
                        .negativeText("取消")
                        .show();
            }

        }
        return false;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                Intent intent=new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.search:
                Intent intent1=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getGameScheduleList(final List<GameSchedule>gameSchedules,List<Header>headers){
        this.gameSchedules=gameSchedules;
        this.headers=headers;
        gameScheduleListView= (StickyListHeadersListView) gameScheduleView.findViewById(R.id.sticky_schedule_list);
        StickyListHeadersAdapter gameScheduleAdapter=new StickyListLeagueAdapter(this,gameSchedules,headers);
        gameScheduleListView.setAdapter(gameScheduleAdapter);
        gameScheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameSchedule g = gameSchedules.get(position);
                String status = g.getStatus();
                String date = g.getDate();
                String time = g.getTime();
                String result = g.getResult();
                String team1 = g.getTeam1();
                String team1Info = g.getTeam1Info();
                String team2 = g.getTeam2();
                String team2Info = g.getTeam2Info();
                String video = g.getVideo();
                String videoLink = g.getVideoLink();
                String report = g.getReport();
                String reportLink = g.getReportLink();
                Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                intent.putExtra("status", status);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("result", result);
                intent.putExtra("team1", team1);
                intent.putExtra("team1Info", team1Info);
                intent.putExtra("team2", team2);
                intent.putExtra("team2Info", team2Info);
                intent.putExtra("video", video);
                intent.putExtra("videoLink", videoLink);
                intent.putExtra("report", report);
                intent.putExtra("reportLink", reportLink);
                startActivity(intent);
            }
        });

        gameScheduleListView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                swipyRefreshLayout.setRefreshing(false);
            }
        });

        gameScheduleListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable=false;
                if (gameScheduleListView!=null&&gameScheduleListView.getChildCount()>0){
                    boolean firstItemVisible=gameScheduleListView.getFirstVisiblePosition()==0;
                    boolean topOfFirstItemVisible = gameScheduleListView.getChildAt(0).getTop() == 0;
                    enable=firstItemVisible&&topOfFirstItemVisible;
                }
                swipyRefreshLayout.setEnabled(enable);
            }
        });

    }

    public void getTableList(final List<Table>tables){
        this.tables=tables;
        tableListView= (ListView) tableView.findViewById(R.id.tab_table_list);
        TableAdapter tableAdapter=new TableAdapter(this,tables);
        tableListView.setAdapter(tableAdapter);
        tableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Table t = tables.get(position);
                String clubName = t.getClub();
                Intent intent = new Intent(MainActivity.this, TableTeamMatchActivity.class);
                intent.putExtra("club", clubName);
                startActivity(intent);
            }
        });

        tableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if (tableListView != null && tableListView.getChildCount() > 0) {
                    boolean firstItemVisible = tableListView.getFirstVisiblePosition() == 0;
                    boolean topOfFirstItemVisible = tableListView.getChildAt(0).getTop() == 0;
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                swipyRefreshLayout.setEnabled(enable);
            }
        });
    }

    public void getScorerList(final List<Scorer>scorers){
        this.scorers=scorers;
        scorerListView= (ListView) scorerView.findViewById(R.id.tab_scorer_list);
        ScorerAdapter scorerAdapter=new ScorerAdapter(this,scorers);
        scorerListView.setAdapter(scorerAdapter);
        scorerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Scorer s = scorers.get(position);
                String playerInfo = s.getPlayerInfo();
                Intent intent = new Intent(MainActivity.this, PlayerInfoActivity.class);
                intent.putExtra("playerInfo", playerInfo);
                startActivity(intent);
            }
        });

        scorerListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if (scorerListView != null && scorerListView.getChildCount() > 0) {
                    boolean firstItemVisible = scorerListView.getFirstVisiblePosition() == 0;
                    boolean topOfFirstItemVisible = scorerListView.getChildAt(0).getTop() == 0;
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                swipyRefreshLayout.setEnabled(enable);
            }
        });
    }





}
