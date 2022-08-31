/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.solitaire;

/**
 *
 * @author Niwa / Kurniawan - 205314148
 * @author Synraax / Dito - 205314159
 */
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.objects.NativeDebug;

public class Kartu {

    public final static String IMG_BACK = "baseback";
    public final static String IMG_PATH = "img";
    public final static String IMG_EXT = ".jpg";
    public final static String IMG_BASE = "base";
    private int nilai;
    private JenisKartu jenis;
    private Image img;
    private boolean terbuka;

    public Kartu(JenisKartu jenis, int nilai, boolean terbuka) {
        if (nilai < 0 || nilai > 13) {
            throw new IllegalArgumentException("Bad Card Number");
        }
        this.nilai = nilai;
        this.jenis = jenis;
        this.setTerbuka(terbuka);
    }

    public int getNilai() {
        return this.nilai;
    }

    public String getJenisKartu() {
        return this.jenis.toString();
    }

    public Image getImage() {
        return this.img;
    }

    public Image getBackImage() {
        URL path = getClass().getClassLoader().getResource(IMG_PATH + "/" + IMG_BACK + IMG_EXT);
        return new ImageIcon(path).getImage();
    }

    public void setTerbuka(boolean terbuka) {
        ImageIcon ic;
        if (terbuka) {
            URL path = null;
            if (nilai == 0) {
                path = getClass().getClassLoader().getResource(IMG_PATH + "/" + IMG_BASE
                        + jenis.toString().toLowerCase() + IMG_EXT);
            } else {
                if (nilai >= 10) {
                    path = getClass().getClassLoader().getResource(IMG_PATH + "/" + nilai
                            + this.jenis.toString().toLowerCase() + IMG_EXT);
                } else {
                    path = getClass().getClassLoader().getResource(IMG_PATH + "/0" + nilai
                            + this.jenis.toString().toLowerCase() + IMG_EXT);
                }
            }
            try {
                ic = new ImageIcon(path);
                this.img = ic.getImage();
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Assets Gambar tidak ditemukan", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            this.terbuka = true;
        } else {
            this.img = getBackImage();
            this.terbuka = false;
        }
    }

    public boolean isTerbuka() {
        return this.terbuka;
    }

    @Override
    public String toString() {
        return String.format("[%s - %d]", this.jenis, this.nilai);
    }

//    public static void main(String[] args) {
//        for (JenisKartu jen : JenisKartu.values()) {
//            for (int i = 1; i <= 13; i++) {
//                URL path = getClass().getClassLoader().getResource(IMG_PATH + "/" + (i < 10 ? "0" : "") + i + jen.toString().toLowerCase() + IMG_EXT);
//                System.out.println(path);
//            }
//        }
//    }
}
