package chap04.filter;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

// 같은 데이터는 빼고 통지하는 Flowable
public class DistinctSample {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("A", "B", "A", "B", "A", "B").distinct();

        flowable.subscribe(new DebugSubscriber<>());
    }
}
