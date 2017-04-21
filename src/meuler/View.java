package meuler;
import java.awt.Color;
import java.awt.Cursor;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

public class View extends JFrame{
    private Methods methods = new Methods();
    
    public View(){
        super("Meuler");
        methods.setBounds(0,0,905,740);
        setSize(905,348);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.black);
        add(methods);
        setVisible(true);
    }
}
