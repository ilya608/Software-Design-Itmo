package ru.itmo.kirpichev.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import ru.itmo.kirpichev.doublyLinkedList.DoublyLinkedList;
import ru.itmo.kirpichev.doublyLinkedList.LinkedListNode;

/**
 * @author ilyakirpichev
 */
public class LRUCacheImpl<K, V> implements LRUCache<K, V> {
    private final int capacity;
    private final Map<K, LinkedListNode<CacheElement>> elementByKey;
    private final DoublyLinkedList<CacheElement> doublyLinkedList;

    public LRUCacheImpl(int capacity) {
        this.capacity = capacity;
        this.doublyLinkedList = new DoublyLinkedList<>();
        elementByKey = new HashMap<>();
    }

    @Override
    public Optional<V> get(K key) {
        LinkedListNode<CacheElement> cachedElement = elementByKey.get(key);
        if (cachedElement == null) {
            return Optional.empty();
        }
        doublyLinkedList.moveToFront(cachedElement);

        return Optional.of(cachedElement.getElement().value);
    }

    @Override
    public void put(K key, V value) {
        int currentSize = this.size();
        if (this.elementByKey.containsKey(key)) {
            LinkedListNode<CacheElement> element = this.elementByKey.get(key);
            this.doublyLinkedList.moveToFront(element);
            element.getElement().setValue(value);
        } else {
            CacheElement newElement = new CacheElement(key, value);
            if (this.size() >= this.capacity) {
                CacheElement oldestElement = this.doublyLinkedList.deleteLast();
                this.elementByKey.remove(oldestElement.getKey());
                currentSize--;
            }
            LinkedListNode<CacheElement> addedElement = this.doublyLinkedList.addFront(newElement);
            this.elementByKey.put(key, addedElement);
            currentSize++;
        }
        Assertions.assertEquals(currentSize, this.size());
        Assertions.assertEquals(elementByKey.get(key).getElement().value, value);
    }

    @Override
    public boolean isEmpty() {
        return doublyLinkedList.isEmpty();
    }

    @Override
    public int size() {
        return this.doublyLinkedList.size();
    }

    @Override
    public void clear() {
        elementByKey.clear();
        doublyLinkedList.clear();
        Assertions.assertEquals(0, this.size());
    }

    public class CacheElement {
        private final K key;
        private V value;

        public void setValue(V value) {
            this.value = value;
        }

        public CacheElement(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }
    }
}
