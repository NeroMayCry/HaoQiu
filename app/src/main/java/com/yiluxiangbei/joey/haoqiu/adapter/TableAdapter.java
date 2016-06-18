package com.yiluxiangbei.joey.haoqiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yiluxiangbei.joey.haoqiu.R;
import com.yiluxiangbei.joey.haoqiu.entity.Table;

import java.util.List;

/**
 * Created by admin on 2016/2/25.
 */
public class TableAdapter extends BaseAdapter{

    private Context context;
    private List<Table>mList;
    private LayoutInflater inflater;

    public TableAdapter(Context context, List<Table> mList) {
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

            convertView=inflater.inflate(R.layout.item_table,null);
            viewHolder=new ViewHolder();
            viewHolder.tvRank= (TextView) convertView.findViewById(R.id.tvRank);
            viewHolder.tvClub= (TextView) convertView.findViewById(R.id.tvClub);
            viewHolder.tvGames= (TextView) convertView.findViewById(R.id.tvGames);
            viewHolder.tvWin= (TextView) convertView.findViewById(R.id.tvWin);
            viewHolder.tvDraw= (TextView) convertView.findViewById(R.id.tvDraw);
            viewHolder.tvLose= (TextView) convertView.findViewById(R.id.tvLose);
            viewHolder.tvDif= (TextView) convertView.findViewById(R.id.tvDif);
            viewHolder.tvScore= (TextView) convertView.findViewById(R.id.tvScore);
            convertView.setTag(viewHolder);
        }

        viewHolder= (ViewHolder) convertView.getTag();
        Table table=mList.get(position);
        viewHolder.tvRank.setText(table.getRank());
        viewHolder.tvClub.setText(table.getClub());
        viewHolder.tvGames.setText(table.getGames());
        viewHolder.tvWin.setText(table.getWin());
        viewHolder.tvDraw.setText(table.getDraw());
        viewHolder.tvLose.setText(table.getLose());
        viewHolder.tvDif.setText(table.getGoalDifference());
        viewHolder.tvScore.setText(table.getScore());

        return convertView;
    }

    class ViewHolder{

        TextView tvRank;
        TextView tvClub;
        TextView tvGames;
        TextView tvWin;
        TextView tvDraw;
        TextView tvLose;
        TextView tvDif;
        TextView tvScore;

    }

}
