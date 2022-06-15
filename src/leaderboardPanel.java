import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class leaderboardPanel extends JPanel {
    private int level;
    private String username;
    ArrayList<JLabel> labelArray = new ArrayList<>();

    public leaderboardPanel(int level){
        this.level = level;
        addComponents();
    }

    public void addComponents(){
        this.setLayout(null);

        JLabel titleLabel = new JLabel("Leaderboard");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        titleLabel.setBounds(50,10,150,30);

        JTable leaderboard = createLeaderboard();
        leaderboard.setBounds(0, 50, 350, 300);
        leaderboard.setIntercellSpacing(new Dimension(10, 10));
        leaderboard.setRowHeight(30);

        this.add(leaderboard);
        this.add(titleLabel);
        this.setPreferredSize(new Dimension(400,400));
    }

    public JTable createLeaderboard(){
        databaseInteract db = new databaseInteract();
        String[] columns = {"ranking", "username", "score"};
        Object[][] data = {{"ranking", "username", "score"},
                {1, db.getUsername(1), db.getScore(1)},
                {2, db.getUsername(2), db.getScore(2)},
                {3, db.getUsername(3), db.getScore(3)}};

        return new JTable( data, columns);
    }
}
