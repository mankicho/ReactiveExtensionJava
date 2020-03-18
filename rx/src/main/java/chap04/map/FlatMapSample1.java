package chap04.map;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

// 빈 문자가 포함된 원본 대문자 데이터를 소문자 데이터로 변환해 통지, 빈 문자는 건너뜀
public class FlatMapSample1 {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("A", "", "B", "", "C")
                // flatMap 메서드로 빈 문자를 제거하거나 소문자로 변환한다
                .flatMap(data -> {
                    if ("".equals(data)) {
                        return Flowable.empty();
                    } else {
                        return Flowable.just(data.toLowerCase());
                    }
                });

        flowable.subscribe(new DebugSubscriber<>());
//        결과
//        main: a
//        main: b
//        main: c
//        main 완료
    }
}
