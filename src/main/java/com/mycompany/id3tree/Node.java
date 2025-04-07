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

public class Node {
    private String data;
    private NodeType type;
    private Node parent;
    private List<Node> children = new ArrayList<>();

    public Node(String data, NodeType type) {
        this.data = data;
        this.type = type;
    }

    public void addChild(Node child) {
        child.parent = this;
        children.add(child);
    }

    public void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + data + " [" + type + "]");
        for (int i = 0; i < children.size() - 1; i++) {
            children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
        }
        if (children.size() > 0) {
            children.get(children.size() - 1).print(prefix + (isTail ? "    " : "│   "), true);
        }
    }
}

