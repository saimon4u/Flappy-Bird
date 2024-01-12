import javax.swing.*;
import java.awt.*;
class BackgroundOfAuthenticationPage extends JPanel{
    private Image backgroundImage;

    public BackgroundOfAuthenticationPage(){
        backgroundImage = new ImageIcon("resource/ok.jpg").getImage();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        AuthenticationPage.authenticationPage.paint(g);
    }
}