package pckg_model;

import pckg_io.UpravljanjeDatotekama;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {

        VideoIgra v1 = new VideoIgra("Rockstar",2004,"",false,"GTA San Andreas",
                9,true,"PC/CONSOLE",true,true,"","Story");

        VideoIgra v2 = new VideoIgra("Rockstar",2012,"",true,"GTA V",
                8,true,"PC/CONSOLE",true,true,"","Story");

        VideoIgra v3 = new VideoIgra("Unity",2013,"",true,"Far cry 3",
                10,true,"PC/CONSOLE",true,true,"","Story");

        List<VideoIgra> l1 = new ArrayList<>();
        l1.add(v1);
        l1.add(v2);
        l1.add(v3);
        Collections.sort(l1);

        try {
            UpravljanjeDatotekama.spremiIgre(l1);
            System.out.println("igre spremljene");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try{
            List<VideoIgra> ucitane = UpravljanjeDatotekama.ucitajIgre();
            Collections.sort(ucitane);
            for (VideoIgra ig : ucitane) {
                System.out.println(ig);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            UpravljanjeDatotekama.provjeriKorisnika("Luka","12345");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            UpravljanjeDatotekama.provjeriKorisnika("Marko","12345");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            UpravljanjeDatotekama.izvozListeUTxt(l1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
