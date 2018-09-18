package com.codemo.www.rideme.Fragments;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.codemo.www.rideme.Booker;
import com.codemo.www.rideme.MainActivity;
import com.codemo.www.rideme.R;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static MainActivity main;
    private static final String TAG = "HomeFragment";

//    private RadioButton

    private TextView selectDate;
    private TextView payment;
    private EditText selectTime;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private Button book;
    private RadioGroup typeRadio;
    private RadioGroup packRadio;
    private RadioGroup locRadio;
    private String type = "mountain";
    private String pack = "city";
    private String date = "";
    private String location = "location1";
    private int hours = 5;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        selectDate = (TextView) view.findViewById(R.id.selectDate);
        payment = (TextView) view.findViewById(R.id.payment);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        main,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                setDate(month+"/"+dayOfMonth+"/"+year);
                selectDate.setText(getDate());
                Log.d(TAG, "date is................................"+year+" "+month+1+" "+dayOfMonth);
            }
        };
        Calendar c = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
        selectDate.setText(currentDate);
        setDate(currentDate);
        book = (Button) view.findViewById(R.id.bookBtn);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeBooking();
            }
        });
        typeRadio = (RadioGroup) view.findViewById(R.id.typeRadio);
        typeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton71) {
                    setType("mountain");
                    Log.d(TAG, "change type to mountain");
                }else{
                    setType("normal");
                    Log.d(TAG, "change type to normal");
                }
                payment.setText(calculateFee());
            }
        });
        packRadio = (RadioGroup) view.findViewById(R.id.packRadio);
        packRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton7) {
                    setPack("city");
                    Log.d(TAG, "change type to city");
                }else{
                    setPack("uni");
                    Log.d(TAG, "change type to uni");
                }
                payment.setText(calculateFee());
            }
        });
        locRadio = (RadioGroup) view.findViewById(R.id.locRadio);
        locRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton51) {
                    setLocation("location1");
                    Log.d(TAG, "change type to location1");
                }else{
                    setLocation("location2");
                    Log.d(TAG, "change type to location2");
                }
            }
        });
        selectTime = (EditText) view.findViewById(R.id.selectTime);
        selectTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0)
                    setHours(Integer.valueOf(s.toString()));
                payment.setText(calculateFee());

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if(s.length() == 0)
//                    selectTime.setText("5");
//                payment.setText(calculateFee());
            }
        });
        return view;
    }

    private String calculateFee() {
        int rent = 0;
        if(getType() == "mountain") {
            if(getPack() == "city") {
                rent = 400;
                if(getHours()<=5) {

                }else{
                    rent = rent + (getHours()-5)*100;
                }
            }else{
                rent = 0;
            }
        }else{
            if(getPack() == "city") {
                rent = 300;
                if(getHours()<=5) {

                }else{
                    rent = rent + (getHours()-5)*100;
                }
            }else{
                rent = 80;
            }
        }

        if ( rent == 0){
            return "Not Available";
        }
        return "Rs. "+String.valueOf(rent)+".00";
    }

    private void makeBooking() {
        Booker b = new Booker(getMain());

        b.execute(getMain().getUsername(),
                getMain().getNic(), getType(),getPack(), getLocation(), String.valueOf(getHours())+" hrs", getDate(), calculateFee());
    }

    public static MainActivity getMain() {
        return HomeFragment.main;
    }

    public static void setMain(MainActivity main) {
        HomeFragment.main = main;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
