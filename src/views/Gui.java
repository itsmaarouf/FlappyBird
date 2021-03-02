package views;

import controllers.Controller;
import models.modelsImpl.Tubes;
import util.observer.IObserver;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class Gui extends JFrame implements IObserver, ActionListener {
    Controller controller;
    private JButton[][] GuiTubes;
    private JLabel[][] SPACE;
    int height;
    int width;
    JPanel gridPanel;
    boolean gameOver=false;

    public Gui(Controller controller) {
        this.controller = controller;
        height = this.controller.getGame().getHeight();
        width = this.controller.getGame().getWidth();

        //Tubes[] myTubes = this.controller.getGame().getTubes();
        this.GuiTubes = new JButton[height][width];
        this.SPACE = new JLabel[height][width];

        Container mainContainer = this.getContentPane();
        //mainContainer.setLayout(new BorderLayout(8, 6));

        JPanel TopPanel = new JPanel();
        TopPanel.setBorder(new LineBorder(Color.BLACK, 3));
        TopPanel.setBackground(Color.CYAN);


        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(height,width,0,0));
        gridPanel.setBackground(Color.cyan);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int tubeIndex = controller.getTubeIndex(j,i);
                if (tubeIndex >-1) {

                    SPACE[i][j] = new JLabel();
                    SPACE[i][j].setBackground(Color.GREEN);
                    SPACE[i][j].setOpaque(true);
                    gridPanel.add(SPACE[i][j]);
                    //SPACE[i][j].addActionListener(this);

                }else if (i == this.controller.getGame().getBird().getPositionY()
                        && j == this.controller.getGame().getBird().getPositionX() ) {

                    GuiTubes[i][j] = new JButton();
                    gridPanel.add(GuiTubes[i][j]);
                    GuiTubes[i][j].addActionListener(this);

                }else{

                    SPACE[i][j] = new JLabel();
                    SPACE[i][j].setPreferredSize(new Dimension(10, 20));
                    SPACE[i][j].setBackground(Color.cyan);
                    SPACE[i][j].setOpaque(true);
                    gridPanel.add(SPACE[i][j]);
                    //SPACE[i][j].addActionListener(this);

                }
            }
        }

        TopPanel.add(gridPanel);
        mainContainer.add(TopPanel, BorderLayout.CENTER);

        this.setSize(555, 455);
        this.setLocation(100, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void update() {


    }

    public void run(){

        while(!gameOver) {

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int tubeIndex = controller.getTubeIndex(j,i);

                    if (tubeIndex >-1) {
                        SPACE[i][j].setBackground(Color.GREEN);
                        SPACE[i][j].setOpaque(true);
                    }
                    /*else if (i == this.controller.getGame().getBird().getPositionY()
                            && j == this.controller.getGame().getBird().getPositionX() ) {


                        gridPanel.add(GuiTubes[i][j]);
                        GuiTubes[i][j].addActionListener(this);

                    }*/
                    else{
                        //SPACE[i][j].setPreferredSize(new Dimension(10, 20));
                        SPACE[i][j].setBackground(Color.cyan);
                        SPACE[i][j].setOpaque(true);
                    }
                }
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            gameOver = this.controller.gameOverTest();
            this.controller.changeBirdPosition();
            this.controller.changeTubesPositions();
            this.controller.gameScore();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.goUp();
    }
}
