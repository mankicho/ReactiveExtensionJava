package chap03;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

// 데이터를 받는 측이 무거운 처리 작업을 한다면??
public class SyncSlowerSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1000L, TimeUnit.MILLISECONDS)
                // 데이터 통지할 때의 시스템 시각을 출력
                .doOnNext(data -> System.out.println("emit " + System.currentTimeMillis() + "밀리초 " + data))
                // 구독한다. 2000L 초의 시간이걸리는 무거운 작업을 한다고 가정.
                .subscribe(data -> Thread.sleep(2000L));

        Thread.sleep(8000L);

        // 1000L 초마다 데이터를 통지하는 생산자를 만들었지만 소비자가 데이터를 처리하는데 2000L 초가 걸려서 통지시간도 2000L 초가 걸린다.

//결과    emit 1584508578920밀리초 0
//        emit 1584508580921밀리초 1
//        emit 1584508582922밀리초 2
//        emit 1584508584922밀리초 3
    }
}
