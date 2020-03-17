package chap01;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FlowableSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {

            public void subscribe(FlowableEmitter<String> flowableEmitter) throws Exception {
                String[] data = {"Hello, World", "안녕, RxJava"};
                for (String str : data) {
                    // 구독이 취소되면 처리중단.
                    if (flowableEmitter.isCancelled()) {
                        return;
                    }
                    // 데이터 통지하기.
                    flowableEmitter.onNext(str);
                }
                // 데이터 통지가 끝나면 완료신호 보내기.
                flowableEmitter.onComplete();
            }
            // 역압력 전략을 Buffer 로 지정. 수신자의 속도에 맞춰 통지하고 나머지는 버퍼에 담아둔다.
        }, BackpressureStrategy.BUFFER);

        // Subscriber 처리를 개별 스레드에서 실행한다.
        flowable.observeOn(Schedulers.computation()).subscribe(new Subscriber<String>() {
            // observeOn 메서드는 데이터를 통지하는 측, 데이터를 전달받고 처리하는 측을 각각 별도의 스레드에서 처리하기위해

            private Subscription subscription;

            public void onSubscribe(Subscription subscription) {
                // Subscription 객체를 저장.
                this.subscription = subscription;
                // 데이터 한개 요청
                this.subscription.request(2L);
            }

            public void onNext(String s) {
                // 실행중인 스레드 이름 가져오기
                String threadName = Thread.currentThread().getName();
                // 받은 데이터 출력
                System.out.println(threadName + " : " + s);
                // 다음에 받을 데이터 개수 요청
                this.subscription.request(1L);
            }

            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            public void onComplete() {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " 완료");
            }
        });
        Thread.sleep(500);

    }
}
