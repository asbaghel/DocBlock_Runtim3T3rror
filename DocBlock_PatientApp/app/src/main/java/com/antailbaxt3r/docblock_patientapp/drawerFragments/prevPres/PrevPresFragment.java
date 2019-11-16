package com.antailbaxt3r.docblock_patientapp.drawerFragments.prevPres;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.antailbaxt3r.docblock_patientapp.R;

public class PrevPresFragment extends Fragment {

    private PrevPresViewModel prevPresViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        prevPresViewModel =
                ViewModelProviders.of(this).get(PrevPresViewModel.class);
        View root = inflater.inflate(R.layout.fragmentprev_pres, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        prevPresViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}