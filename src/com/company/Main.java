package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        File[] csv1 = new File("/Users/tomirissayat/Desktop/4&5/APJava/csv").listFiles();
        ArrayList<MyFile> arr = new ArrayList<>();
        for (File f: csv1){
            arr.add(new MyFile(f.getPath()));
        }
        for (MyFile a: arr){
            a.start();
        }
        for (MyFile a: arr){
            a.join();
        }

        File report = new File("/Users/tomirissayat/Desktop/4&5/APJava/report/finalReport1.csv");
        if(report.createNewFile()) {
            PrintWriter writer = new PrintWriter(report);
            writer.write("name, id, published_from, published_to, average length \n");
            for (Report r : MyFile.allReports) {
                writer.write(r.toString());
            }
            writer.flush();
            writer.close();
        }
    }
}




