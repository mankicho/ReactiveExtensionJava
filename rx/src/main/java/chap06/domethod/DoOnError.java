package chap06.domethod;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

// map 이 호출되기전에는 에러가 발생하지않기때문에 doOnError 가 호출이 안되지만 map 호출 후 에러가 발생하기때문에 뒤의 doOnError 가 호출됨.
// 이 처럼 위치에 따라 호출될수도있고 안될수도있음.
public class DoOnError {
    public static void main(String[] args) {
        Flowable.range(1, 5)
                // 에러 통지 시 로그 출력
                .doOnError(error -> System.out.println("기존 데이터 = " + error.getMessage()))
                // 데이터가 3 일때 에러 발생
                .map(data -> {
                    if (data == 3) {
                        throw new Exception("에러 발생");
                    }
                    return data;
                })
                // 에러 통지 시 로그출력
                .doOnError(error -> System.out.println("map 후 데이터 = " + error.getMessage()))
                .subscribe(new DebugSubscriber<>());
    }
}
