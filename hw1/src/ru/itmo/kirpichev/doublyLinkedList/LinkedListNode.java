package ru.itmo.kirpichev.doublyLinkedList;

import java.util.Optional;

/**
 * @author ilyakirpichev
 */
public class LinkedListNode<T> {
    private final T element;
    private Optional<LinkedListNode<T>> prev;
    private Optional<LinkedListNode<T>> next;

    public LinkedListNode(T element) {
        this.element = element;
        prev = Optional.empty();
        next = Optional.empty();
    }

    public T getElement() {
        return element;
    }

    public Optional<LinkedListNode<T>> getPrevO() {
        return prev;
    }


    public Optional<LinkedListNode<T>> getNextO() {
        return next;
    }

    public void setPrevO(Optional<LinkedListNode<T>> prev) {
        this.prev = prev;
    }

    public void setNextO(Optional<LinkedListNode<T>> next) {
        this.next = next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = Optional.of(next);
    }

    public void setPrev(LinkedListNode<T> prev) {
        this.prev = Optional.of(prev);
    }
}
