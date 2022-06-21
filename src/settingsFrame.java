import javax.swing.*;
import java.awt.*;

public class settingsFrame extends JFrame{
    private final setPanel panel;

    public settingsFrame(setPanel panel){
        this.panel = panel;

        JPanel theme = new JPanel();
        this.add(theme);
        JButton darkMode = new JButton("Dark Mode");
        theme.add(darkMode);
        darkMode.addActionListener(e -> setDarkMode());
        JButton lightMode = new JButton("Light Mode");
        theme.add(lightMode);
        lightMode.addActionListener(e -> setLightMode());


        //panel to change the color that displays with a correct pick
        JPanel buttonColor = new JPanel();
        this.add(buttonColor);
        buttonColor.setLayout(new GridLayout(2,2,20,20));
        JButton[] color = {new JButton(), new JButton(), new JButton(), new JButton()};
        color[0].setBackground(Color.BLUE);
        color[1].setBackground(Color.ORANGE);
        color[2].setBackground(Color.PINK);
        color[3].setBackground(Color.GREEN);
        for(JButton b : color){
            buttonColor.add(b);
            b.addActionListener(e -> panel.correctColor = b.getBackground());

        }

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Theme", theme);
        tabbedPane.addTab("Button Color", buttonColor);

        this.add(tabbedPane);
        this.setSize(200,200);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void setLightMode() {
        panel.getContentPane().setBackground(null);
        panel.panel.setBackground(null);
        panel.label.setForeground(null);
    }

    public void setDarkMode(){
        panel.getContentPane().setBackground(new Color(40,40,40));
        panel.panel.setBackground(Color.BLACK);
        panel.label.setForeground(Color.WHITE);
    }
}
