import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection connection;

    /**
     * Establishes a database connection using the configuration from DBConfig.
     */
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName(DBConfig.DRIVER);
        connection = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);
    }

    /**
     * Closes the database connection.
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Failed to close the database connection: " + e.getMessage());
            }
        }
    }

    /**
     * Inserts a hockey team into the database.
     *
     * @param team HockeyTeam object containing the team details.
     */
    public void insertHockeyTeam(HockeyTeam team) throws SQLException {
        String sql = "INSERT INTO hockey_teams (name, location, founded_year) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, team.getName());
            stmt.setString(2, team.getLocation());
            stmt.setInt(3, team.getFoundedYear());
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves all hockey teams from the database.
     *
     * @return A list of HockeyTeam objects.
     */
    public List<HockeyTeam> getAllHockeyTeams() throws SQLException {
        List<HockeyTeam> teams = new ArrayList<>();
        String sql = "SELECT name, location, founded_year FROM hockey_teams";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String location = rs.getString("location");
                int foundedYear = rs.getInt("founded_year");
                teams.add(new HockeyTeam(name, location, foundedYear));
            }
        }
        return teams;
    }
}
