package com.example.exercisethreecw2.database.model;

public class ImageAvatarModel {
    public Integer imageResource;
    public boolean isImageSelected;

    public ImageAvatarModel(Integer imageResource, boolean isImageSelected) {
        this.imageResource = imageResource;
        this.isImageSelected = isImageSelected;
    }

    public Integer getImageResource() {
        return imageResource;
    }

    public void setImageResource(Integer imageResource) {
        this.imageResource = imageResource;
    }

    public boolean isImageSelected() {
        return isImageSelected;
    }

    public void setIsImageSelected(boolean isImageSelected) {
        this.isImageSelected = isImageSelected;
    }
}
