package study.spring.advanced.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring.advanced.trace.callback.TraceCallback;
import study.spring.advanced.trace.callback.TraceTemplate;
import study.spring.advanced.trace.logtrace.LogTrace;
import study.spring.advanced.trace.template.AbstractTemplate;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository,LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {
        template.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
