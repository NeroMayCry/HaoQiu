package com.yiluxiangbei.joey.haoqiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yiluxiangbei.joey.haoqiu.R;


public class TableMatchActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvDate;
    private TextView tvTime;
    private TextView tvResult;
    private TextView tvTeam1;
    private TextView tvTeam2;
    private TextView tvReport;
    private TextView tvVideo;
    private String date;
    private String time;
    private String result;
    private String team1;
    private String team2;
    private String team1Info;
    private String team2Info;
    private String report;
    private String reportLink;
    private String video;
    private String videoLink;
    private ImageView imgTeam1;
    private ImageView imgTeam2;
    private CardView videoCard;
    private CardView reportCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_match);
        toolbar = (Toolbar) findViewById(R.id.toolbar_table_match);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setViews();

        Intent intent=getIntent();
        date=intent.getStringExtra("date");
        time=intent.getStringExtra("time");
        result=intent.getStringExtra("result");
        team1=intent.getStringExtra("team1");
        team2=intent.getStringExtra("team2");
        team1Info=intent.getStringExtra("team1Info");
        team2Info=intent.getStringExtra("team2Info");
        report=intent.getStringExtra("report");
        reportLink=intent.getStringExtra("reportLink");
        video=intent.getStringExtra("video");
        videoLink=intent.getStringExtra("videoLink");
        tvDate.setText(date);
        tvTime.setText(time);
        tvResult.setText(result);
        tvTeam1.setText(team1);
        tvTeam2.setText(team2);
        tvReport.setText(report);
        tvVideo.setText(video);

        int resId1=setTeamImageResId(team1);
        int resId2=setTeamImageResId(team2);
        Picasso.with(this).load(resId1).into(imgTeam1);
        Picasso.with(this).load(resId2).into(imgTeam2);

        setListeners();

    }

    private void setListeners(){
        tvTeam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TableMatchActivity.this,LoadWebActivity.class);
                intent.putExtra("url", team1Info);
                startActivity(intent);
            }
        });

        tvTeam2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TableMatchActivity.this,LoadWebActivity.class);
                intent.putExtra("url", team2Info);
                startActivity(intent);
            }
        });

        reportCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TableMatchActivity.this, LoadWebActivity.class);
                intent.putExtra("url", reportLink);
                startActivity(intent);
            }
        });

        videoCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TableMatchActivity.this, LoadWebActivity.class);
                intent.putExtra("url", videoLink);
                startActivity(intent);
            }
        });

    }

    private void setViews() {

        tvDate=(TextView) findViewById(R.id.date);
        tvTime=(TextView) findViewById(R.id.time);
        tvResult=(TextView) findViewById(R.id.result);
        tvTeam1=(TextView) findViewById(R.id.team1);
        tvTeam2=(TextView) findViewById(R.id.team2);
        tvReport=(TextView) findViewById(R.id.report);
        tvVideo=(TextView) findViewById(R.id.video);
        imgTeam1= (ImageView) findViewById(R.id.img_team1);
        imgTeam2= (ImageView) findViewById(R.id.img_team2);
        videoCard= (CardView) findViewById(R.id.video_card);
        reportCard= (CardView) findViewById(R.id.report_card);

    }

    public int setTeamImageResId(String team){
        int resId=0;
        switch (team){
            case "莱斯特":
                resId=R.mipmap.leicester_city;
                break;
            case "热刺":
                resId=R.mipmap.hotspurs;
                break;
            case "阿森纳":
                resId=R.mipmap.arsenal;
                break;
            case "曼城":
                resId=R.mipmap.man_city;
                break;
            case "西汉姆联":
                resId=R.mipmap.west_ham;
                break;
            case "曼联":
                resId=R.mipmap.man_utd;
                break;
            case "斯托克城":
                resId=R.mipmap.stoke_city;
                break;
            case "南安普敦":
                resId=R.mipmap.southamptons;
                break;
            case "利物浦":
                resId=R.mipmap.liverpool;
                break;
            case "切尔西":
                resId=R.mipmap.chelsea;
                break;
            case "埃弗顿":
                resId=R.mipmap.everton;
                break;
            case "沃特福德":
                resId=R.mipmap.watford;
                break;
            case "西布朗":
                resId=R.mipmap.wba;
                break;
            case "伯恩茅斯":
                resId=R.mipmap.bournemouth;
                break;
            case "水晶宫":
                resId=R.mipmap.crystal_palace;
                break;
            case "斯旺西":
                resId=R.mipmap.swansea;
                break;
            case "桑德兰":
                resId=R.mipmap.sunderland;
                break;
            case "诺维奇":
                resId=R.mipmap.norwich;
                break;
            case "纽卡斯尔":
                resId=R.mipmap.newcastle_utd;
                break;
            case "阿斯顿维拉":
                resId=R.mipmap.aston_villa;
                break;
            case "拜仁慕尼黑":
                resId=R.mipmap.fc_bayern;
                break;
            case "多特蒙德":
                resId=R.mipmap.bvb;
                break;
            case "柏林赫塔":
                resId=R.mipmap.berlin_hertha;
                break;
            case "沙尔克04":
                resId=R.mipmap.shalk04;
                break;
            case "美因茨":
                resId=R.mipmap.mainz;
                break;
            case "门兴":
                resId=R.mipmap.borussia;
                break;
            case "沃尔夫斯堡":
                resId=R.mipmap.wolfsburg;
                break;
            case "勒沃库森":
                resId=R.mipmap.leverkusen;
                break;
            case "因戈尔施塔特":
                resId=R.mipmap.ingolstadt;
                break;
            case "汉堡":
                resId=R.mipmap.hamburg;
                break;
            case "斯图加特":
                resId=R.mipmap.stuttgart;
                break;
            case "科隆":
                resId=R.mipmap.koln;
                break;
            case "不来梅":
                resId=R.mipmap.werder_bremen;
                break;
            case "奥格斯堡":
                resId=R.mipmap.augsburg;
                break;
            case "达姆施塔特":
                resId=R.mipmap.darmstadt;
                break;
            case "法兰克福":
                resId=R.mipmap.frankfurt;
                break;
            case "霍芬海姆":
                resId=R.mipmap.hoffenheim;
                break;
            case "汉诺威96":
                resId=R.mipmap.hannover;
                break;
            case "巴塞罗那":
                resId=R.mipmap.barcelona;
                break;
            case "马德里竞技":
                resId=R.mipmap.madrid_athletic;
                break;
            case "皇家马德里":
                resId=R.mipmap.real_madrid;
                break;
            case "比利亚雷亚尔":
                resId=R.mipmap.villarreal;
                break;
            case "塞维利亚":
                resId=R.mipmap.sevilla;
                break;
            case "毕尔巴鄂竞技":
                resId=R.mipmap.bilbao;
                break;
            case "维戈塞尔塔":
                resId=R.mipmap.celta;
                break;
            case "埃瓦尔":
                resId=R.mipmap.eibar;
                break;
            case "皇家社会":
                resId=R.mipmap.real_sociedad;
                break;
            case "皇家贝蒂斯":
                resId=R.mipmap.real_betis;
                break;
            case "瓦伦西亚":
                resId=R.mipmap.valencia;
                break;
            case "马拉加":
                resId=R.mipmap.malaga;
                break;
            case "拉科鲁尼亚":
                resId=R.mipmap.la_coruna;
                break;
            case "西班牙人":
                resId=R.mipmap.espanyol;
                break;
            case "拉斯帕尔马斯":
                resId=R.mipmap.las_palmas;
                break;
            case "赫塔菲":
                resId=R.mipmap.getafe;
                break;
            case "巴列卡诺":
                resId=R.mipmap.vallecano;
                break;
            case "格拉纳达":
                resId=R.mipmap.granada;
                break;
            case "希洪竞技":
                resId=R.mipmap.de_gijon;
                break;
            case "莱万特":
                resId=R.mipmap.levante;
                break;
            case "尤文图斯":
                resId=R.mipmap.juvertus;
                break;
            case "那不勒斯":
                resId=R.mipmap.napoli;
                break;
            case "罗马":
                resId=R.mipmap.roma;
                break;
            case "佛罗伦萨":
                resId=R.mipmap.florenza;
                break;
            case "国际米兰":
                resId=R.mipmap.inter;
                break;
            case "AC米兰":
                resId=R.mipmap.ac_milan;
                break;
            case "萨索洛":
                resId=R.mipmap.sassuolo;
                break;
            case "拉齐奥":
                resId=R.mipmap.lazio;
                break;
            case "博洛尼亚":
                resId=R.mipmap.bologna;
                break;
            case "切沃":
                resId=R.mipmap.chievo;
                break;
            case "恩波利":
                resId=R.mipmap.empoli;
                break;
            case "都灵":
                resId=R.mipmap.torino;
                break;
            case "桑普多利亚":
                resId=R.mipmap.sampdoria;
                break;
            case "热那亚":
                resId=R.mipmap.geona;
                break;
            case "亚特兰大":
                resId=R.mipmap.atalanta;
                break;
            case "乌迪内斯":
                resId=R.mipmap.udinese;
                break;
            case "巴勒莫":
                resId=R.mipmap.palermo;
                break;
            case "弗罗西诺内":
                resId=R.mipmap.frosinone;
                break;
            case "卡尔皮":
                resId=R.mipmap.carpi;
                break;
            case "维罗纳":
                resId=R.mipmap.verona;
                break;
            case "巴黎圣日耳曼":
                resId=R.mipmap.psg;
                break;
            case "摩纳哥":
                resId=R.mipmap.monaco;
                break;
            case "里昂":
                resId=R.mipmap.lyonnais;
                break;
            case "尼斯":
                resId=R.mipmap.nice;
                break;
            case "雷恩":
                resId=R.mipmap.rennais;
                break;
            case "卡昂":
                resId=R.mipmap.caen;
                break;
            case "圣埃蒂安":
                resId=R.mipmap.saint_etienne;
                break;
            case "南特":
                resId=R.mipmap.fc_nantes;
                break;
            case "昂热":
                resId=R.mipmap.angers;
                break;
            case "洛里昂":
                resId=R.mipmap.lorient;
                break;
            case "巴斯蒂亚":
                resId=R.mipmap.bastia;
                break;
            case "波尔多":
                resId=R.mipmap.bordeaux;
                break;
            case "马赛":
                resId=R.mipmap.marseille;
                break;
            case "里尔":
                resId=R.mipmap.lille;
                break;
            case "蒙彼利埃":
                resId=R.mipmap.montpellier;
                break;
            case "兰斯":
                resId=R.mipmap.lens;
                break;
            case "甘冈":
                resId=R.mipmap.guingamp;
                break;
            case "阿雅克肖":
                resId=R.mipmap.ajaccio;
                break;
            case "图卢兹":
                resId=R.mipmap.toulouse;
                break;
            case "特鲁瓦":
                resId=R.mipmap.troyes;
                break;

        }
        return resId;
    }
}
