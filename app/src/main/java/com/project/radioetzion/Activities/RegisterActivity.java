package com.project.radioetzion.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.project.radioetzion.MainActivity;
import com.project.radioetzion.R;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRRegister;
    private EditText txtRUser, txtREmail, txtRPass, txtRPassAgain;
    private FirebaseAuth auth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resigter);
        setPointer();
    }

    private void setPointer() {
        btnRRegister = findViewById(R.id.btnRRegister);
        txtREmail = findViewById(R.id.txtREmail);
        txtRPass = findViewById(R.id.txtRPass);
        txtRPassAgain = findViewById(R.id.txtPassAgain);
        txtRUser = findViewById(R.id.txtRUser);
        progressBar = findViewById(R.id.registerPB);
        progressBar.setVisibility(View.INVISIBLE);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        }

        btnRRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = txtREmail.getText().toString();
                final String password = txtRPass.getText().toString();

                try {
                    if (password.length() > 0 && email.length() > 0) {
                        progressBar.setVisibility(View.VISIBLE);
                        auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                                            Log.v("error", task.getResult().toString());
                                        } else {
                                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                });
                    } else {
                        Toast.makeText(RegisterActivity.this, "Fill All Fields", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }


    public  void masho(String str, inter inter) {


        inter.shimon(str);

    }

    public interface inter {
        void shimon(String str);
    }

}
