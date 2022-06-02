package minesweeper;

public class Mines {

    // need to create a mines map or generate it automatically
    int numMines;
    boolean[][] isMined = new boolean[GameBoard.ROWS][GameBoard.COLUMNS];

    // constructor
    public Mines() {
        super();
    }

    public void newMines(int numMines) {
        this.numMines = numMines;
        // assume numMines=10
        isMined[0][0] = true;
        isMined[5][2] = true;
        isMined[9][5] = true;
        isMined[6][7] = true;
        isMined[8][2] = true;
        isMined[2][4] = true;
        isMined[5][7] = true;
        isMined[7][7] = true;
        isMined[3][6] = true;
        isMined[4][8] = true;
    }
}
