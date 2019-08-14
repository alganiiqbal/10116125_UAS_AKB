package com.example.pc_1.akb;
/**
 12/08/2019
 10116125
 Al Ghani Iqbal Dzulfiqar
 AKB -3
 **/

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class LoginActivity extends AppCompatActivity {
    EditText Edtuser,Edtpass;
    String user,pass;
    Button Btnlogin,Btnregister;
    TextView register_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkFirstRun();

        Edtuser = findViewById(R.id.Edtuser);
        Edtpass = findViewById(R.id.Edtpass);
        Btnlogin = findViewById(R.id.btn_login);
        register_link = findViewById(R.id.link_signup);
        Btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             user = Edtuser.getText().toString();
             pass = Edtpass.getText().toString();

                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

                // TODO: Implement your own authentication logic here.

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                if(user.isEmpty()||user.equals(""))
                                {
                                    Toast.makeText(LoginActivity.this, "Field User Masih Kosong!", Toast.LENGTH_SHORT).show();
                                    Edtuser.requestFocus();
                                }
                                else if(pass.isEmpty()||pass.equals("")){
                                    Toast.makeText(LoginActivity.this, "Field Password Masih Kosong!", Toast.LENGTH_SHORT).show();
                                    Edtpass.requestFocus();
                                }


                                else if(user.equals("Admin")&&pass.equals("Admin")){
                                    login();

                                }
                                else if(!cekUser(user)||!cekPassword(pass)){
                                    Toast.makeText(LoginActivity.this, "Username atau Password SALAH!!", Toast.LENGTH_SHORT).show();
                                    Edtuser.setText("");
                                    Edtpass.setText("");
                                    Edtuser.requestFocus();
                                }
                                else{
                                    login();
                                }
                                // onLoginFailed();
                                progressDialog.dismiss();
                            }
                        }, 2200);



            }
        });



        register_link.setOnClickListener(new View.OnClickListener() {
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



    private void checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;
        int currentVersionCode = BuildConfig.VERSION_CODE;
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);
        if (currentVersionCode == savedVersionCode) {
            return;

        } else if (savedVersionCode == DOESNT_EXIST) {

            Pref.setRegisteredUser(getBaseContext(),"Admin");
            Pref.setRegisteredPass(getBaseContext(),"Admin");

        } else if (currentVersionCode > savedVersionCode) {


        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }

}
