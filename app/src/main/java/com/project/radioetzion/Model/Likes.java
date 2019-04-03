package com.project.radioetzion.Model;

public class Likes {
    String name;
    Boolean isLike;

    public Likes() {
    }

    public Likes(String name, Boolean isLike) {
        this.name = name;
        this.isLike = isLike;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "name='" + name + '\'' +
                ", isLike=" + isLike +
                '}';
    }
}
