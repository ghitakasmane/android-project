package com.example.project_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_test.ProfilDataSource;

import java.util.Date;

public class AddEngineerProfileActivity extends AppCompatActivity {

    private ProfilDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_engineer_profile);

        // Initialize the database data source
        dataSource = new ProfilDataSource(this);
        dataSource.open();

        // Reference to UI components
        final EditText editTextName = findViewById(R.id.editTextName);
        final EditText editTextEducation = findViewById(R.id.editTextEducation);
        final EditText editTextExperiences = findViewById(R.id.editTextExperiences);
        final EditText editTextSkills = findViewById(R.id.editTextSkills);
        final EditText editTextCentreInteret = findViewById(R.id.editTextCentreInteret);
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonBack = findViewById(R.id.buttonBack) ;

        // Save button click listener
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user inputs
                String name = editTextName.getText().toString();
                String education = editTextEducation.getText().toString();
                String experiences = editTextExperiences.getText().toString();
                String skills = editTextSkills.getText().toString();
                String centreInteret = editTextCentreInteret.getText().toString();

                // Add the engineer profile to the database
                long result = dataSource.ajouterIngenieur(
                        new Ingenieur(name, education, experiences, skills, centreInteret, ""));

                if (result != -1) {
                    Toast.makeText(AddEngineerProfileActivity.this,
                            "Engineer profile added successfully", Toast.LENGTH_SHORT).show();
                    // Optionally, navigate to the list view or another activity
                } else {
                    Toast.makeText(AddEngineerProfileActivity.this,
                            "Failed to add engineer profile", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent1 = new Intent(AddEngineerProfileActivity.this, EngineerListActivity.class);
                startActivity(myIntent1);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database data source
        dataSource.close();
    }
}
