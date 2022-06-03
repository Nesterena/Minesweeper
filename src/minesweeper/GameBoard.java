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
    public static int ROWS = 10;
    public static int COLUMNS = 20;

    // define named variables for UI size
    public static final int CELL_SIZE = 50;
    public static final int CANVAS_WIDTH = CELL_SIZE * COLUMNS;
    public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;

//    public String difficulty;

    public char flag = '⚑';
    public char mine = '✸';

//    public String getDifficulty() {
//        return this.difficulty;
//    }

//    public void setDifficulty (String difficulty) {
//        this.difficulty = difficulty.toUpperCase();
//        if (difficulty.equals("BEGINNER")) {
//            this.ROWS = 10;
//            this.COLUMNS = 10;
//            this.numMines = 10;
//        } else if (difficulty.equals("INTERMEDIATE")) {
//            this.ROWS = 20;
//            this.COLUMNS = 20;
//            this.numMines = 15;
//        } else if (difficulty.equals("ADVANCED")) {
//            this.ROWS = 30;
//            this.COLUMNS = 20;
//            this.numMines = 30;
//        } else {
//            System.out.println("Selected default level");
//            this.ROWS = 10;
//            this.COLUMNS = 10;
//            this.numMines = 10;
//        }
//    }


    // define properties
    Cell[][] cells = new Cell [ROWS][COLUMNS];
    int numMines = 10;

    public GameBoard() {

//        setDifficulty(difficulty);

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

            if (e.getButton() == MouseEvent.BUTTON1) {
                // if you hit a mine, game is over
                if (sourceCell.isMined) {
                    System.out.println("Game Over");
                    sourceCell.setText("✸");
                    JOptionPane.showMessageDialog(GameBoard.this, "GAME OVER");
                    Main.MainGame.getBoard().newGame();
                } else {
                    revealCell(sourceCell.row, sourceCell.column);
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                if (sourceCell.isFlagged) {
                    System.out.println("Flag added");
                    sourceCell.setText("⚑");
                }
                else {
                    revealCell(sourceCell.row, sourceCell.column);
                }
            }
            // ADD A PART ABOUT WINNING
        }
    }
}
