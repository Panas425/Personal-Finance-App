package com.example.anas.assignment1;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import javax.xml.transform.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BarcodeScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {


    private Controller controller = new Controller();
    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private String code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_barcode_scanner );
        scan();
    }

    public void scan(){
        scannerView = new ZXingScannerView( getApplicationContext() );
        setContentView( scannerView );
        scannerView.setResultHandler( this );
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(com.google.zxing.Result result) {
        Toast.makeText( getApplicationContext(),result.getText(),Toast.LENGTH_SHORT ).show();
        code = result.getText();
        controller.saveBarcode(this.getApplicationContext(),code );
        if(result.getText()!=null){
            scannerView.stopCamera();
            Intent intent = new Intent( this,MainTabberActivity.class );
            startActivity( intent );
        }
        scannerView.resumeCameraPreview( this );
    }
}
