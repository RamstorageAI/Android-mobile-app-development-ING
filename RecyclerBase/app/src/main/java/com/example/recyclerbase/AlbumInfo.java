package com.example.recyclerbase;

public class AlbumInfo {

    private String title;
    private String info;
    private int imageId;

    public AlbumInfo(String title, String info, int imageId) {
        this.title = title;
        this.info = info;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public int getImageId() {
        return imageId;
    }
}
