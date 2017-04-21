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
    private JLabel reiniciar = new JLabel(new ImageIcon(getClass().getResource("assets/Bm.png")));
    private JLabel metodos[] = new JLabel[7];
    private JPanel barra_superior = new JPanel();
    private JPanel barra_inferior = new JPanel();
    private JLabel nom = new JLabel("Meuler by Jose Caceres");
    private JPanel cinta_metodos = new JPanel();
    private DaVinci daVinci = new DaVinci(); 
    private AlgorithmHandler algorithmHandler = new AlgorithmHandler();
    private boolean cinta_metodos_activa=false, reiniciar_activo=false, master_terminado=false, reiniciar_grafica=false;
    private int cont_cinta=-510, cont_reiniciar=-61, aux=0,k=0,tipo_cont=-1, cont_reiniciar_grafica=61;
    private Timer timer_global;
    
    public Methods(){
        setLayout(null);
        setSize(905,740);
        setBackground(Color.white);
        preparar_barra_superior();
        preparar_cinta_metodos();
        preparar_vector();
        add(barra_superior);
        add(barra_inferior);
        add(daVinci);
        instalar_timer();
        setVisible(true);
    }
    
    public void preparar_barra_superior(){ 
        barra_superior.setSize(800, 61);
        barra_inferior.setSize(800,61);
        barra_superior.setLayout(null);
        barra_inferior.setLayout(null);
        barra_superior.setBackground(new Color(18, 18, 18));
        barra_inferior.setBackground(new Color(18, 18, 18));
        barra_superior.add(logo);
        barra_inferior.add(nom);
        barra_inferior.add(reiniciar);
        barra_superior.setBounds(0, 0, 905, 61);
        barra_inferior.setBounds(0,261,905,61);
        logo.setBounds(0, 0, 89, 61);
        logo.setCursor(Cursor.getPredefinedCursor(12));
        reiniciar.setCursor(Cursor.getPredefinedCursor(0));
        logo.addMouseListener(this);
        nom.setBounds(40,0,150,30);
        reiniciar.setBounds(835,0,65,61);
        reiniciar.addMouseListener(this);
        nom.setForeground(Color.white);
    }
    
    public void preparar_cinta_metodos(){
    int ancho_m=0;
    barra_superior.add(cinta_metodos);    
    cinta_metodos.setBounds(-511,0,600,61);
    cinta_metodos.setLayout(null);
    cinta_metodos.setBackground(new Color(18, 18, 18));
    for (int i = 0; i < 7; i++) {
        
        if(i>0)
            ancho_m=ancho_m+59;
        
        metodos[i]=new JLabel(new ImageIcon(getClass().getResource("assets/metodo.png")));
        metodos[i].addMouseListener(this);
        cinta_metodos.add(metodos[i]);
        metodos[i].setBounds(0+ancho_m, 0, 60, 61);
        metodos[i].setCursor(Cursor.getPredefinedCursor(12));
        }
    }
    
    public void preparar_vector(){
        daVinci.setDataIndex(0, (int) (Math.random() * 179));
        for (int i=1; i < 179; i++) {
               daVinci.setDataIndex(i, (int) (Math.random() * 179));
               for (int j = 0; j < i; j++) {
                   if (daVinci.getDataIndex(i) == daVinci.getDataIndex(j)){
                       i--;
                       break;
                   }
                }
        }
        daVinci.preparar_auxiliar();
        daVinci.setBounds(0, 61, 905, 200);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!reiniciar_activo){
    if(e.getSource()==metodos[0]){
        algorithmHandler.setOBJ(daVinci, 0);
        algorithmHandler.run();
        reiniciar_activo=true;
    } 
    if(e.getSource()==metodos[1]){
        algorithmHandler.setOBJ(daVinci, 1);
        algorithmHandler.run();
        reiniciar_activo=true;
    }
    if(e.getSource()==metodos[2]){
        algorithmHandler.setOBJ(daVinci, 2);
        algorithmHandler.run(); 
        reiniciar_activo=true;
    }
    if(e.getSource()==metodos[3]){
         algorithmHandler.setOBJ(daVinci, 3);
         algorithmHandler.run();
         reiniciar_activo=true;
    }
    if(e.getSource()==metodos[4]){
         algorithmHandler.setOBJ(daVinci, 4);
         algorithmHandler.run();
         reiniciar_activo=true;
    }
    if(e.getSource()==metodos[5]){
         algorithmHandler.setOBJ(daVinci, 5);
         algorithmHandler.run();
         reiniciar_activo=true;
    }
    if(e.getSource()==metodos[6]){
        
    }
        }
       
    if(e.getSource()==reiniciar && master_terminado){
        reiniciar_activo=false;
        reiniciar_grafica=true;
        reiniciar.setCursor(Cursor.getPredefinedCursor(0));
        reiniciar.setIcon(new ImageIcon(getClass().getResource("assets/Bm.png")));
        master_terminado=false;
    }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==logo || e.getSource()==metodos[0] || e.getSource()==metodos[1] || e.getSource()==metodos[2] || e.getSource()==metodos[3] || e.getSource()==metodos[4] || e.getSource()==metodos[5] || e.getSource()==metodos[6]){
    cinta_metodos_activa=true;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    if(e.getSource()==logo || e.getSource()==metodos[0] || e.getSource()==metodos[1] || e.getSource()==metodos[2] || e.getSource()==metodos[3] || e.getSource()==metodos[4] || e.getSource()==metodos[5] || e.getSource()==metodos[6]){
    cinta_metodos_activa=false;
        }  
    }
    
    public void instalar_timer(){
        
          timer_global = new Timer (1, new ActionListener (){
                    @Override
                  public void actionPerformed(ActionEvent e){
                      if(cont_cinta<90 && cinta_metodos_activa){
                          cont_cinta=cont_cinta+3;
                          if(cont_cinta<86){
                          cinta_metodos.setBounds(cont_cinta,0,600,61);
                          }
                      } 
                      if(cont_cinta>(-510) && !cinta_metodos_activa){
                          if(cont_cinta==-511){
                          cont_cinta=-510;
                          }else{
                          cont_cinta=cont_cinta-2;
                          cinta_metodos.setBounds(cont_cinta,0,600,61);
                          }
                      }
                      
                      if(algorithmHandler.inspeccionar_termino()==true){
                      master_terminado=true;
                      algorithmHandler.sin_terminar();
                      reiniciar.setIcon(new ImageIcon(getClass().getResource("assets/Bm1.png")));
                      reiniciar.setCursor(Cursor.getPredefinedCursor(12));
                      }
                      if(reiniciar_grafica && cont_reiniciar_grafica<460){
                          cont_reiniciar_grafica=cont_reiniciar_grafica+2;
                          daVinci.setBounds(0, cont_reiniciar_grafica, 905, 200);
                          
                          if(cont_reiniciar_grafica==461){
                                 daVinci.RestartData();
                                 daVinci.repaint();  
                                 reiniciar_grafica=false;
                          }
                      }
                      if(!reiniciar_grafica && cont_reiniciar_grafica>62){
                          if(cont_reiniciar_grafica==65){
                              cont_reiniciar_grafica=61;
                              daVinci.setBounds(0, cont_reiniciar_grafica, 905, 200);
                          }else{
                              cont_reiniciar_grafica=cont_reiniciar_grafica-4;
                              daVinci.setBounds(0, cont_reiniciar_grafica, 905, 200);
                          }
                      }
                    }
                });
       
             
         
            timer_global.start();
    }
}
