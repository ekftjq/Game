package TextGame.GUI;
import TextGame.FlowChart;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Moon on 4/8/2016.
 * Creating a GUI with MVC Pattern
 * This is the Model
 */
public class Screen {
    //a screen has a image and a text
    FlowChart f;
    BufferedImage img;
    BufferedReader text;
    File sound;

    public Screen(FlowChart f){
        this.f = f;
        img = null;
        try {
            img = ImageIO.read(getClass().getResource(f.getImagePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream(f.getTextPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        text = new BufferedReader(new InputStreamReader(fstream));

        try {
            sound = new File(f.getMusicPath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getImg() {
        return this.img;
    }

    public BufferedReader getText() {
        return this.text;
    }

    public File getSound() {
        return sound;
    }

    public void setText(BufferedReader t) {
        this.text = t;
    }

    public void setImg(BufferedImage i) {
        this.img = i;
    }

    public void setSound(File sound) {
        this.sound = sound;
    }
}
