package TextGame.GUI;

import TextGame.Load;
import TextGame.Save;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

/**
 * Created by Moon on 4/8/2016.
 * This is the controller
 */
public class ScreenController extends JPanel {
    private Screen model;
    private ScreenView view;
    JMenu menu;
    JMenuBar menuBar;
    JMenuItem savemenu;
    JMenuItem loadmenu;
    JMenuItem reviewmenu;
    Bgm b;
    JButton yesbutton;
    JButton nobutton;
    JButton moveon;
    static ArrayList<String> review = new ArrayList<>();

    /**
     * This is the main Controller that loads the Screen and the ScreenView
     * @param m this is the model of the GUI
     * @param v this is the View of the GUI
     */
    public ScreenController(Screen m, ScreenView v){
        this.model = m;
        this.view = v;
        view.addImage();
        view.addText();
    }

    //sets the GUI visible after adding all the components
    public void setVisiable() {
        view.getFrame().setVisible(true);
    }

    //a mouseLister to read the text
    public void addMouseListner() {
        MouseListener listener = new MouseListener() {
            String line;
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if((line = model.getText().readLine()) != null) {
                        //view.getTextbox().setText(line);
                        new Timer(10, new ActionListener() {
                            int i = 0;
                            public void actionPerformed(ActionEvent e) {
                                view.getTextbox().append(String.valueOf(line.charAt(i++)));
                                if (i == line.length()) {
                                    ((Timer) e.getSource()).stop();
                                }
                            }
                        }).start();
                        view.getTextbox().setText(null);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        view.getImage().addMouseListener(listener);
        view.getBottempanel().addMouseListener(listener);
        view.getTextbox().addMouseListener(listener);
    }

    //adds a menubar to the frame that consists of Save, Load and Review
    public void addmenubar() {
        menuBar = new JMenuBar();

        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        savemenu = new JMenuItem("Save", KeyEvent.VK_T);
        menu.add(savemenu);
        loadmenu = new JMenuItem("Load", KeyEvent.VK_T);
        menu.add(loadmenu);
        reviewmenu = new JMenuItem("Review", KeyEvent.VK_T);
        menu.add(reviewmenu);

        view.getFrame().setJMenuBar(menuBar);

        try { //save
            savemenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Save sav = new Save(model.f, review);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try { //load
            loadmenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Load load = new Load();
                    Screen loaded = new Screen(load.flow);
                    b.stopMusic();
                    view.getFrame().dispose();
                    ScreenView nextview = new ScreenView(loaded, load.flow);
                    ScreenController nextcon = new ScreenController(loaded, nextview);
                    review = load.review;
                    nextcon.addMusic();
                    nextcon.addYesButton();
                    nextcon.addNoButton();
                    nextcon.addMouseListner();
                    nextcon.addmenubar();
                    nextcon.setVisiable();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try { //review
            reviewmenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (review.size() == 0) {
                        JOptionPane.showMessageDialog(view.getFrame(), "Nothing to review");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < review.size(); i++) {
                            int num = i+1;
                            sb.append("Choice number " + num + " : ");
                            sb.append(review.get(i).toString());
                            sb.append("\n");
                        }
                        JOptionPane.showMessageDialog(view.getFrame(), sb);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addMusic() {
        b = new Bgm(model.getSound());
        b.playMusic();
    }

    /**
     * Adds a Next button which progresses the story without making a choice
     */
    public void addMoveOn() {
        moveon = new JButton("Next");
        moveon.setBackground(BLACK);
        moveon.setForeground(WHITE);

        try {
            moveon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(model.f.hasChildren()) {
                        //System.out.println("added to review");
                        //System.out.println(review.size());
                        b.stopMusic();
                        view.getFrame().dispose();
                        Screen next = new Screen(model.f.getChildren().get(0));
                        ScreenView nextview = new ScreenView(next, model.f.getChildren().get(0));
                        ScreenController nextcon = new ScreenController(next, nextview);
                        openScreen(nextcon, model.f.getChildren().get(0).getNoChoices());
                        System.out.println("pressed next");
                    }
                    else {
                        JOptionPane.showMessageDialog(view.getFrame(), "You have died...");
                        System.exit(0);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.getBottempanel().add(moveon);
    }

    //adds a Yes Button to the frame
    public void addYesButton() {
        yesbutton = new JButton("Yes");
        yesbutton.setBackground(BLACK);
        yesbutton.setForeground(WHITE);

        try {
            yesbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(model.f.hasChildren()) {
                        review.add("Yes");
                        //System.out.println("added to review");
                        //System.out.println(review.size());
                        b.stopMusic();
                        view.getFrame().dispose();
                        Screen next = new Screen(model.f.getChildren().get(0));
                        ScreenView nextview = new ScreenView(next, model.f.getChildren().get(0));
                        ScreenController nextcon = new ScreenController(next, nextview);
                        openScreen(nextcon, model.f.getChildren().get(0).getNoChoices());
                        System.out.println("pressed yes");
                    }
                    else {
                        JOptionPane.showMessageDialog(view.getFrame(), "More Awaits");
                        System.exit(0);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.getBottempanel().add(yesbutton);
    }

    //adds a No button to the frame
    public void addNoButton() {
        nobutton = new JButton("No");
        nobutton.setBackground(BLACK);
        nobutton.setForeground(WHITE);

        try {
            nobutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(model.f.hasChildren()) {
                        review.add("No");
                        b.stopMusic();
                        view.getFrame().dispose();
                        Screen next = new Screen(model.f.getChildren().get(1));
                        ScreenView nextview = new ScreenView(next, model.f.getChildren().get(1));
                        ScreenController nextcon = new ScreenController(next, nextview);
                        openScreen(nextcon, model.f.getChildren().get(1).getNoChoices());
                        System.out.println("pressed no");
                    }
                    else {
                        JOptionPane.showMessageDialog(view.getFrame(), "More awaits");
                        System.exit(0);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.getBottempanel().add(nobutton);
    }

    private void openScreen(ScreenController nextcon, Boolean noChoices) {
        if(noChoices) {
            nextcon.addMusic();
            nextcon.addMouseListner();
            nextcon.addmenubar();
            nextcon.setVisiable();
            nextcon.addMoveOn();
        }
        //else if(lastChoice) {

        //}
        else {
            nextcon.addMusic();
            nextcon.addYesButton();
            nextcon.addNoButton();
            nextcon.addMouseListner();
            nextcon.addmenubar();
            nextcon.setVisiable();
        }
    }

}

