package pt.technic.apps.minesfinder.controller;

import pt.technic.apps.minesfinder.view.ButtonMinefield;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ContentViewCtrl {
    private static String imageMineValue;
    private static String imageResourceValue;

    public static void setMineImage(String value) {
        try {
            imageMineValue = "/pt/technic/apps/minesfinder/resources/" + value + ".png";
        } catch (NullPointerException e) {
            Logger.getLogger(ButtonMinefield.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static String getMineImage() {
        return imageMineValue;
    }

    public static String setImageResources(String value) {
        try {
            imageResourceValue = "/pt/technic/apps/minesfinder/resources/" + value + ".png";
        } catch (NullPointerException e) {
            Logger.getLogger(ButtonMinefield.class.getName()).log(Level.SEVERE, null, e);
        }

        return imageResourceValue;
    }

}
