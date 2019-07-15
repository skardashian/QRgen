package com.samiyeul.qrgen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity
        implements ZXingScannerView.ResultHandler {


    ZXingScannerView qrCodeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qrCodeScanner = new ZXingScannerView(this);
        setContentView(qrCodeScanner);
    }

    @Override
    public void handleResult(Result result) {
        MainActivity.resultTextView.setText(result.getText());

        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        qrCodeScanner.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        qrCodeScanner.setResultHandler(this);
        qrCodeScanner.startCamera();
    }
}
