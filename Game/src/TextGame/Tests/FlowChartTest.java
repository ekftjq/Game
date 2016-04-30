package TextGame.Tests;

import TextGame.FlowChart;
import TextGame.GUI.Screen;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Moon on 4/15/2016.
 */
public class FlowChartTest {

    String ImagePathA = "/resources/images/rand0.jpg";
    String TextPathA = "src/resources/texts/scene0.txt";
    String SoundPathA = "src/resources/sounds/sound0.wav";
    String ImagePathB = "/resources/images/rand1.jpg";
    String TextPathB = "src/resources/texts/scene1.txt";
    String SoundPathB = "src/resources/sounds/sound1.wav";
    FlowChart f = new FlowChart(TextPathA, ImagePathA, SoundPathA);
    Screen s = new Screen(f);

    private BufferedImage loadImg() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource(ImagePathA));
            //img = ImageIO.read(getClass().getResource());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    private File loadSound() {
        File file = null;
        try {
            file = new File(SoundPathA);
            //Thread.sleep(clip.getMicrosecondLength());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return file;
    }

    private BufferedReader loadText() {
        BufferedReader br;
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream(f.getTextPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        br = new BufferedReader(new InputStreamReader(fstream));
        return br;
    }

    @Test
    //Checks if the text is same when loading from the FlowChart and directly loading the text
    public void TestText() throws Exception {
        BufferedReader test = s.getText();
        BufferedReader ori = loadText();
        String line;
        String line2;
        String re1 = null;
        String re2 = "";
        try {
            while((line = test.readLine()) != null) {
                re1 = line;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            while((line2 = ori.readLine()) != null) {
                re2 = line2;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        //System.out.println(re1);
        assertEquals(re1, re2);
    }

    @Test
    //Compares if the image is same when loading from the FlowChart and directly loading the image
    //it takes too long to do the whole loop, so I'm doing only 100 pixels
    public void TestImg() throws Exception {
        BufferedImage testimg = s.getImg();
        boolean same = true;
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(testimg.getRGB(i,j) != loadImg().getRGB(i,j)) {
                    same = false;
                }
            }
        }
        assertEquals(same, true);
    }

    @Test
    //Checks if the music is same when loading from the FlowChart and directly loading the music
    public void TestMusic() throws Exception {
        File file = s.getSound();
        assertEquals(file, loadSound());
    }

}
