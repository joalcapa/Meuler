package meuler;
import java.awt.Color;
import java.awt.Cursor;
import java.io.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Methods extends JPanel implements MouseListener{
    private JLabel logo = new JLabel(new ImageIcon(getClass().getResource("assets/logo.png")));
    private JLabel restart = new JLabel(new ImageIcon(getClass().getResource("assets/Bm.png")));
    private JLabel methods[] = new JLabel[7];
    private JPanel topBar = new JPanel();
    private JPanel bottomBar = new JPanel();
    private JLabel title = new JLabel("Meuler by Jose Caceres");
    private JPanel barMethods = new JPanel();
    private DaVinci daVinci = new DaVinci();
    private AlgorithmHandler algorithmHandler = new AlgorithmHandler(daVinci);
    private boolean isBarMethodsActive = false, restartActive = false, AlgorithmFinalize = false, restartDaVinci = false;
    private int countBar = -510, countRestartDaVinci = 61, countMethods = 6;
    private Timer timer;
    
    public Methods(){
        setLayout(null);
        setSize(905,740);
        setBackground(Color.white);
        initTopBar();
        initMethods();
        initArray();
        add(topBar);
        add(bottomBar);
        add(daVinci);
        initTimer();
        setVisible(true);
    }
    
    public void initTopBar() { 
        topBar.setSize(800, 61);
        bottomBar.setSize(800,61);
        topBar.setLayout(null);
        bottomBar.setLayout(null);
        topBar.setBackground(new Color(18, 18, 18));
        bottomBar.setBackground(new Color(18, 18, 18));
        topBar.add(logo);
        bottomBar.add(title);
        bottomBar.add(restart);
        topBar.setBounds(0, 0, 905, 61);
        bottomBar.setBounds(0,261,905,61);
        logo.setBounds(0, 0, 89, 61);
        logo.setCursor(Cursor.getPredefinedCursor(12));
        restart.setCursor(Cursor.getPredefinedCursor(0));
        logo.addMouseListener(this);
        title.setBounds(40,0,150,30);
        restart.setBounds(835,0,65,61);
        restart.addMouseListener(this);
        title.setForeground(Color.white);
    }
    
    public void initMethods() {
    int width=0;
    topBar.add(barMethods);    
    barMethods.setBounds(-511,0,600,61);
    barMethods.setLayout(null);
    barMethods.setBackground(new Color(18, 18, 18));
    for (int i = 0; i < countMethods; i++) {
        if(i>0) width+=59;
        methods[i]=new JLabel(new ImageIcon(getClass().getResource("assets/metodo.png")));
        methods[i].addMouseListener(this);
        barMethods.add(methods[i]);
        methods[i].setBounds(width, 0, 60, 61);
        methods[i].setCursor(Cursor.getPredefinedCursor(12));
    }
    }
    
    public void initArray() {
        boolean running = true;
        daVinci.setDataIndex(0, (int) (Math.random() * 179));
        for (int i=1; i < 179; i++) {
            daVinci.setDataIndex(i, (int) (Math.random() * 179));
            for (int j = 0; j < i && running; j++)
                if (daVinci.getDataIndex(i) == daVinci.getDataIndex(j)) {
                       i--;
                       running = false;
                }
            running = true;
        }
        daVinci.prepareAuxiliaryData();
        daVinci.setBounds(0, 61, 905, 200);
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!restartActive){
            for (int i = 0; i < countMethods; i++)
            if(e.getSource() == methods[i]) {
                algorithmHandler.setTypeAlgorithm(i);
                algorithmHandler.run();
                restartActive = true;
            } 
        }
        if(e.getSource() == restart && AlgorithmFinalize) {
            restartActive=false;
            restartDaVinci=true;
            restart.setCursor(Cursor.getPredefinedCursor(0));
            restart.setIcon(new ImageIcon(getClass().getResource("assets/Bm.png")));
            AlgorithmFinalize=false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == logo || e.getSource() == methods[0] || e.getSource() == methods[1] ||
           e.getSource() == methods[2] || e.getSource() == methods[3] || e.getSource() == methods[4] ||
           e.getSource() == methods[5] || e.getSource() == methods[6]) {
            isBarMethodsActive = true;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == logo || e.getSource() == methods[0] || e.getSource() == methods[1] ||
           e.getSource() == methods[2] || e.getSource() == methods[3] || e.getSource() == methods[4] ||
           e.getSource() == methods[5] || e.getSource() == methods[6]) {
            isBarMethodsActive = false;
        }  
    }
    
    public void initTimer() {
        timer = new Timer (1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(countBar < 90 && isBarMethodsActive) {
                    countBar+=3;
                    if(countBar<86) barMethods.setBounds(countBar,0,600,61);
                } 
                if(countBar > -510 && !isBarMethodsActive) {
                    if(countBar==-511) countBar=-510;
                    else {
                        countBar-=2;
                        barMethods.setBounds(countBar,0,600,61);
                    }
                }
                if(algorithmHandler.inspectFinalization()) {
                    AlgorithmFinalize=true;
                    algorithmHandler.setFinalize(false);
                    restart.setIcon(new ImageIcon(getClass().getResource("assets/Bm1.png")));
                    restart.setCursor(Cursor.getPredefinedCursor(12));
                }
                if(restartDaVinci && countRestartDaVinci < 460) {
                    countRestartDaVinci+=2;
                    daVinci.setBounds(0, countRestartDaVinci, 905, 200);
                    if(countRestartDaVinci == 461) {
                        daVinci.RestartData();
                        daVinci.repaint();  
                        restartDaVinci = false;
                    }
                }
                if(!restartDaVinci && countRestartDaVinci > 62){
                    if(countRestartDaVinci == 65) {
                        countRestartDaVinci=61;
                        daVinci.setBounds(0, countRestartDaVinci, 905, 200);
                    } else {
                        countRestartDaVinci-=4;
                        daVinci.setBounds(0, countRestartDaVinci, 905, 200);
                    }
                }
            }
        });     
        timer.start();
    }
}
