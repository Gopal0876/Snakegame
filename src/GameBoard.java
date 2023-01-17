import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameBoard extends JPanel implements ActionListener {
    public int hight=400;
    public int width=400;
    public int dots=3;
    public int dot_max=1600;
    public int dot_size=10;
    public int x[]=new int[dot_max];
    public int y[]=new int [dot_max];
    public int apple_x;
    public int apple_y;
    Image body;
    Image head;
    Image apple;

    //for snake move variable initilization
    boolean leftDirection=false;
    boolean rightDirection=false;
    boolean upDirection=false;
    boolean downDirection=true;
    int DELAY=200;

    //colision
    private boolean inGame=true;



GameBoard(){
    addKeyListener(new TAdapter());
    setBackground(Color.BLACK);
    loadImages();
    setPreferredSize(new Dimension(width,hight));
    setFocusable(true);

    initGame();

}
//initilize the snake game
    public void initGame(){
    locateApple();
    for(int i=0;i<dots;i++){
        x[i]=50+(i*dot_size);
        y[i]=50;

    }
    //move the snake timer
        Timer timer=new Timer(DELAY,this);
        timer.start();

    }
    //load images from resourse folder
    private void loadImages(){
    ImageIcon bodyIcon=new ImageIcon("src/resource/dot.png");
    body=bodyIcon.getImage();
    ImageIcon headIcon=new ImageIcon("src/resource/head.png");
    head=headIcon.getImage();
    ImageIcon appleIcon=new ImageIcon("src/resource/apple.png");
    apple=appleIcon.getImage();
    }
    @Override
    public void paintComponent(Graphics graphics){
    super.paintComponent(graphics);
    doDrawing(graphics);
    }

    //draw images at their position
    private void doDrawing(Graphics graphics){
    if(inGame){
        graphics.drawImage(apple,apple_x,apple_y,this);
        for(int i=0;i<dots;i++){
            if(i==0) {
                //draw image head

                graphics.drawImage(head,x[0],y[0],this);
            }
            else graphics.drawImage(body,x[i],y[i],this);
        }
    }
    else{
        gameOver(graphics);
    }

    }
    //Make graphys gameover
    private void gameOver(Graphics graphics){
    String msg="Game Over!";
    Font font=new Font("Helvetica",Font.BOLD,14);
    FontMetrics metrics= getFontMetrics(font);
    graphics.setColor(Color.white);
    graphics.setFont(font);
    graphics.drawString(msg,(width-metrics.stringWidth(msg))/2, hight/2);

    }

//snake moving
    @Override
    public void actionPerformed(ActionEvent actionEvent){
    if(inGame) {
        checkCollision();
        checkApple();
        move();
    }
        repaint();
    }

    //randomized the position of apple
    private void locateApple(){
    apple_x=((int)(Math.random()*39))*dot_size;
    apple_y=((int)(Math.random()*39))*dot_size;
    }

    //ltes snake move
    public void move() {
        for (int i = dots - 1; i >= 0; i--) {
            if(leftDirection) {
                if (i == 0) x[0] -= 10;
                else {
                    x[i] = x[i - 1];
                    y[i] = y[i - 1];
                }

            }
            if (rightDirection) {
                if (i == 0) x[0] += 10;
                else {
                    x[i] = x[i - 1];
                    y[i] = y[i - 1];
                }
            }


        if (upDirection) {
            if (i == 0) y[0] -= 10;
            else {
                y[i] = y[i - 1];
                x[i] = x[i - 1];
            }

        }
        if (downDirection) {
            if (i == 0) y[0] += 10;
            else {
                y[i] = y[i - 1];
                x[i] = x[i - 1];
            }

        }
    }

    }
    //make snake eat food
    private void checkApple(){
    if(x[0]==apple_x&&y [0]==apple_y){
        dots++;
        locateApple();
        }
    }

    //check colition
    private void checkCollision(){
    //collision left border
    if(x[0]<0){
        inGame=false;
    }
    //collision right border
        if(x[0]>=width){
            inGame=false;
        }
        //collision up border
        if(y[0]<0){
            inGame=false;
        }
        //collision down border
        if(y[0]>=hight){
            inGame=false;
        }
    for(int i=4;i<dots;i++){
        if(x[0]==x[i]&& y[0]==y[i]){
            inGame=false;
        }
    }
    }

    //control the snake by botton
    private class TAdapter extends KeyAdapter{
    @Override
        public void keyPressed (KeyEvent keyEvent){
        int key=keyEvent.getKeyCode();
        if(key ==keyEvent.VK_LEFT && (!rightDirection)){
            leftDirection=true;
            upDirection=false;
            downDirection=false;

        }
        if(key ==keyEvent.VK_RIGHT && (!leftDirection)){
            rightDirection=true;
            upDirection=false;
            downDirection=false;

        }
        if(key ==keyEvent.VK_UP && (!downDirection)){
            leftDirection=false;
            upDirection=true;
            rightDirection=false;

        }
        if(key ==keyEvent.VK_DOWN && (!upDirection)){
            leftDirection=false;
            downDirection=true;
            rightDirection=false;

        }

    }
    }
    }

