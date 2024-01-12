import javax.swing.*;
import java.awt.*;
class BackgroundOfMainFrame extends JPanel{
    private Image backgroundImage;

    public BackgroundOfMainFrame(){
        backgroundImage = new ImageIcon("resource/ok.jpg").getImage();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        MenuFrame.menuFrame.paint(g);
    }
}