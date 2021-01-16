package com.company;

public class Report {
    private String id;
    private String name;
    private String publish_from;
    private String publish_to;
    private int length = 0;
    private int articlesAmount = 0;

    public Report(String id, String name, String publish_from, String publish_to, int length) {
        this.id = id;
        this.name = name;
        this.publish_from = publish_from;
        this.publish_to = publish_to;
        this.length = length;
        this.articlesAmount++;
    }

    public void addArticle(int length){
        this.length += length;
        articlesAmount++;
    }

    public void addArticle(int length, int articlesAmount){
        this.length += length;
        this.articlesAmount += articlesAmount;
    }

    public String getId() {
        return id;
    }

    public String getPublish_from() {
        return publish_from;
    }

    public void setPublish_from(String publish_from) {
        this.publish_from = publish_from;
    }

    public String getPublish_to() {
        return publish_to;
    }

    public void setPublish_to(String publish_to) {
        this.publish_to = publish_to;
    }

    public int getLength() {
        return length;
    }

    public int getArticlesAmount() {
        return articlesAmount;
    }

    public int getAverageLength() {
        return length/articlesAmount;
    }

    @Override
    public String toString() {
        return name + ", " + id + ", " + publish_from + ", " + publish_to + ", " + getAverageLength() + " chars\n";
    }
}
