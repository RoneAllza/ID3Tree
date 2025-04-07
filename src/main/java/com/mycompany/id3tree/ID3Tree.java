/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.id3tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RAA
 */
public class ID3Tree {
    public static void main(String[] args) {
        List<List<String>> data = CSVReader.readCSV("study.csv");

        List<String> attributes = new ArrayList<>(data.get(0));
        attributes.remove(attributes.size() - 1); // remove label

        DecisionNode root = ID3.buildTree(data, attributes);
        System.out.println("=== ID3 Decision Tree ===");
        root.print("");
    }
}
