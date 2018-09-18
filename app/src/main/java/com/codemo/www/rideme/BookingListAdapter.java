package com.codemo.www.rideme;

/**
 * Created by aminda on 9/18/2018.
 */
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookingListAdapter extends ArrayAdapter<Booking> {

    private static final String TAG = "BookingListAdapter";

    private Context mContext;
    private int mResource;

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public BookingListAdapter(Context context, int resource, ArrayList<Booking> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the booking information
        String date = getItem(position).getDate();
        String pack = getItem(position).getPack();
        String rent = getItem(position).getRent();

        //Create the booking object with the information
        Booking person = new Booking(date,pack,rent);


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView dateTv = (TextView) convertView.findViewById(R.id.dateText);
        TextView packTv = (TextView) convertView.findViewById(R.id.packText);
        TextView rentTv = (TextView) convertView.findViewById(R.id.rentText);

        dateTv.setText(date);
        packTv.setText(pack);
        rentTv.setText(rent);

        return convertView;
    }
}