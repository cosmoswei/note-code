import com.wei.lite.flow.LiteFlowAppRun;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest(classes = LiteFlowAppRun.class)
public class LiteFlowTest {

    @Resource
    private FlowExecutor flowExecutor;

    @Test
    public void firstTest() {
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg");
    }
}
