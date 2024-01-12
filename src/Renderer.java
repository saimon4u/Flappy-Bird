import java.awt.Graphics;
import javax.swing.JPanel;
public class Renderer extends JPanel{
    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        FlappyBird.flappyBird.paint(graphics);
    }
}
