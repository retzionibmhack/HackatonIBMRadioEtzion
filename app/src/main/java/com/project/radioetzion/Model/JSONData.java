package com.project.radioetzion.Model;

import com.google.gson.annotations.SerializedName;

public class JSONData {

    @SerializedName("body")
    private double creationDate;
    private double duration;
    private String filePath;
    private double fileSize;
    private String streamId;
    private String streamName;
    private String type;
    private double vodId;
    private String vodName;

    public String getStreamName() {
        return streamName;
    }

    public JSONData() {
    }

    public JSONData(double creationDate, double duration, String filePath, double fileSize, String streamId, String streamName, String type, double vodId, String vodName) {
        this.creationDate = creationDate;
        this.duration = duration;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.streamId = streamId;
        this.streamName = streamName;
        this.type = type;
        this.vodId = vodId;
        this.vodName = vodName;
    }

    public double getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(double creationDate) {
        this.creationDate = creationDate;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getVodId() {
        return vodId;
    }

    public void setVodId(double vodId) {
        this.vodId = vodId;
    }

    public String getVodName() {
        return vodName;
    }

    public void setVodName(String vodName) {
        this.vodName = vodName;
    }

    @Override
    public String toString() {
        return "JSONData{" +
                "creationDate=" + creationDate +
                ", duration=" + duration +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", streamId='" + streamId + '\'' +
                ", streamName='" + streamName + '\'' +
                ", type='" + type + '\'' +
                ", vodId=" + vodId +
                ", vodName='" + vodName + '\'' +
                '}';
    }
}
