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
import com.example.haozhang.minilinkedin.model.Project;
import com.example.haozhang.minilinkedin.util.DateUtils;

import java.util.Arrays;

/**
 * Created by haozhang on 3/29/17.
 *
 */

public class ProjectEditActivity extends EditActivity<Project> {

    public static final String KEY_PROJECT = "project";
    public static final String KEY_PROJECT_ID = "project_id";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_edit;
    }

    @Override
    protected void setupUIForCreate() {
        findViewById(R.id.project_delete_btn).setVisibility(View.GONE);
    }

    @Override
    protected void setupUIForEdit(@NonNull final Project data) {
        ((EditText) findViewById(R.id.project_edit_name)).setText(data.name);
        ((EditText) findViewById(R.id.project_edit_startDate)).setText(DateUtils.dateToString(data.startDate));
        ((EditText) findViewById(R.id.project_edit_endDate)).setText(DateUtils.dateToString(data.endDate));
        ((EditText) findViewById(R.id.project_edit_detail)).setText(TextUtils.join("\n", data.details));

        findViewById(R.id.project_delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result = new Intent();
                result.putExtra(KEY_PROJECT_ID, data.id);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
    }

    @Override
    protected void saveAndExit(@Nullable Project data) {
        if (data == null) {
            data = new Project();
        }
        data.name = ((EditText) findViewById(R.id.project_edit_name)).getText().toString();
        data.startDate = DateUtils.stringToDate(((EditText) findViewById(R.id.project_edit_startDate)).getText().toString());
        data.endDate = DateUtils.stringToDate(((EditText) findViewById(R.id.project_edit_endDate)).getText().toString());
        data.details = Arrays.asList(TextUtils.split(((EditText) findViewById(R.id.project_edit_detail)).getText().toString(), "\n"));

        Intent result = new Intent();
        result.putExtra(KEY_PROJECT, data);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    @Override
    protected Project initializeData() {
        return getIntent().getParcelableExtra(KEY_PROJECT);
    }
}
