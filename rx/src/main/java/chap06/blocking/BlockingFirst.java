package chap06.blocking;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

// blockingFirst() 메서드는 첫번째로 통지하는데이터를 받는다.
// blockingFirst() 메서드를 호출한 스레드는 결과를 받을때까지 중지된다.
public class BlockingFirst {
    public static void main(String[] args) throws InterruptedException{
        Flowable<Long> flowable = Flowable.interval(1000L, TimeUnit.MILLISECONDS);
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(3000L);
        // 0,1,2,3 출력하고 첫번째 데이터인 0을 받고 스레드가 종료된다.
        long actual = flowable.blockingFirst();
        System.out.println("actual = " + actual);
    }
}
