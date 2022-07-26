package frame;

import colors.CustomColors;
import panel.SetPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int SCREEN_WIDTH = (int) dimension.getWidth();
    public static final int SCREEN_HEIGHT = (int) dimension.getHeight();


    public MainFrame(String name){

        this.setLayout(null);
        this.getContentPane().setBackground(CustomColors.darker);
        SetPanel setPanel = new SetPanel(name);
        setPanel.setBounds(SCREEN_WIDTH / 4, 10, 700,700);
        this.add(setPanel);

        this.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
