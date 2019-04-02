package com.project.radioetzion.Model;

public class StreamItems {

    private int imgStream;
    private String txtStream;
    private int duration;
    private String createdTime;
    private String filePath;

    public StreamItems(int imgStream, String txtStream, int duration, String createdTime, String filePath) {
        this.imgStream = imgStream;
        this.txtStream = txtStream;
        this.duration = duration;
        this.createdTime = createdTime;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getImgStream() {
        return imgStream;
    }

    public String getTxtStream() {
        return txtStream;
    }

    public int getDuration() {
        return duration;
    }

    public String getCreatedTime() {
        return createdTime;
    }
}
