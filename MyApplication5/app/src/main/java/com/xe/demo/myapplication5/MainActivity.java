package com.xe.demo.myapplication5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /**
     * 这个接口，有且只有一个方法，是接口必须实现的，所以它是函数式接口
     */
    interface MyInterface1 {
        void test();
    }

    /**
     * 这个接口，有2个方法，都是接口必须实现的，所以它不是函数式接口
     */
    interface MyInterface2 {
        void test();

        void test2();
    }

    /**
     * 这个接口，没有一个方法是接口必须实现的，所以它不是函数式接口
     */
    interface MyInterface3 {

    }

    /**
     * 这个接口，虽然自己本身没有定义一个抽象方法，
     * 但可以从父类那里继承一个抽象方法，
     * 所以它是函数式接口
     */
    interface MyInterface4 extends MyInterface1 {

    }

    /**
     * 这个接口，虽然有2个方法，但是 default 标签的方法在子类中不是必须实现的，
     * 实现的方法只有 test()，所以它是函数式接口
     */
    interface MyInterface5 {
        void test();

        default void test2() {

        }

        ;
    }

    /**
     * 这个接口，虽然有2个方法，但是该接口继承于 Object，
     * 在父类 Object 那里已经实现了 toString() 方法，
     * 因此 toString() 不是必须要实现的，
     * 实现的方法只有 test()，它是函数式接口
     */
    interface MyInterface6 {
        void test();

        String toString();
    }

    /**
     * 无参数无返回值
     */
    interface MyInterface7 {
        void test();
    }

    /**
     * 无参数有返回值
     */
    interface MyInterface8 {
        int test();
    }

    /**
     * 有参数无返回值
     */
    interface MyInterface9 {
        void test(int x, int y);
    }

    /**
     * 有参数有返回值
     */
    interface MyInterface10 {
        int test(int x, int y);
    }

    interface MyInterface11 {
        void test(int x);
    }

    /**
     * 无参数无返回值的接口
     */
    interface MyInterface12 {
        void test();
    }

    /**
     * 无参数有返回值的接口
     */
    interface MyInterface13 {
        int test();
    }

    /**
     * 有2个参数无返回值的接口
     */
    interface MyInterface14 {
        void test(int x,int y);
    }

    /**
     * 有2个参数有返回值的接口
     */
    interface MyInterface15 {
        int test(int x,int y);
    }

    /**
     * 没有参数没有返回值
     */
    interface MyInterface16 {
        void test();
    }

    /**
     * 没有参数有 MyClass2 类型对象返回值
     */
    interface MyInterface17 {
        MyClass2 test();
    }

    /**
     * 有2个参数以及有 MyClass2 类型对象返回值
     */
    interface MyInterface18 {
        MyClass2 test(String name,int age);
    }


    interface MyInterface19 {
        void test(MyClass2 mc2,int x,int y);
    }

    interface MyInterface20 {
        void test(MyClass2 mc2);
    }

    static class MyClass {
        public void test() {
            System.out.println("执行MyClass类对象的test()方法");
        }
        public int test2() {
            System.out.println("执行MyClass类对象的test2()方法");
            return 1;
        }
        public void test3(int x,int y) {
            System.out.println("执行MyClass类对象的test3()方法");
        }
        public int test4(int x,int y) {
            System.out.println("执行MyClass类对象的test()方法");
            return 1;
        }
    }

    class MyClass2 {
        private String name;
        private int age;
        public MyClass2(){
            System.out.println("执行了MyClass2类对象的无参数构造方法");
        }
        public MyClass2(String name,int age){
            System.out.println("执行了MyClass2类对象的有2个参数的构造方法，" +
                    "一个参数为：" + name + "，一个参数为：" + age);
        }
        public void fun(int x,int y) {
            System.out.println("调用了MyClass2类对象的有参数fun方法，参数x = " + x + ",参数y = " + y);
        }
        public void fun2() {
            System.out.println("调用了MyClass2类对象的无参数fun方法");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
    }
    private void test7() {
        int num = 2;
        MyInterface7 myInterface7 = () -> {
            int j = num * 3;
        };

        /**
         * error，不可以再更改了
         */
//        num = 5;
    }
    private void test6() {
        MyClass2 myClass2 = new MyClass2();

        /**
         * 在 lambda 表达式实现的过程中，
         * 接口方法种除了第一个参数，所有的参数个数以及类型要和对象方法中参数的个数以及类型一一对应
         */
        MyInterface19 myInterface19_1 = (mc2, x, y) -> mc2.fun(x,y);

        /**
         * 在 lambda 表达式实现的过程中，
         * 接口方法种除了第一个参数，所有的参数个数以及类型要和对象方法中参数的个数以及类型一一对应
         */
        MyInterface19 myInterface19_2 = MyClass2 ::fun;

        myInterface19_1.test(myClass2,1,2);
        myInterface19_2.test(myClass2,1,2);
    }

    private void test5() {
        /**
         *  引用了 MyClass2() 构造方法，new 后面不能有大括号
         */
        MyInterface16 myInterface16 = MyClass2 ::new;

        /**
         *  引用了 MyClass2() 构造方法，new 后面不能有大括号
         */
        MyInterface17 myInterface17 = MyClass2 ::new;

        /**
         *  引用了 MyClass2(String name,int age) 构造方法，new 后面不能有大括号
         */
        MyInterface18 myInterface18 = MyClass2 ::new;

        /**
         * 这里接口中的方法没有返回值
         */
        myInterface16.test();

        MyClass2 myClass2_1 = myInterface17.test();
        MyClass2 myClass2_2 = myInterface18.test("小二",21);
    }
    private void test4() {
        MyClass myClass = new MyClass();

        /**
         * lambda 表达式对方法的引用实现了无参数无返回值的接口
         * 注意方法后面不能出现大括号
         */
        MyInterface12 myInterface12 = myClass ::test;

        /**
         * lambda 表达式对方法的引用实现了无参数有返回值的接口
         * 注意方法后面不能出现大括号
         */
        MyInterface13 myInterface13 = myClass ::test2;

        /**
         * lambda 表达式对方法的引用实现了2个参数无返回值的接口
         * 注意方法后面不能出现大括号
         */
        MyInterface14 myInterface14 = myClass ::test3;

        /**
         * lambda 表达式对方法的引用实现了2个参数有返回值的接口
         * 注意方法后面不能出现大括号
         */
        MyInterface15 myInterface15 = myClass ::test4;


        myInterface12.test();
        int result = myInterface13.test();
        myInterface14.test(1,2);
        int result2 = myInterface15.test(1,2);
    }

    private void test3() {

        /**
         * 没有简化 lambda 表达式小括号
         */
        MyInterface11 myInterface11_1 = (x) -> {
            System.out.println("传入的参数为：" + x);
        };

        /**
         * 简化 lambda 表达式小括号之后，参数 x 一定要写上
         */
        MyInterface11 myInterface11_2 = x -> {
            System.out.println("传入的参数为：" + x);
        };

        /**
         *lambda 表达式的方法体只有一条语句，可以省略大括号
         */
        MyInterface11 myInterface11_3 = x ->
            System.out.println("传入的参数为：" + x);

        MyInterface10 myInterface10 = (int x, int y) ->
            x - y;


        myInterface11_1.test(2);
        myInterface11_2.test(2);
        myInterface11_3.test(2);
    }

    private void test2() {

        /**
         * 这里省略了全部的参数类型，
         * 注意：参数类型要么一个都不省略，要么全省略，否则语法报错
         */
        MyInterface9 myInterface9 = (x, y) -> {
            int sum = x + y;
            System.out.println("用 lambda 表达式实现 有参数无返回值的接口，sum的值为" + sum);
        };
        myInterface9.test(1,2);
    }

    private void test1() {
        /**
         * 用 lambda 表达式实现 无参数无返回值的接口
         */
        MyInterface7 myInterface7 = () -> {
            System.out.println("用 lambda 表达式实现 无参数无返回值的接口");
        };

        /**
         * 用 lambda 表达式实现 无参数有返回值的接口
         * lambda 表达式的返回值类型和接口方法中的返回值类型一样
         */
        MyInterface8 myInterface8 = () -> {
            System.out.println("用 lambda 表达式实现 无参数有返回值的接口");
            return 0;
        };

        /**
         * 用 lambda 表达式实现 有参数无返回值的接口
         * 这里的 lambda 表达式函数中参数的个数和参数类型要与接口中方法的参数个数和参数类型一一对应
         */
        MyInterface9 myInterface9 = (int x, int y) -> {
            int sum = x + y;
            System.out.println("用 lambda 表达式实现 有参数无返回值的接口，sum的值为" + sum);
        };

        /**
         * 用 lambda 表达式实现 有参数有返回值的接口
         * 这里的 lambda 表达式函数中参数的个数和参数类型要与接口中方法的参数个数和参数类型一一对应
         * lambda 表达式的返回值类型和接口方法中的返回值类型一样
         */
        MyInterface10 myInterface10 = (int x, int y) -> {
            return x - y;
        };

        myInterface7.test();
        int result = myInterface8.test();
        myInterface9.test(1, 2);
        int result2 = myInterface10.test(1, 2);

    }

}
