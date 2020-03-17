package chap02.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AtomicCounterSample {
    // 순차로 값을 증가시키는 AtomicCount 객체 => 원자성 보장하는 객체
    public static void main(String[] args) throws Exception {

        final AtomicCounter counter = new AtomicCounter();

        // 10,000 번 계산하는 비동기 처리 작업
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();

        // 비동기 실행
        Future<Boolean> future1 = service.submit(task, true);
        Future<Boolean> future2 = service.submit(task, true);

        // 결과가 반환될 때까지 기다린다.
        if (future1.get() && future2.get()) {
            System.out.println(counter.get());
        } else {
            System.out.println("실패");
        }

        // 비동기 처리 작업을 종료한다.
        service.shutdown();
    }
    // 결과는 20,000 , AtomicInteger 는 원자성을 보장하기 때문에 멀티스레드 환경에서 실행해도 원하는 결과 보장

}
