import com.wei.AppRun;
import com.wei.list.LogisticsService;
import com.wei.list.TransferFeeRequest;
import com.wei.spring.StrategyContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = AppRun.class)
class ApplicationTests {


    @Resource
    private List<LogisticsService> logisticsService;

    @Resource
    private Map<String, LogisticsService> logisticsServiceMap;

    @Test
    void contextLoads() {

        TransferFeeRequest transferFeeRequest = TransferFeeRequest.builder()
                .unitPrice(new BigDecimal("10"))
                .distance(new BigDecimal("20"))
                .type(1).build();

        LogisticsService logisticsService = this.logisticsService.stream()
                .filter(l -> l.isCurrentLogistics(transferFeeRequest.getType()))
                .findFirst().orElseThrow(RuntimeException::new);
        if (logisticsService != null) {
            BigDecimal bigDecimal = logisticsService.calculateFee(transferFeeRequest);
            System.out.println(bigDecimal);
        }

        TransferFeeRequest transferFeeRequestMap = TransferFeeRequest.builder()
                .unitPrice(new BigDecimal("10"))
                .distance(new BigDecimal("20"))
                .build();
        LogisticsService jdTransfercompany = logisticsServiceMap.get("JDTransfercompany");
        System.out.println(jdTransfercompany.calculateFee(transferFeeRequestMap));
    }

    @Resource
    private StrategyContext strategyContext;

    @Test
    void springStrategy() {
        System.out.printf("test: %s", strategyContext.calculatePrice("vipMember"));
    }

}
