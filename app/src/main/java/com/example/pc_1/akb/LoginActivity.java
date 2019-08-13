package com.example.pc_1.akb;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.prefs.Preferences;

public class LoginActivity extends AppCompatActivity {
    EditText Edtuser,Edtpass;
    Button Btnlogin,Btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkFirstRun();

        Edtuser = findViewById(R.id.edt_username);
        Edtpass = findViewById(R.id.edt_password);
        Btnlogin = findViewById(R.id.btn_login);
        Btnregister = findViewById(R.id.btn_register);
        Btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String user = Edtuser.getText().toString();
            String pass = Edtpass.getText().toString();

            if(!cekUser(user)||!cekPassword(pass)){
                Dialog();
            }
            else{
                login();
            }

            }
        });



        Btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });



    }
    private void login(){
        Pref.setLoggedInUser(getBaseContext(),Pref.getRegisteredUser(getBaseContext()));
        Pref.setLoggedInStatus(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(),MainActivity.class));finish();
    }


    private boolean cekPassword(String password){
        return password.equals(Pref.getRegisteredPass(getBaseContext()));
    }


    private boolean cekUser(String user){
        return user.equals(Pref.getRegisteredUser(getBaseContext()));
    }

    private void Dialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Username atau Password anda salah !");
        alertDialogBuilder
                .setMessage("Silahkan Masukan Kembali username atau password yang benar!")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        LoginActivity.this.finish();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {

            // This is just a normal run
            return;

        } else if (savedVersionCode == DOESNT_EXIST) {

            Pref.setRegisteredUser(getBaseContext(),"Admin");
            Pref.setRegisteredPass(getBaseContext(),"Admin");

        } else if (currentVersionCode > savedVersionCode) {

            // TODO This is an upgrade
        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }

}
