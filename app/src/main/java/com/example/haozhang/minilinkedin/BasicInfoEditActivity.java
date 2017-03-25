package com.example.haozhang.minilinkedin;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import com.example.haozhang.minilinkedin.EditActivity;
import com.example.haozhang.minilinkedin.R;
import com.example.haozhang.minilinkedin.model.BasicInfo;

/**
 * Created by haozhang on 3/24/17.
 */

public class BasicInfoEditActivity extends EditActivity<BasicInfo> {

    public static final String KEY_BASIC_INFO = "basic_info";


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

    @Override
    protected BasicInfo initializeData() {
        return getIntent().getParcelableExtra(KEY_BASIC_INFO);
    }
}
