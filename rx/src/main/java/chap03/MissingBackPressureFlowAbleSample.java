package chap03;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

// 버퍼에 쌓아둔 데이터가 너무 많으면 MissingBackpressureException 이 발생
public class MissingBackPressureFlowAbleSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(10L, TimeUnit.MILLISECONDS).
                doOnNext(value -> System.out.println("emit = " + value)); // 데이터 통지 시 출력

        flowable.observeOn(Schedulers.computation()) // 각각 다른스레드에서 데이터를 받는다.
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        // 무제한 데이터통지
                        subscription.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        try {
                            System.out.println("waiting...");
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("aLong = " + aLong);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("에러 = " + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("완료");
                    }
                });
        Thread.sleep(5000L);
        // 데이터 통지 측과 수신측의 속도 차이가 많이나면 버퍼에 통지할 데이터가 많이 쌓여서 예외가 발생한다.
    }
}
