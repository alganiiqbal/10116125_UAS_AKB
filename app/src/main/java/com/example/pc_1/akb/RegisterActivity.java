package com.example.pc_1.akb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText EdtUser,EdtPassword,EdtPasswordUlang;
    Button BtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EdtUser = findViewById(R.id.edt_username);
        EdtPassword = findViewById(R.id.edt_password);
        EdtPasswordUlang = findViewById(R.id.edt_passwordulang);
        BtnRegister = findViewById(R.id.btn_register);



        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = EdtUser.getText().toString();
                String password = EdtPassword.getText().toString();
                String passwordulang = EdtPasswordUlang.getText().toString();
                if(passwordulang.equals(password)){

                    Pref.setRegisteredUser(getBaseContext(),User);
                    Pref.setRegisteredPass(getBaseContext(),password);

                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                else{

                }
            }
        });

    }
}
