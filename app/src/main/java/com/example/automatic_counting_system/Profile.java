package com.example.automatic_counting_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private TextView ETName;
    private TextView ETAddress;
    private TextView ETMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setTitle("Profile");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.item_profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.item_payment:
                        startActivity(new Intent(getApplicationContext(),Payment.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.item_profile:
                        return true;
                }
                return false;
            }
        });
            ETName = findViewById(R.id.pole_dlya_name);
            ETAddress = findViewById(R.id.pole_dlya_address);
            ETMail = findViewById(R.id.pole_dlya_email);
            String userId = FirebaseAuth.getInstance().getUid();
            DatabaseReference current_user_db = FirebaseDatabase.getInstance()
                    .getReference("Users").child(userId);
            current_user_db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Users userProfile = snapshot.getValue(Users.class);
                    ETMail.setText(userProfile.getEmail());
                    ETName.setText(userProfile.getName());
                    ETAddress.setText(userProfile.getAddress());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
}