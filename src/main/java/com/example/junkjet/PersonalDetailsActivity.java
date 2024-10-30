package com.example.junkjet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalDetailsActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextName, editTextAddress;
    private RadioGroup radioGroupBusinessType;
    private PersonalDetailsDatabaseHelper personalDetailsDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details); // Reference to the XML layout

        // Initialize the views
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        radioGroupBusinessType = findViewById(R.id.radioGroupBusinessType);
        personalDetailsDatabaseHelper = new PersonalDetailsDatabaseHelper(this); // Initialize database helper

        // Handle the submission logic
        findViewById(R.id.buttonSubmit).setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String name = editTextName.getText().toString().trim();
            String address = editTextAddress.getText().toString().trim();

            // Check which radio button is selected
            int selectedId = radioGroupBusinessType.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedId);
            String businessType = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "";

            // Add personal details to the database
            if (personalDetailsDatabaseHelper.addPersonalDetails(email, name, address, businessType)) {
                Toast.makeText(this, "details saved successfully!", Toast.LENGTH_SHORT).show();

                Intent intent  =new Intent(PersonalDetailsActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Please Register With Existing Email", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
