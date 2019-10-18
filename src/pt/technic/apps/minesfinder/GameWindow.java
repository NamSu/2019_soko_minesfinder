package pt.technic.apps.minesfinder;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Gabriel Massadas
 */
public class GameWindow extends javax.swing.JFrame {

    private ButtonMinefield[][] buttons;
    private Minefield minefield;
    private RecordTable record;
    private MinesFinder minesFinder = new MinesFinder();

    /**
     * Creates new form GameWindow
     */
    public GameWindow() {
        initComponents();
        //menubar();
    }

    class TimerThread extends Thread {
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
                    e.printStackTrace();
                }
            }
        }

        public JLabel getTimerLabel() {
            return timerLabel;
        }
    }

    private void menubar() {

        JMenuBar jMenuBar = new JMenuBar();
        JMenu tequipment = new JMenu("   난이도 선택     ");
        JMenu help = new JMenu("   도움말   ");
        JLabel timerLabel = new JLabel();
        TimerThread timer = new TimerThread(timerLabel);

        GameWindowMenuAction listener = new GameWindowMenuAction();

        tequipment.add(new JMenuItem("Easy")).addActionListener(listener);
        tequipment.add(new JMenuItem("Medium")).addActionListener(listener);
        tequipment.add(new JMenuItem("Hard")).addActionListener(listener);
        tequipment.add(new JMenuItem("Extreme")).addActionListener(listener);
        tequipment.add(new JMenuItem("UserSetting")).addActionListener(listener);
        tequipment.add(new JMenuItem("Exit")).addActionListener(listener);

        help.add(new JMenuItem("개발자")).addActionListener(listener);

        timer.start();

        jMenuBar.add(tequipment);
        jMenuBar.add(help);

        jMenuBar.add(new JLabel("            경과시간     :    "));
        jMenuBar.add(timerLabel);
        jMenuBar.add(new JLabel("       남은 지뢰수     :    "));

        setJMenuBar(jMenuBar);
    }

    class GameWindowMenuAction implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String cmd = evt.getActionCommand();

            Timer timer = new Timer(true);
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, "Oh, Time Over!!!", "Time Attack!",
                            JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                }
            };

            switch (cmd) {
                case "Easy":
                    setVisible(false);
                    minesFinder.btnEasyActionPerformed(evt);
                    break;
                case "Medium":
                    setVisible(false);
                    minesFinder.btnMediumActionPerformed(evt);
                    break;
                case "Hard":
                    setVisible(false);
                    minesFinder.btnHardActionPerformed(evt);
                    break;
                case "Extreme":
                    setVisible(false);
                    minesFinder.btnExtremeActionPerfomed(evt);
                    break;
                case "UserSetting":
                    setVisible(false);
                    minesFinder.btnUserSettingGameActionPerfomed(evt);
                    break;
                case "Exit":
                    setVisible(false);
                    break;
                case "개발자":
                    DeveloperView developerView = new DeveloperView();
                    developerView.show();
                    break;
            }
        }
    }



    public GameWindow(Minefield minefield, RecordTable record) {
        initComponents();
        menubar();


        this.minefield = minefield;
        this.record = record;

        buttons = new ButtonMinefield[minefield.getWidth()][minefield.getHeight()];

        getContentPane().setLayout(new GridLayout(minefield.getWidth(),
                minefield.getHeight()));

        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("actionperfomed start");

                // starting gun sound
                BGM startBgm = new BGM("gun.mp3", false);
                startBgm.start();

                ButtonMinefield button = (ButtonMinefield) e.getSource();

                int x = button.getCol();
                int y = button.getLine();
                minefield.revealGrid(x, y);
                updateButtonsStates();
                if (minefield.isGameFinished()) {
                    if (minefield.isPlayerDefeated()) {
                        // defeated sound
                        BGM defeatBgm = new BGM("Defeat.mp3", false);
                        defeatBgm.start();

                        JOptionPane.showMessageDialog(null, "지뢰를 밟았습니다ㅜ", "실패", JOptionPane.INFORMATION_MESSAGE);
                        defeatBgm.stop();
                    } else {
                        //victory sound
                        BGM victoryBgm = new BGM("victory.mp3", false);
                        victoryBgm.start();

                        JOptionPane.showMessageDialog(null, "축하합니다. 당신은 모든 지뢰를 " + (minefield.getGameDuration() / 1000) + "초 만에 찾았습니다. 확인을 눌러 랭킹을 기록하세요.", "성공", JOptionPane.INFORMATION_MESSAGE);
                        long a = minefield.getGameDuration();
                        long b = record.getScore();
                        boolean newRecord = minefield.getGameDuration() < record.getScore();

                        victoryBgm.stop();

                        if (newRecord) {
                            String name = JOptionPane.showInputDialog("이름을 입력하세요.");
                            if(!name.equals("")) {
                                record.setRecord(name, minefield.getGameDuration());
                            }
                        }
                    }
                    MinesFinder.bgm.resume();
                    setVisible(false);
                }
            }
        };

        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    ButtonMinefield botao = (ButtonMinefield) e.getSource();
                    int x = botao.getCol();
                    int y = botao.getLine();
                    if (minefield.getGridState(x, y) == minefield.COVERED) {
                        minefield.setMineMarked(x, y);
                    } else if (minefield.getGridState(x,
                            y) == minefield.MARKED) {
                        minefield.setMineQuestion(x, y);
                    } else if (minefield.getGridState(x,
                            y) == minefield.QUESTION) {
                        minefield.setMineCovered(x, y);
                    }
                    updateButtonsStates();
                }
            }

            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        };

/*        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                ButtonMinefield botao = (ButtonMinefield) e.getSource();
                int x = botao.getWidth();
                int y = botao.getHeight();
                if (e.getKeyCode() == KeyEvent.VK_UP && y > 0) {
                    buttons[x][y - 1].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT && x > 0) {
                    buttons[x - 1][y].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && y
                        < minefield.getHeight() - 1) {
                    buttons[x][y + 1].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && x
                        < minefield.getWidth() - 1) {
                    buttons[x + 1][y].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_M) {
                    if (minefield.getGridState(x, y) == minefield.COVERED) {
                        minefield.setMineMarked(x, y);
                    } else if (minefield.getGridState(x,
                            y) == minefield.MARKED) {
                        minefield.setMineQuestion(x, y);
                    } else if (minefield.getGridState(x,
                            y) == minefield.QUESTION) {
                        minefield.setMineCovered(x, y);
                    }
                    updateButtonsStates();
                }
            }

            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        };*/

        // Create buttons for the player
        for (int x = 0; x < minefield.getWidth(); x++) {
            for (int y = 0; y < minefield.getHeight(); y++) {
                buttons[x][y] = new ButtonMinefield(x, y);
                buttons[x][y].addActionListener(action);
                buttons[x][y].addMouseListener(mouseListener);
                //buttons[x][y].addKeyListener(keyListener); // does not use keyListner
                getContentPane().add(buttons[x][y]);
            }
        }
    }

    private void updateButtonsStates() {
        for (int x = 0; x < minefield.getWidth(); x++) {
            for (int y = 0; y < minefield.getHeight(); y++) {
                buttons[x][y].setEstado(minefield.getGridState(x, y));
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GameWindow");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1024, Short.MAX_VALUE) // size-variables var
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 768, Short.MAX_VALUE) // size-variables var
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}