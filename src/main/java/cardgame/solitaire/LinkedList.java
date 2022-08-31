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
import java.util.Random;

public class LinkedList<TypeData> {

    private ListNode<TypeData> firstNode;
    private ListNode<TypeData> lastNode;
    int size;

    public LinkedList() {
        this.firstNode = null;
        this.lastNode = null;
        this.size = 0;
    }

    public void addFirst(TypeData data) {
        ListNode<TypeData> baru = new ListNode<>(data);
        if (isEmpty()) {
            this.firstNode = baru;
            this.lastNode = baru;
        } else {
            baru.setNext(this.firstNode);
            this.firstNode = baru;
        }
        this.size++;
    }

    public void addLast(TypeData data) {
        ListNode<TypeData> baru = new ListNode<>(data);
        if (isEmpty()) {
            this.firstNode = baru;
            this.lastNode = baru;
        } else {
            this.lastNode.setNext(baru);
            this.lastNode = baru;
        }
        this.size++;
    }

    public ListNode<TypeData> removeFirst() {
        ListNode<TypeData> bantu = this.firstNode;
        if (!isEmpty()) {
            this.size--;
            if (this.firstNode == this.lastNode) {
                this.firstNode = null;
                this.lastNode = null;
                return bantu;
            } else {
                this.firstNode = this.firstNode.getNext();
                return bantu;
            }
        } else {
            return null;
        }
    }

    public ListNode<TypeData> removeLast() {
        ListNode<TypeData> bantu = this.firstNode;
        if (!isEmpty()) {
            this.size--;
            if (this.firstNode == this.lastNode) {
                this.firstNode = null;
                this.lastNode = null;
                return bantu;
            } else {
                while (bantu.getNext() != this.lastNode) {
                    bantu = bantu.getNext();
                }
                this.lastNode = bantu;
                bantu = bantu.getNext();
                this.lastNode.setNext(null);
                return bantu;
            }
        } else {
            return null;
        }
    }

    public ListNode<TypeData> get(int i) {
        if (this.size == 0 || i > this.size) {
            return null;
        }
        int count = 0;
        ListNode<TypeData> bantu = this.firstNode;
        while (!(bantu == null && count == i)) {
            bantu = bantu.getNext();
            count++;
        }
        return bantu;
    }

    public boolean isEmpty() {
        return this.firstNode == null && this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void shuffle() {
        ListNode<TypeData> temp[] = new ListNode[this.size];
        Random rand = new Random();
        ListNode<TypeData> bantu = this.firstNode;
        int i = 0;
        while (bantu != null) {
            temp[i++] = bantu;
            bantu = bantu.getNext();
        }
        for (i = temp.length; i > 1; i--) {
            int n = rand.nextInt(i);
            ListNode x = temp[i - 1];
            temp[i - 1] = temp[n];
            temp[n] = x;
        }
        this.firstNode = null;
        this.lastNode = null;
        this.size = 0;
        for (ListNode<TypeData> x : temp) {
            this.addLast(x.getData());
        }
    }

    public void display() {
        ListNode<TypeData> bantu = this.firstNode;
        while (bantu != null) {
            System.out.print(bantu.getData() + ", ");
            bantu = bantu.getNext();
        }
    }

    public String toString() {
        String temp = "null";
        if (!isEmpty()) {
            temp = "";
            ListNode<TypeData> bantu = this.firstNode;
            if (this.firstNode == this.lastNode) {
                temp += String.format("%s", bantu.getData());
            } else {
                while (bantu.getNext() != this.lastNode) {
                    temp += String.format("%s ->", bantu.getData());
                    bantu = bantu.getNext();
                }
                temp += String.format("%s ->", bantu.getData());
                temp += String.format("%s", bantu.getNext().getData());
            }
        }
        return temp;
    }
}
