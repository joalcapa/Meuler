package meuler;
import java.applet.*;
import java.awt.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author JoseCaceres
 */
public class AlgorithmHandler extends Applet implements Runnable{
private int aux, k, typeAlgorithm;
private Thread hilo = null;
private DaVinci daVinci;
private Boolean termino=false;

    public AlgorithmHandler(){
        aux = 0; k = 0; typeAlgorithm = -1;
      
    }
    
    public void setOBJ(DaVinci daVinci, int typeAlgorithm){
        this.daVinci = daVinci;
        this.typeAlgorithm = typeAlgorithm;
    }
    
    public Boolean inspeccionar_termino(){
        if(termino){
        return true;
        }else{
        return false;
        }
    }
    
    public void sin_terminar(){
        termino=false;
    }
    
    @Override
    public void run() {
        
  if (hilo == null)
       {
          hilo = new Thread(this, "Reloj");
          hilo.start();
       }
        
    Thread hiloActual = Thread.currentThread();
       while (hilo == hiloActual)
       {   
           if(typeAlgorithm==0){
         for (int i = 1; i < 179; i++) {
             
                aux=daVinci.getDataIndex(i);
                k=i-1;
                while(k>=0 && aux<daVinci.getDataIndex(k)){
                    daVinci.setDataIndex((k+1), daVinci.getDataIndex(k));
                    k--;
       
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AlgorithmHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                     daVinci.repaint();
                    
                }
                daVinci.setDataIndex((k+1), aux);
                
                
            try {
               Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(AlgorithmHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            daVinci.repaint();
            } 
         termino=true;
         hilo=null;
       }
           
           if(typeAlgorithm==1){
               System.out.println("JAJAJA");
              int ls=0, li=0, pm=0;
            for (int i = 1; i < 179; i++) {
                li=0;
                ls=i-1;
                aux=daVinci.getDataIndex(i);
                while(li<=ls)
                {
                    pm=(int)((ls+li)/2);
                    if(aux<daVinci.getDataIndex(pm))
                        ls=pm-1;
                    else
                        li=pm+1;
                }
                for(int j=i; j>li; j--)
                    daVinci.setDataIndex(j, daVinci.getDataIndex(j-1));
                daVinci.setDataIndex(li, aux);
                daVinci.repaint();
                  try {
                      Thread.sleep(1);
                  } catch (InterruptedException ex) {
                      Logger.getLogger(AlgorithmHandler.class.getName()).log(Level.SEVERE, null, ex);
                  }
            }
            termino=true;
            hilo=null;
           }
           
           if (typeAlgorithm==2){
        Boolean sw=true;
        aux=0;
        while(sw){
            sw=false;
            for (int i = 0; i < 179; i++){
                for (int j = i+1; j < 179; j++) {
                if(daVinci.getDataIndex(i)>daVinci.getDataIndex(j)){
                    sw=true;
                    aux=daVinci.getDataIndex(i);
                    daVinci.setDataIndex(i, daVinci.getDataIndex(j));
                    daVinci.setDataIndex((j),aux);
                    daVinci.repaint();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AlgorithmHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            }
            
        }
        termino=true;
        hilo=null;
        }
        
    if(typeAlgorithm==3){
    int li=0, ls=178, pos=178-1;
    while(li<ls){
        for(int j=ls; j>li; j--)
            if(daVinci.getDataIndex(j)<daVinci.getDataIndex(j-1)){
                aux= daVinci.getDataIndex(j);
                daVinci.setDataIndex(j,daVinci.getDataIndex(j-1));
                daVinci.setDataIndex(j-1,aux);
                daVinci.repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(AlgorithmHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
                pos=j;
            }
        li=pos;
        for(int j=li; j<ls; j++)
            if(daVinci.getDataIndex(j)>daVinci.getDataIndex(j+1)){
                aux=daVinci.getDataIndex(j);
                daVinci.setDataIndex(j, daVinci.getDataIndex(j+1));
                daVinci.setDataIndex(j+1,aux);
                daVinci.repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(AlgorithmHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
                pos=j;
            }
        ls=pos;
    }
    termino=true;
    hilo=null;
    }
    
    if(typeAlgorithm==4){
     int may=0, pos=0;
        for (int i = 179-1; i >0 ; i--) {
            may=daVinci.getDataIndex(0);
            pos=0;
            for(int j=1; j<=i; j++)
                if(daVinci.getDataIndex(j)>may){
                    may=daVinci.getDataIndex(j);
                    pos=j;
                }
            daVinci.setDataIndex(pos, daVinci.getDataIndex(i));
            daVinci.setDataIndex(i, may);
            daVinci.repaint();
         try {
             Thread.sleep(1);
         } catch (InterruptedException ex) {
             Logger.getLogger(AlgorithmHandler.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
        termino=true;
        hilo=null;
        break;
    }
    
    if(typeAlgorithm==5){
    int  sec=0;
    sec = (int)(179/2);
    while(sec>0){
    for(int i=sec; i<179; i++){
        aux=daVinci.getDataIndex(i);
        k=i-sec;
        while(k>=0 && aux<daVinci.getDataIndex(k)){
            daVinci.setDataIndex(k+sec, daVinci.getDataIndex(k));
            k-=sec;
            daVinci.repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(AlgorithmHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        daVinci.setDataIndex(k+sec, aux);
        daVinci.repaint();
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(AlgorithmHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    sec=(int)(sec/2);
    }
    termino=true;
    hilo=null;
    }
     
    } 
       
    }
}
