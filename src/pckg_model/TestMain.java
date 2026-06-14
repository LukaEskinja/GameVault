package pckg_model;

public class TestMain {
    public static void main(String[] args) {

        VideoIgra v1 = new VideoIgra("Rockstar",2004,"",false,"GTA San Andreas",
                9,true,"PC/CONSOLE",true,true,"","Story");

        System.out.println(v1);

        System.out.println(v1.getDeveloper());
    }
}
