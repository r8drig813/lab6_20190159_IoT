package com.example.lab6_20190159;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivityContainer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);

        Intent intent = getIntent();
        if (intent != null) {
            String fragmentName = intent.getStringExtra("fragment");
            if (fragmentName != null) {
                if (fragmentName.equals("IngresoFragment")) {
                    loadFragment(new IngresoFragment());
                } else if (fragmentName.equals("EgresoFragment")) {
                    loadFragment(new EgresoFragment());
                } else {
                    loadFragment(new IngresoFragment()); // Default fragment
                }
            } else {
                Log.e("MainActivityContainer", "Fragment name is null");
                loadFragment(new IngresoFragment()); // Default fragment
            }
        } else {
            Log.e("MainActivityContainer", "Intent is null");
            loadFragment(new IngresoFragment()); // Default fragment
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
