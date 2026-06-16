package pckg_gui;

import pckg_model.VideoIgra;

import javax.swing.*;
import java.awt.*;

public class DodajIgruPanel extends JPanel {

    private GlavniProzor glavni;

    private JTextField txtNaziv;
    private JTextField txtDeveloper;
    private JTextField txtGodina;

    private JTextArea txtKomentar;

    private JRadioButton rbIgram;
    private JRadioButton rbZavrseno;
    private JRadioButton rbPlaniram;

    private JCheckBox cbMultiplayer;
    private JCheckBox cbSingleplayer;
    private JCheckBox cbOmiljeno;
    private JCheckBox cbPreporucujem;

    private JComboBox<String> cbZanr;
    private JComboBox<String> cbPlatforma;

    private JSlider sliderOcjena;
    private JLabel lblOcjenaVrijednost;

    private VideoIgra igra;

    public DodajIgruPanel(GlavniProzor glavni) {
        this.glavni = glavni;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        izgradiSucelje();
    }

    private void izgradiSucelje(){

        //OSNOVNI PODATCI
        JPanel pGornji = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4, 6, 4, 6);
        g.fill = GridBagConstraints.HORIZONTAL;

        //naziv
        g.gridx = 0;
        g.gridy = 0;
        g.weightx = 0;
        pGornji.add(new JLabel("Naziv igre:*"), g);
        txtNaziv = new JTextField(20);
        g.gridx = 1;
        g.weightx = 1;
        pGornji.add(txtNaziv, g);

        //developer
        g.gridx = 2;
        g.weightx = 0;
        pGornji.add(new JLabel("Developer:"), g);
        txtDeveloper = new JTextField(15);
        g.gridx = 3;
        g.weightx = 1;
        pGornji.add(txtDeveloper, g);

        //godina
        g.gridx = 0;
        g.gridy = 1;
        g.weightx = 0;
        pGornji.add(new JLabel("Godina:"), g);
        txtGodina = new JTextField(6);
        g.gridx = 1;
        g.weightx = 0;
        pGornji.add(txtGodina, g);

        //žanr
        g.gridx = 2;
        g.weightx = 0;
        pGornji.add(new JLabel("Žanr:"), g);
        String[] zanrovi = {"RPG", "FPS", "Open World", "Sandbox", "Strategija",
                "Sport", "Horror", "Platformer", "Simulacija", "Ostalo"};
        cbZanr = new JComboBox<>(zanrovi);
        g.gridx = 3;
        g.weightx = 1;
        pGornji.add(cbZanr, g);

        //platforma
        g.gridx = 0;
        g.gridy = 2;
        g.weightx = 0;
        pGornji.add(new JLabel("Platforma:"), g);
        String[] platforme = {"PC", "PlayStation 5", "PlayStation 4", "Xbox Series X",
                "Xbox One", "Nintendo Switch", "Mobile"};
        cbPlatforma = new JComboBox<>(platforme);
        g.gridx = 1;
        g.weightx = 1;
        pGornji.add(cbPlatforma, g);


        //STATUS
        JPanel pSrednji = new JPanel(new GridLayout(1,3,10,0));

        JPanel pStatus = new JPanel(new GridLayout(3,1,0,4));
        ButtonGroup bgStatus = new ButtonGroup();
        rbIgram = new JRadioButton("Igram");
        rbZavrseno = new JRadioButton("Završeno");
        rbPlaniram = new JRadioButton("Planiram igrati");
        rbZavrseno.setSelected(true);
        bgStatus.add(rbIgram);
        bgStatus.add(rbZavrseno);
        bgStatus.add(rbPlaniram);
        pStatus.add(rbIgram);
        pStatus.add(rbZavrseno);
        pStatus.add(rbPlaniram);

        //OZNAKE
        JPanel pOznake = new JPanel(new GridLayout(4, 1, 0, 4));
        cbMultiplayer = new JCheckBox("Multiplayer");
        cbSingleplayer = new JCheckBox("Singleplayer");
        cbOmiljeno = new JCheckBox("Omiljeno");
        cbPreporucujem = new JCheckBox("Preporučujem");
        pOznake.add(cbMultiplayer);
        pOznake.add(cbSingleplayer);
        pOznake.add(cbOmiljeno);
        pOznake.add(cbPreporucujem);

        //OCJENA
        JPanel pOcjena = new JPanel(new BorderLayout(0, 6));
        sliderOcjena = new JSlider(0, 10, 0);
        sliderOcjena.setMajorTickSpacing(1);
        sliderOcjena.setPaintTicks(true);
        sliderOcjena.setPaintLabels(true);
        sliderOcjena.setSnapToTicks(true);
        lblOcjenaVrijednost = new JLabel("Nije ocijenjeno", SwingConstants.CENTER);
        lblOcjenaVrijednost.setFont(new Font("Arial", Font.BOLD, 13));
        sliderOcjena.addChangeListener(e -> {
            int v = sliderOcjena.getValue();
            lblOcjenaVrijednost.setText( v + " / 10");
        });
        pOcjena.add(sliderOcjena, BorderLayout.CENTER);
        pOcjena.add(lblOcjenaVrijednost, BorderLayout.SOUTH);
        pSrednji.add(pStatus);
        pSrednji.add(pOznake);
        pSrednji.add(pOcjena);

        //komentar
        JPanel pKomentar = new JPanel(new BorderLayout());
        txtKomentar = new JTextArea(4, 40);
        txtKomentar.setLineWrap(true);
        txtKomentar.setWrapStyleWord(true);
        pKomentar.add(new JScrollPane(txtKomentar), BorderLayout.CENTER);

        //gumbi
        JPanel pGumbi = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        JButton btnOcisti = new JButton("Očisti");
        JButton btnSpremi = new JButton("Spremi igru");
        btnSpremi.setBackground(new Color(83, 74, 183));
        btnSpremi.setForeground(Color.WHITE);
        btnSpremi.setFocusPainted(false);
        btnOcisti.addActionListener(e -> ocisti());
        btnSpremi.addActionListener(e -> spremiIgru());
        pGumbi.add(btnOcisti);
        pGumbi.add(btnSpremi);

        JPanel pCentar = new JPanel();
        pCentar.setLayout(new BoxLayout(pCentar, BoxLayout.Y_AXIS));
        pCentar.add(pGornji);
        pCentar.add(Box.createVerticalStrut(8));
        pCentar.add(pSrednji);
        pCentar.add(Box.createVerticalStrut(8));
        pCentar.add(pKomentar);
        add(pCentar, BorderLayout.CENTER);
        add(pGumbi, BorderLayout.SOUTH);

        }

        private void spremiIgru() {
            String naziv = txtNaziv.getText().trim();
            if (naziv.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Naziv igre je obavezno polje",
                        "Greška unosa", JOptionPane.WARNING_MESSAGE);
                txtNaziv.requestFocus();
                return;
            }

            int godina = 0;
            String godinaStr = txtGodina.getText().trim();
            if (!godinaStr.isEmpty()) {
                try {
                    godina = Integer.parseInt(godinaStr);
                    if (godina < 1970 || godina > 2026) {
                        throw new NumberFormatException("Van raspona");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Godina mora biti od 1970 do 2026",
                            "Greška unosa", JOptionPane.WARNING_MESSAGE);
                    txtGodina.requestFocus();
                    return;
                }
            }

            String status;
            if (rbIgram.isSelected()) {
                status = "Igram";
            } else if (rbZavrseno.isSelected()) {
                status = "Završeno";
            } else {
                status = "Planiram igrati";
            }

            if (igra == null) {
                VideoIgra novaIgra = new VideoIgra(txtDeveloper.getText().trim(), godina,
                        txtKomentar.getText().trim(), cbMultiplayer.isSelected(), naziv, sliderOcjena.getValue(),
                        cbOmiljeno.isSelected(), (String) cbPlatforma.getSelectedItem(), cbPreporucujem.isSelected(),
                        cbSingleplayer.isSelected(), status, (String) cbZanr.getSelectedItem());

                glavni.dodajIgru(novaIgra);
                JOptionPane.showMessageDialog(this, "Igra" + naziv + "uspješno dodana",
                        "Dodano", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        public void ocisti() {
            igra = null;
            txtNaziv.setText("");
            txtDeveloper.setText("");
            txtGodina.setText("");
            txtKomentar.setText("");
            sliderOcjena.setValue(0);
            rbZavrseno.setSelected(true);
            cbMultiplayer.setSelected(false);
            cbSingleplayer.setSelected(false);
            cbOmiljeno.setSelected(false);
            cbPreporucujem.setSelected(false);
            cbZanr.setSelectedIndex(0);
            cbPlatforma.setSelectedIndex(0);
            txtNaziv.requestFocus();
        }






}


