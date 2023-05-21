package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 *
 * @author reych
 */
public class gameFrame implements KeyListener{
   //Attributes 
   public static int HEIGHT;
   public static int WIDTH;
    
   private JFrame gameFrame;
   private GUI.gamePanel gamePanel;
 
   //Constructor
   public gameFrame(){
       HEIGHT=600;
       WIDTH=800;
       
       this.gamePanel= new gamePanel();
       uiDefalultSettings();
       frameSettings(); 
       
                                           
   }
    
   private void uiDefalultSettings(){
     UIDefaults myDefaults=UIManager.getDefaults();
  
     myDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(
            new Color(255, 255, 255)));
            
     myDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(
            new Color(31, 97, 141 )));
   
     myDefaults.put("activeCaptionBorder", new javax.swing.plaf.ColorUIResource(
            new Color(31, 97, 141 )));
           
     JFrame.setDefaultLookAndFeelDecorated(true); 

   }

   private void frameSettings(){

        this.gameFrame = new JFrame();
        gameFrame.setSize(gamePanel.getWidth(), gamePanel.getHeight());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(gameFrame);

        gameFrame.setFont(new Font("System", Font.PLAIN, 14));
        gameFrame.setTitle("Pong Game.");
        gameFrame.add(gamePanel);
        gameFrame.setResizable(false);
        gameFrame.addKeyListener(this);
    }
   

    public void launchGame() {
        this.gameFrame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
            try {
                Domain.gameFuntions.getInstance().getUserPlatform().userRigthMove();
            } catch (IOException ex) {
                Logger.getLogger(gameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT) {
            try {
                Domain.gameFuntions.getInstance().getUserPlatform().userLeftMove();
            } catch (IOException ex) {
                Logger.getLogger(gameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}