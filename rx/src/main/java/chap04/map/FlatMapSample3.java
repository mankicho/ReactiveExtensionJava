package chap04.map;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

// 숫자를 통지하는 FlowAble 을 생성한 FlowAble 이 통지하는 내용에 따라 생성하고 FlatMap 으로 다른 결과를 통지
// 일반적인 데이터 통지는 그대로, 완료는 100, 에러는 -1 통지
public class FlatMapSample3 {
    public static void main(String[] args) {
        Flowable<Integer> original = Flowable.just(1, 2, 0, 4, 5).map(data -> 10 / data);

        Flowable<Integer> flowable = original.flatMap(
                // 일반 통지 시 데이터
                Flowable::just,
                // 에러 발생 시 데이터
                error -> Flowable.just(-1),
                // 완료 시
                () -> Flowable.just(100));

        flowable.subscribe(new DebugSubscriber<>());
//        결과
//        main: 10
//        main: 5
//        main: -1
//        main 완료
//        10,5,-1,2,2,100 이 나와야 할 거 같은데 10,5,-1,완료 가 나왔다 이유는?
//        정상 데이터는 그대로 통지, 에러가 통지되면 flatMap 메서드의 onErrorMapper 로 지정한 함수형 인터페이스에서 -1 을 통지하며 그 결과 정상적으로 완료를 통지한다.
//        즉 -1 을 통지하고 완료를 통지하는것이다. 이 때 원본 flowable 이 아닌 flatMap 으로 만든 Flowable 이 완료를 통지한 것이므로 onCompleteSupplier 는 시행되지 않는다.
//        에러가 안 발생해서 원본 Flowable 이 정상적으로 완료한다면 100 을 출력할 것이다.
    }
}
