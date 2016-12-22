package com.example.naits.railly;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by naits on 19/12/2016.
 */

public class RouteListAdapter extends BaseAdapter {

    private Context routeContext;
    private List<Route> routeList;

    public RouteListAdapter(Context context, List<Route> routeList){
        this.routeContext = context;
        this.routeList = routeList;
    }

    @Override
    public int getCount() {
        return routeList.size();
    }

    @Override
    public Object getItem(int position) {
        return routeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(routeContext, R.layout.item_route_list, null);
        TextView departureTextView = (TextView)v.findViewById(R.id.route_item_departure);
        TextView arrivalTextView = (TextView)v.findViewById(R.id.route_item_arrival);
        departureTextView.setText(routeList.get(position).getDeparture());
        arrivalTextView.setText(routeList.get(position).getArrival());
        return v;
    }
}
