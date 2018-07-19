package com.nishank.algo.bean;

/**
 * Created by Nishank Gupta on 19-Jul-18.
 */
public class ListNode<T> {

    private T value;
    private ListNode<T> next;
    private ListNode<T> prev;

    public ListNode() {
    }

    public ListNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public ListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(ListNode<T> prev) {
        this.prev = prev;
    }
}
