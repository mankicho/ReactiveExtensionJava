package chap03;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;

public class MainThreadSample {
    public static void main(String[] args) {
        System.out.println("start");

        // just 나 from 메서드 처럼 이미 생산된 데이터로부터 Flowable/Observable 을 만들면 메인 스레드에서 작동한다.
        Flowable.just(1, 2, 3).subscribe(new ResourceSubscriber<Integer>() {
            @Override
            public void onNext(Integer integer) {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + ": " + integer);
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

//        결과
//        start
//        main: 1
//        main: 2
//        main: 3
//        main 완료
//        end
//        MainThread 에서 시행되기때문에 Flowable 의 데이터처리가 끝나야 그다음 처리를한다.
    }
}
