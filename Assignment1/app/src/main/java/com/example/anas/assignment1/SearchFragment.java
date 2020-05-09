package com.example.anas.assignment1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchFragment extends Fragment {
    private Button btnSearch;
    private Button btnStartDate;
    private Button btnEndDate;

    private TextView tvStartDate;
    private TextView tvEndDate;

    private Controller controller = new Controller();

    private static final String SHARED_PREFS = "datesPref";
    public static final String STARTDATE = "startdate";
    public static final String ENDDATE = "enddate";


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_search, container, false );
        initComponents( view );
        initListners();
        return view;
    }

    private void initComponents(View view) {
        btnSearch = (Button) view.findViewById( R.id.btnSearch1 );
        btnStartDate = (Button) view.findViewById( R.id.btnDate1 );
        btnEndDate = (Button) view.findViewById( R.id.btnDate2 );
        tvStartDate = (TextView) view.findViewById( R.id.tvDate1 );
        tvEndDate = (TextView) view.findViewById( R.id.tvDate2 );
    }

    private void initListners() {
        btnStartDate.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.loadCalendar( tvStartDate, getActivity() );
                getStartDate();
            }
        } );
        btnEndDate.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.loadCalendar( tvEndDate, getActivity() );
                getEnddate();
            }
        } );
        btnSearch.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        } );
    }

    public void search() {
        controller.saveDates( getActivity(), STARTDATE, ENDDATE, tvStartDate, tvEndDate );
        Intent intent = new Intent( getActivity(), SearchDatesActivity.class );
        startActivity( intent );
    }

    public void verify() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat( "d-M-yyyy" );
            Date startDate = sdf.parse( getStartDate() );
            Date endDate = sdf.parse( getEnddate() );
            if (getStartDate().matches( "" ) || getEnddate().matches( "" ) || startDate.after( endDate )) {
                Toast.makeText( getActivity(), "You must enter valid dates", Toast.LENGTH_LONG ).show();
            } else {
                search();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String getStartDate() {
        return tvStartDate.getText().toString();
    }

    private String getEnddate() {
        return tvEndDate.getText().toString();
    }
}
