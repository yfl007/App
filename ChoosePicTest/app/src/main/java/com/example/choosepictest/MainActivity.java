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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.util.Log.d;


public class MainActivity extends Activity {
    private static final String TAG = "Nick-MainActivity";
    private static final int TAKE_PICTURE = 1;
    private static final int IMAGE_CROP = 2;
    private Button takePic;
    private Button chooseFromAlbum;
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
        chooseFromAlbum = (Button)findViewById(R.id.choose_from_album);
        chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(Environment.getExternalStorageDirectory(),"output_image.jpg");
                try {
                    if (outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                intent.putExtra("crop", true);
                intent.putExtra("scale", true);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                d(TAG, "MainActivity.onClick.chooseFromAlbum:imagepath=="+imageUri.getPath());
                startActivityForResult(intent,IMAGE_CROP);
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
                d(TAG, "MainActivity.onActivityResult.IMAGE_CROP:resultCode =" + resultCode+",data= "+data);
                Log.d(TAG, "MainActivity.onActivityResult.imageUri==" + imageUri.getPath());
                if (resultCode==RESULT_OK){
                    try {
                        InputStream is = getContentResolver().openInputStream(imageUri);
                        d(TAG, "MainActivity.onActivityResult.is=" + is.toString());
                        final BitmapFactory.Options  options = new BitmapFactory.Options();
                        BufferedInputStream buffer = new BufferedInputStream(is);
                        options.inJustDecodeBounds = true;
                        //options.inSampleSize = 4;
                        buffer.mark(1024*1024);
                        Bitmap bitmap = BitmapFactory.decodeStream(buffer,null,options);
                        buffer.reset();
                        picture.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
            default:
                break;

        }
    }
}
