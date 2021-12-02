package ru.itmo.kirpichev.cache;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author ilyakirpichev
 */
class LRUCacheTest {

    @Test
    public void testAddSomeData_WhenNoEviction_ThenSizeIsIncrease() {
        LRUCache<Integer, Integer> cache = new LRUCacheImpl<>(3);
        List<Integer> keys = List.of(1, 2, 3);

        int currentSize = 0;
        for (Integer key : keys) {
            cache.put(key, key);
            currentSize++;
            Assertions.assertEquals(cache.size(), currentSize);
        }
        Assertions.assertEquals(cache.size(), 3);
    }

    @Test
    public void testAddSomeData_WhenGetData_ThenNewPairExists() {
        Map<String, Integer> data = Map.of("ilia", 100, "sasha", 20);
        LRUCache<String, Integer> cache = getFilledLRUCache(data, 3);
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            Assertions.assertTrue(cache.get(entry.getKey()).isPresent());
            Assertions.assertEquals(cache.get(entry.getKey()).get(), entry.getValue());
        }
    }

    @Test
    public void test() {
        Map<Integer, Integer> data = Map.of(1, 1, 2, 2, 3, 3);
        LRUCache<Integer, Integer> cached = getFilledLRUCache(data, 3);
//        LRUCache<Integer, Integer> cached = new LRUCacheImpl<>(3);
        cached.get(1); // 2 3 1
        int a = 3;
    }

    @Test
    public void addDataToCacheToTheNumberOfSize_WhenAddOneMoreData_ThenLeastRecentlyDataWillEvict() {
        LRUCache<String, String> lruCache = new LRUCacheImpl<>(3);
        lruCache.put("1", "test1");
        lruCache.put("2", "test2");
        lruCache.put("3", "test3");
        lruCache.put("4", "test4");
        Assertions.assertFalse(lruCache.get("1").isPresent());
        Assertions.assertTrue(lruCache.get("2").isPresent());
        Assertions.assertTrue(lruCache.get("3").isPresent());
        Assertions.assertTrue(lruCache.get("4").isPresent());

    }

    @Test
    public void testAddDataWithEviction_ThenExpectRightAnswer() {
        LRUCache<String, String> cache = new LRUCacheImpl<>(3);
        cache.put("1", "test1"); // 1
        cache.put("2", "test2"); // 1 2
        cache.put("3", "test3"); // 1 2 3
        cache.put("2", "new2"); // 1 3 2
        cache.put("5", "test5"); // 3 2 5
        cache.put("6", "test6"); // 2 5 6
        Map<String, String> expected = Map.of("6", "test6", "2", "new2", "5", "test5");
        Assertions.assertEquals(cache.size(), 3);
        for (Map.Entry<String, String> entry : expected.entrySet()) {
            Optional<String> valueO = cache.get(entry.getKey());
            Assertions.assertTrue(valueO.isPresent());
            Assertions.assertEquals(valueO.get(), entry.getValue());
        }
    }

    @Test
    public void testNoData_ThenExpectEmpty() {
        LRUCache<String, String> cache = new LRUCacheImpl<>(3);
        Assertions.assertEquals(cache.isEmpty(), true);
    }

    @Test
    public void testDataExists_ThenExpectNotEmpty() {
        LRUCache<String, String> cache = new LRUCacheImpl<>(3);
        cache.put("3", "3");
        Assertions.assertEquals(cache.isEmpty(), false);
    }

    @Test
    public void testClear() {
        LRUCache<Integer, Integer> cache = new LRUCacheImpl<>(3);
        for (int i = 1; i <= 10; ++i) {
            cache.put(i, i);
        }
        cache.clear();
        Assertions.assertEquals(cache.size(), 0);
    }

    private <K, V> LRUCache<K, V> getFilledLRUCache(Map<K, V> data, int capacity) {
        LRUCache<K, V> result = new LRUCacheImpl<K, V>(capacity);
        for (Map.Entry<K, V> entry : data.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}