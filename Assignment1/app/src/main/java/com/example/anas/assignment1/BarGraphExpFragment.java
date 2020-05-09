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


public class BarGraphExpFragment extends Fragment {
    ArrayList<BarEntry> entries;
    ArrayList<String> labels;
    private DatabaseHelperExp dbh;
    private BarChart bar;
    private BarDataSet barDataSet;
    Controller controller = new Controller();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_bar_graph_exp, container, false );
        dbh = new DatabaseHelperExp( this.getActivity() );
        initBarChart( view );
        addValuestoBarn();
        addCategoryLabels();
        return view;
    }

    public void initBarChart(View view) {
        bar = (BarChart) view.findViewById( R.id.bargraph );
    }

    public void addValuestoBarn() {
        entries = new ArrayList<>();
        entries.add( new BarEntry( (float) getAmountFood(), 0 ) );
        entries.add( new BarEntry( (float) getAmountTravel(), 1 ) );
        entries.add( new BarEntry( (float) getAmountLeisure(), 2 ) );
        entries.add( new BarEntry( (float) getAmountAcc(), 3 ) );
        entries.add( new BarEntry( (float) getAmountOther(), 4 ) );
        barDataSet = new BarDataSet( entries, "Category" );
    }

    public void addCategoryLabels() {
        labels = new ArrayList<>();
        labels.add( "Food" );
        labels.add( "Travel" );
        labels.add( "Accommodation" );
        labels.add( "Leisure" );
        labels.add( "Other" );

        BarData data = new BarData( labels, barDataSet );
        bar.setData( data );
        bar.setDescription( "EXPENDITURE" );
        bar.animateY( 5000 );

    }

    public double getAmountFood() {
        Cursor res = dbh.getExpenditure();
        double amount = 0;
        while (res.moveToNext()) {
            if (res.getString( 1 ).equals( "Name: " + controller.getName( getActivity() ) )
                    && res.getString( 4 ).equals( "Category: Food" ) && res.getString( 4 )!=null) {
                String str = res.getString( 5 );
                str = str.substring( 8 );
                str = str.substring( 0, str.length() - 2 );
                double amountFood = Double.parseDouble( str );
                amount = amountFood + amount;
            }
        }
        return amount;
    }

    public double getAmountTravel() {
        Cursor res = dbh.getExpenditure();
        double amount = 0;
        while (res.moveToNext()) {
            if (res.getString( 1 ).equals( "Name: " + controller.getName( getActivity() ) )
                    && res.getString( 4 ).equals( "Category: Travel" ) && res.getString( 4 )!=null) {
                String str = res.getString( 5 );
                str = str.substring( 8 );
                str = str.substring( 0, str.length() - 2 );
                double amountTravel = Double.parseDouble( str );
                amount = amountTravel + amount;
            }
        }
        return amount;

    }

    public double getAmountLeisure() {
        Cursor res = dbh.getExpenditure();
        double amount = 0;
        while (res.moveToNext()) {
            if (res.getString( 1 ).equals( "Name: " + controller.getName( getActivity() ) )
                    && res.getString( 4 ).equals( "Category: Leisure" ) && res.getString( 4 )!=null) {
                String str = res.getString( 5 );
                str = str.substring( 8 );
                str = str.substring( 0, str.length() - 2 );
                double amountLeisure = Double.parseDouble( str );
                amount = amount + amountLeisure;
            }
        }
        return amount;
    }

    public double getAmountOther() {
        Cursor res = dbh.getExpenditure();
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

    public double getAmountAcc() {
        Cursor res = dbh.getExpenditure();
        double amount = 0;
        while (res.moveToNext()) {
            if (res.getString( 1 ).equals( "Name: " + controller.getName( getActivity() ) )
                    && res.getString( 4 ).equals( "Category: Accommodation" ) && res.getString( 4 )!=null) {
                String str = res.getString( 5 );
                str = str.substring( 8 );
                str = str.substring( 0, str.length() - 2 );
                double amountAcc = Double.parseDouble( str );
                amount = amount+ amountAcc;
            }
        }
        return amount;
    }

}
