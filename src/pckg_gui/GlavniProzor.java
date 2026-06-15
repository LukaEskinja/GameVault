package pckg_gui;

import javax.swing.*;

public class GlavniProzor extends JFrame {

    private DodajIgruPanel dodajPanel;

    public GlavniProzor(){
        setTitle("GameVault");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 620);
        setLocationRelativeTo(null);

        izgradiSucelje();
        setVisible(true);
    }

    private void izgradiSucelje() {
        dodajPanel = new DodajIgruPanel(this);
        add(dodajPanel);

    }

}
