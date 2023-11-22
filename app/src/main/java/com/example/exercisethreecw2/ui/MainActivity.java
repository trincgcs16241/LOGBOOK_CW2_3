package com.example.exercisethreecw2.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.room.Room;

import com.example.exercisethreecw2.database.MyAppDatabase;
import com.example.exercisethreecw2.database.model.ContactModel;
import com.example.exercisethreecw2.databinding.ActivityMainBinding;
import com.example.exercisethreecw2.ui.adapter.ContactAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding viewBinding;
    private MyAppDatabase database;
    private ContactAdapter adapter;
    private ArrayList<ContactModel> contacts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        database = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "my-app-database")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        setContentView(viewBinding.getRoot());
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        viewBinding.btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddNewContactActivity.class);
            startActivity(intent);
        });

        adapter = new ContactAdapter(contacts);
        viewBinding.rvContacts.setLayoutManager(new GridLayoutManager(this, 2));
        viewBinding.rvContacts.setAdapter(adapter);
    }

    private void initData() {
        contacts.clear();
        contacts.addAll(database.getContactDao().getAllListContact());
        adapter.notifyDataSetChanged();
    }
}