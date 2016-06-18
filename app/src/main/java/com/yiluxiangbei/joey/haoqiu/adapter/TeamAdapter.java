package com.yiluxiangbei.joey.haoqiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yiluxiangbei.joey.haoqiu.R;
import com.yiluxiangbei.joey.haoqiu.entity.Team;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 2016/3/5.
 */
public class TeamAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Team> mList=new ArrayList<Team>();
    private ListView listView;

    public TeamAdapter(Context context, List<Team> mList) {
        this.context = context;
        this.mList = mList;
        this.inflater=LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.item_team_match,null);
            viewHolder=new ViewHolder();
            viewHolder.cup= (TextView) convertView.findViewById(R.id.cup);
            viewHolder.date= (TextView) convertView.findViewById(R.id.date);
            viewHolder.time= (TextView) convertView.findViewById(R.id.time);
            viewHolder.team1= (TextView) convertView.findViewById(R.id.team1);
            viewHolder.result= (TextView) convertView.findViewById(R.id.result);
            viewHolder.team2= (TextView) convertView.findViewById(R.id.team2);
            convertView.setTag(viewHolder);
        }

        viewHolder= (ViewHolder) convertView.getTag();
        Team t=mList.get(position);
        viewHolder.cup.setText(t.getCup());
        viewHolder.date.setText(t.getDate());
        viewHolder.time.setText(t.getTime());
        viewHolder.team1.setText(t.getTeam1());
        viewHolder.team2.setText(t.getTeam2());
        viewHolder.result.setText(t.getResult());

        return convertView;
    }

    class ViewHolder{
        TextView cup;
        TextView date;
        TextView time;
        TextView team1;
        TextView result;
        TextView team2;
    }
}
