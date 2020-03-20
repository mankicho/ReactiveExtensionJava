package chap06.blocking;

import chap02.Counter;
import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

import java.util.concurrent.TimeUnit;

// 호출한 스레드에서 소비자의 통지 데이터를 처리할 수 있게 해주는 메서드
// 모든 데이터의 통지 완료 처리가 끝나거나, 에러가 발생해 에러를 통지할때까지 호출한 스레드에서 이후 처리를 진행하지않는다.
// 따라서 비동기적으로 Flowable 이 통지하고 이 통지를 받은 Subscriber 가 어떤 부가작용을 처리할 때 이 부가 작용의 결과를 확인하는 데 blockingSubscribe 메서드를 사용할 수 있다.
public class BlockingSubscribe {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(1000L, TimeUnit.MILLISECONDS).take(5);

        Counter counter = new Counter();

        // flowable 은 interval 로 만들었기때문에 다른스레드에서 처리하지만
        // blockingSubscribe 를 호출하면 데이터 소비는 호출한 스레드에서 하게된다.
        flowable.blockingSubscribe(new DisposableSubscriber<Long>() {
            @Override
            public void onNext(Long aLong) {
                System.out.println(Thread.currentThread().getName() + "에서 onNext 실행중");
                counter.increment();
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(Thread.currentThread().getName() + ":" + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println(Thread.currentThread().getName() + ": 완료");
            }
        });
        Thread.sleep(5000L);
        System.out.println(counter.get() + "값");
    }
}
