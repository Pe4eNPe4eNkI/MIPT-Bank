package com.example.mipt_bank_app.ui.notifications;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.mipt_bank_app.Constants;
import com.example.mipt_bank_app.PersonDB;
import com.example.mipt_bank_app.R;
import com.example.mipt_bank_app.databinding.FragmentNotificationsBinding;

import org.w3c.dom.Text;

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
        if (Constants.entered == 0) {
            TextView reg = (TextView) getView().findViewById(R.id.want_sign_in);

            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.registration);
                }
            });

            Button btn_in = (Button) getView().findViewById(R.id.sign_in_button);


            btn_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PersonDB pdb = new PersonDB(getContext());
                    EditText login_text = (EditText) getView().findViewById(R.id.sign_in_name);
                    EditText password_text = (EditText) getView().findViewById(R.id.sign_in_password);

                    String login = login_text.getText().toString();
                    String password = password_text.getText().toString();
                    Toast.makeText(getActivity(), login + "  " + password, Toast.LENGTH_SHORT).show();
                    if (pdb.personFind(login, password) && !login.isEmpty() && !password.isEmpty()) {
                        Constants.entered = 1;
                        Navigation.findNavController(view).navigate(R.id.account);
                    } else {
                        Toast.makeText(getActivity(), "Incorect login or password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}