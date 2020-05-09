package com.example.anas.assignment1;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.widget.ListView;

import java.util.ArrayList;

public class ListdataIncLayout extends ListView {
    private DatabaseHelperInc dbh = new DatabaseHelperInc( this.getContext() );
    private int category;

    private Controller controller = new Controller();

    public ListdataIncLayout(Context context) {
        super( context );
        ShowIcome();
    }

    //This example uses this method since being built from XML
    public ListdataIncLayout(Context context, AttributeSet attrs) {
        super( context, attrs );
        ShowIcome();
    }

    //Build from XML layout
    public ListdataIncLayout(Context context, AttributeSet attrs, int defStyle) {
        super( context, attrs, defStyle );
        ShowIcome();
    }

    public void ShowIcome() {
        Cursor res = dbh.getIncome();
        ArrayList<CategoryItem> categoryItems = new ArrayList<>();
        while (res.moveToNext()) {
            if (res.getString( 4 ).equals( "Category: Salary") ) {
                category = R.drawable.salary;
            }
            else if (res.getString( 4 ).equals( "Category: Other" )) {
                category = R.drawable.other;
            }
            if (res.getString( 1 ).equals( "Name: " + controller.getName( getContext() ) )) {
                categoryItems.add( new CategoryItem( res.getString( 2 ), 0 ) );
                categoryItems.add( new CategoryItem( res.getString( 1 ), 0 ) );
                categoryItems.add( new CategoryItem( res.getString( 3 ), 0 ) );
                categoryItems.add( new CategoryItem( res.getString( 4 ), category ) );
                categoryItems.add( new CategoryItem( res.getString( 5 ), 0 ) );
                categoryItems.add( new CategoryItem( "", 0 ) );
            }
        }

        CustomAdapter adapter = new CustomAdapter( getContext(), categoryItems );
        setAdapter( adapter );
    }
}
