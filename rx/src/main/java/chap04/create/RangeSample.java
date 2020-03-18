package chap04.create;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

public class RangeSample {
    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.range(10, 3); // 10 부터 순서대로 3 건을 통지하는 flowable 생성
        flowable.subscribe(new DebugSubscriber<>());
    }
}
