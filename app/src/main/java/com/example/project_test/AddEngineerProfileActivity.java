package com.example.project_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.project_test.ProfilDataSource;
import com.google.android.material.navigation.NavigationView;

import java.util.Date;

public class AddEngineerProfileActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    private ProfilDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_engineer_profile);
        DrawerLayout drawer = findViewById(R.id.drawer_layout_add);
        NavigationView navigationView = findViewById(R.id.nav_view_add);
        Toolbar toolbar = findViewById(R.id.toolbarlist_add);  // Add this line
        setSupportActionBar(toolbar);  // Add this line
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_add_engineer) {
            // Open the Add Engineer form
            Intent intent = new Intent(AddEngineerProfileActivity.this, AddEngineerProfileActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_list_engineer) {
            Intent intent = new Intent(AddEngineerProfileActivity.this, EngineerListActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database data source
        dataSource.close();
    }
}
