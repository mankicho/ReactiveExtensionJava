package chap01;

import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class ViolatedReactiveStreamsSample {
    public static void main(String[] args) {
        Flowable.range(1, 3)
                // 구독하기
                .subscribe(new Subscriber<Integer>() {
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("onSubscribe start");
                        subscription.request(Long.MAX_VALUE);
                        System.out.println("onSubscribe end");
                    }
                    // RxJava 2.0.4 버전 onSubscribe start, 1, 2, 3, 완료, onSubscribe end 가 출력.
                    // onSubscribe 도중 request 가 호출되서 메서드 처리 도중 다른 처리가 시행됨
                    // 하지만 RxJava 2.0.6 버전에서는 onSubscribe start, onSubscribe end, 1, 2, 3, 완료 가 출력
                    // RxJava 2.0.7 버전 이후부터는 onSubscribe 메서드가 끝난 뒤 onNext 가 호출되기 때문이다.

                    public void onNext(Integer integer) {
                        System.out.println(integer);
                    }

                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    public void onComplete() {
                        System.out.println("완료");
                    }
                });
    }
}
