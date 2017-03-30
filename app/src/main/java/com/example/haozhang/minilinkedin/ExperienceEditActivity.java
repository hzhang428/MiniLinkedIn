package com.example.haozhang.minilinkedin;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by haozhang on 3/29/17.
 */

public class ExperienceEditActivity extends EditActivity {

    public static final String KEY_EXPERIENCE = "experience";
    public static final String KEY_EXPERIENCE_ID = "experience_id";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_experience_edit;
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
        return getIntent().getParcelableExtra(KEY_EXPERIENCE);
    }
}
