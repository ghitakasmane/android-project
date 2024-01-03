package com.example.project_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class EngineerDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineer_detail);

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Enable the Up button (Back button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        long engineerId = intent.getLongExtra("engineerId", -1);
        String engineerName = intent.getStringExtra("engineerName");
        String engineerFormations = intent.getStringExtra("engineerFormations");
        String engineerExperiences = intent.getStringExtra("engineerExperiences");
        String engineerSkills = intent.getStringExtra("engineerSkills");
        String engineerInterests = intent.getStringExtra("engineerInterests");
        // Retrieve other details...

        // Display the details in TextViews or other UI elements
        TextView idTextView = findViewById(R.id.textViewId); // Replace with your actual TextView ID
        idTextView.setText("ID: " + engineerId);

        TextView nameTextView = findViewById(R.id.textViewName); // Replace with your actual TextView ID
        nameTextView.setText("Name: " + engineerName);

        TextView formationsTextView = findViewById(R.id.textViewFormations); // Replace with your actual TextView ID
        formationsTextView.setText("Formations: " + engineerFormations);

        TextView experiencesTextView = findViewById(R.id.textViewExperiences); // Replace with your actual TextView ID
        experiencesTextView.setText("Experiences: " + engineerExperiences);

        TextView skillsTextView = findViewById(R.id.textViewSkills); // Replace with your actual TextView ID
        skillsTextView.setText("Skills: " + engineerSkills);

        TextView interestsTextView = findViewById(R.id.textViewInterests); // Replace with your actual TextView ID
        interestsTextView.setText("Interests: " + engineerInterests);
    }
    // Handle the Back button click
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Close the current activity when the Back button is pressed
        return true;
    }
}
