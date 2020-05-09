package com.example.anas.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_screen );
    }
    @Override
    public void onBackPressed() {
        backButtonHandler();
        return;
    }

    private void backButtonHandler() {
        Intent intent = new Intent( this,MainActivity.class );
        startActivity( intent );
    }
}
