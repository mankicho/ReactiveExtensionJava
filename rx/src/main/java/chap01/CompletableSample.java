package chap01;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

// 데이터를 통지하지않고 완료나 에러를 통지하는 객체
public class CompletableSample {
    public static void main(String[] args) throws InterruptedException {
        // Completable 생성
        // 중간 업무로직 처리
// 완료 통지
        Completable completable = Completable.create(CompletableEmitter::onComplete); // Completable 에서는 부가 작용이 발생하는 처리를 수행 후 완료를 통지
        completable.subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() { // completable 의 처리를 비동기로 실행.
            @Override
            public void onSubscribe(Disposable disposable) {
                // 아무것도 안함
            }

            @Override
            public void onComplete() {
                System.out.println("완료");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
   Thread.sleep(1000);
    }

}
