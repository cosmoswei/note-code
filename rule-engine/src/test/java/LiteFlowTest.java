import com.wei.lite.flow.LiteFlowAppRun;
import com.wei.lite.flow.context.CustomContext;
import com.wei.lite.flow.context.OrderContext;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.FlowBus;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;


@SpringBootTest(classes = LiteFlowAppRun.class)
public class LiteFlowTest {

    @Resource
    private FlowExecutor flowExecutor;

    @Test
    public void firstTest() {
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg");
    }


    @Test
    public void switchTest() {
        LiteflowResponse response = flowExecutor.execute2Resp("chain2", "arg");
    }

    @Test
    public void forTest() {
        LiteflowResponse response = flowExecutor.execute2Resp("chain3", "arg");
    }

    @Test
    public void iteratorTest() {
        LiteflowResponse response = flowExecutor.execute2Resp("chain4", "arg");
    }

    @Test
    public void contextTest() {
        OrderContext orderContext = new OrderContext();
        orderContext.setOrderNo("SO11223344");
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", new Date(), CustomContext.class);
        CustomContext customContext = response.getContextBean(CustomContext.class);
        System.err.println("customContext = " + customContext);
        FlowBus.reloadScript("","");
        FlowBus.reloadChain("","");
    }
}
