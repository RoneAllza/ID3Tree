/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.id3tree;

/**
 *
 * @author RAA
 */
import java.io.*;
import java.util.*;

public class CSVReader {
    public static List<List<String>> readCSV(String filename) {
        List<List<String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                data.add(Arrays.asList(tokens));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}


