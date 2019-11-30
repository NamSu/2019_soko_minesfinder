package pt.technic.apps.minesfinder;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * create NamYounSu, LeeJeongHun, JeongWu
 *
 */
public class MinesFinder extends javax.swing.JFrame {

    private RecordTable recordEasy;
    private RecordTable recordMedium;
    private RecordTable recordHard;
    private RecordTable recordExtreme;
    private RecordTable recordUserSetting;

    public static BGM bgm = new BGM("bgm.mp3", true);

    /**
     * Creates new form MinesFinder
     */
    public MinesFinder() {
        initComponents();
        recordEasy = new RecordTable();
        recordMedium = new RecordTable();
        recordHard = new RecordTable();
        recordExtreme = new RecordTable();
        recordUserSetting = new RecordTable();

        readGameRecords();

        labelEasyName.setText(recordEasy.getName());
        labelEasyPoints.setText(Long.toString(recordEasy.getScore()/1000));

        labelMediumName.setText(recordMedium.getName());
        labelMediumPoints.setText(Long.toString(recordMedium.getScore()/1000));

        labelHardName.setText(recordHard.getName());
        labelHardPoints.setText(Long.toString(recordHard.getScore()/1000));

        labelExtremeName.setText(recordExtreme.getName());
        labelExtremePoints.setText(Long.toString(recordExtreme.getScore()/1000));

        labelUserSettingName.setText(recordUserSetting.getName());
        labelUserSettingPoints.setText(Long.toString(recordUserSetting.getScore()/1000));

        recordEasy.addRecordTableListener(new RecordTableListener() {
            @Override
            public void recordUpdated(RecordTable record) {
                recordEasyUpdated(record);
            }
        });

        recordMedium.addRecordTableListener(new RecordTableListener() {
            @Override
            public void recordUpdated(RecordTable record) {
                recordMediumUpdated(record);
            }
        });

        recordHard.addRecordTableListener(new RecordTableListener() {
            @Override
            public void recordUpdated(RecordTable record) {
                recordHardUpdated(record);
            }
        });

        recordExtreme.addRecordTableListener(new RecordTableListener() {
            @Override
            public void recordUpdated(RecordTable record) {
                recordExtremeUpdated(record);
            }
        });

        recordUserSetting.addRecordTableListener(new RecordTableListener() {
            @Override
            public void recordUpdated(RecordTable record) {
                recordUserSettingUpdated(record);
            }
        });
    }

    private void recordEasyUpdated(RecordTable record) {
        labelEasyName.setText(record.getName());
        labelEasyPoints.setText(Long.toString(record.getScore()/1000));
        FireBaseCtrl fireBaseCtrl = new FireBaseCtrl();
        fireBaseCtrl.update(record.getScore() / 1000, "Easy", record.getName());
        fireBaseCtrl.close();
        saveGameRecords();
    }

    private void recordMediumUpdated(RecordTable record) {
        labelMediumName.setText(record.getName());
        labelMediumPoints.setText(Long.toString(record.getScore()/1000));
        FireBaseCtrl fireBaseCtrl = new FireBaseCtrl();
        fireBaseCtrl.update(record.getScore() / 1000, "Medium", record.getName());
        fireBaseCtrl.close();
        saveGameRecords();
    }

    private void recordHardUpdated(RecordTable record) {
        labelHardName.setText(record.getName());
        labelHardPoints.setText(Long.toString(record.getScore()/1000));
        FireBaseCtrl fireBaseCtrl = new FireBaseCtrl();
        fireBaseCtrl.update(record.getScore() / 1000, "Hard", record.getName());
        fireBaseCtrl.close();
        saveGameRecords();
    }

    private void recordExtremeUpdated(RecordTable record) {
        labelExtremeName.setText(record.getName());
        labelExtremePoints.setText(Long.toString(record.getScore()/1000));
        FireBaseCtrl fireBaseCtrl = new FireBaseCtrl();
        fireBaseCtrl.update(record.getScore() / 1000, "Extreme", record.getName());
        fireBaseCtrl.close();
        saveGameRecords();
    }

    private void recordUserSettingUpdated(RecordTable record) {
        labelUserSettingName.setText(record.getName());
        labelUserSettingPoints.setText(Long.toString(record.getScore()/1000));
        saveGameRecords();
    }

    private void saveGameRecords() {
        ObjectOutputStream oos = null;
        try {
            File f = new File(System.getProperty("user.dir") + File.separator + ".minesfinder.records");
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(recordEasy);
            oos.writeObject(recordMedium);
            oos.writeObject(recordHard);
            oos.writeObject(recordExtreme);
            oos.writeObject(recordUserSetting);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

    private void readGameRecords() {
        ObjectInputStream ois = null;
        File f = new File(System.getProperty("user.dir") + File.separator + ".minesfinder.records");
        if (f.canRead()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(f));
                recordEasy = (RecordTable) ois.readObject();
                recordMedium = (RecordTable) ois.readObject();
                recordHard = (RecordTable) ois.readObject();
                recordExtreme = (RecordTable) ois.readObject();
                recordUserSetting = (RecordTable) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        panelTitle = new javax.swing.JLabel();
        panelRecords = new javax.swing.JPanel();
        Records = new javax.swing.JLabel();
        labelEasy = new javax.swing.JLabel();
        labelEasyName = new javax.swing.JLabel();
        labelEasyPoints = new javax.swing.JLabel();
        labelMedium = new javax.swing.JLabel();
        labelMediumName = new javax.swing.JLabel();
        labelMediumPoints = new javax.swing.JLabel();
        labelHard = new javax.swing.JLabel();
        labelHardName = new javax.swing.JLabel();
        labelHardPoints = new javax.swing.JLabel();
        labelExtreme = new javax.swing.JLabel();
        labelExtremeName = new javax.swing.JLabel();
        labelExtremePoints = new javax.swing.JLabel();
        labelUserSetting = new javax.swing.JLabel();
        labelUserSettingName = new javax.swing.JLabel();
        labelUserSettingPoints = new javax.swing.JLabel();
        panelBtns = new javax.swing.JPanel();
        btnEasy = new javax.swing.JButton();
        btnMedium = new javax.swing.JButton();
        btnHard = new javax.swing.JButton();
        btnGetInternetRank = new javax.swing.JButton();
        btnExtreme = new javax.swing.JButton();
        btnuserSettingGame = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnTutorial = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MinesFinder");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(800, 600)); // set-home-windows variable var
        setResizable(false);

        panelTitle.setBackground(new java.awt.Color(126, 87, 194));
        panelTitle.setFont(new java.awt.Font("Malgun Gothic", 1, 24)); // NOI18N
        panelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelTitle.setText("MinesFinder");
        panelTitle.setOpaque(true);
        getContentPane().add(panelTitle, java.awt.BorderLayout.PAGE_START);

        panelRecords.setBackground(new java.awt.Color(176, 133, 245));

        Records.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        Records.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Records.setText("Internal Records");

        labelEasy.setFont(new java.awt.Font("Malgun Gothic", 0, 14)); // NOI18N
        labelEasy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEasy.setText("Easy");

        labelEasyName.setText("Player");

        labelEasyPoints.setText("9999");

        labelMedium.setFont(new java.awt.Font("Malgun Gothic", 0, 14)); // NOI18N
        labelMedium.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMedium.setText("Medium");

        labelMediumName.setText("Player");

        labelMediumPoints.setText("9999");

        labelHard.setFont(new java.awt.Font("Malgun Gothic", 0, 14)); // NOI18N
        labelHard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHard.setText("Hard");

        labelHardName.setText("Player");

        labelHardPoints.setText("9999");

        labelExtreme.setFont(new java.awt.Font("Malgun Gothic", 0, 14)); // NOI18N
        labelExtreme.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelExtreme.setText("Extreme");

        labelExtremeName.setText("Player");

        labelExtremePoints.setText("9999");

        labelUserSetting.setFont(new java.awt.Font("Malgun Gothic", 0, 14)); // NOI18N
        labelUserSetting.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUserSetting.setText("User Setting");

        labelUserSettingName.setText("Player");

        labelUserSettingPoints.setText("9999");

        btnGetInternetRank.setText("Show Internet Rank");
        btnGetInternetRank.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnGetInternetRankActionPerformed(evt);
            }
        });

        btnuserSettingGame.setText("사용자 설정 게임");
        btnuserSettingGame.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnUserSettingGameActionPerfomed(evt);
            }
        });

        javax.swing.GroupLayout panelRecordsLayout = new javax.swing.GroupLayout(panelRecords);
        panelRecordsLayout.setHorizontalGroup(
                panelRecordsLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(Records, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addGroup(panelRecordsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelRecordsLayout.createParallelGroup(Alignment.LEADING)

                                        .addComponent(labelEasy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelRecordsLayout.createSequentialGroup()
                                                .addComponent(labelEasyName)
                                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(labelEasyPoints))

                                        .addComponent(labelMedium, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelRecordsLayout.createSequentialGroup()
                                                .addComponent(labelMediumName)
                                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(labelMediumPoints))

                                        .addComponent(labelHard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelRecordsLayout.createSequentialGroup()
                                                .addComponent(labelHardName)
                                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(labelHardPoints))

                                        .addComponent(labelExtreme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelRecordsLayout.createSequentialGroup()
                                                .addComponent(labelExtremeName)
                                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(labelExtremePoints))

                                        .addComponent(labelUserSetting, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelRecordsLayout.createSequentialGroup()
                                                .addComponent(labelUserSettingName)
                                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(labelUserSettingPoints))

                                        .addComponent(btnGetInternetRank, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelRecordsLayout.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

                                        .addComponent(btnuserSettingGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelRecordsLayout.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

                                )

                                .addContainerGap()
                        ));
        panelRecordsLayout.setVerticalGroup(
                panelRecordsLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(panelRecordsLayout.createSequentialGroup()
                                .addComponent(Records)

                                .addGap(18, 18, 18)
                                .addComponent(labelEasy)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(panelRecordsLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(labelEasyPoints)
                                        .addComponent(labelEasyName))

                                .addGap(18, 18, 18)
                                .addComponent(labelMedium)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(panelRecordsLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(labelMediumPoints)
                                        .addComponent(labelMediumName))

                                .addGap(18, 18, 18)
                                .addComponent(labelHard)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(panelRecordsLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(labelHardPoints)
                                        .addComponent(labelHardName))

                                .addGap(18, 18, 18)
                                .addComponent(labelExtreme)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(panelRecordsLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(labelExtremePoints)
                                        .addComponent(labelExtremeName))

                                .addGap(18, 18, 18)
                                .addComponent(labelUserSetting)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(panelRecordsLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(labelUserSettingPoints)
                                        .addComponent(labelUserSettingName))

                                .addGap(18, 18, 18)
                                .addComponent(btnGetInternetRank, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)

                                .addGap(18, 18, 18)
                                .addComponent(btnuserSettingGame, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)


                                .addPreferredGap(ComponentPlacement.RELATED, 173, Short.MAX_VALUE)

                        ));
        panelRecords.setLayout(panelRecordsLayout);

        getContentPane().add(panelRecords, java.awt.BorderLayout.LINE_START);

        panelBtns.setLayout(new java.awt.GridLayout(2, 0));

        btnEasy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/easy.png"))); // NOI18N
        btnEasy.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEasyActionPerformed(evt);
            }
        });
        panelBtns.add(btnEasy);

        btnMedium.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/medium.png"))); // NOI18N
        //btnMedium.setText("Medium");
        btnMedium.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMediumActionPerformed(evt);
            }
        });
        panelBtns.add(btnMedium);

        btnHard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/hard.png"))); // NOI18N
        //btnHard.setText("Hard");
        btnHard.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHardActionPerformed(evt);
            }
        });
        panelBtns.add(btnHard);

        btnExtreme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/extreme.png"))); // NOI18N
        //btnExtreme.setText("Extreme");
        btnExtreme.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtremeActionPerfomed(evt);
            }
        });
        panelBtns.add(btnExtreme);

        btnTutorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pt/technic/apps/minesfinder/resources/tutorial.png"))); // NOI18N
        //btnTutorial.setText("Play Tutorial");
        btnTutorial.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTutorialActionPerformed(evt);
            }
        });
        panelBtns.add(btnTutorial);

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        panelBtns.add(btnExit);

        getContentPane().add(panelBtns, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void btnUserSettingGameActionPerfomed(java.awt.event.ActionEvent evt) {
        JTextField width = new JTextField(5);
        JTextField height = new JTextField(5);
        JTextField minesNum = new JTextField(5);

        JPanel userSettingGamePanel = new JPanel();
        userSettingGamePanel.add(new JLabel("가로"));
        userSettingGamePanel.add(width);
        userSettingGamePanel.add(new JLabel("세로"));
        userSettingGamePanel.add(height);
        userSettingGamePanel.add(new JLabel("지뢰 수"));
        userSettingGamePanel.add(minesNum);

        int userPushBtn = JOptionPane.showConfirmDialog(null, userSettingGamePanel, "가로,세로,지뢰의 수를 입력하세요.", JOptionPane.OK_CANCEL_OPTION);

        if (userPushBtn == JOptionPane.OK_OPTION) {
            bgm.suspend();
            GameWindow gameWindow = new GameWindow(new Minefield(Integer.parseInt(width.getText()), Integer.parseInt(height.getText()), Integer.parseInt(minesNum.getText())), recordUserSetting);
            setMinesTheme();
            gameWindow.setVisible(true);
        }
    }

    private void btnGetInternetRankActionPerformed(ActionEvent evt) {
        RankingWebView rankingWebView = new RankingWebView();
    }

    private void btnTutorialActionPerformed(java.awt.event.ActionEvent evt) {
        TutorialView tutorialView = new TutorialView();
    }

    public void btnExtremeActionPerfomed(java.awt.event.ActionEvent evt) {
        if (isExtremePlay()) {
            GameWindow gameWindow = new GameWindow(new Minefield(14, 14, 60), recordExtreme);
            setMinesTheme();

            bgm.suspend();
            gameWindow.setVisible(true);
        } else {
            return;
        }
    }

    public void btnEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEasyActionPerformed
        GameWindow gameWindow = new GameWindow(new Minefield(6, 6, 8), recordEasy);
        setMinesTheme();

        bgm.suspend();
        gameWindow.setVisible(true);
    }//GEN-LAST:event_btnEasyActionPerformed

    public void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    public void btnMediumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMediumActionPerformed
        GameWindow gameWindow = new GameWindow(new Minefield(8, 8, 20), recordMedium);
        setMinesTheme();

        bgm.suspend();
        gameWindow.setVisible(true);
    }//GEN-LAST:event_btnMediumActionPerformed

    public void btnHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHardActionPerformed
        GameWindow gameWindow = new GameWindow(new Minefield(10, 10, 36), recordHard);
        setMinesTheme();

        bgm.suspend();
        gameWindow.setVisible(true);
    }//GEN-LAST:event_btnHardActionPerformed

    private boolean isExtremePlay() {
        File f = new File(System.getProperty("user.dir") + File.separator + ".minesfinder.records");
        return f.canRead();
    }

    private void setMinesTheme() {
        String[] answer = {"지뢰", "꽃"};
        int userAnswer = JOptionPane.showOptionDialog(null, "지뢰 모양을 선택하세요.", "지뢰 모양 선택",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, answer, answer[0]);

        if (userAnswer == 0) {
            ButtonMinefield.setMineImage("/pt/technic/apps/minesfinder/resources/mines.png");
        } else if (userAnswer == 1) {
            ButtonMinefield.setMineImage("/pt/technic/apps/minesfinder/resources/flower.png");
        } else {
            ButtonMinefield.setMineImage("/pt/technic/apps/minesfinder/resources/mines.png");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */

        // input starting bgm
        bgm.start();

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MinesFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MinesFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MinesFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MinesFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MinesFinder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Records;
    private javax.swing.JButton btnEasy;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHard;
    private javax.swing.JButton btnMedium;
    private javax.swing.JButton btnExtreme;
    private javax.swing.JButton btnuserSettingGame;
    private javax.swing.JButton btnGetInternetRank;
    private javax.swing.JButton btnTutorial;
    private javax.swing.JLabel labelEasy;
    private javax.swing.JLabel labelEasyName;
    private javax.swing.JLabel labelEasyPoints;
    private javax.swing.JLabel labelHard;
    private javax.swing.JLabel labelHardName;
    private javax.swing.JLabel labelHardPoints;
    private javax.swing.JLabel labelExtreme;
    private javax.swing.JLabel labelExtremeName;
    private javax.swing.JLabel labelExtremePoints;
    private javax.swing.JLabel labelMedium;
    private javax.swing.JLabel labelMediumName;
    private javax.swing.JLabel labelMediumPoints;
    private javax.swing.JLabel labelUserSetting;
    private javax.swing.JLabel labelUserSettingName;
    private javax.swing.JLabel labelUserSettingPoints;
    private javax.swing.JPanel panelBtns;
    private javax.swing.JPanel panelRecords;
    private javax.swing.JLabel panelTitle;
}