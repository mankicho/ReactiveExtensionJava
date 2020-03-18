package chap04.create;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

import java.time.LocalTime;

public class DeferSample {
    public static void main(String[] args) throws InterruptedException{
        Flowable<LocalTime> flowable = Flowable.defer(() ->Flowable.just(LocalTime.now()));

        flowable.subscribe(new DebugSubscriber<>("No.1"));
        Thread.sleep(2000L);
        flowable.subscribe(new DebugSubscriber<>("No.2"));

    }
}
