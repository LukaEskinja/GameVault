package pckg_model;

import pckg_io.UpravljanjeDatotekama;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {

        VideoIgra v1 = new VideoIgra("Rockstar",2004,"",false,"GTA San Andreas",
                9,true,"PC/CONSOLE",true,true,"","Story");

        VideoIgra v2 = new VideoIgra("Rockstar",2012,"",true,"GTA V",
                8,true,"PC/CONSOLE",true,true,"","Story");

        List<VideoIgra> l1 = new ArrayList<>();
        l1.add(v1);
        l1.add(v2);

        try {
            UpravljanjeDatotekama.spremiIgre(l1);
            System.out.println("igre spremljene");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try{
            List<VideoIgra> ucitane = UpravljanjeDatotekama.ucitajIgre();
            for (VideoIgra ig : ucitane) {
                System.out.println(ig);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
