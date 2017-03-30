package com.example.haozhang.minilinkedin;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.haozhang.minilinkedin.model.Experience;
import com.example.haozhang.minilinkedin.util.DateUtils;

import java.util.Arrays;

/**
 * Created by haozhang on 3/29/17.
 *
 */

public class ExperienceEditActivity extends EditActivity<Experience> {

    public static final String KEY_EXPERIENCE = "experience";
    public static final String KEY_EXPERIENCE_ID = "experience_id";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_experience_edit;
    }

    @Override
    protected void setupUIForCreate() {
        findViewById(R.id.experience_delete_btn).setVisibility(View.GONE);
    }

    @Override
    protected void setupUIForEdit(@NonNull final Experience data) {
        ((EditText) findViewById(R.id.experience_edit_company)).setText(data.company);
        ((EditText) findViewById(R.id.experience_edit_title)).setText(data.title);
        ((EditText) findViewById(R.id.experience_edit_startDate)).setText(DateUtils.dateToString(data.startDate));
        ((EditText) findViewById(R.id.experience_edit_endDate)).setText(DateUtils.dateToString(data.endDate));
        ((EditText) findViewById(R.id.experience_edit_work)).setText(TextUtils.join("\n", data.items));

        findViewById(R.id.experience_delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result = new Intent();
                result.putExtra(KEY_EXPERIENCE_ID, data.id);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
    }

    @Override
    protected void saveAndExit(@Nullable Experience data) {
        if (data == null) {
            data = new Experience();
        }
        data.company = ((EditText) findViewById(R.id.experience_edit_company)).getText().toString();
        data.title = ((EditText) findViewById(R.id.experience_edit_title)).getText().toString();
        data.startDate = DateUtils.stringToDate(((EditText) findViewById(R.id.experience_edit_startDate)).getText().toString());
        data.endDate = DateUtils.stringToDate(((EditText) findViewById(R.id.experience_edit_endDate)).getText().toString());
        data.items = Arrays.asList(TextUtils.split(((EditText) findViewById(R.id.experience_edit_work)).getText().toString(), "\n"));

        Intent result = new Intent();
        result.putExtra(KEY_EXPERIENCE, data);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    @Override
    protected Experience initializeData() {
        return getIntent().getParcelableExtra(KEY_EXPERIENCE);
    }
}
