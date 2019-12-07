package pt.technic.apps.minesfinder.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * create NamYounSu, LeeJeongHun, JeongWu
 *
 */
public class TutorialView {
    public TutorialView() {
        initialViewer();
        playTutorial();
    }

    private void initialViewer() {
        JOptionPane.showMessageDialog(null, "안녕하세요, 지뢰찾기 튜토리얼이에요.",
                "Tutorial", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "지뢰찾기는 기본적으로 지뢰를 클릭하지 않고 다른 땅을 클릭하는 게임입니다.",
                "Tutorial", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "입력은 마우스만 있으면 되요 :)",
                "Tutorial", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "그럼, 튜토리얼을 시작해볼까요?",
                "Tutorial", JOptionPane.INFORMATION_MESSAGE);
    }

    private void playTutorial() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            Logger.getLogger(TutorialView.class.getName()).log(Level.SEVERE, null, e);
        }

        JOptionPane.showMessageDialog(null, "게임창이 뜨면 움직이지 마시고 확인을 눌러주세요.",
                "Tutorial InGame", JOptionPane.INFORMATION_MESSAGE);
        GameWindow gameWindow = new GameWindow(new Minefield(4, 4, 4), null); // selection one things
        gameWindow.setVisible(true);

        JOptionPane.showMessageDialog(null, "기본적으로 위에선 난이도선택, 도움말등을 볼 수 있어요.",
                "Tutorial InGame", JOptionPane.INFORMATION_MESSAGE);
        robot.mouseMove(110,32);
        JOptionPane.showMessageDialog(null, "또한 칸을 클릭하면 상황에 따라 넓어지거나, 인접한 지뢰의 숫자가 표시됩니다. 운이 안좋으면 지뢰를 밟겠지요.",
                "Tutorial InGame", JOptionPane.INFORMATION_MESSAGE);
        robot.mouseMove(150, 150);

        JOptionPane.showMessageDialog(null, "또한 우측 클릭을 해서 지뢰를 체크하거나 알 수 없는곳을 표시할 수 있어요.",
                "Tutorial InGame", JOptionPane.INFORMATION_MESSAGE);
        robot.mousePress(InputEvent.BUTTON2_DOWN_MASK); // windows상에서는 권한 부족으로 클릭이 되지 않음.
        robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);

        JOptionPane.showMessageDialog(null, "그럼, 이대로 클리어 해서 본게임에서 만나요!",
                "Tutorial InGame", JOptionPane.INFORMATION_MESSAGE);
    }
}
