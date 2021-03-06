package com.example.haozhang.minilinkedin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.haozhang.minilinkedin.model.Education;
import com.example.haozhang.minilinkedin.util.DateUtils;

import java.util.Arrays;

/**
 * Created by haozhang on 3/18/17.
 *
 */

@SuppressWarnings("ConstantConditions")
public class EducationEditActivity extends EditActivity<Education> {

    public static final String KEY_EDUCATION = "education";
    public static final String KEY_EDUCATION_ID = "education_id";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_education_edit;
    }

    @Override
    protected void setupUIForCreate() {
        findViewById(R.id.education_delete_btn).setVisibility(View.GONE);
    }

    @Override
    protected void setupUIForEdit(@NonNull final Education data) {
        ((EditText) findViewById(R.id.education_edit_school)).setText(data.school);
        ((EditText) findViewById(R.id.education_edit_major)).setText(data.major);
        ((EditText) findViewById(R.id.education_edit_startDate)).setText(DateUtils.dateToString(data.startDate));
        ((EditText) findViewById(R.id.education_edit_endDate)).setText(DateUtils.dateToString(data.endDate));
        ((EditText) findViewById(R.id.education_edit_courses)).setText(TextUtils.join("\n", data.courses));

        findViewById(R.id.education_delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result = new Intent();
                result.putExtra(KEY_EDUCATION_ID, data.id);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
    }

    @Override
    protected void saveAndExit(@Nullable Education data) {
        if (data == null) {
            data = new Education();
        }
        data.school = ((EditText) findViewById(R.id.education_edit_school)).getText().toString();
        data.major = ((EditText) findViewById(R.id.education_edit_major)).getText().toString();
        data.startDate = DateUtils.stringToDate(((EditText) findViewById(R.id.education_edit_startDate)).getText().toString());
        data.endDate = DateUtils.stringToDate(((EditText) findViewById(R.id.education_edit_endDate)).getText().toString());
        data.courses = Arrays.asList(TextUtils.split(((EditText) findViewById(R.id.education_edit_courses)).getText().toString(), "\n"));

        Intent result = new Intent();
        result.putExtra(KEY_EDUCATION, data);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    @Override
    protected Education initializeData() {
        return getIntent().getParcelableExtra(KEY_EDUCATION);
    }
}
