package com.interview.questions.populator;

import java.io.Serializable;
import java.util.Map;

public class EntryImpl<K, V> implements Map.Entry<K, V> , Serializable{

	private static final long serialVersionUID = 740160891624377513L;
	private final K key;
    private V value;

    public EntryImpl(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }
}