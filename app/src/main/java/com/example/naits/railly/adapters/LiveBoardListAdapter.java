package com.example.naits.railly.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.naits.railly.R;
import com.example.naits.railly.model.Route;

import java.util.List;

/**
 * Created by naits on 22/12/2016.
 */

public class LiveBoardListAdapter extends BaseAdapter{

    private Context context;
    List<Route> routeList;

    public LiveBoardListAdapter(Context context, List<Route> routeList){
        this.context = context;
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
        View v = View.inflate(context, R.layout.item_liveboard_list, null);
        TextView arrivalTime = (TextView) v.findViewById(R.id.liveboard_item_hour);
        TextView destination = (TextView) v.findViewById(R.id.liveboard_item_destination);
        TextView platform = (TextView) v.findViewById(R.id.liveboard_item_platform);
        TextView canceled = (TextView) v.findViewById(R.id.liveboard_item_canceled);
        TextView delay = (TextView) v.findViewById(R.id.liveboard_item_delay);

        arrivalTime.setText(routeList.get(position).getArrivalTime());
        destination.setText(routeList.get(position).getDestination());
        platform.setText("Plat. " + routeList.get(position).getPlatform());

        String isCanceled = routeList.get(position).getCanceled();
        if (!isCanceled.equals("false")) {
            canceled.setTextColor(Color.RED);
            canceled.setText("Canceled");
            delay.setText("");
        }
        else {
            String isOnTime = routeList.get(position).getDelay();
            if (isOnTime.equals("0")) {
                delay.setText("On time");
            } else {
                delay.setTextColor(Color.RED);
                delay.setText(routeList.get(position).getDelay() + " min");
            }
        }
        return v;
    }
}
