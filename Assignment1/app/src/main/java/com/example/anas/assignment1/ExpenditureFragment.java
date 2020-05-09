package com.example.anas.assignment1;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
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


public class ExpenditureFragment extends Fragment {
    private Button btnDate;

    private EditText tbTitle;
    private EditText tbAmount;

    private Spinner spCategory;

    private Button btnAdd;
    private Button btnShow, btnScan;

    private TextView tvDate;

    private DatabaseHelperExp dbh;
    private DatabaseHelperInc dbhInc;

    private Controller controller = new Controller();
    HomeScreenFragment homeScreenFragment;

    private String category;

    private final String CATEGORY = "labels";
    private final String ICON = "icon";
    private int icon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_expenditure, container, false );
        dbh = new DatabaseHelperExp( getActivity() );
        dbhInc = new DatabaseHelperInc( getActivity() );
        homeScreenFragment = new HomeScreenFragment();
        initComponents( view );
        initListners();
        getProduct();
        return view;
    }

    public void initComponents(View view) {
        btnDate = (Button) view.findViewById( R.id.btnDate1 );
        tbTitle = (EditText) view.findViewById( R.id.tbTitle );
        spCategory = (Spinner) view.findViewById( R.id.spinnerCategory );
        tbAmount = (EditText) view.findViewById( R.id.tbAmount );
        btnAdd = (Button) view.findViewById( R.id.btnAdd );
        btnShow = (Button) view.findViewById( R.id.btnShow );
        tvDate = (TextView) view.findViewById( R.id.tvDate1 );
        btnScan = (Button) view.findViewById( R.id.btnScan );
        initList(  );
    }

    public void initListners() {
        btnAdd.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
                controller.getName( getActivity() );
            }
        } );
        btnDate.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.loadCalendar( tvDate, getActivity() );
            }
        } );
        btnShow.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                showExp();
            }
        } );
        btnScan.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getActivity(),BarcodeScannerActivity.class );
                startActivity( intent );
            }
        } );
    }

    public void addData() {
        String name = "Name: " + controller.getName( getActivity() );
        String date = tvDate.getText().toString();
        String title = "Title: " + tbTitle.getText().toString();
        String amount = "Amount: " + tbAmount.getText().toString() + "kr";
        String addCategory = "Category: " + category;

        boolean isInserted = dbh.addData( name, date, addCategory, title, amount );

        if (!isInserted) {
            Toast.makeText( getActivity(), "Data not saved", Toast.LENGTH_LONG ).show();
        } else {
            Toast.makeText( getActivity(), "Data saved", Toast.LENGTH_LONG ).show();
        }
    }

    public void showExp() {
        Intent intent = new Intent( getActivity(), ListdataExpActivity.class );
        startActivity( intent );
    }

    public void verify() {
        String name = controller.getName( getActivity() );
        String date = tvDate.getText().toString();
        String title = tbTitle.getText().toString();
        String amount = tbAmount.getText().toString();
        try {
            if (name.matches( "" ) || date.matches( "" ) || title.matches( "" ) || category.matches( "Select category") || amount.matches( "" )) {
                Toast.makeText( getActivity(), "You must fill the boxes", Toast.LENGTH_LONG ).show();
            } else{
                Double num = Double.parseDouble( amount );
                addData();
            }
        } catch (NumberFormatException e) {
            Toast.makeText( getActivity(), "Amount is not a number", Toast.LENGTH_LONG ).show();
        }

    }
    public void initList(){
        ArrayList<CategoryItem> categoryItems = new ArrayList<>();
        categoryItems.add( new CategoryItem( "Select Category",0));
        categoryItems.add( new CategoryItem( "Food", R.drawable.food ));
        categoryItems.add( new CategoryItem( "Travel", R.drawable.travel ));
        categoryItems.add( new CategoryItem( "Accommodation", R.drawable.accommondation ));
        categoryItems.add( new CategoryItem( "Leisure", R.drawable.leisure ));
        categoryItems.add( new CategoryItem( "Other", R.drawable.other ));

        CustomAdapter adapter = new CustomAdapter( getActivity(), categoryItems );
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
    public void getProduct(){
        String code = controller.getCode( getActivity() );
        if(code.equals( "6591022292916") ){
            tbTitle.setText( "Coca Cola" );
            spCategory.setSelection( 1 );
            tbAmount.setText( "10" );
            controller.saveBarcode( getActivity(),null );
        }
        else if (code.equals( "123ABCabc" )){
            tbTitle.setText( "Fanta" );
            spCategory.setSelection( 1 );
            tbAmount.setText( "13" );
            controller.saveBarcode( getActivity(),null );
        }
        else if(code.equals( "TTS0023" )){
            tbTitle.setText( "Sprite" );
            spCategory.setSelection( 1 );
            tbAmount.setText( "10" );
            controller.saveBarcode( getActivity(),null );
        }
    }

}
