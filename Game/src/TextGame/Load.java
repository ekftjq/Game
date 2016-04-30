package TextGame;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Moon on 4/22/2016.
 * Loads the saved position
 * I have created a separate constructor for test cases to load the right test file.
 */
public class Load implements Serializable {
    public FlowChart flow;
    public ArrayList<String> review;

    public Load() {
        try {
            FileInputStream saveFile = new FileInputStream("SaveFlowchart.sav"); //open the savefile
            ObjectInputStream save = new ObjectInputStream(saveFile);

            flow = (FlowChart) save.readObject();
            review = (ArrayList<String>) save.readObject();

            save.close();

            System.out.println("Load Complete");
            System.out.println(flow.getImagePath().toString());

        } catch(Exception exc){
            exc.printStackTrace();
        }
    }

    public Load(String test) { //load for testcases
        try {
            FileInputStream saveFile = new FileInputStream("SaveFlowchart" + test + ".sav"); //open the savefile
            ObjectInputStream save = new ObjectInputStream(saveFile);

            flow = (FlowChart) save.readObject();

            save.close();

            System.out.print("Load Complete");
            //System.out.println(flow.getImagePath().toString());

        } catch(Exception exc){
            exc.printStackTrace();
        }
    }

    public FlowChart getFlow() {
        return flow;
    }
}
