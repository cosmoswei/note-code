package wei.demo09;

import com.lmax.disruptor.EventFactory;

/**
 * @author cosmoswei
 */
public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
