package chap03;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class RetrySample {
    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.<Integer>create(emitter -> {
            // flowable 처리 시작
            System.out.println("Flowable 처리 시작");
            // 통지 처리
            for (int i = 1; i <= 3; i++) {
                if (i == 2) {
                    throw new RuntimeException("고의적 에러");
                }
                // 데이터 통지
                emitter.onNext(i);
            }
            // 완료 통지
            emitter.onComplete();
            // flowable 처리 완료
            System.out.println("처리 완료");
        }, BackpressureStrategy.BUFFER).doOnSubscribe(subscription -> System.out.println("flowable : donOnSubscribe")) // 구독 시작하면 출력
                .retry(2);  // 에러 발생시 2번까지 재시도

        // 구독
        flowable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("subscriber: onSubscribe");
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer = " + integer);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("종료");
            }
        });
//        결과
//        subscriber: onSubscribe
//        flowable : donOnSubscribe
//        Flowable 처리 시작
//                integer = 1
//        flowable : donOnSubscribe
//        Flowable 처리 시작
//                integer = 1
//        flowable : donOnSubscribe
//        Flowable 처리 시작
//                integer = 1
//        java.lang.RuntimeException: 고의적 에러
//        데이터 통지하는 flowable 을 create 로 생성 후 2 가 통지될때 에러를 던지므로 2번 더 시도하다가 예외가 던져지고 종료된다.
    }
}
