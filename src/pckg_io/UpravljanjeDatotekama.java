package pckg_io;

import pckg_model.VideoIgra;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UpravljanjeDatotekama {

    private static final String IGRE_DATA = "DATA/igre.ser";
    private static final String KORISNICI = "DATA/korisnici.txt";
    private static final String IZVOZ= "DATA/izvoz.txt";


    //upravljanje sa igre.ser

    public static void spremiIgre(List<VideoIgra> igre) throws FileNotFoundException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(IGRE_DATA))) {
            oos.writeObject(igre);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<VideoIgra> ucitajIgre() {
        File f = new File(IGRE_DATA);
        if (!f.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<VideoIgra>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //korisnici

    public static boolean provjeriKorisnika(String korisnickoIme, String lozinka) throws IOException {
        File f = new File(KORISNICI);
        if (!f.exists()){
            throw new IOException("Datoteka korisnici.txt nije pronađena");
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))){
            String linija;
            while ((linija = br.readLine()) != null){
                linija = linija.trim();
                if (linija.isEmpty()){
                    continue;
                }
                String[] dijelovi = linija.split(":");
                if (dijelovi.length == 2) {
                    if (dijelovi[0].equals(korisnickoIme) && dijelovi[1].equals(lozinka)) {
                        System.out.println("Korisink pronađen");
                        return true;
                    }
                }
            }
        }
        System.out.println("Korisink nije pronađen");
        return false;
    }

    //izvoz liste

    public static void izvozListeUTxt(List<VideoIgra> igre) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(IZVOZ))){
            pw.println("=============");
            pw.println("GameVault - Ranglista");
            pw.println("=============");
            pw.println();

            int br = 1;
            for (VideoIgra ig : igre) {
            pw.println(br + "." + ig.getNaziv() + " (" + ig.getGodina() + ")");
            pw.println(" Developer: " + ig.getDeveloper());
            pw.println(" Žanr: " + ig.getZanr());
            pw.println(" Platforma: " + ig.getPlatforma());
            pw.println(" Status    : " + ig.getStatus());
            pw.println(" Ocjena    : " + ig.getOcjena() + "/10");
            if (!ig.getKomentar().isBlank()) {
                pw.println(" Komentar : " + ig.getKomentar());
            }
            pw.println();
            br++;
            }
            pw.println("Ukupno igrara:" + igre.size());
        }
    }





}
