package com.example.haozhang.minilinkedin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.haozhang.minilinkedin.model.BasicInfo;
import com.example.haozhang.minilinkedin.model.Education;
import com.example.haozhang.minilinkedin.model.Experience;
import com.example.haozhang.minilinkedin.model.Project;
import com.example.haozhang.minilinkedin.util.DateUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_EDUCATION_EDIT = 100;
    private static final int REQ_CODE_PROJECT_EDIT = 101;
    private static final int REQ_CODE_EXPERIENCE_EDIT = 102;
    private static final int REQ_CODE_BASIC_INFO_EDIT = 103;

    private static final String MODEL_EDUCATION = "educations";
    private static final String MODEL_PROJECT = "projects";
    private static final String MODEL_EXPERIENCE = "experiences";
    private static final String MODEL_BASIC_INFO = "basic_info";

    private BasicInfo basicInfo;
    private List<Education> educations;
    private List<Experience> experiences;
    private List<Project> projects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUI();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQ_CODE_BASIC_INFO_EDIT:
                    break;
                case REQ_CODE_EDUCATION_EDIT:
                    Education education = data.getParcelableExtra(EducationEditActivity.KEY_EDUCATION);
                    if (educations == null) {
                        educations = new ArrayList<>();
                    }
                    updateEducation(education);
                    break;
                case REQ_CODE_EXPERIENCE_EDIT:
                    break;
                case REQ_CODE_PROJECT_EDIT:
                    break;
            }
        }
    }

    private void setupUI() {
        setContentView(R.layout.activity_main);

        ImageButton addEducationBtn = (ImageButton) findViewById(R.id.add_education_btn);
        addEducationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EDUCATION_EDIT);
            }
        });
//        setupBasicInfo();
//        setupEducations();
//        setupExperiences();
//        setupProjects();
    }

    private void setupBasicInfo() {
        ((TextView) findViewById(R.id.name)).setText(basicInfo.name);
        ((TextView) findViewById(R.id.email)).setText(basicInfo.email);
    }

    private void setupEducations() {
        LinearLayout educationsLayout = (LinearLayout) findViewById(R.id.educations);
        educationsLayout.removeAllViews();
        for (Education education : educations) {
            educationsLayout.addView(getEducationView(education));
        }
    }

    private View getEducationView(final Education education) {
        View view = getLayoutInflater().inflate(R.layout.education_item, null);
        String range = "(" + DateUtils.dateToString(education.startDate) + "~" + DateUtils.dateToString(education.endDate) + ")";
        ((TextView) view.findViewById(R.id.education_school)).setText(education.school + " " + range);
        ((TextView) view.findViewById(R.id.education_courses)).setText(formatItems(education.courses));
        view.findViewById(R.id.edit_school_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                intent.putExtra(EducationEditActivity.KEY_EDUCATION, education);
                startActivityForResult(intent, REQ_CODE_EDUCATION_EDIT);
            }
        });
        return view;
    }

    private void setupExperiences() {
        LinearLayout experienceLayout = (LinearLayout) findViewById(R.id.experiences);
        for (Experience experience : experiences) {
            experienceLayout.addView(getExperienceView(experience));
        }
    }

    private View getExperienceView(Experience experience) {
        View view = getLayoutInflater().inflate(R.layout.experience_item, null);
        ((TextView) view.findViewById(R.id.experience_company)).setText(experience.company);
        ((TextView) view.findViewById(R.id.experience_detail)).setText(formatItems(experience.items));
        return view;
    }

    private void setupProjects() {
        LinearLayout projectLayout = (LinearLayout) findViewById(R.id.projects);
        for (Project project : projects) {
            projectLayout.addView(getProjectView(project));
        }
    }

    private View getProjectView(Project project) {
        View view = getLayoutInflater().inflate(R.layout.project_item, null);
        ((TextView) view.findViewById(R.id.project_name)).setText(project.name);
        ((TextView) view.findViewById(R.id.project_detail)).setText(formatItems(project.details));
        return view;
    }

    /*
     * If education with the same id is found update it.
     * If not, add the new education to the list.
     */
    private void updateEducation(Education education) {
        boolean found = false;
        for (int i = 0; i < educations.size(); i++) {
            Education e = educations.get(i);
            if (TextUtils.equals(education.id, e.id)) {
                found = true;
                educations.set(i, education);
                break;
            }
        }
        if (!found) {
            educations.add(education);
        }
        setupEducations();
    }

    private String formatItems(List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append("-" + item + "\n");
        }
        return sb.toString();
    }
}
