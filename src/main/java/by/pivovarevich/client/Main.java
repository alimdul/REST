package by.pivovarevich.client;

import by.pivovarevich.client.controller.Handler;
import by.pivovarevich.client.view.MainFrame;

import java.awt.*;

public class Main {
    public static void main(String args[]){
        Handler handler = new Handler();
        MainFrame mainFrame = new MainFrame("Справочник по цветам", new Dimension(600,400), handler);
        mainFrame.init();
    }
}
