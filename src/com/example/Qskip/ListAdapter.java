package com.example.Qskip;

/**
 * Created with IntelliJ IDEA.
 * User: akash
 * Date: 11/16/13
 * Time: 5:34 AM
 * To change this template use File | Settings | File Templates.
 */


import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

    private ArrayList<Stores> listData;

    private LayoutInflater layoutInflater;

    private Context mContext;

    public ListAdapter(Context context, ArrayList<Stores> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_layout, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.title);
            holder.distView = (TextView) convertView.findViewById(R.id.dist);
            holder.waitimeView = (TextView) convertView.findViewById(R.id.waitime);
            holder.imageView = (ImageView) convertView.findViewById(R.id.thumbImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Stores st = (Stores) listData.get(position);
        holder.nameView.setText(st.getName());
        holder.distView.setText("Distance:"+ String.valueOf(st.getDistance()) + "miles");
        holder.waitimeView.setText("Current wait time:"+ st.getSeq() + "minutes");

        if (holder.imageView != null) {
            new ImageDownloaderTask(holder.imageView).execute(st.getImgUrl());
        }

        return convertView;
    }

    static class ViewHolder {
        TextView nameView;
        TextView distView;
        TextView waitimeView;
        ImageView imageView;
    }
}