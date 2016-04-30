package TextGame.GUI;

import TextGame.FlowChart;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

/**
 * Created by Moon on 4/8/2016.
 * This is the View
 */
public class ScreenView extends JPanel {
    JFrame frame;
    JTextArea textbox;
    Screen s;
    FlowChart flow;
    JLabel image;
    Panel bottempanel;
    JPanel mainPanel;

    public ScreenView(Screen s, FlowChart flow) {
        this.s = s;
        this.flow = flow;
        frame = new JFrame("Can you Escape?");
        frame.setBackground(BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setBackground(BLACK);

        frame.setSize(800, 800);
    }

    public void addImage() {
        image = new JLabel(new ImageIcon(s.getImg()));
        mainPanel.add(image, BorderLayout.CENTER);
    }

    public void addText() {
        //text section of the GUI
        textbox = new JTextArea(10, 45);
        textbox.setEditable(false);
        Font font = new Font("Verdana", Font.BOLD, 12);
        textbox.setFont(font);
        textbox.setForeground(WHITE);
        textbox.setBackground(BLACK);

        bottempanel = new Panel();
        bottempanel.add(textbox);
        bottempanel.setBackground(BLACK);

        mainPanel.add(bottempanel, BorderLayout.SOUTH);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextArea getTextbox() {
        return textbox;
    }

    public JLabel getImage() {
        return image;
    }

    public Panel getBottempanel() {
        return bottempanel;
    }

    public void setImage(JLabel image) {
        this.image = image;
    }

    public void setTextbox(JTextArea textbox) {
        this.textbox = textbox;
    }
}
