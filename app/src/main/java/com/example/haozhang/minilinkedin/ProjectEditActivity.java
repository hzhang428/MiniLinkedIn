package com.example.haozhang.minilinkedin;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by haozhang on 3/29/17.
 */

public class ProjectEditActivity extends EditActivity {

    public static final String KEY_PROJECT = "project";
    public static final String KEY_EDUCATION_ID = "project_id";


    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void setupUIForCreate() {

    }

    @Override
    protected void setupUIForEdit(@NonNull Object data) {

    }

    @Override
    protected void saveAndExit(@Nullable Object data) {

    }

    @Override
    protected Object initializeData() {
        return getIntent().getParcelableExtra(KEY_PROJECT);
    }
}
