package com.example.notificationdrawerdemo.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notificationdrawerdemo.R;
import com.example.notificationdrawerdemo.database.DatabaseOperation;
import com.example.notificationdrawerdemo.model.User;
import com.example.notificationdrawerdemo.other.SessionManager;

import java.util.List;

/**
 * Created by Brishav on 12/18/2017.
 */

public class SignUpActivity extends AppCompatActivity {
    SessionManager session;
    DatabaseOperation userOperation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        final User newUser = new User();
        userOperation = new DatabaseOperation(this);

        session=new SessionManager(this);
        final EditText username, password ,image;
        TextView Login;
        Button submit;
        username=findViewById(R.id.signUp_inpText);
        password=findViewById(R.id.signUp_inpPassword);
        image=findViewById(R.id.imageUrl);
        submit=findViewById(R.id.SignUpButton);
        Login=findViewById(R.id.LoginActivity);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(i);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userOperation.open();
                String name=username.getText().toString();
                String pasword=password.getText().toString();
                String imageURL=image.getText().toString();
                String defaultURLImage;
                if (!name.isEmpty()&&!pasword.isEmpty()){
                    if(pasword.length()<8){
                      shoeError("Invalid Password");
                    }else{
                        if (!imageURL.isEmpty()) {
                            newUser.setUserImageUrl(imageURL);
                            session.saveImgUrl(imageURL);
                        }else{
                            newUser.setUserImageUrl("https://www.accelerateokanagan.com/themes/accelerateokanagan/img/avatars/default-avatar.png");
                            session.saveImgUrl("https://www.accelerateokanagan.com/themes/accelerateokanagan/img/avatars/default-avatar.png");
                        }
                        newUser.setUsername(name);
                        newUser.setUserpassword(pasword);
                        userOperation.addUser(newUser);
                        session.saveUsername(name);
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                }else
                    shoeError("Empty fields");
            }
        });
    }

    public void shoeError(String message){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Invalid")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
