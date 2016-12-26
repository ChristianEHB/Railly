package com.example.naits.railly.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.naits.railly.R;
import com.example.naits.railly.model.Connection;
import com.example.naits.railly.util.DateUtil;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Ruben on 26/12/2016.
 */

public class ExpandableRouteListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Connection> connections;

    public ExpandableRouteListAdapter(Context context, List<Connection> connections) {
        this.context = context;
        this.connections = connections;
    }

    @Override
    public int getGroupCount() {
        return connections.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //connections[i] = connection -> connection.getVias = vias -> vias.size();
        return connections.get(groupPosition).getVias().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return connections.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return connections.get(groupPosition).getVias().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return connections.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return connections.get(groupPosition).getVias().get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        // ???
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.item_connection_explist, null);
        TextView departureTextView = (TextView) v.findViewById(R.id.route_item_departure);
        TextView arrivalTextView = (TextView) v.findViewById(R.id.route_item_arrival);
        TextView arrivalTimeTextView = (TextView) v.findViewById(R.id.route_item_arrivalTime);
        TextView departureTimeTextView = (TextView) v.findViewById(R.id.route_item_departureTime);
        TextView changesTextView = (TextView) v.findViewById(R.id.route_item_changes_value);
        TextView durationTextView = (TextView) v.findViewById(R.id.route_item_duration_value);
        TextView numberTextView = (TextView) v.findViewById(R.id.route_item_number);


        String departureText, arrivalText, departureTimeText, arrivalTimetext;

        departureText = connections.get(groupPosition).getDeparture().getToStation().getName();
        arrivalText = connections.get(groupPosition).getArrival().getToStation().getName();

        departureTimeText = DateUtil.timeStampToDate(connections.get(groupPosition).getDeparture().getDepartureInfo().getTimeStamp()).substring(11);
        arrivalTimetext = DateUtil.timeStampToDate(connections.get(groupPosition).getArrival().getDepartureInfo().getTimeStamp()).substring(11);

        departureTextView.setText(departureText);
        arrivalTextView.setText(arrivalText);

        departureTimeTextView.setText(departureTimeText);
        arrivalTimeTextView.setText(arrivalTimetext);

        changesTextView.setText(((Integer) connections.get(groupPosition).getVias().size()).toString());
        durationTextView.setText(((Float) connections.get(groupPosition).getDurationInMinutes()).toString());

        Integer tableRow = (Integer) groupPosition +1;
        numberTextView.setText(tableRow.toString());



        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.item_route_list, null);
        TextView departureTextView = (TextView) v.findViewById(R.id.route_item_departure);
        TextView arrivalTextView = (TextView) v.findViewById(R.id.route_item_arrival);
        TextView arrivalTimeTextView = (TextView) v.findViewById(R.id.route_item_arrivalTime);
        TextView departureTimeTextView = (TextView) v.findViewById(R.id.route_item_departureTime);

        String departureText, arrivalText, departureTimeText, arrivalTimetext;

        departureText = connections.get(groupPosition).getVias().get(childPosition).getStation().getName();
        arrivalText = connections.get(groupPosition).getVias().get(childPosition).getDepInfo().toString();

        //departureTimeText = DateUtil.timeStampToDate(connections.get(groupPosition).getVias().get(childPosition).getDepInfo().toString());
        //arrivalTimetext = DateUtil.timeStampToDate(connections.get(groupPosition).getArrival().getDepartureInfo().getTimeStamp());

        departureTextView.setText(departureText);
        arrivalTextView.setText(arrivalText);

        //departureTimeTextView.setText(departureTimeText);
        //arrivalTimeTextView.setText(arrivalTimetext);

        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
