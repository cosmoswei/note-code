package com.wei.demo;

import org.caffinitas.ohc.CacheSerializer;

import java.nio.ByteBuffer;

public class TestCacheSerializer implements CacheSerializer {
    @Override
    public void serialize(Object value, ByteBuffer buf) {

    }

    @Override
    public Object deserialize(ByteBuffer buf) {
        return null;
    }

    @Override
    public int serializedSize(Object value) {
        return 0;
    }
}
