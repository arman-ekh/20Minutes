package io.github.TwentyMinUtesTillDown.Models;

import com.badlogic.gdx.Gdx;

import java.awt.*;

public class MouseUtils {
    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void moveMouseTo(int screenX, int screenY) {
        if (robot == null) return;


        Point windowLocation = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds().getLocation();

        int finalX = screenX -250;
        int finalY = screenY ;


        robot.mouseMove(finalX, finalY);
    }
}
