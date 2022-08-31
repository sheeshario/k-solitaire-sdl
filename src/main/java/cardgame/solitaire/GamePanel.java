/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.solitaire;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Niwa / Kurniawan - 205314148
 * @author Synraax / Dito - 205314159
 */
public class GamePanel extends JPanel implements MouseListener {

    public final static Point DECK_POSITION = new Point(580, 20);
    public final static Point ACAKAN_POSITION = new Point(20, 150);
    public final static int MARGIN = 80;
    public final static int IMG_HEIGHT = 106;
    public final static int IMG_WIDTH = 72;
    private Component source, target;
    private Tumpukan deck;
    private Tumpukan sampah;
    private Tumpukan[] terurut;
    private Tumpukan[] acakan;

    public GamePanel() {
        super.setLayout(null);
        init();
        addMouseListener(this);
    }

    public void init() {
        JenisKartu[] jenisKartu = JenisKartu.values();
        deck = new Tumpukan(DECK_POSITION.x, DECK_POSITION.y);
        terurut = new Tumpukan[4];
        sampah = new Tumpukan(DECK_POSITION.x - MARGIN, DECK_POSITION.y);
        acakan = new Tumpukan[10];
        for (JenisKartu jenis : jenisKartu) {
            for (int j = 1; j <= 13; j++) {
                deck.push(new Kartu(jenis, j, false));
            }
        }
        deck.getTumpukan().shuffle();
        for (int i = 0; i < jenisKartu.length; ++i) {
            terurut[i] = new Tumpukan(20 + MARGIN * i, 20);
            terurut[i].push(new Kartu(jenisKartu[i], 0, true));
            add(terurut[i]);
        }
        int sizeAcakan = 1;
        for (int i = 1; i <= acakan.length; ++i) {
            acakan[i - 1] = new Tumpukan(ACAKAN_POSITION.x + MARGIN * (i - 1),
                    ACAKAN_POSITION.y) {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    int yOffset = 0;
                    if (!this.isEmpty()) {
                        for (int k = 0; k < this.getTumpukan().size(); k++) {
                            if (k + 1 == this.getTumpukan().size()) {
                                g.drawImage(this.topCard().getImage(), 0,
                                        yOffset, IMG_WIDTH, IMG_HEIGHT, null);
                                yOffset += 20;
                            } else {
                                g.drawImage(this.topCard().getBackImage(), 0,
                                        yOffset, IMG_WIDTH, IMG_HEIGHT, null);
                                yOffset += 20;
                            }
                        }
                        this.setSize(IMG_WIDTH, IMG_HEIGHT + (yOffset - 20));
                    }
                }
            };
            if (i % 2 == 1) {
                sizeAcakan++;
            }
            for (int j = 0; j < sizeAcakan; j++) {
                acakan[i - 1].push(deck.pop());
            }
            if (!acakan[i - 1].topCard().isTerbuka()) {
                acakan[i - 1].topCard().setTerbuka(true);
            }
            add(acakan[i - 1]);
        }
        add(deck);
        add(sampah);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        source = e.getComponent().getComponentAt(e.getPoint());
        if (source instanceof Tumpukan) {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        target = e.getComponent().getComponentAt(e.getPoint());
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        if ((target instanceof Tumpukan && source instanceof Tumpukan)
                && (target != deck || target != sampah)) {
            try {
                if (!((Tumpukan) source).isEmpty()) {
                    for (Tumpukan x : terurut) {
                        if (x == target) {
                            ((Tumpukan) source).moveTo(((Tumpukan) target));
                        }
                    }
                    for (Tumpukan x : acakan) {
                        if (x == target) {
                            if (x.isEmpty()) {
                                x.push(((Tumpukan) source).pop());
                            } else {
                                if (x.topCard().getNilai() - 1
                                        == ((Tumpukan) source).
                                                topCard().getNilai()) {
                                    x.push(((Tumpukan) source).pop());
                                }
                            }
                            if (!((Tumpukan) source).isEmpty()) {
                                ((Tumpukan) source).topCard().setTerbuka(true);
                            }
                        }
                    }
                    repaint();
                }
            } catch (Exception ep) {
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        source = e.getComponent().getComponentAt(e.getPoint());
        if (source == deck) {
            if (deck.getTumpukan().size() == 0) {
                deck.repaint();
                deck.setEnabled(false);
                deck.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JOptionPane.showMessageDialog(null, "Kartu Habis");
                    }
                });
            } else {
                if (sampah.getTumpukan().size() != 0) {
                    sampah.topCard().setTerbuka(false);
                }
                sampah.push(deck.pop());
                sampah.topCard().setTerbuka(true);
                sampah.repaint();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public Tumpukan[] getTerurut() {
        return this.terurut;
    }
}
