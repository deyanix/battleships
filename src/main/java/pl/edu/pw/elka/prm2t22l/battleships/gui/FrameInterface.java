package pl.edu.pw.elka.prm2t22l.battleships.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameInterface extends JFrame implements ActionListener {

    private InterfaceMainMenu mainMenu;
    private InterfaceConfigWindow configWindow;

    public FrameInterface() {

        Image icon = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\warship.png");
        setIconImage(icon);
        setTitle("BATTLESHIPS");
        setResizable(false);
        setSize(600,600);
        try {

            mainMenu=new InterfaceMainMenu();
            mainMenu.addActionListener(this);
            setContentPane(mainMenu);
            configWindow=new InterfaceConfigWindow();
            configWindow.addActionListener(this);

            //setContentPane(new InterfaceMainMenu());  //PanelMainManu to nazwa klasy zawierającej panel main menu
            //setContentPane(new InterfaceConfigWindow());
/*
            cPanelsContainer = getContentPane();
            clMainCardLayout = new CardLayout();
            cPanelsContainer.setLayout(clMainCardLayout);

            cPanelsContainer.add(new InterfaceMainMenu());
            cPanelsContainer.add(new InterfaceConfigWindow());
*/

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ChangePanel")) {
            ChangePanelEvent changePanelEvent = (ChangePanelEvent) e;
            try {
                switch (changePanelEvent.getTarget()) {
                    case 1: setContentPane(new InterfaceMainMenu()); break;
                    case 2: setContentPane(new InterfaceConfigWindow()); break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
