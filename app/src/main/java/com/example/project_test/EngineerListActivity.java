package com.example.project_test;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toolbar;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class EngineerListActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {
    private ProfilDataSource dataSource;
    private EngineerListAdapter adapter;
    Button buttonAdd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineer_list);

        dataSource = new ProfilDataSource(this);
        dataSource.open();
        buttonAdd = (Button)findViewById(R.id.buttonAdd) ;
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Toolbar toolbarr = findViewById(R.id.toolbarr);  // Add this line
//        setSupportActionBar(toolbarr);  // Add this line
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        Cursor cursor = dataSource.getAllProfilsCursor();

        ListView listView = findViewById(R.id.listViewEngineers); // Replace with your actual ListView ID
        adapter = new EngineerListAdapter(this, cursor, 0);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Cursor selectedCursor = (Cursor) adapter.getItem(position);

            // Extract details from the cursor
            long engineerId = getColumnValue(selectedCursor, DBHelper.COLUMN_ID, -1);
            String engineerName = getColumnValue(selectedCursor, DBHelper.COLUMN_NOM, "");
            String engineerFormations = getColumnValue(selectedCursor, DBHelper.COLUMN_FORMATIONS_DIPLOMES, "");
            String engineerExperiences = getColumnValue(selectedCursor, DBHelper.COLUMN_EXPERIENCES_STAGES, "");
            String engineerSkills = getColumnValue(selectedCursor, DBHelper.COLUMN_COMPETENCES_TECHNIQUES, "");
            String engineerInterests = getColumnValue(selectedCursor, DBHelper.COLUMN_CENTRES_INTERETS, "");


            // Pass the details to the new activity
            Intent intent = new Intent(EngineerListActivity.this, EngineerDetailActivity.class);
            intent.putExtra("engineerId", engineerId);
            intent.putExtra("engineerName", engineerName);
            intent.putExtra("engineerFormations", engineerFormations);
            intent.putExtra("engineerExperiences", engineerExperiences);
            intent.putExtra("engineerSkills", engineerSkills);
            intent.putExtra("engineerInterests", engineerInterests);
            // Add other details to the intent...
            startActivity(intent);
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent1 = new Intent(EngineerListActivity.this, AddEngineerProfileActivity.class);
                startActivity(myIntent1);
            }
        });
    }
    private <T> T getColumnValue(Cursor cursor, String columnName, T defaultValue) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex != -1) {
            if (defaultValue instanceof Long) {
                return (T) (Long) cursor.getLong(columnIndex);
            } else if (defaultValue instanceof String) {
                return (T) cursor.getString(columnIndex);
            }
            // Add more types if needed
        }
        return defaultValue;
    }
    // Handle navigation drawer item clicks
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_add_engineer) {
            // Open the Add Engineer form
            Intent intent = new Intent(EngineerListActivity.this, AddEngineerProfileActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }
}
