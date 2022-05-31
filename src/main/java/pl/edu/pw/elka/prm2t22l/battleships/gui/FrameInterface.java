package pl.edu.pw.elka.prm2t22l.battleships.gui;

import javax.swing.*;
import java.awt.*;

public class FrameInterface extends JFrame {
    public FrameInterface() {

        Image icon = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\warship.png");
        setIconImage(icon);
        setTitle("BATTLESHIPS");
        setResizable(false);
        setSize(600,600);
        try {
            add(new InterfaceMainMenu());  //PanelMainManu to nazwa klasy zawierającej panel main menu
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
