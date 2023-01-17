import javax.swing.*;

public class SnakeGame extends JFrame {
   GameBoard gameBoard;
    SnakeGame(){
        gameBoard=new GameBoard();
        add(gameBoard);

        setVisible(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SnakeGame snakegame=new SnakeGame();
    }
}
