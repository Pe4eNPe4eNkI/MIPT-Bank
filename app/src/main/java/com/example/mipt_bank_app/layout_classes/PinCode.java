package com.example.mipt_bank_app.layout_classes;


import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.example.mipt_bank_app.PinCodeDB;
import com.example.mipt_bank_app.R;
import com.example.mipt_bank_app.person.Adult;
import com.example.mipt_bank_app.person.AdultBuilder;
import com.example.mipt_bank_app.person.AdultParams;
import com.example.mipt_bank_app.person.PersonDB;

import java.util.ArrayList;

import com.example.mipt_bank_app.Constants;

import org.apache.commons.lang3.StringUtils;

public class PinCode extends Fragment {

    private String pinCode = "";
    private String mParam1;
    private String mParam2;

    public PinCode() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pin_code, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        PinCodeDB db = new PinCodeDB(getContext());
        if (!db.checkPerson().first) { //
            Navigation.findNavController(view).navigate(R.id.action_pinCode2_to_navigation_home);
        } else {
            Button bClearLast = (Button) getView().findViewById(R.id.button_delete_last);

            ArrayList<Pair<Button, String>> arrayList = new ArrayList<>();
            arrayList.add(new Pair<>((Button) getView().findViewById(R.id.button_0), "0"));
            arrayList.add(new Pair<>((Button) getView().findViewById(R.id.button_1), "1"));
            arrayList.add(new Pair<>((Button) getView().findViewById(R.id.button_2), "2"));
            arrayList.add(new Pair<>((Button) getView().findViewById(R.id.button_3), "3"));
            arrayList.add(new Pair<>((Button) getView().findViewById(R.id.button_4), "4"));
            arrayList.add(new Pair<>((Button) getView().findViewById(R.id.button_5), "5"));
            arrayList.add(new Pair<>((Button) getView().findViewById(R.id.button_6), "6"));
            arrayList.add(new Pair<>((Button) getView().findViewById(R.id.button_7), "7"));
            arrayList.add(new Pair<>((Button) getView().findViewById(R.id.button_8), "8"));
            arrayList.add(new Pair<>((Button) getView().findViewById(R.id.button_9), "9"));

            ArrayList<ImageView> imageViews = new ArrayList<>();
            imageViews.add((ImageView) getView().findViewById(R.id.first_circle));
            imageViews.add((ImageView) getView().findViewById(R.id.second_circle));
            imageViews.add((ImageView) getView().findViewById(R.id.third_circle));
            imageViews.add((ImageView) getView().findViewById(R.id.fourth_circle));

            for (Pair<Button, String> elem : arrayList) {
                elem.first.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pinCode = pinCode + elem.second;
                        imageViews.get(pinCode.length() - 1).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));

                        if (pinCode.length() >= 4) {
                            if (db.checkPinCode(pinCode)) {
                                Cursor cursor = db.getPerson(pinCode);
                                cursor.moveToFirst();
                                int a = cursor.getCount();
                                PersonDB personDB = new PersonDB(getContext());
                                Cursor personCursor = personDB.getPersonByLoginPassword(cursor.getString(0), cursor.getString(1));

                                personCursor.moveToFirst();
                                String login = personCursor.getString(0);
                                String id = personCursor.getString(1);
                                String password = personCursor.getString(2);
                                String name = personCursor.getString(3);
                                String surname = personCursor.getString(4);
                                String address = personCursor.getString(5);
                                String passport_id = personCursor.getString(6);

                                AdultParams adultParams = new AdultParams(name, surname, address, passport_id, login, password);

                                AdultBuilder adultBuilder = new AdultBuilder(adultParams);
                                adultBuilder.build();
                                Adult adult = adultBuilder.getPerson();
                                adult.setID(id);
                                Constants.adult = adult;
                                Constants.entered = 1;

                                Navigation.findNavController(view).navigate(R.id.action_pinCode2_to_navigation_home);
                            } else {
                                pinCode = "";
                                for (ImageView elem : imageViews) {
                                    elem.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.black)));
                                }
                                Toast.makeText(getActivity(), "Wrong PinCode", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
            }
            bClearLast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pinCode = StringUtils.chop(pinCode);
                    if (pinCode.length() >= 0) {
                        imageViews.get(pinCode.length()).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.black)));
                    }
                }
            });

        }


    }
}