package chap04.filter;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class SkipSample {
    public static void main(String[] args) throws InterruptedException {
//         skip : 처음 2 건은 통지하지 않는다.
        Flowable<Long> skip = Flowable.interval(300L, TimeUnit.MILLISECONDS).skip(2); // 인자로 지정한 개수만큼 건너뛰고 통지한다.
        skip.subscribe(new DebugSubscriber<>());
        Thread.sleep(2000L);

//         skipUntil : 인자로 지정한 Flowable / Observable 이 데이터를 통지할 때 까지 결과데이터를 통지하지않는다.

        System.out.println(Thread.currentThread().getName() + " : 여기서 다른 Flowable v1");

        Flowable<Long> skipUntil = Flowable.interval(300L, TimeUnit.MILLISECONDS).skipUntil(Flowable.timer(1000L, TimeUnit.MILLISECONDS)); // 인자로 지정한 개수만큼 건너뛰고 통지한다.
        skipUntil.subscribe(new DebugSubscriber<>()); // 인자로 넣어준 Flowable 이 데이터를 통지하고 나서 skipUntil 이 데이터를 통지한다.
        Thread.sleep(2000L);

        System.out.println(Thread.currentThread().getName() + " : 여기서 다른 Flowable v2");

//         skipWhile : 지정한 조건이 true 면 데이터를 통지하지 않는 연산자. 받은 데이터가 false 면 해당 지점부터 데이터를 통지.

        Flowable<Long> skipWhile = Flowable.interval(300L, TimeUnit.MILLISECONDS).skipWhile(data -> data != 3);
        skipWhile.subscribe(new DebugSubscriber<>());
        Thread.sleep(2000L);

//        skipLast : 끝에서부터 지정한 범위만큼의 데이터는 통지하지 않는다.
    }
}
