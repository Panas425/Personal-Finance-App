package com.example.anas.assignment1;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.widget.ListView;

import java.text.ParseException;
import java.util.ArrayList;

public class SearchDatesLayout extends ListView{
    private DatabaseHelperInc dbh = new DatabaseHelperInc( this.getContext() );
    private Controller controller = new Controller();
    private ArrayList<CategoryItem> categoryItems;
    private CustomAdapter adapter;
    private int category;

    public SearchDatesLayout(Context context) throws ParseException {
        super(context);
        showIncome();
    }
    //This example uses this method since being built from XML
    public SearchDatesLayout(Context context, AttributeSet attrs) throws ParseException {
        super(context, attrs);
        showIncome();
    }

    //Build from XML layout
    public SearchDatesLayout(Context context, AttributeSet attrs, int defStyle) throws ParseException {
        super(context, attrs, defStyle);
        showIncome();
    }
    private void showIncome() throws ParseException {
        Cursor res = dbh.getIncome();
        categoryItems = new ArrayList<>(  );
        controller = new Controller();
        ArrayList<String> dates = controller.getDaysBetweenDates(controller.getStartdate( getContext() ),controller.getEnddate( getContext() ));
            while(res.moveToNext()){
                if (res.getString( 4 ).equals( "Category: Salary") ) {
                    category = R.drawable.salary;
                }
                else if (res.getString( 4 ).equals( "Category: Other" )) {
                    category = R.drawable.other;
                }
                for (int i = 0; i <dates.size(); i++) {
                    if (res.getString( 1 ).equals( "Name: " + controller.getName( getContext()) ) && res.getString( 2 ).equals( dates.get( i ) )) {
                        categoryItems.add( new CategoryItem( res.getString( 2 ), 0 ) );
                        categoryItems.add( new CategoryItem( res.getString( 1 ), 0 ) );
                        categoryItems.add( new CategoryItem( res.getString( 3 ), 0 ) );
                        categoryItems.add( new CategoryItem( res.getString( 4 ), category ) );
                        categoryItems.add( new CategoryItem( res.getString( 5 ), 0 )  );
                        categoryItems.add( new CategoryItem( "", 0 )  );
                    }
                }


        }
        adapter = new CustomAdapter( getContext(),categoryItems );
        setAdapter( adapter );

    }
}
