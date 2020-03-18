package chap03;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

import java.util.concurrent.TimeUnit;

// observeOn 메서드로 bufferSize 를 지정하는 예제
public class ObserveOnSample {
    public static void main(String[] args) throws Exception {
        Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS).onBackpressureDrop(); // 요청한 만큼 데이터 통지하고 다음 요청까지의 데이터는 파기

        // 비동기로 데이터를 받게 하고 버퍼크기를 1로 설정
        // observeOn 메서드는 연산이 실행될 쓰레드를 지정해준다. 매번 변경할 수 있다.
        flowable.observeOn(Schedulers.computation(), false, 1)
                .subscribe(new ResourceSubscriber<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        // 무거운 처리를 한다 가정하고 1초 기다림
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.exit(1);
                        }
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": " + aLong);
                    }


                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println(Thread.currentThread().getName() + " 완료");
                    }
                });
        Thread.sleep(7000L);
//        결과
//        RxComputationThreadPool-1: 0
//        RxComputationThreadPool-1: 4
//        RxComputationThreadPool-1: 8
//        RxComputationThreadPool-1: 12
//        RxComputationThreadPool-1: 16
//        300 초마다 데이터를 생산하는 생산자, 1초동안 하는 무거운 작업처리때문에 데이터 소비하는데 걸리는 시간 1초
    }
}
