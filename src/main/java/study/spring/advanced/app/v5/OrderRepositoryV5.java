package study.spring.advanced.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import study.spring.advanced.trace.callback.TraceTemplate;
import study.spring.advanced.trace.logtrace.LogTrace;
import study.spring.advanced.trace.template.AbstractTemplate;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    @Autowired
    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId) {
        template.execute("OrderRepository.save()", () -> {
            //저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
