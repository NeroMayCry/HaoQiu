package com.yiluxiangbei.joey.haoqiu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yiluxiangbei.joey.haoqiu.R;
import com.yiluxiangbei.joey.haoqiu.entity.GameSchedule;
import com.yiluxiangbei.joey.haoqiu.entity.Header;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by admin on 2016/2/23.
 */
public class StickyListLeagueAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<GameSchedule>gameSchedules;
    private List<Header> headers;

    public int endedColor=0xffe51c23;
    public int competingColor=0xff03a9f4;
    public int unStartedColor=0xffffc107;

    public StickyListLeagueAdapter(Context context, List<GameSchedule> gameSchedules, List<Header> headers) {
        this.context = context;
        this.gameSchedules = gameSchedules;
        this.headers = headers;
        if (context==null){
            Log.i("context","为空");
        }
        inflater=LayoutInflater.from(context);
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {

        HeaderViewHolder hvh;
        if (convertView==null){
            hvh=new HeaderViewHolder();
            convertView=inflater.inflate(R.layout.item_header,parent,false);
            hvh.tvHeader= (TextView) convertView.findViewById(R.id.tvHeader);
            convertView.setTag(hvh);
        }
        hvh= (HeaderViewHolder) convertView.getTag();
        Header header=headers.get(0);
        GameSchedule gameSchedule=gameSchedules.get(position);
        if(gameSchedule.getId()==1) {
            hvh.tvHeader.setText(header.getSaiCheng1());
        }if (gameSchedule.getId()==2) {
            hvh.tvHeader.setText(header.getSaiCheng2());
        }
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        long i=0;
        GameSchedule gameSchedule=gameSchedules.get(position);
        if (gameSchedule.getId()==1){
            i=0;
        }if (gameSchedule.getId()==2){
            i=10;
        }
        return i;
    }

    @Override
    public int getCount() {
        return gameSchedules.size();
    }

    @Override
    public Object getItem(int position) {
        return gameSchedules.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder=new ViewHolder();
            convertView = inflater.inflate(R.layout.item_schedule, parent, false);
            //viewHolder.tvStatus = (TextView) convertView.findViewById(R.id.isEnded);
            viewHolder.tvStatus= (TextView) convertView.findViewById(R.id.isEnded);
            viewHolder.tvTeam1 = (TextView) convertView.findViewById(R.id.team1League);
            viewHolder.tvResult = (TextView) convertView.findViewById(R.id.resultLeague);
            viewHolder.tvTeam2 = (TextView) convertView.findViewById(R.id.team2League);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        GameSchedule gameSchedule = gameSchedules.get(position);
        viewHolder.tvStatus.setText(gameSchedule.getStatus());
        Log.i("gamestatus", gameSchedule.getStatus());
        /*List<String> tags=new ArrayList<>();
        tags.add(gameSchedule.getStatus());
        viewHolder.tvStatus.setTags(tags);*/
        viewHolder.tvStatus.setText(gameSchedule.getStatus());
        if(gameSchedule.getStatus().equals("已结束")){
            viewHolder.tvStatus.setBackgroundColor(endedColor);
        }else if (gameSchedule.getStatus().equals("进行中")){
            viewHolder.tvStatus.setBackgroundColor(competingColor);
        }else {
            viewHolder.tvStatus.setBackgroundColor(unStartedColor);
        }

        viewHolder.tvTeam1.setText(gameSchedule.getTeam1());
        viewHolder.tvResult.setText(gameSchedule.getResult());
        viewHolder.tvTeam2.setText(gameSchedule.getTeam2());

        return convertView;
    }

    class ViewHolder{

        TextView tvStatus;
        //TagContainerLayout tvStatus;
        TextView tvTeam1;
        TextView tvResult;
        TextView tvTeam2;

    }

    class HeaderViewHolder{
        TextView tvHeader;
    }

}
