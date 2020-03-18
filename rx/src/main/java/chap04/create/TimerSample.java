package chap04.create;

import io.reactivex.Flowable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TimerSample {
    public static void main(String[] args) throws InterruptedException {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss.SSS");

        // 처리 시작 시각 출력
        System.out.println("시작 시각 : " + LocalTime.now().format(formatter));

        // 1000L 초 뒤에 숫자 '0'을 통지하는 FLowable 을 생성한다.
        Flowable<Long> flowable = Flowable.timer(1000L, TimeUnit.MILLISECONDS);

        // 구독 시작

        flowable.subscribe(data -> {
                    System.out.println(Thread.currentThread().getName() + ": " + LocalTime.now().format(formatter) + ": data=" + data);
                },
                error -> System.out.println("에러=" + error),
                () -> System.out.println("완료"));
        Thread.sleep(1500L);
//        결과
//        시작 시각 : 03:23.966
//        RxComputationThreadPool-1: 03:25.300: data=0
//        완료
//        시작 시각 후 거의 1000L 초 뒤에 데이터 통지 후 완료되는것을 알 수 있다.
    }
}
