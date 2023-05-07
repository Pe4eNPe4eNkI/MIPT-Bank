package com.example.mipt_bank_app.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.mipt_bank_app.Constants;
import com.example.mipt_bank_app.R;
import com.example.mipt_bank_app.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        /*TextView cl = (TextView) getView().findViewById(R.id.block);
        cl.setText("HUIHUI");*/
        if (Constants.entered == 0) {
            TextView reg = (TextView) getView().findViewById(R.id.want_sign_in);

            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.registration);
                }
            });

            Button btn = (Button) getView().findViewById(R.id.sign_in_button);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Constants.entered = 1;
                    Navigation.findNavController(view).navigate(R.id.account);
                }
            });
        }

        else {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}