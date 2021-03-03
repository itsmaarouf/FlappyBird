package views;

import controllers.Controller;
import util.observer.IObserver;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.Style;
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
    JPanel TopPanel;
    JPanel BottomPanel;
    JButton FlyButten;

    public Gui(Controller controller) {
        this.controller = controller;
        height = this.controller.getGame().getHeight();
        width = this.controller.getGame().getWidth();

        this.GuiTubes = new JButton[height][width];
        this.SPACE = new JLabel[height][width];

        Container mainContainer = this.getContentPane();

        TopPanel = new JPanel();
        TopPanel.setBorder(new LineBorder(Color.BLACK, 3));
        TopPanel.setPreferredSize(new Dimension(300, 400));

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(height,width,0,0));
        gridPanel.setPreferredSize(new Dimension(300, 400));

        BottomPanel = new JPanel();
        BottomPanel.setBorder(new LineBorder(Color.BLACK, 1));
        BottomPanel.setPreferredSize(new Dimension(300, 50));
        TopPanel.setBackground(Color.CYAN);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                int tubeIndex = controller.getTubeIndex(j,i);
                /*if (j == 1 && i == 1) {

                    score[i][j] =new JLabel("0");
                    score[i][j].setPreferredSize(new Dimension(20, 20));
                    gridPanel.add(SPACE[i][j]);

                }else*/ if (tubeIndex >-1) {

                    SPACE[i][j] = new JLabel();
                    SPACE[i][j].setPreferredSize(new Dimension(20, 20));
                    SPACE[i][j].setBackground(Color.GREEN);
                    SPACE[i][j].setOpaque(true);
                    gridPanel.add(SPACE[i][j]);

                }else if (i == this.controller.getGame().getBird().getPositionY()
                        && j == this.controller.getGame().getBird().getPositionX() ) {

                    SPACE[i][j] =  new JLabel("(O.0)");
                    SPACE[i][j].setPreferredSize(new Dimension(20, 20));
                    gridPanel.add(SPACE[i][j]);

                }else{

                    SPACE[i][j] = new JLabel();
                    SPACE[i][j].setPreferredSize(new Dimension(20, 20));
                    SPACE[i][j].setBackground(Color.cyan);
                    SPACE[i][j].setOpaque(true);
                    gridPanel.add(SPACE[i][j]);

                }
            }
        }

        FlyButten = new JButton("Click to Fly");
        BottomPanel.add(FlyButten);
        FlyButten.addActionListener(this);

        TopPanel.add(gridPanel);
        TopPanel.add(BottomPanel);
        mainContainer.add(TopPanel, BorderLayout.CENTER);
        mainContainer.add(BottomPanel, BorderLayout.SOUTH);

        this.setSize(300, 450);
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

                        SPACE[i][j].setText("");
                        SPACE[i][j].setBackground(Color.GREEN);
                        SPACE[i][j].setOpaque(true);

                    }else if (i == this.controller.getGame().getBird().getPositionY()
                            && j == this.controller.getGame().getBird().getPositionX() ) {

                        SPACE[i][j].setText("OO");

                    }else{

                        SPACE[i][j].setText("");
                        SPACE[i][j].setBackground(Color.cyan);
                        SPACE[i][j].setOpaque(true);
                    }
                }
            }

            this.getContentPane().repaint();

            try {
                TimeUnit.MILLISECONDS.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.controller.changeBirdPosition();
            this.controller.changeTubesPositions();
            this.controller.gameScore();
            gameOver = this.controller.gameOver();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.goUp();
    }
}
