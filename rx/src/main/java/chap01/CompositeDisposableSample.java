package chap01;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CompositeDisposableSample {
    public static void main(String[] args) throws InterruptedException {
        // Disposable 을 합친다.
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        // CompositeDisposable 에 Disposable 추가. Flowable.subscribe() 가 Disposable 을 반환.
        compositeDisposable.add(Flowable.range(1, 3).doOnCancel(() -> System.out.println("No.1 canceld")).observeOn(Schedulers.computation())
                .subscribe(data -> {
                    Thread.sleep(100L);
                    System.out.println("No.1 : " + data);
                }));

        // CompositeDisposable 에 Disposable 추가. Flowable.subscribe() 가 Disposable 을 반환.
        compositeDisposable.add(Flowable.range(1, 3).doOnCancel(() -> System.out.println("No.2 canceld")).observeOn(Schedulers.computation())
                .subscribe(data -> {
                    Thread.sleep(100L);
                    System.out.println("No.2 : " + data);
                }));

        Thread.sleep(150L);
        // 한번에 구독 해지
        compositeDisposable.dispose();
    }
}
