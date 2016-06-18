package com.yiluxiangbei.joey.haoqiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yiluxiangbei.joey.haoqiu.R;
import com.yiluxiangbei.joey.haoqiu.entity.Scorer;

import java.util.List;

/**
 * Created by admin on 2016/2/25.
 */
public class ScorerAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Scorer>mList;


    public ScorerAdapter(Context context, List<Scorer> mList) {
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
            convertView=inflater.inflate(R.layout.item_scorer,null);
            viewHolder=new ViewHolder();
            viewHolder.playerRank= (TextView) convertView.findViewById(R.id.tvRank);
            viewHolder.playerName= (TextView) convertView.findViewById(R.id.tvPlayer);
            viewHolder.playerClub= (TextView) convertView.findViewById(R.id.tvClub);
            viewHolder.playerGoal= (TextView) convertView.findViewById(R.id.tvGoal);
            convertView.setTag(viewHolder);
        }

        viewHolder= (ViewHolder) convertView.getTag();
        Scorer scorer=mList.get(position);
        viewHolder.playerRank.setText(scorer.getPlayerRank());
        viewHolder.playerName.setText(scorer.getPlayerName());
        viewHolder.playerClub.setText(scorer.getPlayerClub());
        viewHolder.playerGoal.setText(scorer.getPlayerGoal());

        return convertView;
    }

    class ViewHolder{
        TextView playerRank;
        TextView playerName;
        TextView playerClub;
        TextView playerGoal;

    }

}
