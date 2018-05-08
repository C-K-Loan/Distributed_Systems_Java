package Examples.Tutspot.Tutspot;

import Examples.Tutspot.Tutspot.RunnableDemo;

public class Main {

    public static void main(String[] args) {
        // write your code here

        RunnableDemo R1 = new RunnableDemo("Thread-1");
        R1.start();

        RunnableDemo R2 = new RunnableDemo("Thread-2");
        R2.start();


    }
}
