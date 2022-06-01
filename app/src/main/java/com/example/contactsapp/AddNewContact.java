package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.contactsapp.databinding.ActivityAddNewContactBinding;

import java.util.ArrayList;

public class AddNewContact extends AppCompatActivity {

    private ActivityAddNewContactBinding binding;
    private Intent intent;
    private AppDatabase appDatabase;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewContactBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        appDatabase = AppDatabase.getInstance(this);
        contactDao = appDatabase.contactDao();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AddNewContact.this, MainActivity.class);
                intent.putExtra("Contact", addContact());
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }


    public ArrayList<String> addContact()
    {
        ArrayList<String> x = new ArrayList<String>();
        String name = binding.textName.getText().toString();
        String phone = binding.textPhone.getText().toString();
        String email = binding.textEmail.getText().toString();
        x.add(name);
        x.add(phone);
        x.add(email);
        if(!phone.equals("") && !name.equals(""))
            return x;
        else
            return null;
    }

}