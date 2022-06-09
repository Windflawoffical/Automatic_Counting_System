package com.example.automatic_counting_system;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class registration extends AppCompatActivity implements View.OnClickListener{

    private EditText ETemail;
    private EditText ETpassword;
    private EditText ETName;
    private EditText ETAddress;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("Registration");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out

                }

            }
        };
        ETemail = (EditText) findViewById(R.id.et_email);
        ETpassword = (EditText) findViewById(R.id.et_password);
        ETName = (EditText) findViewById(R.id.et_name);
        ETAddress = (EditText) findViewById(R.id.et_address);

        findViewById(R.id.btn_registration).setOnClickListener(this);

        final EditText editText = (EditText) findViewById(R.id.et_password);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.showHideBtn);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) editText.setTransformationMethod(null);
                else editText.setTransformationMethod(new PasswordTransformationMethod());
                editText.setSelection(editText.length());
            }
        });
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_registration)
        {
            registration_1(ETemail.getText().toString(),
                    ETpassword.getText().toString(),
                    ETName.getText().toString(),
                    ETAddress.getText().toString());
        }
    }
    public void registration_1(String email , String password,String name, String address) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = FirebaseAuth.getInstance().getUid();
                    DatabaseReference current_user_db = FirebaseDatabase.getInstance()
                            .getReference("Users").child(userId);
                    Users user = new Users(email,password,name,address);

                    current_user_db.setValue(user);

                    Toast.makeText(registration.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(registration.this, "Регистрация провалена", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}