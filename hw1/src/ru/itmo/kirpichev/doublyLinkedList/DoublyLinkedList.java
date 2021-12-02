package ru.itmo.kirpichev.doublyLinkedList;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;

/**
 * @author ilyakirpichev
 */
public class DoublyLinkedList<T> {
    private LinkedListNode<T> front;
    private LinkedListNode<T> end;
    private int size;

    public DoublyLinkedList() {
        this.front = null;
        this.end = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public LinkedListNode<T> addFront(T element) {
        LinkedListNode<T> newNode = new LinkedListNode<>(element);
        if (isEmpty()) {
            front = newNode;
            end = front;
        } else {
            front.setNext(newNode);
            newNode.setPrev(front);
            front = newNode;
        }
        size++;
        Assertions.assertEquals(element, getFront());
        return newNode;
    }

    public void moveToFront(LinkedListNode<T> element) {
        if (isEmpty()) {
            return;
        }
        cutElement(element);
        front.setNext(element);
        element.setPrev(front);
        front = element;
    }

    public T getLast() {
        return end.getElement();
    }

    public T getFront() {
        return front.getElement();
    }

    public T deleteLast() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Delete from empty list");
        }
        T result;
        if (end.getNextO().isEmpty()) {
            result = end.getElement();
            cutElement(end);
        } else {
            T expected = end.getNextO().get().getElement();

            result = end.getElement();
            LinkedListNode<T> newEnd = end.getNextO().get();
            cutElement(end);
            end = newEnd;

            Assertions.assertEquals(expected, this.getLast());
        }
        size--;
        return result;
    }

    public void clear() {
        front = null;
        end = null;
        size = 0;
    }

    private void cutElement(LinkedListNode<T> element) {
        final int expectedSize = this.size();

        Optional<LinkedListNode<T>> prev = element.getPrevO();
        Optional<LinkedListNode<T>> next = element.getNextO();

        prev.ifPresent(p -> p.setNextO(next));
        next.ifPresent(p -> p.setPrevO(prev));

        element.setPrevO(Optional.empty());
        element.setNextO(Optional.empty());

        processCasesWhenElementFrontOrBegin(element, prev, next);

        Assertions.assertEquals(expectedSize, this.size());
    }

    private void processCasesWhenElementFrontOrBegin(LinkedListNode<T> element, Optional<LinkedListNode<T>> prev,
            Optional<LinkedListNode<T>> next)
    {
        if (front == end && element == front) {
            front = null;
            end = null;
        } else if (element == front) {
            this.front = prev.orElse(null);
        } else if (element == end) {
            this.end = next.orElse(null);
        }
    }

}
