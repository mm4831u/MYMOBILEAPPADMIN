package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty;
    TextView no_data;
    MyDatabaseHelper myDB;
    ArrayList<String> class_id, class_title, class_instructor, class_time, class_dow, class_capacity, class_dur, class_price;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty = findViewById(R.id.empty);
        no_data = findViewById(R.id.no_data);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        class_id = new ArrayList<>();
        class_title = new ArrayList<>();
        class_instructor = new ArrayList<>();
        class_time = new ArrayList<>();
        class_dow = new ArrayList<>();
        class_capacity = new ArrayList<>();
        class_dur = new ArrayList<>();
        class_price = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, class_id, class_title, class_instructor, class_time, class_dow, class_capacity, class_dur, class_price);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            empty.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()){
                class_id.add(cursor.getString(0));
                class_title.add(cursor.getString(1));
                class_instructor.add(cursor.getString(2));
                class_time.add(cursor.getString(3));
                class_dow.add(cursor.getString(4));
                class_capacity.add(cursor.getString(5));
                class_dur.add(cursor.getString(6));
                class_price.add(cursor.getString(7));
            }
            empty.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_all){
            Toast.makeText(this,"delete",Toast.LENGTH_SHORT);
            confrimDialog();

        }
        return super.onOptionsItemSelected(item);
    }
    void confrimDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("delete all?");
        builder.setMessage("are you sure you want to delete all data?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.deleteAllData();
                //recreate();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);

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