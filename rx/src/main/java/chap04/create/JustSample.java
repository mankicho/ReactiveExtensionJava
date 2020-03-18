package chap04.create;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

public class JustSample {
    public static void main(String[] args) {
        // 인자 데이터를 순서대로 통지하는 flowable 생성
        Flowable<String> flowable = Flowable.just("A", "B", "C", "D", "E", "F", "G");

        // 구독 시작
        flowable.subscribe(new DebugSubscriber<>());
//        결과
//        main: A
//        main: B
//        main: C
//        main: D
//        main: E
//        main: F
//        main: G
//        main 완료

    }
}
