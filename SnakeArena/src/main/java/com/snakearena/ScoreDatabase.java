
package com.snakearena;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScoreDatabase {

    // Save a player's game score
    public static void saveScore(
            String playerName, int score)
            throws SQLException {

        String sql = "INSERT INTO scores "
                + "(player_name, score) VALUES (?, ?)";

        try (Connection con =
                     DatabaseConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, playerName);
            ps.setInt(2, score);

            ps.executeUpdate();
        }
    }

    // Get the top 10 scores for the leaderboard
    public static List<Object[]> getTopScores()
            throws SQLException {

        List<Object[]> scores = new ArrayList<>();

        String sql = "SELECT player_name, score, played_at "
                + "FROM scores "
                + "ORDER BY score DESC, played_at ASC, id ASC "
                + "LIMIT 10";

        try (Connection con =
                     DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            int rank = 1;

            while (rs.next()) {
                Object[] row = {
                    rank++,
                    rs.getString("player_name"),
                    rs.getInt("score"),
                    rs.getTimestamp("played_at")
                };

                scores.add(row);
            }
        }

        return scores;
    }

    // Get a player's all-time highest score
    public static int getBestScore(String playerName)
            throws SQLException {

        String sql = "SELECT COALESCE(MAX(score), 0) "
                + "FROM scores WHERE player_name = ?";

        try (Connection con =
                     DatabaseConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, playerName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return 0;
    }
}