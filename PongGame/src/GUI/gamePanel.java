package GUI;

import Domain.gameFuntions;
import java.awt.Graphics;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author reych
 */
public class gamePanel extends JPanel implements Runnable{
    private long espera;
    private final long time;
    private final long FPS;
    private Thread thread;
        
    public gamePanel(){
            this.FPS=60;
            this.time=1000/FPS;
            this.setSize(800, 600); 
                        
    }
    
    @Override
    public void addNotify(){
        super.addNotify();
        if (this.thread==null) {
            this.thread=new Thread(this);
            this.thread.start();
        }
    }//addNotify

    
    @Override
    public void run() {
        //data saving
        long start;//comienzo
        long elapse;//lapso

        while(true){
         repaint();

           //retorna el valor actual del running como tiempo
            start = System.nanoTime();

            //captura el tiempo en hacer todas las operaciones
            elapse = System.nanoTime() - start;

            //de nano a milisegundos
            this.espera = this.time - elapse / 1000;

            gameFuntions();

            if (this.espera < 0) {
                this.espera = 5;
            }

        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            Domain.gameFuntions.getInstance().drawingAllElements(g);
        } catch (IOException ex) {
            Logger.getLogger(gamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gameFuntions() {
        try {
            gameFuntions.getInstance().funtions();
        } catch (IOException ex) {
            Logger.getLogger(gamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
