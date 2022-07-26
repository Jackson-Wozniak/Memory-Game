package panel;

import colors.CustomColors;
import database.DatabaseInteract;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class LeaderboardPanel extends JPanel {

    public LeaderboardPanel(){
        this.setLayout(null);
        this.setBackground(CustomColors.darker);

        JLabel titleLabel = new JLabel("Leaderboard");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 35));
        titleLabel.setBounds(160,5,250,50);
        titleLabel.setForeground(CustomColors.light);

        JTable leaderboard = createLeaderboard();
        leaderboard.setBackground(CustomColors.darker);
        leaderboard.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 19));
        leaderboard.setForeground(CustomColors.light);
        leaderboard.setBounds(5, 55, 590, 240);
        leaderboard.setIntercellSpacing(new Dimension(10, 10));
        leaderboard.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));
        leaderboard.setRowHeight(60);
        leaderboard.setShowGrid(true);
        leaderboard.setEnabled(false);
        TableColumnModel columnModel = leaderboard.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(200);

        this.add(leaderboard);
        this.add(titleLabel);
        this.setPreferredSize(new Dimension(600,295));
    }

    public JTable createLeaderboard(){
        DatabaseInteract db = new DatabaseInteract();
        String[] columns = {"ranking", "username", "score"};
        Object[][] data = {{"ranking", "username", "score"},
                {1, db.getUsername(1), db.getScore(1)},
                {2, db.getUsername(2), db.getScore(2)},
                {3, db.getUsername(3), db.getScore(3)}};
        return new JTable( data, columns);
    }
}
