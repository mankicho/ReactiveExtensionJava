package chap04.merge;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class MergeSample {

    public static void main(String[] args) throws InterruptedException{
        Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS);
        Flowable<Long> mergeFlowable = Flowable.interval(300L, TimeUnit.MILLISECONDS).map(data -> data + 100);

        // 두 개의 데이터 통지자를 합쳐서 새로운 Flowable 을 만든다. 데이터 순서는 섞여서 통지된다.
        Flowable<Long> merge = Flowable.merge(flowable,mergeFlowable);

        merge.subscribe(new DebugSubscriber<>());

        Thread.sleep(3000L);

    }
}
