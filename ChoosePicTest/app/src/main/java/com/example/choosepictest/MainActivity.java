package com.example.choosepictest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static android.util.Log.d;


public class MainActivity extends Activity {
    private static final String TAG = "Nick-MainActivity";
    private static final int TAKE_PICTURE = 1;
    private static final int IMAGE_CROP = 2;
    private Button takePic;
    private ImageView picture;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        takePic = (Button)findViewById(R.id.take_pic);
        picture = (ImageView)findViewById(R.id.picture);
        takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d(TAG, "MainActivity.onClick."+Environment.getExternalStorageDirectory().getPath());
                File outputImage = new File(Environment.getExternalStorageDirectory(),"tempImage.jpg");
                try {
                    if (outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PICTURE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PICTURE:
                if (resultCode==RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    startActivityForResult(intent, IMAGE_CROP);
                }
                break;
            case IMAGE_CROP:
                Log.d(TAG, "MainActivity.onActivityResult.IMAGE_CROP:resultCode ="+ resultCode);
                if (resultCode==RESULT_OK){
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;

        }
    }
}
