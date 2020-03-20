package chap06.blocking;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

// 마지막 데이터를 가져온다.
// 완료가 통지될 때까지 다음 처리가 진행되지않는다.
public class BlockingLast {
    public static void main(String[] args) {
        long actual =
                // 다른 스레드에서 Flowable 처리를 한다.
                Flowable.interval(300L, TimeUnit.MILLISECONDS)
                        .take(3)
                        // 마지막 통지 데이터를 메인스레드에서 얻는다.
                        .blockingLast();

        System.out.println("actual = " + actual);
    }
}
