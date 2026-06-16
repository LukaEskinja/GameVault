package pckg_gui;

import pckg_model.VideoIgra;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaPanel extends JPanel{

    private GlavniProzor glavni;
    private DefaultTableModel modelTablice;
    private JTextField txtPretraga;
    private JComboBox<String> cbFilterStatus;
    private JComboBox<String> cbFilterZanr;
    private JTable tablica;

    private static final String[] STUPCI = {"Naziv", "Developer", "Godina", "Žanr", "Platforma",
            "Status", "Ocjena", "Omiljeno"
    };

    public ListaPanel(GlavniProzor glavni) {
        this.glavni = glavni;
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(10,14,10,14));
        izgradiSucelje();
    }

    private void izgradiSucelje(){

        //pretraga
        JPanel pFilter = new JPanel(new FlowLayout(FlowLayout.LEFT,8,4));
        pFilter.add(new JLabel("Pretraži:"));
        txtPretraga = new JTextField(16);
        pFilter.add(txtPretraga);

        pFilter.add(new JLabel("Status:"));
        cbFilterStatus = new JComboBox<>(new String[]{"Svi statusi", "Igram", "Završeno", "Planiram igrati"});
        pFilter.add(cbFilterStatus);

        pFilter.add(new JLabel("Žanr:"));
        cbFilterZanr = new JComboBox<>(new String[]{"Svi žanrovi", "RPG", "FPS", "Open World", "Sandbox",
                "Strategija", "Sport", "Horror", "Platformer", "Simulacija", "Ostalo"});
        pFilter.add(cbFilterZanr);

        JButton btnFilter = new JButton("Filtriraj");
        btnFilter.addActionListener(e -> osvjezi());
        pFilter.add(btnFilter);

        JButton btnOcisti = new JButton("Poništi filter");
        btnOcisti.addActionListener(e -> {
            txtPretraga.setText("");
            cbFilterStatus.setSelectedIndex(0);
            cbFilterZanr.setSelectedIndex(0);
            osvjezi();
        });
        pFilter.add(btnOcisti);

        //Tablica

        modelTablice = new DefaultTableModel(STUPCI,0){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tablica = new JTable(modelTablice);
        tablica.setRowHeight(26);
        tablica.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablica.getTableHeader().setFont(new Font("Arial",Font.BOLD,12));
        tablica.setFont(new Font("Arial", Font.PLAIN, 12));

        int[] sirine = {200, 140, 60, 90, 120, 110, 60, 30};
        for (int i = 0; i < sirine.length; i++) {
            tablica.getColumnModel().getColumn(i).setPreferredWidth(sirine[i]);
        }

        JScrollPane scroll = new JScrollPane(tablica);

        JPanel pGumbi = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 4));
        JButton btnUredi = new JButton("Uredi");
        //napisati metodu
        JButton btnObrisi = new JButton("Obriši");
        btnObrisi.addActionListener(e -> obrisiOdabranu());

        JLabel lblBroj = new JLabel();
        pGumbi.add(lblBroj);
        pGumbi.add(Box.createHorizontalStrut(40));
        pGumbi.add(btnUredi);
        pGumbi.add(btnObrisi);

        add(pFilter, BorderLayout.NORTH);
        add(scroll,  BorderLayout.CENTER);
        add(pGumbi,  BorderLayout.SOUTH);

        osvjezi();
    }


    //osvjezi
    public void osvjezi() {
        modelTablice.setRowCount(0);

        String pretraga = txtPretraga.getText().trim().toLowerCase();
        String filterStatus = (String) cbFilterStatus.getSelectedItem();
        String filterZanr = (String) cbFilterZanr.getSelectedItem();

        for (VideoIgra ig : glavni.getListaIgara()) {

            boolean odgovaraPretrazi = pretraga.isEmpty()
                    || ig.getNaziv().toLowerCase().contains(pretraga)
                    || ig.getDeveloper().toLowerCase().contains(pretraga);

            boolean odgovaraStatusu = "Svi statusi".equals(filterStatus)
                    || ig.getStatus().equals(filterStatus);

            boolean odgovaraZanru = "Svi žanrovi".equals(filterZanr)
                    || ig.getZanr().equals(filterZanr);

            if (!odgovaraPretrazi || !odgovaraStatusu || !odgovaraZanru) {
                continue;
            }

            modelTablice.addRow(new Object[]{ig.getNaziv(),ig.getDeveloper(),ig.getGodina(),
            ig.getZanr(),ig.getPlatforma(),ig.getStatus(),ig.getOcjena(),ig.isOmiljeno()});

        }



    }

    // gumbi za brisanje i uređivanje igara

    private int getOdabraniIndeks() {
        int row = tablica.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this,"Odaberi igru iz liste","nema odabira",
                    JOptionPane.INFORMATION_MESSAGE);
            return  -1;
        }
        String naziv = (String) modelTablice.getValueAt(row,0);
        List<VideoIgra> sve = glavni.getListaIgara();
        for (int i = 0; i < sve.size(); i++) {
            if(sve.get(i).getNaziv().equals(naziv)){
                return i;
            }
        }
        return -1;
    }

    private void obrisiOdabranu() {
        int idx = getOdabraniIndeks();
        if (idx < 0){
            return;
        }
        VideoIgra igra = glavni.getListaIgara().get(idx);

        int potvrda = JOptionPane.showConfirmDialog(this,"Sigurno želiš izbrisati ovu igru " + igra.getNaziv(),
                "Potvrda brisanja", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

        if (potvrda == JOptionPane.YES_OPTION) {
            glavni.ukloniIgru(igra);
            osvjezi();
        }

    }


}
