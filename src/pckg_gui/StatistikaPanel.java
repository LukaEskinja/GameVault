package pckg_gui;

import pckg_model.VideoIgra;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        //Status barovi
        JPanel pProgress = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5,6,5,6);
        g.fill = GridBagConstraints.HORIZONTAL;

        pbZavrseno = noviProgressBar(new Color(59,109,17));
        pbIgram = noviProgressBar(new Color(24,95,165));
        pbPlaniram = noviProgressBar(new Color(133,79,11));

        g.gridx = 0;
        g.gridy = 0;
        g.weightx = 0;
        pProgress.add(new JLabel("Završeno"), g);
        g.gridx = 1;
        g.weightx = 1;
        pProgress.add(pbZavrseno, g);

        g.gridx = 0;
        g.gridy = 1;
        g.weightx = 0;
        pProgress.add(new JLabel("Igram"), g);
        g.gridx = 1;
        g.weightx = 1;
        pProgress.add(pbIgram, g);

        g.gridx = 0;
        g.gridy = 2;
        g.weightx = 0;
        pProgress.add(new JLabel("Planiram igrati"), g);
        g.gridx = 1;
        g.weightx = 1;
        pProgress.add(pbPlaniram, g);

        //ocjene
        JPanel pOcjene = new JPanel(new GridLayout(1,3,10,0));

        lblProsjecna = kartica("Prosječna ocjena","-",new Color(83,74,183));
        lblNajvisa = kartica("Najviša ocjena","-",new Color(59,109,17));
        lblNajniza = kartica("Najniža ocjena","-",new Color(163,45,45));

        pOcjene.add(lblProsjecna.getParent());
        pOcjene.add(lblNajvisa.getParent());
        pOcjene.add(lblNajniza.getParent());

        //dodatno(žanrovi,platvorme.oznake)
        JPanel pDodatno = new JPanel(new GridLayout(1, 4, 10, 0));

        lblNajZanr = kartica("Najzastupljeniji žanr", "-", new Color(30, 120, 120));
        lblNajPlatforma = kartica("Najzastupljenija platforma", "-", new Color(30, 120, 120));
        lblOmiljeni = kartica("Omiljeno", "-", new Color(180, 50, 100));
        lblPreporuceni = kartica("Preporučujem", "-", new Color(83, 74, 183));

        pDodatno.add(lblNajZanr.getParent());
        pDodatno.add(lblNajPlatforma.getParent());
        pDodatno.add(lblOmiljeni.getParent());
        pDodatno.add(lblPreporuceni.getParent());

        //slaganje
        pSve.add(pKartice);
        pSve.add(Box.createVerticalStrut(10));
        pSve.add(pProgress);
        pSve.add(Box.createVerticalStrut(10));
        pSve.add(pOcjene);
        pSve.add(Box.createVerticalStrut(10));
        pSve.add(pDodatno);

        add(new JScrollPane(pSve), BorderLayout.CENTER);

        //gumb za osvjezavanje
        JButton btnOsvjezi = new JButton("Osvježi statistiku");
        btnOsvjezi.addActionListener(e -> osvjezi());
        JPanel pDno = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pDno.add(btnOsvjezi);
        add(pDno, BorderLayout.SOUTH);

        osvjezi();
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

        pbZavrseno.setValue((int)(zavrseno * 100/ ukupno));
        pbIgram.setValue((int)(igram * 100 / ukupno));
        pbPlaniram.setValue((int)(planiram * 100 / ukupno));

        //ocjene
        List<VideoIgra> ocijenjene = igre.stream().filter(i -> i.getOcjena() > 0).collect(Collectors.toList());

        if (ocijenjene.isEmpty()) {
            lblNajniza.setText("-");
            lblNajvisa.setText("-");
            lblProsjecna.setText("-");
        } else {
            double prosjek = ocijenjene.stream().mapToInt(VideoIgra::getOcjena).average().orElse(0);
            lblProsjecna.setText(String.format("%.1f/10",prosjek));

            VideoIgra najvisa = ocijenjene.stream().max(Comparator.comparingInt(VideoIgra::getOcjena)).orElse(null);
            VideoIgra najniza = ocijenjene.stream().min(Comparator.comparingInt(VideoIgra::getOcjena)).orElse(null);

            if(najvisa != null) {
                lblNajvisa.setText(formatIgra(najvisa));
            }

            if(najniza != null){
                lblNajniza.setText(formatIgra(najniza));
            }

        }
        //dodatno(zanimljive informacije na dnu prozora)
        igre.stream().collect(Collectors.groupingBy(VideoIgra::getZanr, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .ifPresent(e -> lblNajZanr.setText(e.getKey() + " (" + e.getValue() + ")"));

        igre.stream().collect(Collectors.groupingBy(VideoIgra::getPlatforma, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .ifPresent(e -> lblNajPlatforma.setText(e.getKey() + " (" + e.getValue() + ")"));

        long omiljenih     = igre.stream().filter(VideoIgra::isOmiljeno).count();
        long preporucenih  = igre.stream().filter(VideoIgra::isPreporucujem).count();
        lblOmiljeni.setText(String.valueOf(omiljenih));
        lblPreporuceni.setText(String.valueOf(preporucenih));
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

    private JProgressBar noviProgressBar(Color boja){
        JProgressBar pb = new JProgressBar(0,100);
        pb.setStringPainted(true);
        pb.setForeground(boja);
        pb.setPreferredSize(new Dimension(400,22));
        return pb;
    }

    private String formatIgra(VideoIgra igra) {
        return igra.getNaziv() + " (" + igra.getOcjena() + "/10)";
    }


}
