import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args){
        try {

            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String name = JOptionPane.showInputDialog("Enter a username");
        new setPanel(name);

    }
}
