package com.example.asus.marketforall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final FirebaseAuth auth = FirebaseAuth.getInstance();
    private static final FirebaseUser user = auth.getCurrentUser();

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alreadyLogin();
    }

    private void alreadyLogin() {
        if(user == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            dl = (DrawerLayout) findViewById(R.id.activity_main);
            t = new ActionBarDrawerToggle(this, dl, R.string.common_open_on_phone, R.string.bottom_sheet_behavior);

            dl.addDrawerListener(t);
            t.syncState();

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            nv = (NavigationView) findViewById(R.id.nv);
            nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();
                    switch (id) {
                        case R.id.my_profile:
                            Toast.makeText(MainActivity.this, "My Profile", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.my_shop:
                            Toast.makeText(MainActivity.this, "My Shop", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.settings:
                            Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return t.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
