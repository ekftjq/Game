package TextGame;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Moon on 4/22/2016.
 * Save the current position in the flowchart
 * I have created a separate file and constructor for test cases in case they mess up with the original file.
 */
public class Save implements Serializable {
    FlowChart flow;
    ArrayList<String> review;

    public Save(FlowChart f, ArrayList<String> r) {
        this.flow = f;
        this.review = r;

        try {
            FileOutputStream saveFile = new FileOutputStream("SaveFlowchart.sav");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);

            save.writeObject(flow);
            save.writeObject(review);

            System.out.println("Saved");

            //close the file
            save.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public Save(FlowChart f, String test) { //save for test cases
        this.flow = f;

        try {
            FileOutputStream saveFile = new FileOutputStream("SaveFlowchart" + test + ".sav");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);

            save.writeObject(flow);

            System.out.print("Saved");

            //close the file
            save.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
