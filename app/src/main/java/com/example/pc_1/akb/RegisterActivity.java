package com.example.pc_1.akb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText EdtNama,EdtUser,EdtPassword,EdtPasswordUlang;
    Button BtnRegister;
    String User,password,passwordulang;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EdtNama = findViewById(R.id.EdtName);
        EdtUser = findViewById(R.id.EdtUser);
        EdtPassword = findViewById(R.id.EdtPassword);
        EdtPasswordUlang = findViewById(R.id.EdtPasswordUlang);
        register = findViewById(R.id.link_login);
        BtnRegister = findViewById(R.id.btn_signup);



        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    User = EdtUser.getText().toString();
                    password = EdtPassword.getText().toString();
                    passwordulang = EdtPasswordUlang.getText().toString();
                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Daftar Akun...");
                progressDialog.show();

                // TODO: Implement your own authentication logic here.

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {

                if(User.isEmpty()||EdtUser.equals("")){
                    Toast.makeText(RegisterActivity.this, "Field User Masih Kosong!", Toast.LENGTH_SHORT).show();
                    EdtUser.requestFocus();
                }
                else if(EdtNama.equals("")){
                    Toast.makeText(RegisterActivity.this, "Field Nama Masih Kosong!", Toast.LENGTH_SHORT).show();
                    EdtNama.requestFocus();
                }

                else if(password.isEmpty()||EdtPassword.equals("")){
                    Toast.makeText(RegisterActivity.this, "Field password Masih Kosong!", Toast.LENGTH_SHORT).show();
                    EdtPassword.requestFocus();
                }

                else if(EdtPasswordUlang.equals("")||passwordulang.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Field password Ulang Masih Kosong!", Toast.LENGTH_SHORT).show();
                    EdtPasswordUlang.requestFocus();
                }

                else if(!passwordulang.equals(password)){
                    Toast.makeText(RegisterActivity.this, "Password dan Password Ulang Tidak Sama!", Toast.LENGTH_SHORT).show();
                    EdtPasswordUlang.requestFocus();
                }
                else if(cekUser(User)){
                    Toast.makeText(RegisterActivity.this, "Akun dengan Username "+User+" Sudah Terdaftar!", Toast.LENGTH_SHORT).show();
                    EdtUser.setText("");
                    EdtUser.requestFocus();
                }

                else{

                    Pref.setRegisteredUser(getBaseContext(),User);
                    Pref.setRegisteredPass(getBaseContext(),password);

                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);

                }
                                // onLoginFailed();
                                progressDialog.dismiss();
                            }
                        }, 2200);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }

    private boolean cekUser(String user){
        return user.equals(Pref.getRegisteredUser(getBaseContext()));
    }
}
