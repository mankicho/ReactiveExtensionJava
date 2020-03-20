package chap06.domethod;

import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

// 구독이 시작되면서 doOnSubscribe 가 출력되고 그 후에 Subscriber 의 onNext 가 출력된다.
public class DoOnSubscribe {
    public static void main(String[] args) {
        Flowable.range(1, 5)
                // 구독 시작 시 호출
                .doOnSubscribe(subscription -> System.out.println("doOnSubscribe"))
                // 구독
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("--- Subscriber : onSubscribe");
                        subscription.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("--- Subscriber: onNext: " +integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
