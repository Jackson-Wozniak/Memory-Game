import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;

public class setPanel extends JFrame {
    int incorrect = 0;
    int correct = 0;
    int level = 3;
    JPanel panel;
    Random random;
    ArrayList<Integer> buttonsColor;
    ArrayList<Integer> guesses;
    JButton[] arrB;
    JLabel label;
    JLabel gameOver;
    JLabel incorrectLabel;
    final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50);
    String name;


    public setPanel(String name){
        this.name = name;
        this.setLayout(null);
        random = new Random();

        label = new JLabel("Current Level: " + level);
        label.setBounds(120,0,490,50);
        label.setFont(font);
        this.add(label);

        incorrectLabel = new JLabel("0/10 Incorrect");
        incorrectLabel.setBounds(250,60,200,40);
        incorrectLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.add(incorrectLabel);


        gameOver = new JLabel("GAME OVER");
        gameOver.setBounds(150,200,400,100);
        gameOver.setFont(font);
        gameOver.setVisible(false);
        this.add(gameOver);

        panel = new JPanel();
        panel.setBounds(75,100,500,500);
        panel.setLayout(new GridLayout(6, 6, 10, 10));
        this.add(panel);
        //panel.setBackground(Color.black);

        arrB = new JButton[36];
        for (int i = 0; i < arrB.length; i++) {
            arrB[i] = new JButton();
            arrB[i].setFocusable(false);
            panel.add(arrB[i]);
        }

        guesses = new ArrayList<>();

        this.setSize(700, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        newLevels();
    }

    public void newLevels(){
        timeDelay(500);
        setColor();
        timeDelay(1000);
        setBackgroundNull(arrB);

        for (int i = 0; i < arrB.length; i++) {
            int finalI = i;
            arrB[i].addActionListener(e -> {
                if(guesses.contains(finalI)) return;

                guesses.add(finalI);
                if(buttonsColor.contains(finalI)){
                    arrB[finalI].setBackground(Color.GREEN);
                    addCorrect();
                }else{
                    arrB[finalI].setEnabled(false);
                    arrB[finalI].setBackground(Color.RED);
                    addIncorrect();
                }
            });
        }
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
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                setBackgroundNull(arrB);
            }
        }, 1000);


    }

    public void addCorrect(){
        correct++;
        if(correct >= level){
            correct = 0;
            level++;
            guesses.clear();
            buttonsColor.clear();
            setBackgroundNull(arrB);
            setColor();
        }
    }
    public void addIncorrect(){
        incorrect++;
        incorrectLabel.setText(incorrect + "/10 Incorrect");
        if(incorrect >= 10){
            panel.setVisible(false);
            checkForRanking();
        }
    }

    public void checkForRanking(){
        databaseInteract db = new databaseInteract();

        if(level > db.getScore(1)){
            db.setUsername(1, name, level);
            createLeaderboard();
            return;
        }
        if(level > db.getScore(2)){
            db.setUsername(2, name, level);
            createLeaderboard();
            return;
        }
        if(level > db.getScore(3)){
            db.setUsername(3, name, level);
            createLeaderboard();
            return;
        }
        createLeaderboard();
    }

    public void createLeaderboard(){
        leaderboardPanel pane = new leaderboardPanel();
        pane.setBounds(50,50,600,500);
        this.add(pane);
        this.setVisible(true);
    }

    public void setBackgroundNull(JButton[] arr){
        for(JButton b : arr){
            b.setBackground(null);
            b.setEnabled(true);
        }
    }

    public void timeDelay(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
