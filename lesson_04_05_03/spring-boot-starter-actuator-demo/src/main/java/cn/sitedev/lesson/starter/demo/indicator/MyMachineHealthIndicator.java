package cn.sitedev.lesson.starter.demo.indicator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义HealthIndicator<br>
 *     当浏览器访问http://localhost:8080/actuator/health时, 该接口返回:
 *     {
 *     "status": "UP",
 *     "details": {
 *         "myMachine": {
 *             "status": "UP",
 *             "details": {
 *                 "availableProcessors": 8,
 *                 "totalMemory": 272629760,
 *                 "freeMemory": 180664528,
 *                 "maxMemory": 3801088000
 *             }
 *         },
 *         "diskSpace": {
 *             "status": "UP",
 *             "details": {
 *                 "total": 119791415296,
 *                 "free": 75281174528,
 *                 "threshold": 10485760
 *             }
 *         }
 *     }
 * }
 *
 * // 其中的key "myMachine" = 自定义HealthIndicator的类名截去"HealthIndicator"后的名称(首字母小写)
 */
@Component
public class MyMachineHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        Map<String, Object> details = new HashMap<>(4);
        details.put("availableProcessors", Runtime.getRuntime().availableProcessors());
        details.put("freeMemory", Runtime.getRuntime().freeMemory());
        details.put("maxMemory", Runtime.getRuntime().maxMemory());
        details.put("totalMemory", Runtime.getRuntime().totalMemory());
        builder.up().withDetails(details).build();

    }
}
