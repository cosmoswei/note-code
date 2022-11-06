package com.wei.fst;

import lombok.extern.slf4j.Slf4j;
import org.nustaq.serialization.FSTConfiguration;
import org.springframework.util.StopWatch;

@Slf4j
public class CacheSerialization {
    private static final ThreadLocal<FSTConfiguration> FSTS = ThreadLocal.withInitial(() -> {
        FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
//        conf.registerClass(org.roaringbitmap.longlong.Roaring64Bitmap.class);
        conf.setShareReferences(false);
        return conf;
    });

    public byte[] serialize(Object object) {
        return FSTS.get().asByteArray(object);
    }


    public <T> T deserialize(byte[] bin) {
        Object o = FSTS.get().asObject(bin);
        return (T) o;
    }

    private static class CacheSerializationHolder {
        private static final CacheSerialization INSTANCE = new CacheSerialization();
    }

    public static CacheSerialization getInstance() {
        return CacheSerializationHolder.INSTANCE;
    }
}