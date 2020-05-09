package com.example.anas.assignment1;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {
    private EditText tbFirstName;
    private EditText tbSurname;

    private Button btnLogin;

    public static final String FIRSTNAME = "firstname";
    public static final String SURNAME = "surname";


    private Controller controller = new Controller();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_login, container, false );
        initComponents( view );
        initListners();
        return view;
    }

    public void initComponents(View view) {
        tbFirstName = (EditText) view.findViewById( R.id.tbFirstName );
        tbSurname = (EditText) view.findViewById( R.id.tbSurname );
        btnLogin = (Button) view.findViewById( R.id.btnLogin );
    }

    public void initListners() {
        btnLogin.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        } );

    }

    public void Dialougue() {
        AlertDialog.Builder alert = new AlertDialog.Builder( getActivity() );
        alert.setMessage( "Welcome " + controller.getName( getActivity() ) );
        alert.setTitle( "Welcome" );
        alert.setPositiveButton( "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        StartTabbed();
                    }
                } );
        alert.setCancelable( true );
        alert.create().show();
    }

    private void StartExpenditure() {
        Intent intent = new Intent( getActivity(), ExpenditureActivity.class );
        startActivity( intent );
    }

    private void StartTabbed() {
        Intent intent = new Intent( getActivity(), HomeScreenActivity.class );
        startActivity( intent );
    }

    public void verify() {
        String txtFirstname = tbFirstName.getText().toString();
        String txtSurname = tbSurname.getText().toString();
        if (txtFirstname.matches( "" ) || txtSurname.matches( "" )) {
            Toast.makeText( getActivity(), "You must enter first name and surname", Toast.LENGTH_LONG ).show();
        } else {
            controller.saveDataLogin( FIRSTNAME, SURNAME, getActivity(), tbFirstName, tbSurname );
            controller.getName( getActivity() );
            Dialougue();
        }
    }
}
