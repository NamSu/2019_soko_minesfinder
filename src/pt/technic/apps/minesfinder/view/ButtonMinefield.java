package pt.technic.apps.minesfinder.view;

import pt.technic.apps.minesfinder.controller.ContentViewCtrl;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * create NamYounSu, LeeJeongHun, JeongWu
 *
 */
public class ButtonMinefield extends JButton {
    private int state, col, line;

    public ButtonMinefield(int col, int line) {
        this.col = col;
        this.line = line;
        state= Minefield.COVERED;
    }
    
    public void setEstado(int state) {
        this.state=state;
        switch (state) {
            case Minefield.EMPTY:
                setIcon(new ImageIcon(getClass().getResource(ContentViewCtrl.setImageResources("empty"))));
                setBackground(Color.gray);
                break;
            case Minefield.COVERED:
                setIcon(new ImageIcon(getClass().getResource(ContentViewCtrl.setImageResources("empty"))));
                setBackground(null);
                break;
            case Minefield.QUESTION:
                setIcon(new ImageIcon(getClass().getResource(ContentViewCtrl.setImageResources("question"))));
                setBackground(Color.yellow);
                break;
            case Minefield.MARKED:
                setIcon(new ImageIcon(getClass().getResource(ContentViewCtrl.setImageResources("mark"))));
                setBackground(Color.orange);
                break;
            case Minefield.HINT:
                setIcon(new ImageIcon(getClass().getResource(ContentViewCtrl.setImageResources("mines"))));
                setBackground(Color.black);
                break;
            case Minefield.BUSTED:
                setIcon(new ImageIcon(getClass().getResource(ContentViewCtrl.getMineImage())));
                setBackground(Color.red);
                break;
            case Minefield.PORTION:
                setIcon(new ImageIcon(getClass().getResource(ContentViewCtrl.setImageResources("portion"))));
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
