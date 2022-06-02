package minesweeper;

import com.company.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serial;

public class GameBoard extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;

    // define named variables for the game properties
    public static final int ROWS = 10;
    public static final int COLUMNS = 20;

    // define named variables for UI size
    public static final int CELL_SIZE = 50;
    public static final int CANVAS_WIDTH = CELL_SIZE * COLUMNS;
    public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;

    // define properties
    Cell cells [][] = new Cell [ROWS][COLUMNS];
    int numMines = 10;

    public GameBoard() {
        super.setLayout (new GridLayout (ROWS, COLUMNS, 2, 2));

        // allocate the 2D array of Cell, and added into content-pane.
        for (int row = 0; row < ROWS; ++row) {
            for (int column = 0; column < COLUMNS; ++column) {
                cells [row][column] = new Cell (row, column);
                super.add (cells [row][column]);
            }
        }

        CellMouseListener listener = new CellMouseListener();

        for (int row = 0; row < ROWS; ++row) {
            for (int column = 0; column < COLUMNS; ++column) {
                cells [row][column].addMouseListener(listener);
            }
        }

        super.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
    }

    public void newGame() {
        Mines mineMap = new Mines();
        mineMap.newMines(numMines);

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {

                cells[row][column].newGame (mineMap.isMined[row][column]);
            }
        }
    }

    public void gameOver() {

//        Mines mineMap = new Mines();
//        mineMap.newMines(numMines);
//        for (int row = 0; row < ROWS; row++) {
//            for (int column = 0; column < COLUMNS; column++) {
//
//                cells[row][column].endGame (mineMap.isMined[row][column]);
//            }
//        }
    }

    private int getSurroundingMines (int srcRow, int srcColumn) {
        int numMines = 0;
        for (int row = srcRow - 1; row <= srcRow + 1; row++) {
            for (int column = srcColumn - 1; column <= srcColumn + 1; column++) {
                if (row >= 0 && row < ROWS && column >= 0 && column < COLUMNS) {
                    if (cells[row][column].isMined) numMines++;
                }
            }
        }
        return numMines;
    }

    private void revealCell (int srcRow, int srcColumn) {
        int numMines = getSurroundingMines(srcRow, srcColumn);
        cells[srcRow][srcColumn].setText(numMines + "");
        cells[srcRow][srcColumn].isRevealed = true;
        cells[srcRow][srcColumn].paint();
        if (numMines == 0) {
            for (int row = srcRow - 1; row <= srcRow + 1; row++) {
                for (int column = srcColumn - 1; column <= srcColumn + 1; column++) {
                    if (row >= 0 && row < ROWS && column >= 0 && column < COLUMNS) {
                        if (!cells[row][column].isRevealed) revealCell(row, column);
                    }
                }
            }
        }
    }

    public boolean winGame() {
        return true;
    }

    private class CellMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked (MouseEvent e) {
            Cell sourceCell = (Cell) e.getSource();
            System.out.println("You clicked on (" + sourceCell.row + "," + sourceCell.column + ")");

            // left-click to reveal a cell, right-click to plant/remove a flag
            if (e.getButton() == MouseEvent.BUTTON1) {
                // if you hit a mine, game is over
                if (sourceCell.isMined) {
                    JOptionPane.showMessageDialog(GameBoard.this, "GAME OVER");
                    System.out.println("Game Over");
                    sourceCell.setText("*");
                } else {
                    revealCell(sourceCell.row, sourceCell.column);
                }
            } else if (e.getButton() == MouseEvent.BUTTON2) {
                sourceCell.setText("FL");
                // ADD A PART ABOUT FLAGS


            }

            // ADD A PART ABOUT WINNING
        }
    }
}
