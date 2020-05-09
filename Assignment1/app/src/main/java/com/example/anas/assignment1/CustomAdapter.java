package com.example.anas.assignment1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<CategoryItem> {

    public CustomAdapter(Context context, ArrayList<CategoryItem> categotyList){
        super(context,0,categotyList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView( position,convertView,parent );
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView( position,convertView,parent );
    }
    private View initView(int position, View converView,ViewGroup parent){
        if(converView==null){
            converView = LayoutInflater.from( getContext() ).inflate( R.layout.list_layout,parent,false );
        }

        ImageView imageViewCategory = converView.findViewById( R.id.ivCategory );
        TextView textViewCategory = converView.findViewById( R.id.tvCategory );

        CategoryItem item = getItem( position );
        if(item!=null) {
            imageViewCategory.setImageResource( item.getCategoryImage() );
            textViewCategory.setText( item.getCategoryName() );
        }
        return converView;
    }
}
