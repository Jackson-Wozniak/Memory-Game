import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;

public class setPanel extends JFrame {
    int incorrect = 0;
    JPanel panel;
    int correct = 0;
    int level = 3;
    Random random;
    ArrayList<Integer> buttonsColor;
    JButton[] arrB;
    JLabel label;
    final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50);
    JLabel gameOver;

    public setPanel(){
        this.setLayout(null);
        label = new JLabel("Current Level: " + level);
        label.setBounds(120,0,400,100);
        label.setFont(font);
        this.add(label);
        panel = new JPanel();

        gameOver = new JLabel("GAME OVER");
        gameOver.setBounds(150,200,400,100);
        gameOver.setFont(font);
        gameOver.setVisible(false);
        this.add(gameOver);

        random = new Random();

        arrB = new JButton[36];
        for (int i = 0; i < arrB.length; i++) {
            arrB[i] = new JButton();
            panel.add(arrB[i]);
        }
        panel.setBounds(75,100,500,500);
        this.add(panel);
        this.setVisible(true);
        this.setSize(700, 700);
        panel.setLayout(new GridLayout(6, 6, 10, 10));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        newLevels();
    }

    public void newLevels(){
        timeDelay(1000);
        setColor();
        timeDelay(1000);
        setBackgroundNull(arrB);

        for (int i = 0; i < arrB.length; i++) {
            int finalI = i;
            arrB[i].addActionListener(e -> {
                if(buttonsColor.contains(finalI)){
                    arrB[finalI].setBackground(Color.GREEN);
                    addCorrect();
                }else{
                    arrB[finalI].setBackground(Color.RED);
                    addIncorrect();
                }
            });
        }



        /*
        set value for level, use for loop to print out that number of squares per level,
        increasing as the user improves. If they get 3 wrong, game ends. Keep calling method
        that sets the color and gets the guesses with increasing levels. Level 1 = 3 red boxes, 2 = 4 etc
         */
    }

    public void setColor(){
        label.setText("Current Level: " + level);
        setBackgroundNull(arrB);
        buttonsColor = new ArrayList<>();
        for (int i = 0; i < level; i++) {
            int first = random.nextInt(36);
            if(buttonsColor.contains(first)){
                i--; //keep going until first variable is new, decrement I to keep correct level
            }else{
                buttonsColor.add(first);
                arrB[first].setBackground(Color.RED);
            }

            System.out.println(buttonsColor);
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                setBackgroundNull(arrB);
            };
        }, 1000);

    }





    public void addCorrect(){
        correct++;
        if(correct >= level){
            correct = 0;
            level++;
            buttonsColor.removeAll(buttonsColor);
            setBackgroundNull(arrB);
            setColor();
        }
    }
    public void addIncorrect(){
        incorrect++;
        if(incorrect >= 3){
            panel.setVisible(false);
            gameOver.setVisible(true);
            this.setVisible(true);
        }
    }

    public void setBackgroundNull(JButton[] arr){
        for(JButton b : arr) b.setBackground(null);
    }

    public void timeDelay(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
