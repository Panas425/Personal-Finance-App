package com.example.anas.assignment1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class HomeScreenFragment extends Fragment {

    private TextView tvBalance, tvExpenditure, tvIncome, tvInfo;
    private DatabaseHelperExp dbhExp;
    private DatabaseHelperInc dbhInc;
    private Button btnStart;

    private Controller controller;
    private int i;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_home_screen, container, false );
        dbhExp = new DatabaseHelperExp( getActivity() );
        dbhInc = new DatabaseHelperInc( getActivity() );
        controller = new Controller();
        initComponenets( view );
        initListner();
        return view;
    }
    private void initComponenets(View view) {
        tvBalance = (TextView) view.findViewById( R.id.tvBalance );
        tvExpenditure = (TextView) view.findViewById( R.id.tvExp );
        tvIncome = (TextView) view.findViewById( R.id.tvIncome );
        btnStart = (Button)view.findViewById( R.id.btnStart );
        tvInfo = (TextView)view.findViewById( R.id.tvInfo ) ;

        if(getExpenditure()!=0){

        }

        tvInfo.setText( "Hi, " + controller.getName( getActivity() ) + "!" );
        tvExpenditure.setText( String.format( "Total Expenditure: " + "%.2f kr",getExpenditure() ) );
        tvIncome.setText( String.format( "Total Income: " + "%.2f kr",getIncome() ));
        tvBalance.setText( String.format( "Balance: " + "%.2f kr",getBalance() ) );
    }
    public void initListner(){
        btnStart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getActivity(),MainTabberActivity.class );
                startActivity( intent );
            }
        } );
    }
    public double getExpenditure() {
        Cursor res = dbhExp.getExpenditure();
        double amount = 0;
        while (res.moveToNext()) {
            if (res.getString( 1 ).equals( "Name: " + controller.getName( getActivity() ) )) {
                String str = res.getString( 5 );
                str = str.substring( 8 );
                str = str.substring( 0, str.length() - 2 );
                double amount1 = Double.parseDouble( str );
                amount = amount+amount1;
            }
        }
        return amount;
    }
    public double getIncome() {
        Cursor res = dbhInc.getIncome();
        double amount = 0;
        while (res.moveToNext()) {
            if (res.getString( 1 ).equals( "Name: " + controller.getName( getActivity() ) )) {
                String str = res.getString( 5 );
                str = str.substring( 8 );
                str = str.substring( 0, str.length() - 2 );
                double amount1 = Double.parseDouble( str );
                amount = amount+amount1;
            }
        }
        return amount;
    }
    public double getBalance() {
        return getIncome()-getExpenditure();
    }
}
