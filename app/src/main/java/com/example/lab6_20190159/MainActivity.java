package com.example.lab6_20190159;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab6_20190159.databinding.ActivityMainBinding;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        

        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setTheme(R.style.Base_Theme_Lab6_20190159)
                .setIsSmartLockEnabled(false)
                .setLogo(R.drawable.pucp)
                .setAvailableProviders(Arrays.asList(
                        new AuthUI.IdpConfig.EmailBuilder().build(),
                        new AuthUI.IdpConfig.GoogleBuilder().build()
                ))
                .build();

        signInLauncher.launch(intent);

        db = FirebaseFirestore.getInstance();

        ImageButton save = findViewById(R.id.addIngreso);
        save.setOnClickListener(v -> {
            Intent newIngresoIntent = new Intent(MainActivity.this, NuevoIngreso.class);
            startActivity(newIngresoIntent);
        });

        Button ingresoButton = findViewById(R.id.ingresobutton);
        Button egresoButton = findViewById(R.id.egresobutton);
        Button resumeButton = findViewById(R.id.resumeButton);
        Button logoutButton = findViewById(R.id.logoutButton);

        ingresoButton.setOnClickListener(v -> goToMainActivityContainer("IngresoFragment"));
        egresoButton.setOnClickListener(v -> goToMainActivityContainer("EgresoFragment"));
        resumeButton.setOnClickListener(v -> goToMainActivityContainer("ResumeFragment")); // Cambiar según corresponda
        logoutButton.setOnClickListener(v -> logout());
    }

    ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {
                        Log.d("TAG", "Firebase uid: " + user.getUid());
                        Log.d("TAG", "Display name: " + user.getDisplayName());
                        Log.d("TAG", "Email: " + user.getEmail());

                        user.reload().addOnCompleteListener(task -> {
                            if (user.isEmailVerified()) {
                                goToMainActivityContainer("IngresoFragment");
                            } else {
                                user.sendEmailVerification().addOnCompleteListener(task2 -> {
                                    Toast.makeText(MainActivity.this, "Se le ha enviado un correo para validar su cuenta", Toast.LENGTH_LONG).show();
                                });
                            }
                        });
                    } else {
                        Log.d("TAG", "user == null");
                    }
                } else {
                    Log.d("TAG", "Canceló el Log-in");
                }
            }
    );

    public void goToMainActivityContainer(String fragmentName) {
        Intent intent = new Intent(MainActivity.this, MainActivityContainer.class);
        intent.putExtra("fragment", fragmentName);
        startActivity(intent);
        finish();
    }

    public void logout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(task -> {
                    Log.d("infoApp", "logout exitoso");
                    // Redirigir a la actividad de login o cualquier otra actividad después del logout
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                });
    }
}
