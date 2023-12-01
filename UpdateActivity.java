package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText title_input, instructor_input, dow_input, time_input, capacity_input, dur_input, price_input;
    Button update_button, delete_button;
    String id, title, instructor, dow, time, capacity, dur, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        instructor_input = findViewById(R.id.instructor_input2);
        dow_input= findViewById(R.id.dow_input2);
        time_input = findViewById(R.id.time_input2);
        capacity_input = findViewById(R.id.capacity_input2);
        dur_input = findViewById(R.id.dur_input2);
        price_input = findViewById(R.id.price_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        getAndSetIntentData();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(id,
                        title_input.getText().toString().trim(),
                        instructor_input.getText().toString().trim(),
                        dow_input.getText().toString().trim(),
                        time_input.getText().toString().trim(),
                        capacity_input.getText().toString().trim(),
                        dur_input.getText().toString().trim(),
                        price_input.getText().toString().trim());

            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confrimDialog();

            }
        });



    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("instructor")
                && getIntent().hasExtra("dow") && getIntent().hasExtra("time") && getIntent().hasExtra("capacity")
                && getIntent().hasExtra("dur") && getIntent().hasExtra("price")){

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            instructor = getIntent().getStringExtra("instructor");
            dow = getIntent().getStringExtra("dow");
            time = getIntent().getStringExtra("time");
            capacity = getIntent().getStringExtra("capacity");
            dur = getIntent().getStringExtra("dur");
            price = getIntent().getStringExtra("price");

            title_input.setText(title);
            instructor_input.setText(instructor);
            dow_input.setText(dow);
            time_input.setText(time);
            capacity_input.setText(capacity);
            dur_input.setText(dur);
            price_input.setText(price);

        }else{
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }
    }
    void confrimDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("delete "+ title + "?");
        builder.setMessage("are you sure you want to delete"+ title + "?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}