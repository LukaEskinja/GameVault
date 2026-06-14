package pckg_io;

import pckg_model.VideoIgra;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UpravljanjeDatotekama {

    private static final String IGRE_DATA = "DATA/igre.ser";


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





}
