package chap04.create;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.function.Function;
import java.util.function.Supplier;

// 배열에 담긴 객체를 순서대로 통지하는 Flowable 예제
public class FromArraySample {
    public static void main(String[] args) {
        // 배열 데이터를 순서대로 통지하는 flowable 생성
        String[] data = {"A", "B", "C", "D", "E", "F", "G"};

        Flowable<String> flowable = Flowable.fromArray(data);

        flowable.subscribe(new DebugSubscriber<>());
    }
}
