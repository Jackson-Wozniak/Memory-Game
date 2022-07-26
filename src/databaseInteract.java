import java.sql.*;

public class databaseInteract {
    private Connection connect;

    public databaseInteract(){
        try{
            this.connect = DriverManager.getConnection(SQL_Login.URL,
                    SQL_Login.USERNAME, SQL_Login.PASSWORD);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getUsername(int ranking){
        try{
            PreparedStatement selectName = connect.prepareStatement(
                    "select * from leaderboard where ranking=?");
            selectName.setInt(1, ranking);
            ResultSet getName = selectName.executeQuery();
            if(getName.next()){
                return getName.getString(2);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
       return null;
    }

    public int getScore(int ranking){
        try{
            PreparedStatement selectName = connect.prepareStatement(
                    "select * from leaderboard where ranking=?");
            selectName.setInt(1, ranking);
            ResultSet getName = selectName.executeQuery();
            if(getName.next()){
                return getName.getInt(3);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public void setUsername(int ranking, String username, int score){
        try{
            PreparedStatement selectName = connect.prepareStatement(
                    "update leaderboard set username = ?, score = ? where ranking = ?");
            selectName.setString(1, username);
            selectName.setInt(2, score);
            selectName.setInt(3, ranking);
            selectName.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
