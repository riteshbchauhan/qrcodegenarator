package com.example.qrcodegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {
    TextInputEditText entertext;
    Button generateQR;
    ImageView showQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entertext = findViewById(R.id.entertext);
        generateQR = findViewById(R.id.generateQR);
        showQR = findViewById(R.id.showQR);

        generateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                String text = entertext.getText().toString();
                if (text==null){
                    Toast.makeText(MainActivity.this, "Please, Enter the Text", Toast.LENGTH_SHORT).show();
                }else if (text.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please, Enter the Text", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        //Write the text
                        BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300);
                        //Encode the text
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        //Create the QR
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        //Shoe the QR
                        showQR.setImageBitmap(bitmap);

                    } catch (WriterException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}