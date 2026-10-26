
package com.snakearena;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class LeaderboardFrame extends JFrame {

    private final DefaultTableModel model;
    private final JTable table;

    public LeaderboardFrame() {

        setTitle("SnakeArena - Leaderboard");
        setSize(650, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        JLabel heading = new JLabel(
                "SNAKEARENA LEADERBOARD",
                SwingConstants.CENTER
        );

        heading.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        String[] columns = {
            "Rank", "Player", "Score", "Date & Time"
        };

        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(
                    int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(
                e -> loadScores()
        );

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(refreshButton);

        add(heading, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        loadScores();
    }

    private void loadScores() {

        model.setRowCount(0);

        try {
            List<Object[]> scores =
                    ScoreDatabase.getTopScores();

            for (Object[] score : scores) {
                model.addRow(score);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Could not load leaderboard:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(() -> {
        new LeaderboardFrame().setVisible(true);
    });
}
}