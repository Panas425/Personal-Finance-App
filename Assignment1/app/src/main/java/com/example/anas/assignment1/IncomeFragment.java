package com.example.anas.assignment1;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class IncomeFragment extends Fragment {
    private Button btnDate;

    private EditText tbTitle;
    private EditText tbAmount;

    private Spinner spCategory;

    private Button btnAdd;
    private Button btnShow;

    private TextView tvDate;

    private Controller controller = new Controller();

    private ArrayList<CategoryItem> categoryItems;
    private CustomAdapter adapter;

    private String category;
    private String selectCategorytxt = "Select category";

    private DatabaseHelperInc dbh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =  inflater.inflate(R.layout.fragment_income, container, false);
        dbh = new DatabaseHelperInc( getActivity() );
        initComponents( view );
        initListners();
        return view;
    }
    public void initComponents(View view){
        btnDate = (Button) view.findViewById(R.id.btnDate1 );
        tbTitle = (EditText) view.findViewById( R.id.tbTitle );
        spCategory = (Spinner) view.findViewById(R.id.spinnerCategory );
        tbAmount = (EditText) view.findViewById( R.id.tbAmount );
        btnAdd = (Button) view.findViewById( R.id.btnAdd );
        btnShow = (Button) view.findViewById( R.id.btnShow );
        tvDate = (TextView) view.findViewById( R.id.tvDate1 );

        initList();
    }

    public void initListners() {
        btnAdd.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        });
        btnDate.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.loadCalendar( tvDate, getActivity() );
            }
        } );
        btnShow.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                showIncome();
            }
        } );
    }
    public void addData(){
        String name = "Name: " + controller.getName( getActivity() );
        String date = tvDate.getText().toString();
        String title = "Title: " + tbTitle.getText().toString();
        String amount = "Amount: " + tbAmount.getText().toString() + "kr";
        String addCategory = "Category: " + category;

        boolean isInserted = dbh.addData(name,date,addCategory,title,amount);

        if (!isInserted){
            Toast.makeText( getActivity(),"error",Toast.LENGTH_LONG ).show();
        }
        else{
            Toast.makeText(getActivity(), "Data saved", Toast.LENGTH_LONG).show();
        }
    }
    public void showIncome(){
        Intent intent = new Intent( getActivity(), ListdataIncActivity.class );
        startActivity( intent );
    }
    public void verify(){
        String name = controller.getName( getActivity() );
        String date = tvDate.getText().toString();
        String title = tbTitle.getText().toString();
        String amount = tbAmount.getText().toString();
        try {
            if (name.matches( "" )||date.matches( "" )||title.matches( "" )||category.matches( selectCategorytxt )||amount.matches( "" )){
                Toast.makeText(getActivity(), "You must fill the boxes", Toast.LENGTH_LONG).show();
            }
            else{
                Double num = Double.parseDouble(amount);
                addData();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "Amount is not a number", Toast.LENGTH_LONG).show();
        }

    }
    private void initList(){
        categoryItems = new ArrayList<>(  );
        categoryItems.add( new CategoryItem( selectCategorytxt, 0 ));
        categoryItems.add( new CategoryItem( "Salary", R.drawable.salary ));
        categoryItems.add( new CategoryItem( "Other", R.drawable.other ));

        adapter = new CustomAdapter( getActivity(),categoryItems );
        spCategory.setAdapter( adapter );

        spCategory.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CategoryItem clickeditem = (CategoryItem)parent.getItemAtPosition( position );
                category = clickeditem.getCategoryName();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );
    }

}
