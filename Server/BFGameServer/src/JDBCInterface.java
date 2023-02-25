import java.sql.*;

public class JDBCInterface {
	Connection conn = null;
    JDBCInterface() {
        try {
            // Connect to the database
            String url = "jdbc:sqlite:players.db";
            conn = DriverManager.getConnection(url);
            
            
            String sql = "CREATE TABLE IF NOT EXISTS players (id varchar(32) PRIMARY KEY, wins INTEGER, plays INTEGER)";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public String getWinPlayCount(String id){
    	String sql = "SELECT wins, plays FROM players WHERE id = ?";
    	PreparedStatement pstmt;
    	int wins = 0;
    	int plays = 0;
		try {
			pstmt = conn.prepareStatement(sql);
	    	pstmt.setString(1, id);
	    	ResultSet rs = pstmt.executeQuery();
	    	if (rs.next()) {
	    	    wins = rs.getInt("wins");
	    	    plays = rs.getInt("plays");
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	// Print the number of wins and plays, or 0 if the player doesn't exist
    	return  "Number of plays: "+plays+"#Number of wins: " + wins+"# Number of losses:"+(plays-wins);
    }
    
    public boolean updatePlayer(String id,int win) {
    	String sql = "INSERT INTO players (id, plays, wins) VALUES (?, ?, ?) ON CONFLICT (id) DO UPDATE SET plays = players.plays + 1, wins = players.wins+"+(new Integer(win).toString());
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1, id); // Set the value for the id column
        	pstmt.setInt(2, 1); // Set the value for the plays column
        	pstmt.setInt(3, win); // Set the value for the wins column
        	pstmt.executeUpdate(); // Execute the insert or update statement
    	}catch(SQLException e) {
			// TODO Auto-generated catch block
    		e.printStackTrace();
    		return false;
    	}
    	return true;
    }
}