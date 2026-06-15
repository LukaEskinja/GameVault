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
    private JLabel lblOcijenaVrijednost;

    private VideoIgra igra;

    public DodajIgruPanel(GlavniProzor glavni) {
        this.glavni = glavni;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        izgradiSucelje();
        //visible
    }

    private void izgradiSucelje(){
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

        add(pGornji, BorderLayout.NORTH);
    }

}
