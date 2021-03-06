package pt.technic.apps.minesfinder.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * create NamYounSu, LeeJeongHun, JeongWu
 *
 */
public class RankingWebView {
    public RankingWebView() { // not work internet explorer.
        try {
            JOptionPane.showMessageDialog(null, "인터넷 익스플로어에서는 작동하지 않습니다.", "Show Internet Rank", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "만약 인터넷 익스플로어에서 뜨면, 크롬에서 링크를 실행해주세요.", "Show Internet Rank", JOptionPane.INFORMATION_MESSAGE);

            Desktop.getDesktop().browse(new URI("https://minesfinder-rank.firebaseapp.com"));
        } catch (URISyntaxException e) {
            Logger.getLogger(RankingWebView.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(RankingWebView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}