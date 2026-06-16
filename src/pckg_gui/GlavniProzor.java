package pckg_gui;

import pckg_io.UpravljanjeDatotekama;
import pckg_model.VideoIgra;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GlavniProzor extends JFrame {

    private DodajIgruPanel dodajPanel;
    private List<VideoIgra> listaIgara;
    private ListaPanel listaPanel;
    private JTabbedPane tabovi;

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
        try{
            listaIgara = UpravljanjeDatotekama.ucitajIgre();
            if (listaIgara.isEmpty()){
                listaIgara = PresetPodaci.dajPresetIgre();
                UpravljanjeDatotekama.spremiIgre(listaIgara);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this,"Greška pri učitavanju igara" +
                    e.getMessage(),"Greška", JOptionPane.WARNING_MESSAGE);
            listaIgara = new ArrayList<>();

        }
    }

    private void izgradiSucelje() {
        dodajPanel = new DodajIgruPanel(this);
        listaPanel = new ListaPanel(this);

        tabovi = new JTabbedPane();
        tabovi.addTab("Dodaj igru", dodajPanel);
        tabovi.addTab("Moja lista", listaPanel);

        tabovi.addChangeListener(e -> {
            int idx = tabovi.getSelectedIndex();
            if (idx == 1) listaPanel.osvjezi();
        });

        add(tabovi, BorderLayout.CENTER);
    }

    //za panele

    public List<VideoIgra> getListaIgara() {
        return listaIgara;
    }

    public void dodajIgru(VideoIgra igra){
        listaIgara.add(igra);
        spremiIgre();
    }

    public void ukloniIgru(VideoIgra igra) {
        listaIgara.remove(igra);
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
