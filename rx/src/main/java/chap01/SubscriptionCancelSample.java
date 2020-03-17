package chap01;

import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

public class SubscriptionCancelSample {
    public static void main(String[] args) throws InterruptedException {
        // 200 밀리초마다 값을 통지하는 FlowAble
        Flowable.interval(200L, TimeUnit.MILLISECONDS).subscribe(new Subscriber<Long>() {
            private Subscription subscription;
            private long startTIme;

            public void onSubscribe(Subscription subscription) {
                // Subscription 객체 저장.
                this.subscription = subscription;
                startTIme = System.currentTimeMillis();
                subscription.request(Long.MAX_VALUE);
            }

            public void onNext(Long aLong) {
                // 구독 시작부터 500 밀리초가 지나면 구독 해지.
                // 프로그램 시작 후 500 밀리초가 아닌 onNext 가 호출된 후 500 밀리초가 지났는지를 보는것.
                if (System.currentTimeMillis() - startTIme > 500) {
                    subscription.cancel();
                    System.out.println("구독 해지");
                    return;
                }

                System.out.println("long = " + aLong);

            }

            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            public void onComplete() {
                System.out.println("완료..");
            }
        });

        Thread.sleep(2000);

    }
}
