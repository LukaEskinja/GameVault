package pckg_gui;

import javax.swing.*;
import java.awt.*;

public class LoginProzor extends JFrame {

    private JTextField txtKorisnik;
    private JPasswordField txtLozinka;
    private JButton btnPrijava;
    private JLabel lblStatus;

    public LoginProzor() {
        setTitle("GameVault - Prijava");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(380, 280);
        setLocationRelativeTo(null);
        setResizable(false);

        izgradiSucelje();
        setVisible(true);
    }

    private void izgradiSucelje() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6,4,6,4);

        //Naslov i podnalsov
        JLabel lblNaslov = new JLabel(" GameVault ",SwingConstants.CENTER);
        lblNaslov.setFont(new Font("Arial", Font.BOLD, 12));
        lblNaslov.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblNaslov, gbc);

        JLabel lblPodnaslov = new JLabel("Prijava u aplikaciju", SwingConstants.CENTER);
        lblPodnaslov.setFont(new Font("Arial", Font.PLAIN,12));
        lblPodnaslov.setForeground(Color.GRAY);
        gbc.gridy = 1;
        panel.add(lblPodnaslov, gbc);

        //Ime(Korisnika)

        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel lblkorisnik = new JLabel("Korisničko ime:");
        lblkorisnik.setForeground(Color.WHITE);
        panel.add(lblkorisnik, gbc);
        txtKorisnik = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtKorisnik, gbc);

        //Lozinka

        gbc.gridy = 3;
        gbc.gridx = 0;
        JLabel lbllozinka = new JLabel("Lozinka:");
        lbllozinka.setForeground(Color.WHITE);
        panel.add(lbllozinka, gbc);
        txtLozinka = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(txtLozinka, gbc);

        //Gumb

        btnPrijava = new JButton("Prijava");
        btnPrijava.setFont(new Font("Arial", Font.BOLD, 13));
        btnPrijava.setBackground(new Color(83, 74, 183));
        btnPrijava.setForeground(Color.WHITE);
        btnPrijava.setFocusPainted(false);
        gbc.gridy = 5;
        panel.add(btnPrijava, gbc);




        panel.setBackground(new Color(40, 40, 50));
        add(panel);
    }
}
