package study.spring.advanced.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.advanced.trace.TraceStatus;
import study.spring.advanced.trace.helloTrace.HelloTraceV1;
import study.spring.advanced.trace.helloTrace.HelloTraceV2;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping(value = "/v2/request")
    public String request(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(status.getTraceId(), itemId);
            trace.end(status);
            return "ok";

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;//예외를 꼭 다시 던저주어야 한다.
        }
    }
}
