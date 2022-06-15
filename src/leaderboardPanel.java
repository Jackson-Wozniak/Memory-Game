import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class leaderboardPanel extends JPanel {

    public leaderboardPanel(){
        addComponents();
    }

    public void addComponents(){
        this.setLayout(null);

        JLabel titleLabel = new JLabel("Leaderboard");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 35));
        titleLabel.setBounds(160,50,250,50);

        JTable leaderboard = createLeaderboard();
        leaderboard.setBackground(new Color(214,217,223));
        leaderboard.setBorder(new LineBorder(Color.BLACK));
        leaderboard.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 19));
        leaderboard.setBounds(0, 120, 590, 200);
        leaderboard.setIntercellSpacing(new Dimension(10, 10));
        leaderboard.setRowHeight(40);
        TableColumnModel columnModel = leaderboard.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(200);

        this.add(leaderboard);
        this.add(titleLabel);
        this.setPreferredSize(new Dimension(600,500));
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
