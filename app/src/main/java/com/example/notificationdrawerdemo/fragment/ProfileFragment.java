package com.example.notificationdrawerdemo.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.notificationdrawerdemo.R;
import com.example.notificationdrawerdemo.other.SessionManager;

/**
 * Created by Brishav on 12/6/2017.
 */

public class ProfileFragment extends Fragment {
    SessionManager session;
     String username = "Brishav Shakya";
    final String bio = "Hey there this is me Brishav Shakya from Patan Lalitpur";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.profile_activity,
                container, false);
        session=new SessionManager(getContext());
        username=session.getUserDetails();
        TextView inpUsername=view.findViewById(R.id.profile_username);
        TextView inpBio=view.findViewById(R.id.profile_bio);
        ImageView inpCoverPic=view.findViewById(R.id.profile_coverpic);
        ImageView inpUserImg=view.findViewById(R.id.profile_mainpic);

        inpUsername.setText(username);
        inpBio.setText(bio);

        Glide.with(this).load(session.getUserImage())
                .into(inpUserImg);

        Glide.with(this).load(R.color.colorPrimaryDark)
                .into(inpCoverPic);

        return view;
    }
}
