package chap01;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;

import java.time.DayOfWeek;
import java.time.LocalDate;

// 한 건 또는 통지를 안하는 객체
public class MaybeSample {
    public static void main(String[] args) {
        Maybe<DayOfWeek> maybe = Maybe.create(emitter -> {
            emitter.onSuccess(LocalDate.now().getDayOfWeek());
        });

        // 구독
        maybe.subscribe(new MaybeObserver<DayOfWeek>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                // 아무것도 하지않음
            }

            @Override
            public void onSuccess(DayOfWeek dayOfWeek) {
                System.out.println(dayOfWeek);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("완료"); // Maybe 객체는 onComplete 메서드가 실행되지 않는다. 처리를 끝낼때 사용한다.
            }
        });
    }
}
