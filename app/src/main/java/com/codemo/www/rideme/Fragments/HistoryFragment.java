package com.codemo.www.rideme.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.codemo.www.rideme.BookingListAdapter;
import com.codemo.www.rideme.FetchHistory;
import com.codemo.www.rideme.MainActivity;
import com.codemo.www.rideme.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private static MainActivity main;
    private ListView mListView;
    private FetchHistory fetchHistory;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static MainActivity getMain() {
        return main;
    }

    public static void setMain(MainActivity main) {
        HistoryFragment.main = main;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_history, container, false);
        mListView = (ListView) view.findViewById(R.id.bookingList);
        ImageButton refreshBtn = (ImageButton) view.findViewById(R.id.refreshBtn);
        fetchHistoryList();
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchHistoryList();
            }
        });
        return view;
    }

    public void fetchHistoryList(){
        fetchHistory = new FetchHistory(this);
        fetchHistory.execute(getMain().getNic());
    }

    public void updateHistory(ArrayList bookingList){

        BookingListAdapter adapter = new BookingListAdapter(this.getContext(), R.layout.adapter_view_layout, bookingList);
        mListView.setAdapter(adapter);
    }

}
