import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
public class ColumnOperation {
    private Random random = new Random();
    public ArrayList<Rectangle> columns;
    public ColumnOperation(){
        columns = new ArrayList<Rectangle>();
    }
    public void addColumn(boolean start,int WIDTH,int HEIGHT){
        int space = 180;
        int width = 100;
        int height = 100 + random.nextInt(400);
        if(start){
            columns.add(new Rectangle(WIDTH+width+columns.size()*300,HEIGHT-height,width,height));
            columns.add(new Rectangle(WIDTH+width+(columns.size()-1)*300,0,width,HEIGHT - height - space));
        }
        else{
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height, width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
        }
    }
    public void paintColumn(Graphics graphics, BufferedImage topImage,BufferedImage bottomImage){
        for(Rectangle column: columns){
            if(column.y == 0) graphics.drawImage(topImage,column.x,column.y,column.width,column.height,null);
            else graphics.drawImage(bottomImage,column.x,column.y,column.width,column.height,null);
        }
    }
}
