import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


import javax.swing.*;


import javax.imageio.ImageIO;


import java.io.File;
import java.io.IOException;

public class FlappyBird implements ActionListener, MouseListener, KeyListener{
    public static FlappyBird flappyBird;
    private Renderer renderer;
    private Rectangle bird;
    private ColumnOperation columnOperation;
    private CreateTextFile createTextFile;
    private final int WIDTH = 1200, HEIGHT = 900;
    private int score,highScore,yMotion,ticks;
    private double speed = 5;
    private boolean started,gameOver;
    private String username,password,fileName;

    private BufferedImage birdImage,topImage,bottomImage,skyImage;
    private JFrame jFrame;


    public FlappyBird(String user,String pass,String name,int val){
        flappyBird = this;
        fileName = name;
        username = user;
        password = pass;
        highScore = val;
        jFrame = new JFrame();
        Timer timer = new Timer(20,this);
        renderer = new Renderer();
        try{
            birdImage = ImageIO.read(new File("resource/blue_bird.png"));
            topImage = ImageIO.read(new File("resource/tube_top.png"));
            bottomImage = ImageIO.read(new File("resource/tube_bottom.png"));
            skyImage = ImageIO.read(new File("resource/sky.jpg"));
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        jFrame.add(renderer);
        jFrame.setTitle("Flappy Bird");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(380,100,WIDTH,HEIGHT);
        jFrame.addMouseListener(this);
        jFrame.addKeyListener(this);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        bird = new Rectangle(WIDTH/2 - 10,HEIGHT/2 - 10,40,40);
        columnOperation = new ColumnOperation();
        columnOperation.addColumn(true,WIDTH,HEIGHT);
        columnOperation.addColumn(true,WIDTH,HEIGHT);
        columnOperation.addColumn(true,WIDTH,HEIGHT);
        columnOperation.addColumn(true,WIDTH,HEIGHT);
        timer.start();
    }
    public void paint(Graphics graphics){
        graphics.drawImage(skyImage,0,0,WIDTH,HEIGHT,null);
        graphics.drawImage(birdImage,bird.x,bird.y,bird.width,bird.height,null);
        columnOperation.paintColumn(graphics,topImage,bottomImage);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial",1,30));
        graphics.drawString("Score: " + (score/2),10,30);
        String highscoreString = "High Score: " + (highScore);
        int stringWidth = graphics.getFontMetrics().stringWidth(highscoreString);
        graphics.drawString(highscoreString,WIDTH-stringWidth-10,30);
        graphics.setFont(new Font("Arial",1,50));
        graphics.setColor(Color.BLACK);
        if(!started) graphics.drawString("Click to start!",400,HEIGHT/2-50);
        graphics.setColor(Color.RED);
        if(gameOver){
            createTextFile = new CreateTextFile(username,password,fileName,highScore);
            graphics.drawString("Game Over!",400,HEIGHT/2);
            if(score/2 > highScore) highScore = score/2;
            speed = 5;
        }
    }
    private void jump(){
        if(gameOver){
            bird = new Rectangle(WIDTH/2 - 10,HEIGHT/2 - 10,40,40);
            columnOperation.columns.clear();
            yMotion = 0;
            score = 0;
            columnOperation = new ColumnOperation();
            columnOperation.addColumn(true,WIDTH,HEIGHT);
            columnOperation.addColumn(true,WIDTH,HEIGHT);
            columnOperation.addColumn(true,WIDTH,HEIGHT);
            columnOperation.addColumn(true,WIDTH,HEIGHT);
            gameOver = false;
        }
        if(!started) started = true;
        else if(!gameOver){
            if(yMotion > 0) yMotion = 0;
            yMotion -= 10;
        }
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(speed < 10) speed += .0005;
        ticks++;
        if(started){
            for(int i=0; i<columnOperation.columns.size(); i++){
                Rectangle column = columnOperation.columns.get(i);
                column.x -= speed;
            }
            if(ticks %2 == 0 && yMotion < 15) yMotion += 2;
            for(int i=0; i<columnOperation.columns.size(); i++){
                Rectangle column = columnOperation.columns.get(i);
                if(column.x + column.width < 0){
                    columnOperation.columns.remove(column);
                    if(column.y == 0) columnOperation.addColumn(false,WIDTH,HEIGHT);
                }
            }
            bird.y += yMotion;
            for(Rectangle column: columnOperation.columns){
                if(column.y == 0 && bird.x+bird.width/2 > column.x + column.width/2-speed && bird.x + bird.width/2 < column.x + column.width/2 + speed) score += 1;
                if(column.intersects(bird)){
                    gameOver = true;
                    if(bird.x <= column.x) bird.x = column.x - bird.width;
                    else{
                        if(column.y != 0) bird.y = column.y - bird.height;
                        else if(bird.y < column.height) bird.y = column.height;
                    }
                }
            }
            if(bird.y > HEIGHT || bird.y < 0) gameOver = true;
            if(bird.y + yMotion >= HEIGHT){
                bird.y = HEIGHT - bird.height;
                gameOver = true;
            }
        }
        renderer.repaint();
    }
    @Override
    public void keyReleased(KeyEvent keyEvent){
        if(keyEvent.getKeyCode() == keyEvent.VK_SPACE) jump();
        else if(keyEvent.getKeyCode() == keyEvent.VK_ESCAPE){
            jFrame.dispose();
            System.exit(1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent){
        jump();
    }

















    @Override
    public void keyTyped(KeyEvent keyEvent){

    }

    @Override
    public void keyPressed(KeyEvent keyEvent){

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent){

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent){

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent){

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent){

    }
}
