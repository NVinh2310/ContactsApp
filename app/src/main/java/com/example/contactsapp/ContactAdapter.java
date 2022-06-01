package com.example.contactsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    private ArrayList<Contact> contacts;

    public ContactAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.tvView.setText(contacts.get(position).getName());
    }
    @Override
    public int getItemCount() {
        return contacts.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvView;
        public ImageView ivAvatar;
        public ViewHolder(View view){
            super(view);
            tvView = view.findViewById(R.id.name);
            ivAvatar = view.findViewById(R.id.avatar);

        }

    }
}
