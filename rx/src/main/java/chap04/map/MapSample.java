package chap04.map;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

public class MapSample {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("A", "B", "C", "D", "E") // 생성
                .map(String::toLowerCase); // 소문자로 변환

        flowable.subscribe(new DebugSubscriber<>());

//        결과
//        main: a
//        main: b
//        main: c
//        main: d
//        main: e
//        main 완료
    }
}
