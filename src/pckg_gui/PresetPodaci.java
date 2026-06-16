package pckg_gui;

import pckg_model.VideoIgra;

import java.util.ArrayList;
import java.util.List;

public class PresetPodaci {

    public static List<VideoIgra> dajPresetIgre() {
        List<VideoIgra> igre = new ArrayList<>();

        igre.add(new VideoIgra(
                "CD Projekt Red", 2015,
                "Jedna od najboljih RPG igara svih vremena. Priča, world-building i likovi su iznimni.",
                false, "The Witcher 3: Wild Hunt", 10, true, "PC",
                true, true, "Završeno", "RPG"
        ));

        igre.add(new VideoIgra(
                "Rockstar Games", 2013,
                "Nevjerojatan open world s bogatim sadržajem i odličnim online modom.",
                true, "Grand Theft Auto V", 9, false, "PC",
                true, true, "Završeno", "Open World"
        ));

        igre.add(new VideoIgra(
                "Mojang Studios", 2011, "Beskonačna kreativnost. Nikad se ne zamori.",
                true, "Minecraft", 8, true, "PC", true,
                true, "Igram", "Sandbox"
        ));

        igre.add(new VideoIgra(
                "CD Projekt Red", 2020,
                "", false, "Cyberpunk 2077",
                0, false, "PC",
                false, true, "Planiram igrati", "RPG"
        ));

        igre.add(new VideoIgra(
                "Rockstar Games",
                2019, "Remek djelo. Priča i atmosfera su neusporedive.",
                false, "Red Dead Redemption 2", 10, true,
                "PC", true, true, "Završeno", "Open World"
        ));

        igre.add(new VideoIgra(
                "Valve", 2023, "Kompetitivni klasik, ali zna biti frustrirajuć.",
                true, "Counter-Strike 2", 7, false, "PC",
                false, false, "Igram", "FPS"
        ));

        return igre;
    }
}

