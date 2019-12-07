package pt.technic.apps.minesfinder;

import java.awt.Color;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * create NamYounSu, LeeJeongHun, JeongWu
 *
 */
public class ButtonMinefield extends JButton {
    private static String imageValue;
    private int state, col, line;

    public static void setMineImage(String value) {
        try {
            imageValue = value;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public ButtonMinefield(int col, int line) {
        this.col = col;
        this.line = line;
        state=Minefield.COVERED;
    }
    
    public void setEstado(int state) {
        this.state=state;
        switch (state) {
            case Minefield.EMPTY:
                setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/empty.png")));
                setBackground(Color.gray);
                break;
            case Minefield.COVERED:
                setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/empty.png")));
                setBackground(null);
                break;
            case Minefield.QUESTION:
                setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/question.png")));
                setBackground(Color.yellow);
                break;
            case Minefield.MARKED:
                setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/mark.png")));
                setBackground(Color.orange);
                break;
            case Minefield.HINT:
                setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/mines.png")));
                setBackground(Color.black);
                break;
            case Minefield.BUSTED:
                setIcon(new ImageIcon(getClass().getResource(imageValue)));
                setBackground(Color.red);
                break;
            case Minefield.PORTION:
                setIcon(new ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/mark.png")));
                setBackground(Color.blue);
                break;
            default:
                setText(String.valueOf(state));
                setBackground(Color.white);
                break;
        }


    }

    public int getState() {
        return state;
    }

    public int getCol() {
        return col;
    }

    public int getLine() {
        return line;
    }
    
    
}
