package pt.technic.apps.minesfinder.view;

import java.util.Random;

/**
 *
 * create NamYounSu, LeeJeongHun, JeongWu
 *
 */
public class Minefield {

    public static final int EMPTY = 0;
    // from 1 to 8 is the number of mines around
    public static final int COVERED = 9;
    public static final int QUESTION = 10;
    public static final int MARKED = 11;
    public static final int BUSTED = 12;
    public static final int HINT = 13;
    public static final int PORTION = 14;

    public boolean[][] mines;
    public boolean[][] portion;
    private int[][] states;
    private int width;
    private int height;
    private int numMines;
    private int numPotion;
    private int numPotionCheck;
    private Random random;

    private boolean firstPlay;
    private boolean playerDefeated;
    private boolean gameFinished;

    private long timeGameStarted;
    private long timeGameDuration;

    public Minefield(int width, int height, int numMines) {
        if (numMines<=0) {
            throw new IllegalArgumentException("지뢰의 개수는 0보다는 커야합니다.");
        }

        this.width = width;
        this.height = height;
        this.numMines = numMines;
        mines = new boolean[width][height];
        portion = new boolean[width][height];
        states = new int[width][height];

        random = new Random();

        firstPlay = true;
        playerDefeated = false;
        gameFinished = false;

        setRandomPotion();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                states[x][y] = COVERED;
            }
        }
    }

    public void revealGrid(int x, int y) {
        if (states[x][y] == COVERED && !gameFinished) {
            if (firstPlay) {
                firstPlay = false;
                placeMines(x, y);
                placePotion(x, y);
                timeGameStarted=System.currentTimeMillis();
            }

            if (mines[x][y]) {
                if (numPotionCheck == 0) {
                    // makes mines show up
                    for (int a = 0; a < mines[x].length; a++) {
                        for (int b = 0; b < mines[y].length; b++) {
                            if (mines[a][b]) {
                                states[a][b] = BUSTED;
                            }
                        }
                    }

                    playerDefeated = true;
                    gameFinished = true;
                    timeGameDuration=System.currentTimeMillis()-timeGameStarted;
                }

                numPotionCheck--;
                return;
            }

            int minesAround = countMinesAround(x, y);
            states[x][y] = minesAround;

            if (minesAround == 0) {
                revealGridNeighbors(x, y);
            }

            if (portion[x][y]) {
                states[x][y] = PORTION;
                numPotionCheck++;
                return;
            }

            if(checkVictory()) {
                gameFinished=true;
                playerDefeated=false;
                timeGameDuration=System.currentTimeMillis()-timeGameStarted;
                return;
            }
        }
    }

    public long getGameDuration(){
        if(firstPlay){
            return 0;
        }

        if(!gameFinished){
            return System.currentTimeMillis()-timeGameStarted;
        }

        return timeGameDuration;
    }

    private void setRandomPotion() {
        this.numPotion = random.nextInt(5) + 2;
        this.numPotionCheck = 0;
    }

    private void revealGridNeighbors(int x, int y) {
        for (int col = Math.max(0, x - 1); col < Math.min(width, x + 2); col++) {
            for (int line = Math.max(0, y - 1); line < Math.min(height, y + 2); line++) {
                revealGrid(col, line);
            }
        }
    }

    public void setMineMarked(int x, int y) {
        if (states[x][y] == COVERED || states[x][y] == QUESTION) {
            states[x][y] = MARKED;
        }
    }

    public void setMineQuestion(int x, int y) {
        if (states[x][y] == COVERED || states[x][y] == MARKED) {
            states[x][y] = QUESTION;
        }
    }

    public void setMineCovered(int x, int y) {
        if (states[x][y] == MARKED || states[x][y] == QUESTION) {
            states[x][y] = COVERED;
        }
    }

    private boolean checkVictory() {
        boolean victory = true;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!mines[x][y]) {
                    victory = victory && states[x][y] >= 0 && states[x][y] < 9 || states[x][y] == 14;
                }
            }
        }
        return victory;
    }

    private int countMinesAround(int x, int y) {
        int result = 0;
        for (int col = Math.max(0, x - 1); col < Math.min(width, x + 2); col++) {
            for (int line = Math.max(0, y - 1); line < Math.min(height, y + 2); line++) {
                if (mines[col][line]) {
                    result++;
                }
            }
        }
        return result - (mines[x][y] ? 1 : 0);
    }

    public boolean isPlayerDefeated() {
        return playerDefeated;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    private void placeMines(int plX, int plY) {
        // the plX and plY is the player's first play
        for (int i = 0; i < numMines; i++) {
            int x = 0;
            int y = 0;
            do {
                x = random.nextInt(width);
                y = random.nextInt(height);
            } while (mines[x][y] || (x == plX && y == plY));
            mines[x][y] = true;
        }
    }

    private void placePotion(int plX, int plY) {
        // the plX and plY is the player's first play
        for (int i = 0; i < numPotion; i++) {
            int x = 0;
            int y = 0;
            do {
                x = random.nextInt(width);
                y = random.nextInt(height);
            } while (mines[x][y] || portion[x][y] || (x == plX && y == plY));
            portion[x][y] = true;
        }
    }

    public int getGridState(int x, int y) {
        return states[x][y];
    }

    public boolean hasMine(int x, int y) {
        return mines[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumMines() {
        return numMines;
    }

}