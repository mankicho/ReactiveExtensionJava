package chap03;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;

import java.util.concurrent.TimeUnit;

public class NonMainThreadSample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");

        Flowable.interval(300L, TimeUnit.MILLISECONDS).subscribe(new ResourceSubscriber<Long>() {
            @Override
            public void onNext(Long aLong) {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + ": " + aLong);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " 완료");
            }
        });

        System.out.println("end");

        Thread.sleep(1000L);

//        결과
//        start
//        end
//        RxComputationThreadPool-1: 0
//        RxComputationThreadPool-1: 1
//        RxComputationThreadPool-1: 2
//        메인 스레드가 아닌 스레드에서 처리작업을 하기때문에 결과가 이렇게나옴
//        메인 스레드는 start -> flowable 호출 -> end
//        다른 스레드에서 flowable 처리`

    }
}
