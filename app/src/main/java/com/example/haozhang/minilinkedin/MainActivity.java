package com.example.haozhang.minilinkedin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.haozhang.minilinkedin.model.BasicInfo;
import com.example.haozhang.minilinkedin.model.Education;
import com.example.haozhang.minilinkedin.model.Experience;
import com.example.haozhang.minilinkedin.model.Project;
import com.example.haozhang.minilinkedin.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BasicInfo basicInfo;
    private List<Education> educations;
    private List<Experience> experiences;
    private List<Project> projects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fakeData();
        setupUI();
    }

    private void setupUI() {
        setContentView(R.layout.activity_main);
        setupBasicInfo();
        setupEducation();
        setupExperience();
        setupProject();
    }

    private void setupBasicInfo() {
        ((TextView) findViewById(R.id.name)).setText(basicInfo.name);
        ((TextView) findViewById(R.id.email)).setText(basicInfo.email);
    }

    private void setupEducation() {
        String range = "(" + DateUtils.dateToString(educations.get(0).startDate) + "~" + DateUtils.dateToString(educations.get(0).endDate) + ")";
        ((TextView) findViewById(R.id.education_school)).setText(educations.get(0).school + " " + range);
        ((TextView) findViewById(R.id.education_courses)).setText(formatItems(educations.get(0).courses));
    }

    private void setupExperience() {
        ((TextView) findViewById(R.id.experience_company)).setText(experiences.get(0).company);
        ((TextView) findViewById(R.id.experience_detail)).setText(formatItems(experiences.get(0).items));
    }

    private void setupProject() {
        ((TextView) findViewById(R.id.project_name)).setText(projects.get(0).name);
        ((TextView) findViewById(R.id.project_detail)).setText(formatItems(projects.get(0).details));
    }

    private String formatItems(List<String> item) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < item.size(); i++) {
            if (i == 0) {
                sb.append("- " + item.get(i));
            } else {
                sb.append("\n" + "- " + item.get(i));
            }
        }
        return sb.toString();
    }


    private void fakeData() {
        basicInfo = new BasicInfo();
        basicInfo.name = "Hao Zhang";
        basicInfo.email = "hzhang428@gatech.edu";

        educations = new ArrayList<>();
        Education education = new Education();
        education.id = "latest";
        education.school = "Georgia Institution of Technology";
        education.major = "Computational Science and Engineering";
        education.startDate = DateUtils.stringToDate("08/2015");
        education.endDate = DateUtils.stringToDate("05/2018");
        education.courses = new ArrayList<>();
        education.courses.add("Network");
        education.courses.add("Algorithm");
        education.courses.add("Database");
        educations.add(education);

        experiences = new ArrayList<>();
        Experience experience = new Experience();
        experience.id = "latest";
        experience.company = "Norfolk Southern";
        experience.items = new ArrayList<>();
        experience.items.add("Web Application Development");
        experience.items.add("Cassandra data modeling on the backend");
        experience.items.add("Splunk query and data visualization");
        experiences.add(experience);

        projects = new ArrayList<>();
        Project project = new Project();
        project.name = "MiniLinkedin";
        project.details = new ArrayList<>();
        project.details.add("UI design");
        project.details.add("Implement local storage");
        project.details.add("Multi-Language support");
        projects.add(project);
    }
}
