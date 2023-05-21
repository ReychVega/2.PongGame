package Domain;

import static GUI.gameFrame.HEIGHT;
import static GUI.gameFrame.WIDTH;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author reych
 */
public class computerPlatform {
   private double posX;
    private double posY;
    private int width;
    private int height;
    private boolean throwBall;
    private double speed;

    private final BufferedImage computerPlatform;

    public computerPlatform() throws IOException {
        this.height = 25;
        this.width = 100;
        this.posX = WIDTH / 2 - width / 2;
        this.posY = 25;
        this.throwBall = false;
        this.speed = 0.000005;

        this.computerPlatform = ImageIO.read(getClass().getResource("/assets/computerPlatform.jpg"));
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
        
    public boolean isThrowBall() {
        return throwBall;
    }

    public void setThrowBall(boolean throwBall) {
        this.throwBall = throwBall;
    }

    public void paintComponent(Graphics g) {
        g.drawImage(computerPlatform, (int) posX, (int) posY, width, height, null);
    }

    private void computerRigthMove() {
        if ((posX + width + 5 < WIDTH) && (posY<HEIGHT/10) ) {
            this.posX = posX + speed;
        }
    }

    private void computerLeftMove() throws IOException, IOException, IOException {
        if ((posX >= 5) && (posY<HEIGHT/10)) {
            this.posX = posX - speed;
        }
    }

    public void computerMove() throws IOException {
        if (gameFuntions.getInstance().getBallGame().getPosX() > this.posX) {
            computerRigthMove();
        }
        if (gameFuntions.getInstance().getBallGame().getPosX() <= this.posX) {
            computerLeftMove();
        }
    }

    /*
     *collition between the ball and the user platform
     */
    public boolean computerCollition() throws IOException {
        Rectangle computerRect = new Rectangle((int) this.posX, (int) this.posY, this.width, this.height);
        Rectangle ballRect = new Rectangle(
                (int) gameFuntions.getInstance().getBallGame().getPosX(),
                (int) gameFuntions.getInstance().getBallGame().getPosY(),
                gameFuntions.getInstance().getBallGame().getWidth(),
                gameFuntions.getInstance().getComputerPlatform().getHeight());

        if (computerRect.intersects(ballRect)) {
            setThrowBall(true);
            gameFuntions.getInstance().getUserPlatform().setThrowBall(false);
            return true;
        }
        return false;
    }
    
}
