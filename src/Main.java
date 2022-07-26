import frame.MainFrame;

import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args){
        new Main();
    }

    public Main(){
//        try {
//
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        String name = JOptionPane.showInputDialog("Enter a username");
        if(name.isEmpty() || name.length() > 18){
            JOptionPane.showMessageDialog(new JFrame(), "invalid username, must be between 0 - 18 " +
                    "characters", "invalid", JOptionPane.ERROR_MESSAGE);
            new Main();
        }else{
            new MainFrame(name);
        }
    }
}
