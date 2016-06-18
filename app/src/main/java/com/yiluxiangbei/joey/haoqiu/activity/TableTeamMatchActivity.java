package com.yiluxiangbei.joey.haoqiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.yiluxiangbei.joey.haoqiu.R;
import com.yiluxiangbei.joey.haoqiu.adapter.TeamAdapter;
import com.yiluxiangbei.joey.haoqiu.entity.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TableTeamMatchActivity extends AppCompatActivity {

    private ListView listView;
    private TeamAdapter teamAdapter;
    private Toolbar toolbar;
    private SwipyRefreshLayout swipyRefreshLayout;
    private int progressColor=0xffec407a;

    public static final String APPKEY="656615aa9b037d931f9f9addca3809e0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_team_match);
        toolbar= (Toolbar) findViewById(R.id.toolbar_team_match);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView= (ListView) findViewById(R.id.listview_match);
        swipyRefreshLayout= (SwipyRefreshLayout) findViewById(R.id.team_match_swipyrefreshlayout);
        swipyRefreshLayout.setColorSchemeColors(progressColor);

        Intent intent=getIntent();
        final String clubName=intent.getStringExtra("club");


        SwipyRefreshLayout.OnRefreshListener listener=new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                initTableTeamData(clubName);
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


    }

    public void initTableTeamData(String clubName){
        AsyncHttpClient client=new AsyncHttpClient();
        String url="http://op.juhe.cn/onebox/football/team?key="+APPKEY+"&team="+clubName;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] resp) {
                String respText;
                try {
                    respText = new String(resp, "utf-8");
                    Log.i("info", "resp" + respText);
                    getTeamMatchList(parseTeam(respText));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    Log.i("info", "JSON异常");
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(TableTeamMatchActivity.this, "网络加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Team>parseTeam(String json) throws JSONException {
        List<Team> teams=new ArrayList<Team>();
        JSONObject obj=new JSONObject(json).getJSONObject("result");
        JSONArray ary=obj.getJSONArray("list");
        for (int i=0;i<ary.length();i++){
            JSONObject obj1=ary.getJSONObject(i);
            Team t=new Team();
            t.setCup(obj1.getString("c1"));
            t.setDate(obj1.getString("c2"));
            t.setTime(obj1.getString("c3"));
            t.setResult(obj1.getString("c4R"));
            t.setTeam1(obj1.getString("c4T1"));
            t.setTeam2(obj1.getString("c4T2"));
            t.setTeam1Info(obj1.getString("c4T1URL"));
            t.setTeam2Info(obj1.getString("c4T2URL"));
            t.setReport(obj1.getString("c53"));
            t.setReportLink(obj1.getString("c53Link"));
            t.setVideo(obj1.getString("c52"));
            t.setVideoLink(obj1.getString("c52Link"));
            teams.add(t);
        }
        return teams;
    }


    public void getTeamMatchList(final List<Team>teams){

        teamAdapter=new TeamAdapter(this,teams);
        listView.setAdapter(teamAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Team t = teams.get(position);
                String cup = t.getCup();
                String date = t.getDate();
                String time = t.getTime();
                String result = t.getResult();
                String team1 = t.getTeam1();
                String team2 = t.getTeam2();
                String team1Info = t.getTeam1Info();
                String team2Info = t.getTeam2Info();
                String report = t.getReport();
                String reportLink = t.getReportLink();
                String video = t.getVideo();
                String videoLink = t.getVideoLink();
                Intent intent = new Intent(TableTeamMatchActivity.this, TableMatchActivity.class);
                intent.putExtra("cup", cup);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("result", result);
                intent.putExtra("team1", team1);
                intent.putExtra("team2", team2);
                intent.putExtra("team1Info", team1Info);
                intent.putExtra("team2Info", team2Info);
                intent.putExtra("report", report);
                intent.putExtra("reportLink", reportLink);
                intent.putExtra("video", video);
                intent.putExtra("videoLink", videoLink);
                startActivity(intent);

            }
        });
        listView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                swipyRefreshLayout.setRefreshing(false);
            }
        });
    }
}
