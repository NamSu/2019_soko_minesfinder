package pt.technic.apps.minesfinder;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;

public class RankingWebView {
    public static void getOpenRankingView() {
        JFrame frame = new JFrame("Show Internet Ranking");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.getContentPane().setLayout(null);

        final JFXPanel jfxPanel = new JFXPanel();

        // to make visible viewpage true
        frame.add(jfxPanel);
        frame.setVisible(true);
        // have problem in jfxpanel. is resized GameWindow panels.
        jfxPanel.setSize(1024, 768);

        frame.getContentPane().setPreferredSize(new Dimension(1024, 768));
        frame.pack();
        frame.setResizable(false);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setLoadRankingView(jfxPanel);
            }
        });
    }

    private static void setLoadRankingView(final JFXPanel jfxPanel) {
        Group group = new Group();
        Scene scene = new Scene(group);
        jfxPanel.setScene(scene);

        WebView webView = new WebView();

        group.getChildren().add(webView);
        webView.setMinSize(1024, 768);
        webView.setMaxSize(1024, 768);

        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://minesfinder-rank.web.app");
    }
}
