package TextGame.Tests;

import TextGame.FlowChart;
import TextGame.GUI.Screen;
import TextGame.Load;
import TextGame.Save;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Moon on 4/22/2016.
 */
public class SaveLoadTest {

    String ImagePathB = "/resources/images/rand1.jpg";
    String TextPathB = "src/resources/texts/scene1.txt";
    String SoundPathB = "src/resources/sounds/sound1.wav";
    FlowChart f = new FlowChart(TextPathB, ImagePathB, SoundPathB);
    String test = "test";

    @Test
    public void TestCreateSaveFile() throws Exception { //test if Save class printed "Saved" which indicates success
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Save sav = new Save(f, test);
        assertEquals(outContent.toString(), "Saved");
    }

    @Test
    public void TestLoadSaveFile() throws Exception { //test if Load class printed "Load Complete"
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Load load = new Load(test);
        assertEquals(outContent.toString(), "Load Complete");
    }

    @Test
    public void TestSaveLoadFile() throws Exception { //save before changing path, load and check image path
        //System.out.println(f.getImagePath());
        Save sav = new Save(f, test);

        String ImagePathC = "/resources/images/rand2.jpg";
        String TextPathC = "src/resources/texts/scene2.txt";
        String SoundPathC = "src/resources/sounds/sound2.wav";
        FlowChart f = new FlowChart(TextPathC, ImagePathC, SoundPathC);
        Screen s = new Screen(f);

        assertEquals(f.getImagePath(), "/resources/images/rand2.jpg"); //checking if the image is from pathC

        Load load = new Load(test);
        f = load.getFlow();
        //System.out.println(f.getImagePath());
        assertEquals(f.getImagePath(), "/resources/images/rand1.jpg"); //checking if the image is back to the one in pathB
    }

}
