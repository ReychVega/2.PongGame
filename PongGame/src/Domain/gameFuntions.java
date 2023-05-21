
package Domain;

import static GUI.gameFrame.WIDTH;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import static GUI.gameFrame.HEIGHT;
import javax.swing.JOptionPane;

/**
 *
 * @author reych
 */
public class gameFuntions {

    private static gameFuntions singletonGame;
    private userPlatform userPlatform;
    private computerPlatform computerPlatform;
    private ball ballGame;

    private gameFuntions() throws IOException {
       startGame();
    }

    public userPlatform getUserPlatform() {
        return userPlatform;
    }

    public computerPlatform getComputerPlatform() {
        return computerPlatform;
    }

    public ball getBallGame() {
        return ballGame;
    }

    public void setBallGame(ball ballGame) {
        this.ballGame = ballGame;
    }
    
    public static gameFuntions getInstance() throws IOException {
        if (singletonGame == null) {
            singletonGame = new gameFuntions();
        }
        return singletonGame;
    }

    public void funtions() throws IOException{
        movements();
    }
    
    public void drawingAllElements(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        userPlatform.paintComponent(g);
        computerPlatform.paintComponent(g);
        ballGame.paintComponent(g);

    }

    private void movements() throws IOException {

        //collitions cases
        this.userPlatform.userCollition();
        this.computerPlatform.computerCollition();

        //case 1. Computer platform
        if (this.computerPlatform.isThrowBall()) {
            this.ballGame.downMove();
        }
        //case 2. User platform
        if (this.userPlatform.isThrowBall()) {
            this.ballGame.upMove();
        }

        //case 3. wallCollitions
        this.ballGame.wallCollitions();

        //case 4. Overflow 
        overflow();

        //case 5. computer PLatform
        this.computerPlatform.computerMove();

    }

    //overflow
    private void overflow() throws IOException {
       
    //message of game over
        if ((ballGame.getPosY() 
                + ballGame.getHeight()) > HEIGHT) {
            int dialogButton = JOptionPane.showConfirmDialog(null,
                    "Do you want to continue?", "Game Over",
                    JOptionPane.YES_NO_OPTION);
            if (dialogButton == JOptionPane.YES_OPTION) {
                startGame();
            } else if (dialogButton == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        }

    //increase the speed
        if (ballGame.getPosY() < 0) {
            
            double speed = computerPlatform.getSpeed();
            computerPlatform.setSpeed(speed * 2);
            userPlatform.setScore(userPlatform.getScore()+1);
            newGame();
        }
    }
    
    private void startGame() throws IOException {
        this.userPlatform = new userPlatform();
        this.computerPlatform = new computerPlatform();
        this.ballGame = new ball();
    }

   /*
    *
    */
    private void newGame() throws IOException {
        // starting again
        //computer platform
        this.computerPlatform.setThrowBall(false);
        this.computerPlatform.setPosX(WIDTH / 2- this.computerPlatform.getWidth() / 2);
        this.computerPlatform.setPosY(25);

        //userPlatform
        this.userPlatform.setThrowBall(false);
        this.userPlatform.setPosX(WIDTH / 2 - this.userPlatform.getWidth() / 2);
        this.userPlatform.setPosY(HEIGHT - 75);

        //ball
        this.ballGame.setPosX(WIDTH / 2 - this.ballGame.getWidth() / 2);
        this.ballGame.setPosY(48);
    }

}
