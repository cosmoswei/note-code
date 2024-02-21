package wei.demo09;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author cosmoswei
 */
public class Producer {
    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer byteBuffer) {
        long sequence = ringBuffer.next();
        try {
            PCData event = ringBuffer.get(sequence);
            event.setValue(byteBuffer.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
