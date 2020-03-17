package chap01;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import java.time.DayOfWeek;
import java.time.LocalDate;

// 한 건의 데이터만 통지할때 Single 객체 사용
public class SingleSample {
    public static void main(String[] args) {
        // Single 생성
        Single<DayOfWeek> single = Single.create(emitter -> {
            emitter.onSuccess(LocalDate.now().getDayOfWeek());
        });

        // 구독
        single.subscribe(new SingleObserver<DayOfWeek>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                // 구독 준비가 되면
                // 아무것도 하지않음
            }

            @Override
            public void onSuccess(DayOfWeek dayOfWeek) {
                // 데이터 통지를 받았을때
                System.out.println(dayOfWeek);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("에러 = " + throwable.getMessage());
            }
        });
    }
}
