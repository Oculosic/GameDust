package com.mygdx.game;
import java.awt.Toolkit;
import java.awt.Dimension;

public class screen {
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int getScreenWidth(){
        return (int) screenSize.getWidth();
    }
    public static int getScreenHeight(){
        return (int) screenSize.getHeight();
    }
}
