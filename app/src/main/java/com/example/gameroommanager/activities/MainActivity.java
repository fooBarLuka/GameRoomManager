package com.example.gameroommanager.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.gameroommanager.R;
import com.example.gameroommanager.database.MyDataBase;
import com.example.gameroommanager.fragments.AddConsoleFragment;
import com.example.gameroommanager.fragments.ConsoleFragment;
import com.example.gameroommanager.fragments.ReserveFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;

    private BottomNavigationView bottomNavigationView;
    private static int lastSelectedItem = R.id.reserve_navigation_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        MyDataBase.buildConsoleDataBase(this);

        fragmentManager = getSupportFragmentManager();

        initUI();
    }

    private void initUI() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view_id);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        initDefaultFragment();
    }

    private void initDefaultFragment() {
        switch (lastSelectedItem) {
            case R.id.reserve_navigation_id:
                replaceFragment(ReserveFragment.getInstance());
                break;
            case R.id.consoles_navigation_id:
                replaceFragment(ConsoleFragment.getInstance());
                break;
            case R.id.add_console_navigation_id:
                replaceFragment(AddConsoleFragment.getInstance());
                break;
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragments_container_id, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.reserve_navigation_id:
                lastSelectedItem = R.id.reserve_navigation_id;
                replaceFragment(ReserveFragment.getInstance());
                return true;
            case R.id.consoles_navigation_id:
                lastSelectedItem = R.id.consoles_navigation_id;
                replaceFragment(ConsoleFragment.getInstance());
                return true;
            case R.id.add_console_navigation_id:
                lastSelectedItem = R.id.add_console_navigation_id;
                replaceFragment(AddConsoleFragment.getInstance());
                return true;
            default:
                return false;
        }
    }
}
