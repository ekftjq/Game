package TextGame;

import TextGame.GUI.Screen;
import TextGame.GUI.ScreenController;
import TextGame.GUI.ScreenView;

public class Main {

    public static void main(String[] args) {
        int numberofpaths = 9;

        String[] ImagePath = new String[numberofpaths];
        String[] TextPath = new String[numberofpaths];
        String[] SoundPath = new String[numberofpaths];

        for(int i = 0; i< ImagePath.length; i++) {
            ImagePath[i] = "/resources/images/rand"+i+".jpg";
        }

        for(int i = 0; i< TextPath.length; i++) {
            TextPath[i] = "src/resources/texts/scene"+i+".txt";
        }

        for(int i = 0; i< SoundPath.length; i++) {
            SoundPath[i] = "src/resources/sounds/sound"+i+".wav";
        }

        /*
        String ImagePathA = "/resources/images/rand0.png";
        String ImagePathB = "/resources/images/rand2.jpg";
        String ImagePathC = "/resources/images/rand3.jpg";
        String TextPathA = "src/resources/texts/scene1.txt";
        String TextPathB = "src/resources/texts/scene2.txt";
        String TextPathC = "src/resources/texts/scene3.txt";
        String SoundPathA = "src/resources/sounds/sound1.wav";
        String SoundPathB = "src/resources/sounds/sound2.wav";
        String SoundPathC = "src/resources/sounds/sound3.wav";
        String SoundPathD = "src/resources/sounds/sound4.wav";
        */

        /*
                           root
                      a          b
                   c      d     e      f
                 g   h    i   k  l  m   n
                         1  2

         */

        FlowChart main = new FlowChart(TextPath[0], ImagePath[0], SoundPath[0]); //root go to door?
        FlowChart mainyes = new FlowChart(TextPath[1], ImagePath[1], SoundPath[1]);
        FlowChart mainno = new FlowChart(TextPath[2], ImagePath[2], SoundPath[2]);
        FlowChart child1yes = new FlowChart(TextPath[2], ImagePath[2], SoundPath[2]);
        FlowChart child1no = new FlowChart(TextPath[3], ImagePath[3], SoundPath[3], true);

        FlowChart child2next = new FlowChart(TextPath[5], ImagePath[5], SoundPath[5]);
        FlowChart child3yes = new FlowChart(TextPath[6], ImagePath[6], SoundPath[6], true);
        FlowChart child3no = new FlowChart(TextPath[7], ImagePath[3], SoundPath[1], true);

        FlowChart child3next = new FlowChart(TextPath[2], ImagePath[2], SoundPath[2]);

        FlowChart childhand = new FlowChart(TextPath[4], ImagePath[4], SoundPath[4]);
        FlowChart childhandno = new FlowChart(TextPath[8], ImagePath[8], SoundPath[8], true);

        main.addChild(mainyes);
        main.addChild(mainno);

        mainyes.addChild(child1yes);
        mainyes.addChild(child1no);

        mainno.addChild(childhand);
        mainno.addChild(childhandno);

        child1no.addChild(child2next);

        child2next.addChild(child3yes);
        child2next.addChild(child3no);

        child3no.addChild(child3next);

        child3next.addChild(childhand);
        child3next.addChild(childhandno);

/*
        f.addChild(TextPath[1], ImagePath[1], SoundPath[1]); //a should I turn back?
        f.addChild(TextPath[2], ImagePath[2], SoundPath[2]); //b look around, put hand into wall?
        f.getChildren().get(0).addChild(TextPath[2], ImagePath[2], SoundPath[0]); //c = b look around, put hand into wall?
        f.getChildren().get(0).addChild(TextPath[3], ImagePath[3], SoundPath[3], true); //d into the room
        f.getChildren().get(0).getChildren().get(1).addChild(TextPath[5], ImagePath[5], SoundPath[5]); //i
        f.getChildren().get(0).getChildren().get(1).getChildren().get(0).addChild(TextPath[6], ImagePath[5], SoundPath[6], true); //1
        f.getChildren().get(0).getChildren().get(1).getChildren().get(0).addChild(TextPath[7], ImagePath[3], SoundPath[1], true); //2

        f.getChildren().get(0).getChildren().get(1).getChildren().get(0).getChildren().get(0).addChild(TextPath[2], ImagePath[2], SoundPath[2]); //back to b

        f.getChildren().get(1).addChild(TextPath[3], ImagePath[3], SoundPath[3], true); //e
        f.getChildren().get(1).addChild(TextPath[3], ImagePath[3], SoundPath[3], true); //f
        f.getChildren().get(1).getChildren().get(0).addChild(TextPath[5], ImagePath[5], SoundPath[5], true); //k
        f.getChildren().get(1).getChildren().get(0).addChild(TextPath[5], ImagePath[5], SoundPath[5], true); //l
*/

        Screen s = new Screen(main);
        //Screen s2 = new Screen(f.getChildren().get(1));
        ScreenView v = new ScreenView(s, main);
        //ScreenView v2 = new ScreenView(s2.getImg(), s2.getText(), s2.getSound());
        ScreenController controller = new ScreenController(s, v);

        controller.addmenubar();
        controller.addMusic();
        controller.addYesButton();
        controller.addNoButton();
        controller.addMouseListner();
        controller.setVisiable();
    }


}
