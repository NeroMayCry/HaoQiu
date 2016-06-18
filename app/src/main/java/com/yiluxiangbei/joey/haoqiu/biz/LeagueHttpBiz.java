package com.yiluxiangbei.joey.haoqiu.biz;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.yiluxiangbei.joey.haoqiu.activity.MainActivity;
import com.yiluxiangbei.joey.haoqiu.entity.GameSchedule;
import com.yiluxiangbei.joey.haoqiu.entity.Scorer;
import com.yiluxiangbei.joey.haoqiu.entity.Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * Created by admin on 2016/2/23.
 */
public class LeagueHttpBiz {

    public static final String APPKEY = "656615aa9b037d931f9f9addca3809e0";
    public Context context;
    public String respText;

    public LeagueHttpBiz(Context context) {
        this.context = context;
    }

    public String  initScheduleData(final String leagueName){
        AsyncHttpClient client=new AsyncHttpClient();
        String url="http://op.juhe.cn/onebox/football/league?key="+APPKEY+"&league="+leagueName;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    respText=new String(responseBody,"utf-8");
                    Log.i("info", "resp:" + respText);
                    MainActivity mainActivity= (MainActivity) context;
                    mainActivity.getGameScheduleList(parseSchedule(respText),parseHeader(respText));
                    mainActivity.getTableList(parseTable(respText));
                    mainActivity.getScorerList(parseScorer(respText));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, "网络加载失败", Toast.LENGTH_SHORT).show();
            }
        });

        return respText;
    }

    public List<GameSchedule> parseSchedule(String json) throws JSONException {
        List<GameSchedule>gameSchedules=new ArrayList<GameSchedule>();
        JSONObject obj=new JSONObject(json).getJSONObject("result").getJSONObject("views");
        JSONArray ary1=obj.getJSONArray("saicheng1");
        for(int i=0;i<ary1.length();i++){
            JSONObject obj2=ary1.getJSONObject(i);
            GameSchedule g=new GameSchedule();
            g.setStatus(obj2.getString("c1"));
            g.setDate(obj2.getString("c2"));
            g.setTime(obj2.getString("c3"));
            g.setResult(obj2.getString("c4R"));
            g.setTeam1(obj2.getString("c4T1"));
            g.setTeam1Info(obj2.getString("c4T1URL"));
            g.setTeam2(obj2.getString("c4T2"));
            g.setTeam2Info(obj2.getString("c4T2URL"));
            g.setVideo(obj2.getString("c51"));
            g.setVideoLink(obj2.getString("c51Link"));
            g.setReport(obj2.getString("c52"));
            g.setReportLink(obj2.getString("c52Link"));
            g.setId(1);
            gameSchedules.add(g);

        }
        JSONArray ary2=obj.getJSONArray("saicheng2");
        for(int i=0;i<ary2.length();i++){
            JSONObject obj3=ary2.getJSONObject(i);
            GameSchedule g=new GameSchedule();
            g.setStatus(obj3.getString("c1"));
            g.setDate(obj3.getString("c2"));
            g.setTime(obj3.getString("c3"));
            g.setResult(obj3.getString("c4R"));
            g.setTeam1(obj3.getString("c4T1"));
            g.setTeam1Info(obj3.getString("c4T1URL"));
            g.setTeam2(obj3.getString("c4T2"));
            g.setTeam2Info(obj3.getString("c4T2URL"));
            g.setVideo(obj3.getString("c51"));
            g.setVideoLink(obj3.getString("c51Link"));
            g.setReport(obj3.getString("c52"));
            g.setReportLink(obj3.getString("c52Link"));
            g.setId(2);
            gameSchedules.add(g);

        }

        return gameSchedules;
    }

    public List<com.yiluxiangbei.joey.haoqiu.entity.Header> parseHeader(String json) throws JSONException {
        List<com.yiluxiangbei.joey.haoqiu.entity.Header> headers=new ArrayList<com.yiluxiangbei.joey.haoqiu.entity.Header>();
        JSONObject obj1=new JSONObject(json).getJSONObject("result").getJSONObject("tabs");
        com.yiluxiangbei.joey.haoqiu.entity.Header h=new com.yiluxiangbei.joey.haoqiu.entity.Header();
        h.setSaiCheng1(obj1.getString("saicheng1"));
        h.setSaiCheng2(obj1.getString("saicheng2"));
        headers.add(h);
        return headers;
    }

    private List<Table> parseTable(String json) throws JSONException {
        List<Table>tables=new ArrayList<Table>();
        JSONObject obj=new JSONObject(json).getJSONObject("result").getJSONObject("views");
        JSONArray ary=obj.getJSONArray("jifenbang");
        for(int i=0;i<ary.length();i++){
            JSONObject obj2=ary.getJSONObject(i);
            Table t=new Table();
            t.setRank(obj2.getString("c1"));
            t.setClub(obj2.getString("c2"));
            t.setClubInfo(obj2.getString("c2L"));
            t.setGames(obj2.getString("c3"));
            t.setWin(obj2.getString("c41"));
            t.setDraw(obj2.getString("c42"));
            t.setLose(obj2.getString("c43"));
            t.setGoalDifference(obj2.getString("c5"));
            t.setScore(obj2.getString("c6"));
            tables.add(t);

        }
        return tables;
    }

    private List<Scorer> parseScorer(String json) throws JSONException {
        List<Scorer>scorers=new ArrayList<Scorer>();
        JSONObject obj=new JSONObject(json).getJSONObject("result").getJSONObject("views");
        JSONArray ary=obj.getJSONArray("sheshoubang");
        for(int i=0;i<ary.length();i++){
            JSONObject obj2=ary.getJSONObject(i);
            Scorer s=new Scorer();
            s.setPlayerRank(obj2.getString("c1"));
            s.setPlayerName(obj2.getString("c2"));
            s.setPlayerClub(obj2.getString("c3"));
            s.setPlayerInfo(obj2.getString("c2L"));
            s.setPlayerGoal(obj2.getString("c4"));
            scorers.add(s);

        }
        return scorers;
    }


}
