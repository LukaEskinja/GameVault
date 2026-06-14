package pckg_model;

import java.io.Serializable;

public class VideoIgra implements Serializable,Comparable<VideoIgra> {

    private static final long serialVersionUID = 1L;

    private String naziv;
    private String developer;
    private int godina;
    private String zanr;
    private String platforma;

    private String status;
    private int ocjena;
    private String komentar;

    private boolean multiplayer;
    private boolean singleplayer;
    private boolean omiljeno;
    private boolean preporucujem;

    public VideoIgra(String developer, int godina, String komentar, boolean multiplayer,
                     String naziv, int ocjena, boolean omiljeno, String platforma,
                     boolean preporucujem, boolean singleplayer, String status, String zanr) {
        this.developer = developer;
        this.godina = godina;
        this.komentar = komentar;
        this.multiplayer = multiplayer;
        this.naziv = naziv;
        this.ocjena = ocjena;
        this.omiljeno = omiljeno;
        this.platforma = platforma;
        this.preporucujem = preporucujem;
        this.singleplayer = singleplayer;
        this.status = status;
        this.zanr = zanr;
    }

    public String getDeveloper() {
        return developer;
    }

    public int getGodina() {
        return godina;
    }

    public String getKomentar() {
        return komentar;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getOcjena() {
        return ocjena;
    }

    public boolean isOmiljeno() {
        return omiljeno;
    }

    public String getPlatforma() {
        return platforma;
    }

    public boolean isPreporucujem() {
        return preporucujem;
    }

    public boolean isSingleplayer() {
        return singleplayer;
    }

    public String getStatus() {
        return status;
    }

    public String getZanr() {
        return zanr;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public void setMultiplayer(boolean multiplayer) {
        this.multiplayer = multiplayer;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setOcjena(int ocjena) {
        this.ocjena = ocjena;
    }

    public void setOmiljeno(boolean omiljeno) {
        this.omiljeno = omiljeno;
    }

    public void setPlatforma(String platforma) {
        this.platforma = platforma;
    }

    public void setPreporucujem(boolean preporucujem) {
        this.preporucujem = preporucujem;
    }

    public void setSingleplayer(boolean singleplayer) {
        this.singleplayer = singleplayer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    @Override
    public String toString() {
        return "pckg_model.VideoIgra{" +
                "developer='" + developer + '\'' +
                ", naziv='" + naziv + '\'' +
                ", godina=" + godina +
                ", zanr='" + zanr + '\'' +
                ", platforma='" + platforma + '\'' +
                ", status='" + status + '\'' +
                ", ocjena=" + ocjena +
                ", komentar='" + komentar + '\'' +
                ", multiplayer=" + multiplayer +
                ", singleplayer=" + singleplayer +
                ", omiljeno=" + omiljeno +
                ", preporucujem=" + preporucujem +
                '}';
    }

    @Override
    public int compareTo(VideoIgra druga) {
        return Integer.compare(druga.getOcjena(), this.getOcjena());
    }
}
