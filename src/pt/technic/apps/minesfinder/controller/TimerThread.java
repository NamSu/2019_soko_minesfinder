package pt.technic.apps.minesfinder.controller;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimerThread extends Thread {
    JLabel timerLabel;

    public TimerThread(JLabel timerLabel) {
        this.timerLabel = timerLabel;
    }

    @Override
    public void run() {
        int countNum = 0;
        while (true) {
            timerLabel.setText(Integer.toString(countNum));
            countNum++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Logger.getLogger(TimerThread.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public JLabel getTimerLabel() {
        return timerLabel;
    }
}
