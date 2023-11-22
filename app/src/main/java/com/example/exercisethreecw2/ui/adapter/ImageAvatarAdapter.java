package com.example.exercisethreecw2.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.exercisethreecw2.R;
import com.example.exercisethreecw2.database.model.ImageAvatarModel;

import java.util.ArrayList;

public class ImageAvatarAdapter extends RecyclerView.Adapter<ImageAvatarAdapter.ViewHolder> {
    private ArrayList<ImageAvatarModel> avatars = new ArrayList<>();
    private OnImageClickedListener listener;

    public ImageAvatarAdapter(ArrayList<ImageAvatarModel> avatars, OnImageClickedListener listener) {
        this.avatars = avatars;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.avatar_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageAvatarModel avatar = avatars.get(position);
        holder.itemView.setOnClickListener(v -> {
            listener.onClick(avatar.imageResource);
        });
        if (avatar.isImageSelected) {
            holder.imvSelected.setVisibility(View.VISIBLE);
        } else {
            holder.imvSelected.setVisibility(View.INVISIBLE);
        }
        Glide.with(holder.itemView.getContext())
                .load(avatar.imageResource)
                .centerCrop()
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return avatars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatImageView avatar, imvSelected;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.imvAvatar);
            imvSelected = itemView.findViewById(R.id.imvSelected);
        }
    }

    public interface OnImageClickedListener {
        void onClick(int imageResource);
    }
}
