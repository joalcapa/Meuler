# Meuler
Order algorithm plotter

![image GIF Meuler](https://dl.dropboxusercontent.com/s/7dw1jww94rs0e2h/meuler.gif?dl=0)

Meuler is a Java-based project that allows you to visualize the performance of data sorting algorithms, such as the popular algorithm "Bubble", Quicksort, among others.

### Rendering

Basically Meuler, gives the rendering power to DaVinci, so an array is drawn on the screen.

``` java
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
```

### Prosecution of the data

For algorithm processing, Meuler gives AlgorithmHandler the processing power, is able to process the array, and instruct DaVinci to render the change.

``` java
for (int i = 1; i < 179; i++) {
            aux = daVinci.getDataIndex(i);
            k = i-1;
            while( k >= 0 && aux < daVinci.getDataIndex(k)) {
                daVinci.setDataIndex((k+1), daVinci.getDataIndex(k));
                k--;
                try { Thread.sleep(1); } catch (InterruptedException ex) {
                    Logger.getLogger(AlgorithmHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                daVinci.repaint();    
            }
            daVinci.setDataIndex((k+1), aux);    
            try { Thread.sleep(1); } catch (InterruptedException ex) {
                Logger.getLogger(AlgorithmHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            daVinci.repaint();
        } 
        isFinalize = true;
        thread = null;
```
