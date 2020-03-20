package chap06.domethod;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

public class DoOnComplete {
    public static void main(String[] args) {
        Flowable.range(1,5)
                // 완료 시 호출된다
        .doOnComplete(() -> System.out.println("doOnComplete"))
                // 구독
        .subscribe(new DebugSubscriber<>());
    }
}
