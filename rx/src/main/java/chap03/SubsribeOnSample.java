package chap03;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

// subscribeOn 메서드는 Flowable 혹은 Observable 객체가 실행될 쓰레드를 지정해준다.
public class SubsribeOnSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable.just(1, 2, 3, 4, 5).subscribeOn(Schedulers.computation()) // RxComputationThreadPool
                .subscribeOn(Schedulers.io()) // RxCachedThreadScheduler
                .subscribeOn(Schedulers.single()) // RxSingleScheduler
                .subscribe(data -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + ": " + data);
                });

        // 잠시 기다린다
        Thread.sleep(500L);

//        결과
//        RxComputationThreadPool-1: 1
//        RxComputationThreadPool-1: 2
//        RxComputationThreadPool-1: 3
//        RxComputationThreadPool-1: 4
//        RxComputationThreadPool-1: 5
//        subscribeOn => 처음에 지정된 스케줄러로 설정. 그 뒤에건 무시된다.
    }
}
