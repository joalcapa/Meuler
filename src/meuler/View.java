package meuler;
import java.awt.Color;
import java.awt.Cursor;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

public class View extends JFrame implements MouseListener{
    private Methods panel1 = new Methods();
    private JLabel next = new JLabel(new ImageIcon(getClass().getResource("assets/next.png")));
    private JLabel prev = new JLabel(new ImageIcon(getClass().getResource("assets/prev.png")));
    private Timer timer_global;
    private int cont_panel2=320;
    private Boolean panel2_activo=false,tipo=false;
    
    public View(){
        super("Meuler");
        add(next);
        add(prev);
        add(panel1);
        panel1.setBounds(0,0,905,740);
        next.setBounds(852,-3,53,68);
        prev.setBounds(852,-3,53,68);
        prev.setVisible(false);
        next.addMouseListener(this);
        next.setCursor(Cursor.getPredefinedCursor(12));
        prev.addMouseListener(this);
        prev.setCursor(Cursor.getPredefinedCursor(12));
        setSize(905,348);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.black);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       if(e.getSource() == next){
          panel2_activo=true;
          next.setVisible(false);
          prev.setVisible(true);
       }
       if(e.getSource() == prev){
          panel2_activo=false;
          cont_panel2=0;
          prev.setVisible(false);
          next.setVisible(true);
       }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
