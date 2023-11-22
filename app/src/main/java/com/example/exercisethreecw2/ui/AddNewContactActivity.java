package com.example.exercisethreecw2.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.exercisethreecw2.R;
import com.example.exercisethreecw2.database.MyAppDatabase;
import com.example.exercisethreecw2.database.model.ContactModel;
import com.example.exercisethreecw2.database.model.ImageAvatarModel;
import com.example.exercisethreecw2.databinding.ActivityAddNewContactBinding;
import com.example.exercisethreecw2.ui.adapter.ImageAvatarAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class AddNewContactActivity extends AppCompatActivity {
    private ActivityAddNewContactBinding viewBinding;
    private MyAppDatabase database;
    private ImageAvatarAdapter adapter;
    private ArrayList<ImageAvatarModel> avatars = new ArrayList<>(
            Arrays.asList(
                    new ImageAvatarModel(R.drawable.image_avatar_1, false),
                    new ImageAvatarModel(R.drawable.image_avatar_2, false),
                    new ImageAvatarModel(R.drawable.image_avatar_3, false),
                    new ImageAvatarModel(R.drawable.image_avatar_4, false),
                    new ImageAvatarModel(R.drawable.image_avatar_5, false))
    );

    private int imageAvatarSelected = -1;

    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityAddNewContactBinding.inflate(getLayoutInflater());
        database = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "my-app-database")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        setContentView(viewBinding.getRoot());
        initData();
        initView();
    }

    private void initData() {
        adapter = new ImageAvatarAdapter(avatars, imageResource -> {
            imageAvatarSelected = imageResource;
            for (ImageAvatarModel avatarModel : avatars) {
                if (avatarModel.imageResource == imageAvatarSelected) {
                    avatarModel.setIsImageSelected(true);
                } else {
                    avatarModel.setIsImageSelected(false);
                }
            }
            adapter.notifyDataSetChanged();
        });
    }

    private void initView() {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        viewBinding.rvAvatars.setAdapter(adapter);
        viewBinding.rvAvatars.setLayoutManager(layoutManager);

        viewBinding.contactDobTv.setOnClickListener(v -> {
            DatePickerDialog datePicker = new DatePickerDialog(
                    this,
                    (datePicker1, year, month, dayOfMonth) -> {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        viewBinding.contactDobTv.setText(simpleDateFormat.format(calendar.getTime()));
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            datePicker.show();
        });

        viewBinding.btnSubmit.setOnClickListener(v -> {
            String contactName = viewBinding.contactNameEdt.getText().toString();
            String contactDob = viewBinding.contactDobTv.getText().toString();
            String contactEmail = viewBinding.contactNameEdt.getText().toString();
            if (contactName.isEmpty()
                    || contactDob.isEmpty()
                    || contactEmail.isEmpty()
                    || imageAvatarSelected < 0) {
                Toast.makeText(this, "Please fill all field", Toast.LENGTH_SHORT).show();
            } else {
                ContactModel contact = new ContactModel(
                        UUID.randomUUID().toString(),
                        contactName,
                        contactDob,
                        contactEmail,
                        imageAvatarSelected
                );
                database.getContactDao().insert(contact);
                onBackPressed();
            }
        });
    }
}