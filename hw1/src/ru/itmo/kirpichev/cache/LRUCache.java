package ru.itmo.kirpichev.cache;

import java.util.Optional;

/**
 * @author ilyakirpichev
 */
public interface LRUCache<K, V> {
    Optional<V> get(K key);
    void put(K key, V value);
    boolean isEmpty();
    int size();
    void clear();
}
