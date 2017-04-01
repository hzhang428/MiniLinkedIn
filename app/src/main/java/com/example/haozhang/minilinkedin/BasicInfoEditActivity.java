package com.example.haozhang.minilinkedin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haozhang.minilinkedin.EditActivity;
import com.example.haozhang.minilinkedin.R;
import com.example.haozhang.minilinkedin.model.BasicInfo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by haozhang on 3/24/17.
 *
 */

public class BasicInfoEditActivity extends EditActivity<BasicInfo> {

    public static final String KEY_BASIC_INFO = "basic_info";

    public static final int REQ_CODE_IMAGE_CAPTURE = 1;

    private String photoPath;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_basic_info_edit;
    }

    @Override
    protected void setupUIForCreate() {

    }

    @Override
    protected void setupUIForEdit(@NonNull BasicInfo data) {
        ((EditText) findViewById(R.id.basic_info_edit_name)).setText(data.name);
        ((EditText) findViewById(R.id.basic_info_edit_email)).setText(data.email);

        findViewById(R.id.basic_info_edit_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
    }

    @Override
    protected void saveAndExit(@Nullable BasicInfo data) {
        if (data == null) {
            data = new BasicInfo();
        }
        data.name = ((EditText) findViewById(R.id.basic_info_edit_name)).getText().toString();
        data.email = ((EditText) findViewById(R.id.basic_info_edit_email)).getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_BASIC_INFO, data);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQ_CODE_IMAGE_CAPTURE);

            //need to save the data

//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(this,
//                                                          "com.haozhang.android.fileprovider",
//                                                          photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, REQ_CODE_IMAGE_CAPTURE);
//            }
        }
    }

    private File createImageFile() throws IOException {
        String id = UUID.randomUUID().toString();
        String imageFileName = "JPEG_" + id + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        photoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            Log.i("image_capture", (imageBitmap == null) + "");
            ((ImageView) findViewById(R.id.basic_info_image)).setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected BasicInfo initializeData() {
        return getIntent().getParcelableExtra(KEY_BASIC_INFO);
    }
}
