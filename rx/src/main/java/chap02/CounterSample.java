package chap02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CounterSample {
    // 싱글 코어의 다중 스레드 예제
    public static void main(String[] args) throws Exception {
        final Counter counter = new Counter();

        // 10,000 계산하는 비동기 처리 작업
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        };

        // 비동기 처리 작업을 담당하는 관리자 생성
        ExecutorService service = Executors.newCachedThreadPool();

        // 새로운 스레드로 시작.
        Future<Boolean> future1 = service.submit(task, true);
        // 새로운 스레드로 시작.
        Future<Boolean> future2 = service.submit(task, true);

        // 결과가 반환될 때까지 기다린다.
        if (future1.get() && future2.get()) {
            // 계산결과 출력
            System.out.println(counter.get());
        } else {
            System.out.println("실패");
        }

        // 비동기 처리 작업 종료;
        service.shutdown();

        // 결과가 20000 이 나올거라고 예상하지만 그렇지않다. count 필드에 여러 스레드가 접근하기때문에

    }

}
