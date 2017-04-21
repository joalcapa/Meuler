package meuler;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaVinci extends JPanel{
    private int arrayData[] = new int [179];
    private int auxData[] = new int [179];
    
    public DaVinci(){
    setBackground(Color.black);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        int accountant = 0, accountantMinux;
        for (int i = 0; i < 179; i++) {
            accountant+=5;
            accountantMinux = accountant - 1;
            g.setColor(new Color(0, 204, 255));
            g.drawLine(accountant, (200-arrayData[i]), accountant, 200);
            g.setColor(Color.white);
            g.drawLine(accountant, ((200-arrayData[i])-4),accountant, (200-arrayData[i]));
            g.setColor(new Color(0, 204, 255));
            g.drawLine(accountantMinux, (200-arrayData[i]), accountantMinux, 200);
            g.setColor(new Color(0, 204, 255));
            g.drawLine(accountantMinux, ((200-arrayData[i])-4), accountantMinux, (200-arrayData[i]));
        }
    }
    
    public void preparar_auxiliar(){
        System.arraycopy(arrayData, 0, auxData, 0, 179);
    }
    
    public int getDataIndex(int position){
        return arrayData[position];
    } 
    
    public void setDataIndex(int position, int data){
        arrayData[position] = data;
    }
    
    public void RestartData(){
        System.arraycopy(auxData, 0, arrayData, 0, 179);
    }
}
