package chap01;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ObservableSample {
    public static void main(String[] args) throws InterruptedException {
        // 인사말을 통지(발행)하는 Observable 생성
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                // 통지(발행) 할 데이터
                String[] data = {"Hello, World", "안녕, RxJava"};
                for (String str : data) {
                    // 구독이 해지되면 처리 중단.
                    if (observableEmitter.isDisposed()) {
                        return;
                    }
                    // 데이터를 통지
                    observableEmitter.onNext(str);
                }
                // 데이터 통지 완료하면 완료 신호 보내기
                observableEmitter.onComplete();
            }
            // FlowAble 은 역압을 지정해줘야해서 BackPressure 객체를 지정해줘야하지만 Observable 은 역압기능이 없기때문에 지정 할 필요없다
            // 구독하면 데이터가 생성될때마다 통지된다.
        });

        // 소비하는 측의 처리를 개별 스레드로 실행
        observable.observeOn(Schedulers.computation()).
                // 구독하기
                        subscribe(new Observer<String>() {
                    // subscribe 메서드 호출 시 처리
                    public void onSubscribe(Disposable disposable) {
                        // 아무것도 하지 않음
                        // 구독 중 구독을 해지해야하면 전달받은 disposable 을 Observer 내부에 보관해야 한다.
                        // private Disposable disposable; this.disposable = disposable 이런 식으로.
                    }

                    public void onNext(String s) {
                        // 실행하는 스레드 이름을 얻는다.
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + " : " + s);

                        // 특정 시점에 구독을 해지하고싶다면 조건에맞게 disposable.dispose() 를 호출하면 된다.
                    }

                    // 에러 시 처리
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    // 완료 시 처리
                    public void onComplete() {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + " 완료");
                    }
                });
        Thread.sleep(500);
    }
}