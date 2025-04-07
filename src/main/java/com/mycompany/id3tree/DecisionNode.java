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

public class DecisionNode {
    String attribute; // Misal: Outlook
    String label; // Misal: Yes / No kalau daun
    Map<String, DecisionNode> children = new HashMap<>();

    public DecisionNode(String attribute) {
        this.attribute = attribute;
    }

    public DecisionNode(String attribute, String label) {
        this.attribute = attribute;
        this.label = label;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public void print(String prefix) {
        if (isLeaf()) {
            System.out.println(prefix + "-> " + label + " [LEAF]");
        } else {
            System.out.println(prefix + attribute + " [NODE]");
            for (Map.Entry<String, DecisionNode> entry : children.entrySet()) {
                System.out.print(prefix + "|-- " + attribute + " = " + entry.getKey() + ": ");
                entry.getValue().print(prefix + "|   ");
            }
        }
    }
}
