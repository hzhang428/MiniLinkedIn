package com.example.haozhang.minilinkedin;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
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
import com.example.haozhang.minilinkedin.util.ModelUtils;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_EDUCATION_EDIT = 100;
    private static final int REQ_CODE_PROJECT_EDIT = 101;
    private static final int REQ_CODE_EXPERIENCE_EDIT = 102;
    private static final int REQ_CODE_BASIC_INFO_EDIT = 103;

    private static final String MODEL_EDUCATIONS = "educations";
    private static final String MODEL_PROJECTS = "projects";
    private static final String MODEL_EXPERIENCES = "experiences";
    private static final String MODEL_BASIC_INFO = "basic_info";

    private BasicInfo basicInfo;
    private List<Education> educations;
    private List<Experience> experiences;
    private List<Project> projects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
        setupUI();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQ_CODE_BASIC_INFO_EDIT:
                    BasicInfo basicInfo = data.getParcelableExtra(BasicInfoEditActivity.KEY_BASIC_INFO);
                    updateBasicInfo(basicInfo);
                    break;
                case REQ_CODE_EDUCATION_EDIT:
                    Education education = data.getParcelableExtra(EducationEditActivity.KEY_EDUCATION);
                    updateEducation(education);
                    break;
                case REQ_CODE_EXPERIENCE_EDIT:
                    Experience experience = data.getParcelableExtra(ExperienceEditActivity.KEY_EXPERIENCE);
                    updateExperience(experience);
                    break;
                case REQ_CODE_PROJECT_EDIT:
                    Project project = data.getParcelableExtra(ProjectEditActivity.KEY_PROJECT);
                    updateProject(project);
                    break;
            }
        }
    }

    private void loadData() {
        BasicInfo savedBasicInfo = ModelUtils.read(this, MODEL_BASIC_INFO, new TypeToken<BasicInfo>() {
        });
        basicInfo = savedBasicInfo == null ? new BasicInfo() : savedBasicInfo;

        List<Education> savedEducations = ModelUtils.read(this, MODEL_EDUCATIONS, new TypeToken<List<Education>>() {
        });
        educations = savedEducations == null ? new ArrayList<Education>() : savedEducations;

        List<Experience> savedExperiences = ModelUtils.read(this, MODEL_EXPERIENCES, new TypeToken<List<Experience>>() {
        });
        experiences = savedExperiences == null ? new ArrayList<Experience>() : savedExperiences;

        List<Project> savedProjects = ModelUtils.read(this, MODEL_PROJECTS, new TypeToken<List<Project>>() {
        });
        projects = savedProjects == null ? new ArrayList<Project>() : savedProjects;

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

        ImageButton addExperienceBtn = (ImageButton) findViewById(R.id.add_experience_btn);
        addExperienceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExperienceEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EXPERIENCE_EDIT);
            }
        });

        ImageButton addProjectBtn = (ImageButton) findViewById(R.id.add_projects_btn);
        addProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProjectEditActivity.class);
                startActivityForResult(intent, REQ_CODE_PROJECT_EDIT);
            }
        });

        setupBasicInfo();
        setupEducations();
        setupExperiences();
        setupProjects();
    }

    private void setupBasicInfo() {
        ((TextView) findViewById(R.id.name)).setText(TextUtils.isEmpty(basicInfo.name) ? "Your name" : basicInfo.name);
        ((TextView) findViewById(R.id.email)).setText(TextUtils.isEmpty(basicInfo.email) ? "Your email" : basicInfo.email);

        findViewById(R.id.edit_basic_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BasicInfoEditActivity.class);
                intent.putExtra(BasicInfoEditActivity.KEY_BASIC_INFO, basicInfo);
                startActivityForResult(intent, REQ_CODE_BASIC_INFO_EDIT);
            }
        });
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
        ((TextView) view.findViewById(R.id.education_major)).setText(education.major);
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
        experienceLayout.removeAllViews();
        for (Experience experience : experiences) {
            experienceLayout.addView(getExperienceView(experience));
        }
    }

    private View getExperienceView(final Experience experience) {
        View view = getLayoutInflater().inflate(R.layout.experience_item, null);
        String range = "(" + DateUtils.dateToString(experience.startDate) + "~" + DateUtils.dateToString(experience.endDate) + ")";

        ((TextView) view.findViewById(R.id.experience_company)).setText(experience.company + " " + range);
        ((TextView) view.findViewById(R.id.experience_title)).setText(experience.title);
        ((TextView) view.findViewById(R.id.experience_detail)).setText(formatItems(experience.items));

        view.findViewById(R.id.edit_experience_detail_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExperienceEditActivity.class);
                intent.putExtra(ExperienceEditActivity.KEY_EXPERIENCE, experience);
                startActivityForResult(intent, REQ_CODE_EXPERIENCE_EDIT);
            }
        });
        return view;
    }

    private void setupProjects() {
        LinearLayout projectLayout = (LinearLayout) findViewById(R.id.projects);
        projectLayout.removeAllViews();
        for (Project project : projects) {
            projectLayout.addView(getProjectView(project));
        }
    }

    private View getProjectView(final Project project) {
        View view = getLayoutInflater().inflate(R.layout.project_item, null);
        String range = "(" + DateUtils.dateToString(project.startDate) + "~" + DateUtils.dateToString(project.endDate) + ")";

        ((TextView) view.findViewById(R.id.project_name)).setText(project.name + " " + range);
        ((TextView) view.findViewById(R.id.project_detail)).setText(formatItems(project.details));

        view.findViewById(R.id.edit_project_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProjectEditActivity.class);
                intent.putExtra(ProjectEditActivity.KEY_PROJECT, project);
                startActivityForResult(intent, REQ_CODE_PROJECT_EDIT);
            }
        });
        return view;
    }

    private void updateBasicInfo(BasicInfo basicInfo) {
        ModelUtils.save(this, MODEL_BASIC_INFO, basicInfo);

        this.basicInfo = basicInfo;
        setupBasicInfo();
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
        ModelUtils.save(this, MODEL_EDUCATIONS, educations);
        setupEducations();
    }

    private void updateExperience(Experience experience) {
        boolean found = false;
        for (int i = 0; i < experiences.size(); i++) {
            Experience e = experiences.get(i);
            if (TextUtils.equals(experience.id, e.id)) {
                found = true;
                experiences.set(i, experience);
                break;
            }
        }
        if (!found) {
            experiences.add(experience);
        }
        ModelUtils.save(this, MODEL_EXPERIENCES, experiences);
        setupExperiences();
    }

    private void updateProject(Project project) {
        boolean found = false;
        for (int i = 0; i < projects.size(); i++) {
            Project e = projects.get(i);
            if (TextUtils.equals(project.id, e.id)) {
                found = true;
                projects.set(i, project);
                break;
            }
        }
        if (!found) {
            projects.add(project);
        }
        ModelUtils.save(this, MODEL_PROJECTS, projects);
        setupProjects();
    }

    private String formatItems(List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append("-" + item + "\n");
        }
        return sb.toString();
    }
}
