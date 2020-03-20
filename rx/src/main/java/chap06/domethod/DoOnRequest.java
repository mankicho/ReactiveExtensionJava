package chap06.domethod;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

// observeOn 메서드를 적용하기 전에는 기본 size 인 128 이 출력된다. 기본적으로 128 개를 요청하게 되기때문에
// 하지만 다른 스레드에서 처리하게하고 Subscriber 의 요청개수를 1개로 하면
// 데이터 개수를 요청할때 출력문에서 1개로 출력됨.
public class DoOnRequest {
    public static void main(String[] args) throws InterruptedException {
        Flowable.range(1, 5)
                // 데이터 개수를 요청 할 시에 로그를 출력한다.
                .doOnRequest(size -> System.out.println(Thread.currentThread().getName() + "기존 데이터:size=" + size))
                // Subscriber 처리를 다른 스레드에서
                .observeOn(Schedulers.computation())
                // 데이터 개수를 요청할 시 출력
                .doOnRequest(size -> System.out.println(Thread.currentThread().getName() + "--- observeOn 적용 후 size:" + size)).subscribe(new Subscriber<Integer>() {
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });

        Thread.sleep(1000L);
    }
}
