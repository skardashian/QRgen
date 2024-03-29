package com.samiyeul.qrgen;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
//import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screen_height = displayMetrics.heightPixels;
        int screen_width = displayMetrics.widthPixels;

        ImageView qrCode = findViewById(R.id.qr_code);

        MultiFormatWriter MFW = new MultiFormatWriter();
        try {
            // Using journeyapps (working)
//            String text = "woke";
//            BitMatrix BM = MFW.encode(text, BarcodeFormat.QR_CODE, screen_width, screen_width);
//            BarcodeEncoder BE = new BarcodeEncoder();
//            Bitmap bitmap = BE.createBitmap(BM);

            // Using only ZXing
            String text = Uri.encode("woke", "utf-8");
            BitMatrix BM = MFW.encode(text, BarcodeFormat.QR_CODE, screen_width, screen_width);
            Bitmap bitmap = Bitmap.createBitmap(screen_width, screen_width, Bitmap.Config.ARGB_8888);

            for (int i = 0; i < screen_width; i++) {
                for (int j = 0; j < screen_width; j++) {
                    bitmap.setPixel(i, j, BM.get(i, j) ? Color.BLACK : Color.WHITE);
                }
            }

            // keep
            if (bitmap != null) {
                qrCode.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
