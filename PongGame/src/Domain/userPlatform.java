
package Domain;

import static GUI.gameFrame.WIDTH;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import static GUI.gameFrame.HEIGHT;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

/**
 *
 * @author reych
 */
public class userPlatform {
   private double posX;
   private double posY;
   private int width;
   private int height;
   private int speed;
   private int score;
   private boolean throwBall;
   

   private final BufferedImage userPlatform;
    
    public userPlatform() throws IOException{
        this.height=25;
        this.width=100;
        this.posX= WIDTH/2-width/2;     
        this.posY=HEIGHT-75;
        this.speed=15;
        this.score=0;
        this.throwBall=false;
        
        this.userPlatform=ImageIO.read(getClass().getResourceAsStream("/assets/userPlatform.jpg")); 
       
    }
 
    //setters and getters methods
    public double getPosX() {
        return posX;
    }
   
    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }  

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isThrowBall() {
        return throwBall;
    }

    public void setThrowBall(boolean throwBall) {
        this.throwBall = throwBall;
    }
    
    
    //Paint component
    public void paintComponent(Graphics g){
        g.drawImage(userPlatform, (int)posX, (int)posY, width, height, null);
        g.setFont(new Font("System",Font.ITALIC, 15));
        g.setColor(Color.red);
        g.drawString("Score:"+score, 0, 75);
    }
    
    //user movements
    public void userRigthMove(){
        if (posX+width+5<WIDTH) {
          this.posX=posX+speed;  
        }
    }
        
    public void userLeftMove(){
        if (posX>=5) {
            this.posX=posX-speed;
        }
    }
      
     /*
     *collition between the ball and the user platform
     */
    public boolean userCollition() throws IOException {
        Rectangle userRect = new Rectangle((int) this.posX, (int) this.posY, this.width, this.height);
        Rectangle ballRect = new Rectangle(
                (int) gameFuntions.getInstance().getBallGame().getPosX(),
                (int) gameFuntions.getInstance().getBallGame().getPosY(),
                gameFuntions.getInstance().getBallGame().getWidth(),
                gameFuntions.getInstance().getBallGame().getHeight());

        if (userRect.intersects(ballRect)) {
            this.setThrowBall(true);
            gameFuntions.getInstance().getComputerPlatform().setThrowBall(false);
            return true;
        }
        return false;
    }
    
    
    
}
