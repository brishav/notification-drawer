package com.example.notificationdrawerdemo.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.notificationdrawerdemo.R;
import com.example.notificationdrawerdemo.activity.SignUpActivity;
import com.example.notificationdrawerdemo.other.SessionManager;

/**
 * Created by Brishav on 12/6/2017.
 */

public class SettingFragment extends Fragment {

    SessionManager session;
    Button btnLogOut;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.setting_activity,
                container, false);

        session=new SessionManager(getContext());

        btnLogOut= view.findViewById(R.id.logOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth);
                } else {
                    builder = new AlertDialog.Builder(getContext());
                }
                builder.setTitle("Log Out")
                        .setMessage("Are you sure you want to logout??")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                session.logoutUser();
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });
        return view;
    }
}
