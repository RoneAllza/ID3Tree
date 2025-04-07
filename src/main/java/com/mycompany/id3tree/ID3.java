/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.id3tree;

/**
 *
 * @author RAA
 */
import java.util.*;

public class ID3 {

    public static DecisionNode buildTree(List<List<String>> data, List<String> attributes) {
        List<String> labels = getColumn(data, data.get(0).size() - 1);

        if (allSame(labels)) {
            return new DecisionNode(null, labels.get(0));
        }

        if (attributes.isEmpty()) {
            return new DecisionNode(null, majority(labels));
        }

        int bestAttrIndex = bestAttribute(data, attributes);
        String bestAttr = attributes.get(bestAttrIndex);

        DecisionNode root = new DecisionNode(bestAttr);

        Set<String> values = new HashSet<>(getColumn(data, bestAttrIndex));
        for (String value : values) {
            List<List<String>> subset = filterData(data, bestAttrIndex, value);
            if (subset.isEmpty()) {
                root.children.put(value, new DecisionNode(null, majority(labels)));
            } else {
                List<String> newAttrs = new ArrayList<>(attributes);
                newAttrs.remove(bestAttrIndex);

                List<List<String>> reducedData = removeColumn(subset, bestAttrIndex);
                root.children.put(value, buildTree(reducedData, newAttrs));
            }
        }

        return root;
    }

    private static List<List<String>> filterData(List<List<String>> data, int attrIndex, String value) {
        List<List<String>> subset = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).get(attrIndex).equals(value)) {
                subset.add(data.get(i));
            }
        }
        return subset;
    }

    private static List<List<String>> removeColumn(List<List<String>> data, int index) {
        List<List<String>> reduced = new ArrayList<>();
        for (List<String> row : data) {
            List<String> newRow = new ArrayList<>(row);
            newRow.remove(index);
            reduced.add(newRow);
        }
        return reduced;
    }

    private static boolean allSame(List<String> labels) {
        Set<String> unique = new HashSet<>(labels);
        return unique.size() == 1;
    }

    private static String majority(List<String> labels) {
        Map<String, Integer> freq = new HashMap<>();
        for (String label : labels) {
            freq.put(label, freq.getOrDefault(label, 0) + 1);
        }
        return Collections.max(freq.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private static int bestAttribute(List<List<String>> data, List<String> attributes) {
        double baseEntropy = entropy(getColumn(data, data.get(0).size() - 1));
        double bestGain = -1;
        int bestIndex = -1;

        for (int i = 0; i < attributes.size(); i++) {
            double gain = baseEntropy - conditionalEntropy(data, i);
            if (gain > bestGain) {
                bestGain = gain;
                bestIndex = i;
            }
        }

        return bestIndex;
    }

    private static double entropy(List<String> values) {
        Map<String, Integer> freq = new HashMap<>();
        for (String v : values) {
            freq.put(v, freq.getOrDefault(v, 0) + 1);
        }

        double result = 0.0;
        int total = values.size();
        for (int count : freq.values()) {
            double p = (double) count / total;
            result -= p * (Math.log(p) / Math.log(2));
        }

        return result;
    }

    private static double conditionalEntropy(List<List<String>> data, int attrIndex) {
        Map<String, List<String>> subsets = new HashMap<>();
        for (int i = 1; i < data.size(); i++) {
            List<String> row = data.get(i);
            String key = row.get(attrIndex);
            String label = row.get(row.size() - 1);
            subsets.computeIfAbsent(key, k -> new ArrayList<>()).add(label);
        }

        double total = data.size() - 1;
        double result = 0.0;

        for (List<String> subset : subsets.values()) {
            double weight = subset.size() / total;
            result += weight * entropy(subset);
        }

        return result;
    }

    private static List<String> getColumn(List<List<String>> data, int index) {
        List<String> col = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            col.add(data.get(i).get(index));
        }
        return col;
    }
}

