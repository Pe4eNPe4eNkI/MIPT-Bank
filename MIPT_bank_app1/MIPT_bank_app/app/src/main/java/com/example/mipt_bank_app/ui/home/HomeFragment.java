package com.example.mipt_bank_app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.mipt_bank_app.Constants;
import com.example.mipt_bank_app.R;
import com.example.mipt_bank_app.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        /*TextView cl = (TextView) getView().findViewById(R.id.block);
        cl.setText("HUIHUI");*/
        ImageButton withdraw = (ImageButton) getView().findViewById(R.id.withdraw);
        ImageButton replenishment = (ImageButton) getView().findViewById(R.id.replenishment);

        if (Constants.entered == 1) {
            withdraw.setEnabled(true);
            replenishment.setEnabled(true);
        }

        else {
            withdraw.setEnabled(false);
            replenishment.setEnabled(false);
        }

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.withdraw2);
            }
        });

        replenishment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.replenishment2);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}