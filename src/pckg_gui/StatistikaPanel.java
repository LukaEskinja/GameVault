package pckg_gui;

import pckg_model.VideoIgra;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatistikaPanel extends JPanel {

    private GlavniProzor glavni;

    private JLabel lblUkupno;
    private JLabel lblVrijednost;

    private JLabel lblZavrseno;
    private JLabel lblIgram;
    private JLabel lblPlaniram;

    private JLabel lblNajZanr;
    private JLabel lblNajPlatforma;
    private JLabel lblOmiljeni;
    private JLabel lblPreporuceni;

    private JLabel lblProsjecna;
    private JLabel lblNajvisa;
    private JLabel lblNajniza;
    private JLabel getLblProsjecna;

    private JProgressBar pbZavrseno, pbIgram, pbPlaniram;


    public StatistikaPanel(GlavniProzor glavni){
        this.glavni = glavni;
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(14,18,14,18));
        izgradiSucelje();
    }

    private void izgradiSucelje() {
        JPanel pSve = new JPanel();
        pSve.setLayout(new BoxLayout(pSve, BoxLayout.Y_AXIS));

        JPanel pKartice = new JPanel(new GridLayout(1,4,10,0));

        lblUkupno = kartica("Ukupno igara","",new Color(83,74,183));
        lblZavrseno = kartica("Završeno", "", new Color(59, 109, 17));
        lblIgram   = kartica("Igram", "", new Color(24, 95, 165));
        lblPlaniram = kartica("Planiram igrati", "", new Color(133, 79, 11));

        pKartice.add(lblUkupno.getParent());
        pKartice.add(lblZavrseno.getParent());
        pKartice.add(lblIgram.getParent());
        pKartice.add(lblPlaniram.getParent());

        pSve.add(pKartice);
        add(new JScrollPane(pSve), BorderLayout.CENTER);
    }

    //osvjezavanje
    public void osvjezi(){
        List<VideoIgra> igre = glavni.getListaIgara();
        int ukupno = igre.size();

        if (ukupno == 0) {
            lblUkupno.setText("0");
            lblZavrseno.setText("0"); lblIgram.setText("0"); lblPlaniram.setText("0");
            lblProsjecna.setText("-"); lblNajvisa.setText("-"); lblNajniza.setText("-");
            lblNajZanr.setText("-"); lblNajPlatforma.setText("-");
            lblOmiljeni.setText("0"); lblPreporuceni.setText("0");
            pbZavrseno.setValue(0); pbIgram.setValue(0); pbPlaniram.setValue(0);
            return;
        }

        long zavrseno = igre.stream().filter(i ->"Završeno".equals(i.getStatus())).count();
        long igram = igre.stream().filter(i ->"Igram".equals(i.getStatus())).count();
        long planiram = igre.stream().filter(i ->"Planiram igrati".equals(i.getStatus())).count();

        lblUkupno.setText(String.valueOf(ukupno));
        lblZavrseno.setText(String.valueOf(zavrseno));
        lblIgram.setText(String.valueOf(igram));
        lblPlaniram.setText(String.valueOf(planiram));

    }

    //pomocne metode

    private JLabel kartica(String naslov,String vrijednost, Color boja){
        JPanel p = new JPanel(new BorderLayout(0,4));
        p.setBackground(new Color(245,245,250));
        p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200,200,200)
                ),BorderFactory.createEmptyBorder(10,12,10,12)));

        JLabel lblNaslov = new JLabel(naslov, SwingConstants.CENTER);
        lblNaslov.setFont(new Font("Arial",Font.PLAIN,11));
        lblNaslov.setForeground(Color.GRAY);

        JLabel lblVrijednost = new JLabel(vrijednost, SwingConstants.CENTER);
        lblVrijednost.setFont(new Font("Arial",Font.BOLD,20));
        lblVrijednost.setForeground(boja);

        p.add(lblNaslov,BorderLayout.NORTH);
        p.add(lblVrijednost,BorderLayout.CENTER);

        return lblVrijednost;
    }


}
