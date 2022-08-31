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
 * @param <TypeData>
 */
public class Stack<TypeData> {

    @SuppressWarnings("FieldMayBeFinal")
    private LinkedList<TypeData> tumpukan;

    public Stack() {
        this.tumpukan = new LinkedList<>();
    }

    public void push(TypeData val) {
        this.tumpukan.addFirst(val);
    }

    public TypeData pop() {
        if (isEmpty()) {
            return null;
        }
        return this.tumpukan.removeFirst().getData();
    }

    public TypeData peek() {
        if (!isEmpty()) {
            ListNode<TypeData> temp = this.tumpukan.removeFirst();
            this.tumpukan.addFirst(temp.getData());
            return temp.getData();
        }
        return null;
    }

    public int size() {
        return this.tumpukan.size();
    }

    public boolean isEmpty() {
        return this.tumpukan.isEmpty();
    }

    public void shuffle() {
        this.tumpukan.shuffle();
    }

    @Override
    public String toString() {
        return tumpukan.toString();
    }
}
