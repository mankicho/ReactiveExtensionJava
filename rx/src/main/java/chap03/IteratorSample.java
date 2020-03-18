package chap03;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// 이터레이터 패턴을 활용한 데이터 순차처리.
public class IteratorSample {
    public static void main(String[] args) {
        // 리스트에서 Iterator 를 얻는다.
        List<String> list = Arrays.asList("a", "b", "c");
        Iterator<String> iterator = list.iterator();

        // 받을 데이터가 있으면
        while(iterator.hasNext()){
            // 데이터를 얻는다
            String value = iterator.next(); // next 메서드는 Iterator 가 가리키는 인덱스에 있는 데이터를 얻는다.
            // 데이터 조작하기
            System.out.println(value);
        }
    }
}
