package chap04.map;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

// 원본 데이터와 flatMap 내부에서 interval 메서드로 생성한 Flowable 이 통지하는 데이터를 조합해 문자열을 만들고 이를 통지하는 FLowable 을 생성
public class FlatMapSample2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("프로그램 시작");
        Flowable<String> flowable = Flowable.range(1, 3)
                // mapper 와 combiner 를 인자로 받는 flatMap 메서드
                .flatMap(
                        // 첫 번째 인자 : 데이터를 받으면 새로운 Flowable 을 생성한다.
                        data -> {
                            return Flowable.interval(100L, TimeUnit.MILLISECONDS).take(3); // 3건 까지 통지
                        },
                        // 두 번째 인자 : 원본 데이터와 변환한 데이터로 새로운 통지 데이터를 생성한다
                        (sourceData, newData) -> "[" + sourceData + "]" + newData); // 서로 다른 스레드에서 실행돼 비동기로 데이터가 통지되기 때문에 데이터를 받은
                                                                                    // 순서대로 통지되지 않는다.
        // 구독
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(1000L);
    }
}
