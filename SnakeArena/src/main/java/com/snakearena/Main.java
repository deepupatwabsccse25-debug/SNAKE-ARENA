
package com.snakearena;

import javax.swing.*;
import java.awt.*;
import com.snakearena.game.GamePanel;

public class Main {

    private static JFrame menuFrame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::showMainMenu);
    }

    private static void showMainMenu() {

        menuFrame = new JFrame("Snake Arena");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(800, 650);
        menuFrame.setResizable(false);
        menuFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(10, 15, 25));
        panel.setBorder(
                BorderFactory.createEmptyBorder(100, 80, 100, 80)
        );

        JLabel title = new JLabel("SNAKE ARENA");
        title.setFont(new Font("SansSerif", Font.BOLD, 42));
        title.setForeground(new Color(57, 255, 136));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("ARCADE EDITION");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitle.setForeground(new Color(145, 163, 181));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = createButton("START GAME");
        JButton leaderboardButton = createButton("LEADERBOARD");
        JButton exitButton = createButton("EXIT");

        startButton.addActionListener(e -> startGame());

        leaderboardButton.addActionListener(e -> {
            new LeaderboardFrame().setVisible(true);
        });

        exitButton.addActionListener(e -> menuFrame.dispose());

        panel.add(title);
        panel.add(Box.createVerticalStrut(12));
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(55));
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(18));
        panel.add(leaderboardButton);
        panel.add(Box.createVerticalStrut(18));
        panel.add(exitButton);

        menuFrame.setContentPane(panel);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
    }

    private static JButton createButton(String text) {

        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(24, 39, 55));
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(280, 48));
        button.setPreferredSize(new Dimension(280, 48));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(57, 255, 136), 1
                ),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        return button;
    }

    private static void startGame() {

        menuFrame.dispose();

        JFrame gameFrame = new JFrame("Snake Arena");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);

        // Launch the original neon-style game
        gameFrame.add(new GamePanel());

        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
}