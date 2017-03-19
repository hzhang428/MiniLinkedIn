package com.example.haozhang.minilinkedin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        setupEducations();
        setupExperiences();
        setupProjects();
    }

    private void setupBasicInfo() {
        ((TextView) findViewById(R.id.name)).setText(basicInfo.name);
        ((TextView) findViewById(R.id.email)).setText(basicInfo.email);
    }

    private void setupEducations() {
        LinearLayout educationsLayout = (LinearLayout) findViewById(R.id.educations);
        for (Education education : educations) {
            educationsLayout.addView(getEducationView(education));
        }

    }

    private View getEducationView(Education education) {
        View view = getLayoutInflater().inflate(R.layout.education_item, null);
        String range = "(" + DateUtils.dateToString(education.startDate) + "~" + DateUtils.dateToString(education.endDate) + ")";
        ((TextView) view.findViewById(R.id.education_school)).setText(education.school + " " + range);
        ((TextView) view.findViewById(R.id.education_courses)).setText(formatItems(education.courses));
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

        Education education1 = new Education();
        education1.id = "second";
        education1.school = "University of Georgia";
        education1.major = "Soil Microbiology";
        education1.startDate = DateUtils.stringToDate("08/2010");
        education1.endDate = DateUtils.stringToDate("05/2013");
        education1.courses = new ArrayList<>();
        education1.courses.add("Microbial Genetics");
        education1.courses.add("Statistics");
        education1.courses.add("Soil and Hydrology");
        educations.add(education1);

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
