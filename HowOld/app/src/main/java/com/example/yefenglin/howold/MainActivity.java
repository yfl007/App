package com.example.yefenglin.howold;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final int REQ_CODE = 0x110 ;
    private Button mSelectButton;
    private Button mDetectButton;
    private ImageView mPhoto;
    private View mWaintting;
    private TextView mTips;
    private String mimageStr;
    private String mCurrentPhotoPath;
    private Bitmap mPhotoBitMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initViews();
        initEvents();
    }

    private void initEvents() {
        mSelectButton.setOnClickListener(this);
        mDetectButton.setOnClickListener(this);

    }

    private void initViews() {
        mPhoto = (ImageView) findViewById(R.id.image_id);
        mTips = (TextView)findViewById(R.id.tips_id);
        mSelectButton = (Button) findViewById(R.id.get_image_id);
        mDetectButton = (Button) findViewById(R.id.detect_id);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.get_image_id:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQ_CODE);
                break;
            case R.id.detect_id:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(REQ_CODE==requestCode && resultCode==RESULT_OK && data != null){
            ContentResolver cr = this.getContentResolver();
            Uri uri = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor  =cr.query(uri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(filePathColumn[0]);
            mCurrentPhotoPath = cursor.getString(idx);
            cursor.close();
            resizePhoto();
            mPhoto.setImageBitmap(mPhotoBitMap);
        }
    }

    private void resizePhoto() {
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath,op);
        double ratio = Math.max(op.outWidth*1.0d/1024f,op.outHeight*1.0d/1024f);
        op.inSampleSize = (int)Math.ceil(ratio);
        mPhotoBitMap = BitmapFactory.decodeFile(mCurrentPhotoPath,op);
    }
}
