package chap03;

import chap02.Counter;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class MergeSample {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();

        Flowable<Integer> source1 = Flowable.range(1, 10000)
                .subscribeOn(Schedulers.computation()) // Flowable 이 다른 쓰레드에서 처리작업 할 수 있게한다.
                .observeOn(Schedulers.computation()); // 연산 작업을 다른 쓰레드에서 하게한다.
        Flowable<Integer> source2 = Flowable.range(1, 10000)
                .subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());

//        source1.subscribe(data -> counter.increment(),
//                error -> System.out.println(error.getMessage()),
//                () -> System.out.println("source1 : counter.get() = " + counter.get()));
        // 결과 = counter.get() = 19685

//        source2.subscribe(data -> counter.increment(),
//                error -> System.out.println(error.getMessage()),
//                () -> System.out.println("source2 : counter.get() = " + counter.get()));
        // 결과 = counter.get() = 19885

        Flowable.merge(source1, source2).subscribe(data -> counter.increment(),
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("counter.get() = " + counter.get()));
        Thread.sleep(1000L);
        // 결과 = counter.get() = 20000
    }
}
