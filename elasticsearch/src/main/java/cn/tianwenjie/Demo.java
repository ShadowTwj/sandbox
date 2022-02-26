package cn.tianwenjie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianwj
 * @date 2021/9/1 15:30
 */
public class Demo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.parallelStream().forEach(it -> {
            String name = Thread.currentThread().getName();
            System.out.println(name);
            System.out.println(it);
        });
    }
}
