package panel;

import colors.CustomColors;
import database.DatabaseInteract;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;

public class SetPanel extends JPanel {
    int incorrect = 0;
    int correct = 0;
    int level = 3;
    private final JPanel panel;
    private final Random random = new Random();
    private ArrayList<Integer> buttonsColor;
    private final ArrayList<Integer> guesses;
    private final JButton[] arrB;
    private final JLabel label;
    private final JLabel incorrectLabel;
    private final String name;

    public SetPanel(String name){
        this.name = name;
        this.setLayout(null);
        this.setBackground(CustomColors.darker);

        label = new JLabel("Current Level: " + level);
        label.setBounds(120,0,490,50);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        label.setForeground(CustomColors.light);
        this.add(label);

        incorrectLabel = new JLabel("0/10 Incorrect");
        incorrectLabel.setBounds(250,60,200,40);
        incorrectLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        incorrectLabel.setForeground(CustomColors.teal);
        this.add(incorrectLabel);

        panel = new JPanel();
        panel.setBounds(75,100,500,500);
        panel.setLayout(new GridLayout(6, 6, 10, 10));
        panel.setBackground(CustomColors.darker);
        this.add(panel);

        arrB = new JButton[36];
        for (int i = 0; i < arrB.length; i++) {
            arrB[i] = new JButton();
            arrB[i].setFocusable(false);
            arrB[i].setBackground(CustomColors.darkest);
            arrB[i].setBorder(null);
            panel.add(arrB[i]);
        }

        guesses = new ArrayList<>();

        this.setPreferredSize(new Dimension(700,700));
        newLevels();
    }

    public void newLevels(){
        setColor();
        for (int i = 0; i < arrB.length; i++) {
            int finalI = i;
            arrB[i].addActionListener(e -> {
                if(guesses.contains(finalI)) return;

                guesses.add(finalI);
                if(buttonsColor.contains(finalI)){
                    arrB[finalI].setBackground(CustomColors.teal);
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
                arrB[first].setBackground(CustomColors.teal);
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
        DatabaseInteract databaseInteract = new DatabaseInteract();

        if(level > databaseInteract.getScore(1)){
            databaseInteract.setUsername(1, name, level);
            createLeaderboard();
            return;
        }
        if(level > databaseInteract.getScore(2)){
            databaseInteract.setUsername(2, name, level);
            createLeaderboard();
            return;
        }
        if(level > databaseInteract.getScore(3)){
            databaseInteract.setUsername(3, name, level);
            createLeaderboard();
            return;
        }
        createLeaderboard();
    }

    public void createLeaderboard(){
        LeaderboardPanel leaderboardPanel = new LeaderboardPanel();
        leaderboardPanel.setBounds(50,100,600,295);
        this.add(leaderboardPanel);
        this.setVisible(true);
    }

    public void setBackgroundNull(JButton[] arr){
        for(JButton b : arr){
            b.setBackground(CustomColors.darkest);
            b.setEnabled(true);
        }
    }
}
