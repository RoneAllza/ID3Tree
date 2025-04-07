/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.id3tree;

/**
 *
 * @author RAA
 */
public class Tree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public void print() {
        if (root != null) root.print("", true);
    }
}

