package pt.technic.apps.minesfinder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeveloperView extends JDialog{
    public DeveloperView() {
        setTitle("Developer");
        setSize(300, 200);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 1));
        p1.add(new JLabel("201811222 남윤수", 0));
        p1.add(new JLabel("201413311 이정훈", 0));
        p1.add(new JLabel("201800000 정 우", 0));
        p1.add(new JLabel("Version 1.0", 0));

        // 확인 버튼
        JPanel p2 = new JPanel();
        JButton ok = new JButton("OK");
        p2.add(ok);

        getContentPane().add(p1, "Center");
        getContentPane().add(p2, "South");

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setVisible(false);
            }
        });
    }
}
