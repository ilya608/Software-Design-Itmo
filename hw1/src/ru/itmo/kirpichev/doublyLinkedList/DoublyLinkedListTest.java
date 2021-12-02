package ru.itmo.kirpichev.doublyLinkedList;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author ilyakirpichev
 */
class DoublyLinkedListTest {
    @Test
    public void testAddOneElement_ThenSizeIsCorrect() {
        DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<Integer>();
        for (int i = 1; i <= 5; ++i) {
            linkedList.addFront(i);
            Assertions.assertEquals(linkedList.size(), i);
        }
    }

    @Test
    public void testNoElementAdded_ThenEmpty() {
        DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<Integer>();
        Assertions.assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testDeleteFromEmpty_ThenExpectException() {
        DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<Integer>();
        Assertions.assertThrows(IllegalStateException.class, linkedList::deleteLast);
    }

    @Test
    public void testDeleteFromNotEmpty_ThenElementShouldDelete() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<String>();

        List<String> elements = List.of("a", "b", "c", "de");
        elements.forEach(linkedList::addFront);

        List<String> actual = getAllFromEndToStart(linkedList);

        Assertions.assertEquals(elements, actual);
    }

    @Test
    public void testDeleteFromOneElement() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<String>();
        linkedList.addFront("abacaba");
        linkedList.deleteLast();
        Assertions.assertEquals(linkedList.size(), 0);
    }

    public<T> List<T> getAllFromEndToStart(DoublyLinkedList<T> linkedList) {
        List<T> result = new ArrayList<>();
        while (!linkedList.isEmpty()) {
            result.add(linkedList.getLast());
            linkedList.deleteLast();
        }
        return result;
    }
}