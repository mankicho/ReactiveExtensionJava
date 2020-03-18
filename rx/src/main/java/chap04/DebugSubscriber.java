package chap04;

import io.reactivex.subscribers.DisposableSubscriber;

// 디버그용 Subscriber 예제
public class DebugSubscriber<T> extends DisposableSubscriber<T> {
    private String label;

    public DebugSubscriber() {
        super();
    }

    public DebugSubscriber(String label) {
        super();
        this.label = label;
    }

    @Override
    public void onNext(T t) {
        // onNext 메서드 호출 시 출력한다.
        String threadName = Thread.currentThread().getName();
        if (label == null) {
            System.out.println(threadName + ": " + t);
        } else {
            System.out.println(threadName + ": " + label + ": " + t);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        // onError 메서드 호출 시 출력
        String threadName = Thread.currentThread().getName();
        if (label == null) {
            System.out.println(threadName + ": 에러 = " + throwable);
        } else {
            System.out.println(threadName + ": " + label + " 에러 = " + throwable);
        }
    }

    @Override
    public void onComplete() {
        if (label == null) {
            System.out.println(Thread.currentThread().getName() + " 완료");
        } else {
            System.out.println(Thread.currentThread().getName() + ": " + label + " 완료");
        }
    }
}
