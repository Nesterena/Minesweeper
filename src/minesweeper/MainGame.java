package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGame extends JFrame {
    private static final long serialVersionUID = 1L;

    // private variables
    GameBoard board = new GameBoard();
    JButton btnNewGame = new JButton("New Game");

    // constructor to set up all the UI and game components
    public MainGame() {
        Container cp = this.getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(board, BorderLayout.CENTER);

        cp.add(btnNewGame, BorderLayout.SOUTH);
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.newGame();
            }
        });

        board.newGame();

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Minesweeper");
        setVisible(true);
    }
}
