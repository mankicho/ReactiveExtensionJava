package chap04.create;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

public class FromCallableSample {
    public static void main(String[] args) {
        // Callable 의 결과를 통지하는 Flowable 을 생성
        Flowable<Long> flowable = Flowable.fromCallable(System::currentTimeMillis); // 함수형 인터페이스 (System::currentTimeMillis 가 반환하는 값을 통지하는 flowable

        // 구독 시작
        flowable.subscribe(new DebugSubscriber<>());
    }
}
