package chap03;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class SyncFasterSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1000L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> System.out.println("emit: " + System.currentTimeMillis() + "밀리초: " + data))
                // 1000L 초마다 데이터를 통지하지만 소비자가 데이터를 처리하는작업은 500L 초일때 예제
                .subscribe(data -> Thread.sleep(500L));

        Thread.sleep(5000L);

//        결과
//        emit: 1584508747582밀리초: 0
//        emit: 1584508748582밀리초: 1
//        emit: 1584508749583밀리초: 2
//        emit: 1584508750582밀리초: 3
//        emit: 1584508751582밀리초: 4
//        소비자가 처리하는 속도가 데이터 통지속도보다 빠르면 소비자의 속도가 생산자의 속도에 영향을미치지않는다.
//        Flowable.interval 로 만드는게 아니라 create 로 생산자를 만들면 데이터 받는측의 처리 속도에 영향을 받지 않게 해야한다.
    }
}
