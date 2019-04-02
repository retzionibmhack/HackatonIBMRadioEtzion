package com.project.radioetzion.Model;

import com.google.gson.annotations.SerializedName;

public class JSONData {

    @SerializedName("body")
    private String streamName;
    private String vodName;
    private String streamId;
    private String filePath;
    private String vodId;
    private String type;
    private String creationDate;
    private int duration;
    private int fileSize;

    public String getStreamName() {
        return streamName;
    }

    public JSONData(String streamName, String vodName, String streamId, String filePath, String vodId, String type, String creationDate, int duration, int fileSize) {
        this.streamName = streamName;
        this.vodName = vodName;
        this.streamId = streamId;
        this.filePath = filePath;
        this.vodId = vodId;
        this.type = type;
        this.creationDate = creationDate;
        this.duration = duration;
        this.fileSize = fileSize;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public String getVodName() {
        return vodName;
    }

    public void setVodName(String vodName) {
        this.vodName = vodName;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getVodId() {
        return vodId;
    }

    public void setVodId(String vodId) {
        this.vodId = vodId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
}
