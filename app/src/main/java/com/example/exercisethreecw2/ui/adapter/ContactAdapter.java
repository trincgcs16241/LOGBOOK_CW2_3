package com.example.exercisethreecw2.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.exercisethreecw2.R;
import com.example.exercisethreecw2.database.model.ContactModel;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private ArrayList<ContactModel> contacts = new ArrayList<>();

    public ContactAdapter(ArrayList<ContactModel> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel contact = contacts.get(position);
        Glide.with(holder.itemView.getContext())
                .load(contact.contactAvatar)
                .centerCrop()
                .into(holder.avatar);

        holder.contactName.setText(contact.contactName);
        holder.contactDob.setText(contact.contactDob);
        holder.contactEmail.setText(contact.contactEmail);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatImageView avatar;
        public AppCompatTextView contactName, contactDob, contactEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.imvAvatar);
            contactName = itemView.findViewById(R.id.tvContactName);
            contactDob = itemView.findViewById(R.id.tvContactDob);
            contactEmail = itemView.findViewById(R.id.tvContactEmail);
        }
    }
}
