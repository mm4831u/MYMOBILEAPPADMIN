package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText title_input, instructor_input, dow_input, time_input, capacity_input, dur_input, price_input;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        instructor_input = findViewById(R.id.instructor_input);
        dow_input= findViewById(R.id.dow_input);
        time_input = findViewById(R.id.time_input);
        capacity_input = findViewById(R.id.capacity_input);
        dur_input = findViewById(R.id.dur_input);
        price_input = findViewById(R.id.price_input);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                        myDB.addBook(title_input.getText().toString().trim(),
                                instructor_input.getText().toString().trim(),
                                dow_input.getText().toString().trim(),
                                Integer.valueOf(time_input.getText().toString().trim()),
                                Integer.valueOf(capacity_input.getText().toString().trim()),
                                Integer.valueOf(dur_input.getText().toString().trim()),
                                Integer.valueOf(price_input.getText().toString().trim()));
            }
        });

    }

}
