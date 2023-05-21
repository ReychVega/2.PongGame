package Domain;

import static GUI.gameFrame.WIDTH;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import static GUI.gameFrame.HEIGHT;

public class ball {

    private int movementIndicator;
    private double posX;
    private double posY;
    private int width;
    private int height;
    private final double speed;

    private final BufferedImage ball;
//    private final ImageIcon icon;
    
    public ball() throws IOException {
        this.movementIndicator = 0;
        this.width = 30;
        this.height = 30;
        this.posX = WIDTH / 2 - width / 2;
        this.posY = 48;
        this.speed = 0.00005;

        this.ball = ImageIO.read(this.getClass().getResource("/assets/ball.png"));
        
        
    }

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

    public void paintComponent(Graphics g) {
        g.drawImage(ball, (int) posX, (int) posY, width, height, null);
    }

   
    /*
     *Move's ball
     */
    private void rightMove() {
        posX = posX + speed;
    }

    public void upMove() {
        if (posY > 0) {
            posY = posY - speed / 2;
        }
    }

    private void leftMove() {
        this.posX = posX - speed;
    }

    public void downMove() {
        if (posY < HEIGHT - height) {
            posY = posY + speed / 2;
        }
    }   
  
//collitions
    public void wallCollitions() {
        switch (this.movementIndicator) {
            case 0 -> leftMove();
            case 1 -> rightMove();
            case 2 -> leftMove();
        }

        if (rightWallCollition() == true) {
            this.movementIndicator = 2;
        }
        if (leftWallCollition() == true) {
            this.movementIndicator = 1;
        }

    }

    //border collitions: RightWallCollition  
    private boolean rightWallCollition() {
        //posX>width
        
        return posX + width > WIDTH - 10;
    }

    //border collitions: leftWallCollition
    private boolean leftWallCollition() {
        //posX+width<=WIDTH-10
        
        return posX <= 5;
    }
    
}


 