package com.example.hammadhanif.finance_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private TextView welcome;
    private Button bt_Budget;
    private Button salary;
    private Button savings;
    private String uidR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        uidR = getIntent().getExtras().getString("UID");

        databaseReference = FirebaseDatabase.getInstance().getReference().child(uidR);

        // retriving from database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String fullN = dataSnapshot.child("name").getValue().toString();

                welcome.setText("Welcome " + fullN +",");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bt_Budget = findViewById(R.id.cBudget);
        salary = findViewById(R.id.salary);
        welcome = findViewById(R.id.welcome_text);
        savings = findViewById(R.id.savings);


        bt_Budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cIntent = new Intent(MenuActivity.this, CreateActivity.class);
                startActivity(cIntent);
            }
        });

        salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sIntent = new Intent(MenuActivity.this, SalaryActivity.class);
                sIntent.putExtra("UID", uidR); // Passes the Users Authentication to a class
                startActivity(sIntent);
            }
        });

        savings.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               Intent salIntent = new Intent(MenuActivity.this, SavingsTrackerActivity.class);
               startActivity(salIntent);
           }
        });
    }

    private void Logout(){
        mAuth.signOut();
        finish();
        startActivity(new Intent(MenuActivity.this, MainActivity.class));
    }

    public void onClickBillReminder(View view) {
        Intent billRemIntent = new Intent(this, BillReminderActivity.class);
        startActivity(billRemIntent);
    }

    public void onClickCreateBudget(View view) {
        Intent createIntent = new Intent(this, CreateActivity.class);
        startActivity(createIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
           case R.id.profileMenu:
               startActivity(new Intent(MenuActivity.this, ProfileActivity.class));
               // Toast.makeText(this,"Profile Settings selected", Toast.LENGTH_LONG).show();
       }
       switch (item.getItemId()){
           case R.id.exit:
               Logout();
               //Toast.makeText(this,"Logout selected", Toast.LENGTH_LONG).show();
       }
        return super.onOptionsItemSelected(item);
    }
}
