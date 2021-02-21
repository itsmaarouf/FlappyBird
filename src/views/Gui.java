package views;

import controllers.Controller;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    Controller controller;
    private JButton[][] GuiTubes;

    public Gui(Controller controller) {
        this.controller = controller;
        int height = this.controller.getGame().getHeight();
        int width = this.controller.getGame().getWidth();
        this.GuiTubes = new JButton[height][width];
        JPanel panelTop = new JPanel();
        /*for (int i = 0; i <height ; i++) {
            for (int j = 0; j < width; j++) {
                this.GuiTubes[i][j] = new JButton(String.valueOf(this.controller.getGame().getTubeIndex(i,j)));
                //panelTop.add(this.GuiTubes[i][j]);
            }
        }*/
        JLabel myLabel = new JLabel();
        myLabel.setText("ghjmjhgfd");
        myLabel.setBackground(Color.red);
        JButton btn = new JButton();
        JButton btn1 = new JButton();
        //btn.setText("");
        btn.setBackground(Color.red);
        panelTop.add(myLabel);
        panelTop.add(btn);
        panelTop.add(btn1);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(960, 320);
      /*JPanel panel = new JPanel();
        JButton Up = new JButton("Up");
        JButton Down = new JButton("Down");
        JButton Start = new JButton("Start");
        panel.add(Up);
        panel.add(Down);
        panel.add(Start);*/
        this.getContentPane().add(panelTop);
        this.setVisible(true);


    }




}
