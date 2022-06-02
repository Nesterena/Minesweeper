package minesweeper;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {
    private static final long serialVersionUID = 1L;

    // define colors of buttons
    public static final Color BG_NOT_REVEALED = Color.GREEN;
    public static final Color FG_NOT_REVEALED = Color.RED;
    public static final Color BG_REVEALED = Color.DARK_GRAY;
    public static final Color FG_REVEALED = Color.YELLOW;
    public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

    // define variables
    int row, column;
    boolean isRevealed;
    boolean isMined;
    boolean isFlagged;

    // constructor
    public Cell (int row, int column) {
        super();
        this.row = row;
        this.column = column;
        super.setFont(FONT_NUMBERS);
    }

    // creating a new game
    public void newGame(boolean isMined) {
        this.isRevealed = false;
        this.isFlagged = false;
        this.isMined = isMined;
        super.setEnabled(true);
        super.setText("");
        paint();
    }

    // paint method
    public void paint() {
        super.setForeground(isRevealed? FG_REVEALED: FG_NOT_REVEALED);
        super.setBackground(isRevealed? BG_REVEALED: BG_NOT_REVEALED);
    }
}
