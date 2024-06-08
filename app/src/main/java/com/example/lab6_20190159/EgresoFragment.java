package com.example.lab6_20190159;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EgresoFragment extends Fragment {

    private RecyclerView recyclerView;
    private IngresoAdapter ingresoAdapter;
    private List<Ingreso> ingresoList = new ArrayList<>();
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingreso, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ingresoAdapter = new IngresoAdapter(ingresoList);
        recyclerView.setAdapter(ingresoAdapter);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton myButton = view.findViewById(R.id.addIngresoButton);

        // Configura el OnClickListener
        myButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NuevoIngreso.class);
            startActivity(intent);
        });


        Button logoutButton = view.findViewById(R.id.logoutButton);

        // Configura el OnClickListener para el botón de logout
        logoutButton.setOnClickListener(v -> logout());

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button navigateButton = view.findViewById(R.id.ingresoButton);

        // Configura el OnClickListener para el botón
        navigateButton.setOnClickListener(v -> navigateToIngresoFragment());

        db = FirebaseFirestore.getInstance();
        db.collection("Ingreso")
                .addSnapshotListener((snapshot, error) -> {
                    if (error != null) {
                        Log.w("msg-test", "Listen failed.", error);
                        return;
                    }
                    for(QueryDocumentSnapshot doc: snapshot){
                        Ingreso ingreso = doc.toObject(Ingreso.class);
                        Log.d("msg-test", "Titulo: " + ingreso.getTitulo());
                        Log.d("msg-test", "Monto: " + ingreso.getMonto());
                        Log.d("msg-test", "Descripcion: " + ingreso.getDescripcion());
                        Log.d("msg-test", "Fecha: " + ingreso.getFecha());

                    }
                });

        return view;
    }
    private void logout() {
        AuthUI.getInstance()
                .signOut(getContext())
                .addOnCompleteListener(task -> {
                    // Redirigir a la actividad de login o cualquier otra actividad después del logout
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                });
    }

    private void navigateToIngresoFragment() {
        Fragment ingresoFragment = new IngresoFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, ingresoFragment);
        transaction.addToBackStack(null); // Agrega la transacción a la pila de retroceso
        transaction.commit();
    }

}
