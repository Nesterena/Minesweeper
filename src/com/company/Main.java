package com.company;

import minesweeper.Cell;
import minesweeper.GameBoard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class Main extends Component {

    public static void main (String[] args) {
        new MainGame();
    }

    public static class MainGame extends JFrame {
        @Serial
        private static final long serialVersionUID = 1L;

        // creating "New game" button
        static GameBoard board = new GameBoard();
        JButton btnNewGame = new JButton("New Game");

        // creating "End game" button
        JButton btnEndGame = new JButton("End Game");

        // constructor for buttons "New Game" and "End Game"
        public MainGame() {

            Container table = this.getContentPane();
            table.setLayout (new BorderLayout());

            table.add(board, BorderLayout.CENTER);

            table.add (btnNewGame, BorderLayout.NORTH);
            btnNewGame.addActionListener (new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    board.newGame();
                }
            });

            table.add (btnEndGame, BorderLayout.SOUTH);
            btnEndGame.addActionListener (new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e) {
                    JOptionPane.showMessageDialog(MainGame.this, "GAME ENDED");
                    board.newGame();
                }
            });

            board.newGame();

            pack();
            setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            setTitle ("Minesweeper");
            setVisible (true);
        }

        public static GameBoard getBoard() {
            return board;
        }
    }
}