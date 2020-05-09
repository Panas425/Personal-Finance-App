package com.example.anas.assignment1;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.widget.ListView;

import java.util.ArrayList;

public class ListdataExpLayout extends ListView {
    private DatabaseHelperExp dbh = new DatabaseHelperExp( this.getContext() );

    private Controller controller = new Controller();
    public ListdataExpLayout(Context context)
    {
        super(context);
        showExp();
    }
    //This example uses this method since being built from XML
    public ListdataExpLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        showExp();
    }

    //Build from XML layout
    public ListdataExpLayout(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        showExp();
    }
    private void showExp(){
        Cursor res = dbh.getExpenditure();
        ArrayList<CategoryItem> categoryItems = new ArrayList<>();
        int icon = 0;
        while (res.moveToNext()) {
            if(res.getString( 4 ).equals( "Category: Food" )){
                icon = R.drawable.food;
            }
            else if(res.getString( 4 ).equals( "Category: Travel" )){
                icon = R.drawable.travel;
            }
            else if(res.getString( 4 ).equals( "Category: Accommodation" )){
                icon = R.drawable.accommondation;
            }
            else if(res.getString( 4 ).equals( "Category: Leisure" )){
                icon = R.drawable.leisure;
            }
            else if(res.getString( 4 ).equals( "Category: Other" )){
                icon = R.drawable.other;
            }
            if (res.getString( 1 ).equals( "Name: " + controller.getName( getContext() ))) {
                categoryItems.add( new CategoryItem( res.getString( 2 ), 0 ) );
                categoryItems.add( new CategoryItem( res.getString( 1 ), 0 ) );
                categoryItems.add( new CategoryItem( res.getString( 3 ), 0 ) );
                categoryItems.add( new CategoryItem( res.getString( 4 ), icon ) );
                categoryItems.add( new CategoryItem( res.getString( 5 ), 0 )  );
                categoryItems.add( new CategoryItem( "", 0 )  );
                CustomAdapter adapter = new CustomAdapter( getContext(), categoryItems );
                setAdapter( adapter );
            }
        }
    }
}
