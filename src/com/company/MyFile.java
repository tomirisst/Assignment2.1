package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

public class MyFile extends Thread {
    private ArrayList<Article> file;
    private String path;
    private int articlesAmount = 0;
    private ArrayList<Report> reports;
    static ArrayList<Report> allReports = new ArrayList<>();

    public MyFile(String path) {
        this.path = path;
        file = new ArrayList<>();
        reports = new ArrayList<>();
    }

    public void run() {
        File csv1 = new File(path);
        try (BufferedReader bufread = Files.newBufferedReader(csv1.toPath(), StandardCharsets.UTF_8)) {
            bufread.readLine();
            String line = bufread.readLine();
            while (line != null) {
                String[] data = line.split(",");
                if(data.length != 6) {
                    line = bufread.readLine();
                    continue;
                }
                file.add(Article.parse(data));
                createReport(Article.parse(data));
                line = bufread.readLine();
            }
            createAllReport();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(path);
            System.out.println(e.getMessage());
            System.out.println(Thread.currentThread());
        }
    }


    private void createReport(Article a) {
        boolean exist = false;
        for (Report r : reports) {
            if (r.getId().compareToIgnoreCase(a.getSource_id()) == 0) {
                exist = true;
                if (r.getPublish_from().compareTo(a.getDate()) > 0) {
                    r.setPublish_from(a.getDate());
                } else if (r.getPublish_to().compareTo(a.getDate()) < 0) {
                    r.setPublish_to(a.getDate());
                }
                r.addArticle(a.getContent().length());
                break;
            }
        }
        if (exist) return;
        reports.add(new Report(a.getSource_id(), a.getSource_name(), a.getDate(), a.getDate(), a.getContent().length()));
    }

    private synchronized void createAllReport() {
        boolean exist;
        for (Report r : reports) {
            exist = false;
            for (Report all : allReports) {
                if (r.getId().compareToIgnoreCase(all.getId()) == 0) {
                    exist = true;
                    if (all.getPublish_from().compareTo(r.getPublish_from()) > 0) {
                        all.setPublish_from(r.getPublish_from());
                    } else if (all.getPublish_to().compareTo(r.getPublish_to()) < 0) {
                        all.setPublish_to(r.getPublish_to());
                    }
                    all.addArticle(r.getLength(), r.getArticlesAmount());
                    break;
                }
            }
            if (exist) continue;
            allReports.add(r);
        }

    }
}
