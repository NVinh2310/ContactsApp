package com.example.contactsapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;

import com.example.contactsapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<Contact> contactList;
    private ContactAdapter contactAdapter;

    private AppDatabase appDatabase;
    private ContactDao contactDao;
    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK)
            {
                Intent intent = result.getData();
                ArrayList<String> x = intent.getStringArrayListExtra("Contact");

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        contactDao.insertAll(new Contact(0,x.get(0),x.get(1),x.get(2)));
                        contactList = (ArrayList<Contact>) contactDao.getAllContact();
                        binding.rvContacts.setAdapter(new ContactAdapter(contactList));
                    }
                });
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        appDatabase = AppDatabase.getInstance(this);
        contactDao = appDatabase.contactDao();

        contactList = new ArrayList<Contact>();
        contactAdapter = new ContactAdapter(contactList);
        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
//                Contact a =(new Contact(0,"A","0909686868","b@gmail.com"));
//                Contact b =(new Contact(0,"Nguyen Van AB1","0909686868","b@gmail.com"));
//                contactDao.deleteAll();
//                contactDao.insertAll(a,b);
                contactList = (ArrayList<Contact>) contactDao.getAllContact();
                binding.rvContacts.setAdapter(new ContactAdapter(contactList));
            }
        });

        binding.edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.rvContacts.setAdapter(new ContactAdapter(search(binding.edSearch.getText().toString())));
            }
        });

        binding.floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddNewContact.class);
                mActivityResultLauncher.launch(intent);
            }
        });

    }
    public ArrayList<Contact> search(String name){
        ArrayList<Contact> list = new ArrayList<Contact>();
        for(int i =0; i<contactList.size(); i++)
        {
            if(contactList.get(i).getName().contains(name)){
                list.add(contactList.get(i));
            }
        }
        return list;
    }
}