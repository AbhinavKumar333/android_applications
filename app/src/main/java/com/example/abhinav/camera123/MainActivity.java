package com.example.abhinav.camera123;

import android.content.Intent;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.example.abhinav.camera123.R;

import java.io.File;
import java.io.FileNotFoundException;


public class MainActivity extends AppCompatActivity {

    private Button btnCamera;
    private ImageView capturedImage;
    private Button savebutton;
    public Uri ur1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCamera = (Button) findViewById(R.id.btnCamera);

        capturedImage= (ImageView) findViewById(R.id.capturedImage);

        savebutton= (Button) findViewById(R.id.savebutton);



        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    MediaStore.Images.Media.insertImage(getContentResolver(),ur1.toString(),"","");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void openCamera() {
        File dir=
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

        File output=new File(dir, "CameraContentdDemo.jpeg");


        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
        ur1 = Uri.fromFile(output);
        startActivityForResult(intent, 0);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            capturedImage.setImageBitmap(bp);

        }
    }



}