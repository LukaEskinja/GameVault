package pckg_gui;

import pckg_io.UpravljanjeDatotekama;
import pckg_model.VideoIgra;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GlavniProzor extends JFrame {

    private DodajIgruPanel dodajPanel;
    private List<VideoIgra> listaIgara;

    public GlavniProzor(){
        setTitle("GameVault");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 620);
        setLocationRelativeTo(null);

        ucitajIgre();
        izgradiSucelje();

        setVisible(true);
    }

    private void ucitajIgre() {
        listaIgara = new ArrayList<>();
    }

    private void izgradiSucelje() {
        dodajPanel = new DodajIgruPanel(this);
        add(dodajPanel);

    }

    //za panele

    public List<VideoIgra> getListaIgara() {
        return listaIgara;
    }

    public void dodajIgru(VideoIgra igra){
        listaIgara.add(igra);
        spremiIgre();
    }

    public void spremiIgre() {
        try{
            UpravljanjeDatotekama.spremiIgre(listaIgara);
            setTitle("Gamevault Spremljeno");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Greška pri spremanju" + e.getMessage(),
                    "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

}
