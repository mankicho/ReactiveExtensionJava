package chap02;

// 필드값으로 count 를 가진 Counter 객체
public class Counter {
    private volatile int count;

    public void increment() {
        count++;
    }

    public int get() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
