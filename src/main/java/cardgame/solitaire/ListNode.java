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
public class ListNode<TypeData> {

    private TypeData data;
    private ListNode<TypeData> next;

    public ListNode(TypeData data) {
        this.data = data;
        this.next = null;
    }

    public ListNode(TypeData data, ListNode<TypeData> node) {
        this.data = data;
        this.next = node;
    }

    public void setData(TypeData data) {
        this.data = data;
    }

    public TypeData getData() {
        return this.data;
    }

    public void setNext(ListNode<TypeData> node) {
        this.next = node;
    }

    public ListNode<TypeData> getNext() {
        return this.next;
    }

    @Override
    public String toString() {
        return String.format("%s", this.data);
    }
}
