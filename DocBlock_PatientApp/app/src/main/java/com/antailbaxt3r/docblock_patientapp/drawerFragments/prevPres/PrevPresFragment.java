package com.antailbaxt3r.docblock_patientapp.drawerFragments.prevPres;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antailbaxt3r.docblock_patientapp.R;
import com.antailbaxt3r.docblock_patientapp.adapters.ImageAdapter;
import com.antailbaxt3r.docblock_patientapp.api.TouchImageView;
import com.antailbaxt3r.docblock_patientapp.models.Prescription;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PrevPresFragment extends Fragment {

    private PrevPresViewModel prevPresViewModel;
    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private ArrayList<Prescription> presList = new ArrayList<>();

    private DatabaseReference imgRef = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("prescriptions");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        prevPresViewModel =
                ViewModelProviders.of(this).get(PrevPresViewModel.class);
        final View root = inflater.inflate(R.layout.fragmentprev_pres, container, false);
        recyclerView = root.findViewById(R.id.pres_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        imgRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    presList.clear();
                    for(DataSnapshot shot : dataSnapshot.getChildren()){
                        Prescription prescription = shot.getValue(Prescription.class);
                        presList.add(prescription);
                    }

                    adapter = new ImageAdapter(presList, root.getContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return root;
    }
}