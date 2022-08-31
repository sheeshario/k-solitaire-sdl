/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.solitaire;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.EmptyStackException;
import javax.swing.JPanel;

/**
 *
 * @author Niwa / Kurniawan - 205314148
 * @author Synraax / Dito - 205314159
 */
public class Tumpukan extends JPanel {

    public final static int IMG_HEIGHT = 106;
    public final static int IMG_WIDTH = 72;
    private Stack<Kartu> tumpukan;

    public Tumpukan(int x, int y) {
        this(new Point(x, y));
    }

    public Tumpukan(Point point) {
        super.setLocation(point);
        this.tumpukan = new Stack<>();
        super.setSize(IMG_WIDTH, IMG_HEIGHT);
    }

    public Kartu topCard() {
        if (!this.tumpukan.isEmpty()) {
            return this.tumpukan.peek();
        }
        return null;
    }

    public Kartu pop() {
        try {
            return this.tumpukan.pop();
        } catch (EmptyStackException ese) {
            return null;
        }
    }

    public void push(Kartu kartu) {
        this.tumpukan.push(kartu);
    }

    public boolean moveTo(Tumpukan newLoc) {
        if (newLoc.accept(this.topCard())) {
            newLoc.push(this.pop());
            if (!this.isEmpty()) {
                this.topCard().setTerbuka(true);
            }
            return true;
        }
        return false;
    }

    public boolean accept(Kartu kartu) {
        if (this.tumpukan.isEmpty()) {
            return false;
        }
        return kartu.getJenisKartu().equalsIgnoreCase(this.topCard().getJenisKartu())
                && this.topCard().getNilai() == kartu.getNilai() - 1;
    }

    public boolean isEmpty() {
        return this.tumpukan.isEmpty();
    }

    public Stack<Kartu> getTumpukan() {
        return this.tumpukan;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(new Color(0, 0, 0, 0.2f));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (!isEmpty()) {
            g.drawImage(this.topCard().getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
