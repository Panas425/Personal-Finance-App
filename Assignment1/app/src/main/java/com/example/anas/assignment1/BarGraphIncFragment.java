package com.example.anas.assignment1;

import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;


public class BarGraphIncFragment extends Fragment {
    ArrayList<BarEntry> entries;
    ArrayList<String> labels;
    private DatabaseHelperInc dbh;
    private BarChart bar;
    private BarDataSet barDataSet;
    Controller controller = new Controller();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_bar_graph_inc, container, false );
        dbh = new DatabaseHelperInc( this.getActivity() );
        initBarChart( view );
        addValuestoBarn();
        addCategoryLabels();
        return view;
    }

    public void initBarChart(View view) {
        bar = (BarChart) view.findViewById( R.id.bargraphinc );
    }

    public void addValuestoBarn() {
        entries = new ArrayList<>();
        entries.add( new BarEntry( (float) getAmountSalary(), 0 ) );
        entries.add( new BarEntry( (float) getAmountOther(), 1 ) );
        barDataSet = new BarDataSet( entries, "Category" );
    }

    public void addCategoryLabels() {
        labels = new ArrayList<>();
        labels.add( "Salary" );
        labels.add( "Other" );

        BarData data = new BarData( labels, barDataSet );
        bar.setData( data );
        bar.setDescription( "INCOME" );
        bar.animateY( 5000 );

    }

    public double getAmountSalary() {
        Cursor res = dbh.getIncome();
        double amount = 0;
        while (res.moveToNext()) {
            if (res.getString( 1 ).equals( "Name: " + controller.getName( getActivity() ) )
                    && res.getString( 4 ).equals( "Category: Salary" ) && res.getString( 4 )!=null) {
                String str = res.getString( 5 );
                str = str.substring( 8 );
                str = str.substring( 0, str.length() - 2 );
                double amountSalary = Double.parseDouble( str );
                amount = amountSalary + amount;
            }
        }
        return amount;
    }

    public double getAmountOther() {
        Cursor res = dbh.getIncome();
        double amount = 0;
        while (res.moveToNext()) {
            if (res.getString( 1 ).equals( "Name: " + controller.getName( getActivity() ) )
                    && res.getString( 4 ).equals( "Category: Other" ) && res.getString( 4 )!=null) {
                String str = res.getString( 5 );
                str = str.substring( 8 );
                str = str.substring( 0, str.length() - 2 );
                double amountOther = Double.parseDouble( str );
                amount = amountOther + amount;
            }
        }
        return amount;
    }
}
