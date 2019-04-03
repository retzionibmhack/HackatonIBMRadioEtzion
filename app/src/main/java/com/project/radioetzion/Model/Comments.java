package com.project.radioetzion.Model;

public class Comments {
    String name;
    String commentText;
    String date;

    public Comments() {
    }

    public Comments(String name, String commentText, String date) {
        this.name = name;
        this.commentText = commentText;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "name='" + name + '\'' +
                ", commentText='" + commentText + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
