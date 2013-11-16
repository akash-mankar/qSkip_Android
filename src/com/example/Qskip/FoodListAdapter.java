package com.example.Qskip;

/**
 * Created with IntelliJ IDEA.
 * User: akash
 * Date: 11/16/13
 * Time: 5:34 AM
 * To change this template use File | Settings | File Templates.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodListAdapter extends BaseAdapter {

    private ArrayList<Product> listData;

    private LayoutInflater layoutInflater;

    private Context mContext;

    public FoodListAdapter(Context context, ArrayList<Product> listData) {
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
            convertView = layoutInflater.inflate(R.layout.product_row_layout, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.title);
            holder.priceView = (TextView) convertView.findViewById(R.id.price);
            holder.descView = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product st = (Product) listData.get(position);
        holder.nameView.setText(st.getName());
        holder.priceView.setText("Price:$" + st.getPrice());
        holder.descView.setText("Description:"+ st.getDescription());

        return convertView;
    }

    static class ViewHolder {
        TextView nameView;
        TextView priceView;
        TextView descView;
    }
}