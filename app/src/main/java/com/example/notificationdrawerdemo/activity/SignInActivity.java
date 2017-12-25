package com.example.notificationdrawerdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notificationdrawerdemo.R;
import com.example.notificationdrawerdemo.database.DatabaseOperation;
import com.example.notificationdrawerdemo.model.User;
import com.example.notificationdrawerdemo.other.SessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brishav on 12/17/2017.
 */

public class SignInActivity extends AppCompatActivity {
    private DatabaseOperation UserDB;
    List<User> users=new ArrayList<User>();
    SessionManager session;
    String DBName , DBPassword , DBUserImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);
        final EditText username, password;
        Button signIn;
        session=new SessionManager(getApplicationContext());
        username = findViewById(R.id.signin_InpUsername);
        password = findViewById(R.id.signin_InpPassword);
        signIn = findViewById(R.id.SignIn_login);
        UserDB = new DatabaseOperation(this);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String InpName = username.getText().toString();
                String InpPassword = password.getText().toString();
                UserDB.open();
                users = UserDB.getAllUser();
                if(!InpName.isEmpty()&&!InpPassword.isEmpty()){
                for(User user : users){
                    if(user.getUsername().contentEquals(InpName) && user.getUserpassword().contentEquals(InpPassword)){
                       DBName= user.getUsername();
                       DBPassword= user.getUserpassword();
                       DBUserImage= user.getUserImageUrl();
                        Log.d("NameUser",DBUserImage);
                    }
                }
                    if(DBName!=null&&DBPassword!=null){
                        error("Hello "+InpName);
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        session.saveUsername(DBName);
                        session.saveImgUrl(DBUserImage);
                        startActivity(i);
                        finish();
                    }else {
                        error("Invalid");
                    }
                }else {
                    error("Empty Field");
                }



            }
        });
    }
    public void error(String messgae){
        Toast.makeText(getApplicationContext(),messgae,Toast.LENGTH_SHORT).show();
    }
}
