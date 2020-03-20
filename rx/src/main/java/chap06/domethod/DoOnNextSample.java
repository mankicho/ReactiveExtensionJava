package chap06.domethod;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

// doOnNext 는 생산자가 데이터 통지 시 호출된다. 주로 로그로 사용
public class DoOnNextSample {
    public static void main(String[] args) {
        Flowable.range(1, 5)
                // 데이터 통지 시 로그 출력
                .doOnNext(data -> System.out.println("기존 데이터 = " + data))
                // 짝수만 통지하기.
        .filter(data -> data % 2 == 0)
                // 데이터 통지 시 로그 출력
        .doOnNext(data -> System.out.println("필터 적용 후 데이터 = " + data))
                // 구독
        .subscribe(new DebugSubscriber<>());
    }
}
