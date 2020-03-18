package chap04.create;

import io.reactivex.Flowable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

// interval 메서드는 통지 간격마다 0부터 시작하는 Long 타입의 숫자 데이터를 통지하는 Flowable 을 생성하는 연산자.
// 별도 설정이 없으면 Schedulers.computation() 의 스케줄러에서 실행된다.
public class IntervalSample {
    public static void main(String[] args) throws InterruptedException {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss.SSS");
        Flowable<Long> flowable = Flowable.interval(1000L, TimeUnit.MILLISECONDS); // 1000L 초 마다 데이터를 통지
        System.out.println("시작 시각 = " + LocalTime.now().format(formatter));

        // 구독
        flowable.subscribe(data -> {
            String threadName = Thread.currentThread().getName();
            String time = LocalTime.now().format(formatter);
            System.out.println(threadName + ": " + time + ": data = " + data);
        });
        Thread.sleep(5000L);
//        결과
//        시작 시각 = 48:52.646
//        RxComputationThreadPool-1: 48:53.690: data = 0
//        RxComputationThreadPool-1: 48:54.690: data = 1
//        RxComputationThreadPool-1: 48:55.689: data = 2
//        RxComputationThreadPool-1: 48:56.690: data = 3
//        RxComputationThreadPool-1: 48:57.690: data = 4
//        1초마다 데이터를 통지한다.

    }
}
