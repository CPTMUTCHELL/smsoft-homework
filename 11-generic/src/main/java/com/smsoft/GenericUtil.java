package com.smsoft;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenericUtil {

   public static <T> boolean containAllElements(Collection<T> collection, T... elements) {

        for (T element : elements) {
            if (!collection.contains(element))
                return false;
        }
        return true;
    }

    public static <T> Collection<T> unique(Collection<T> a, Collection<T> b) {
       Collection<T> setA = new HashSet<>(a);
       Collection<T> setB = new HashSet<>(b);
       setA.removeIf(b::contains);
        setB.removeIf(a::contains);
        setA.addAll(setB);

        return setA;
    }
    public static <K,V> Map<K,V> mergeMap(Map<K,V> a, Map<K,V> b) {
        Map<K,V> res = new HashMap<>(a);
        b.entrySet().forEach(entry->res.put(entry.getKey(),entry.getValue()));

        return res;
    }
}
