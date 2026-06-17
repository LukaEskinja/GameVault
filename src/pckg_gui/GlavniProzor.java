package pckg_gui;

import pckg_io.UpravljanjeDatotekama;
import pckg_model.VideoIgra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GlavniProzor extends JFrame {

    private DodajIgruPanel dodajPanel;
    private List<VideoIgra> listaIgara;
    private ListaPanel listaPanel;
    private JTabbedPane tabovi;
    private StatistikaPanel statistikaPanel;

    public GlavniProzor(){
        setTitle("GameVault");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 620);
        setLocationRelativeTo(null);

        ucitajIgre();
        izgradiSucelje();
        izgradiMenuBar();

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
        statistikaPanel = new StatistikaPanel(this);

        tabovi = new JTabbedPane();
        tabovi.addTab("Dodaj igru", dodajPanel);
        tabovi.addTab("Moja lista", listaPanel);
        tabovi.addTab("Statistika",statistikaPanel);

        tabovi.addChangeListener(e -> {
            int idx = tabovi.getSelectedIndex();
            if (idx == 1) listaPanel.osvjezi();
            if (idx == 2) statistikaPanel.osvjezi();
        });

        add(tabovi, BorderLayout.CENTER);
    }

    private void izgradiMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuDatoteka = new JMenu("Datoteka");
        menuDatoteka.setMnemonic(KeyEvent.VK_D);

        JMenuItem miSpremi = new JMenuItem("Spremi");
        miSpremi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        miSpremi.addActionListener(e -> spremiIgre());

        JMenuItem miIzvoz = new JMenuItem("Izvezi u txt.");
        miIzvoz.addActionListener(e -> izvezi());


        menuDatoteka.add(miSpremi);
        menuDatoteka.add(miIzvoz);

        menuBar.add(menuDatoteka);
        setJMenuBar(menuBar);
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

    private void izvezi() {
        try{
            UpravljanjeDatotekama.izvozListeUTxt(listaIgara);
            JOptionPane.showMessageDialog(this,"Lista uspješno izvezena","Izvezena",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,"Greška pri izvozu","Greška",JOptionPane.ERROR_MESSAGE);
        }

    }

    public void prebaciNaLIstu() {
        tabovi.setSelectedIndex(1);
        listaPanel.osvjezi();
    }

}
