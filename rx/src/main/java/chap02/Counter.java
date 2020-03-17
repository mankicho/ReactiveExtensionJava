package chap02;

 // 필드값으로 count 를 가진 Counter 객체
public class Counter {
    private volatile int count;

    void increment() {
        count++;
    }

    int get() {
        return count;
    }
}
