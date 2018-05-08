package HomeWork2;


import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws  java.io.FileNotFoundException {
       // testThreadingAndPollingTwoThreads();

       int amountOfMessages = 1000;
        testThreadingAndPollingOneThread(amountOfMessages);

    }



    public static void testIntArrays(){

        int[] testarr = new int[100];



    }

    public static void testThreadingAndPollingOneThread(int amountOfMessages) throws java.io.FileNotFoundException{
        MessageSequencer sequencer = new MessageSequencer();
        MsgReciever reciever = new MsgReciever(sequencer,amountOfMessages,0);

        Thread t =         new Thread(reciever);

        t.start();
        for ( int i = 0 ; i < 100; i++){
            //System.out.println("Sending msg");
            reciever.recvieveExternalMessage(i);
        }

        reciever.storeMessageLogToFile();

    }

    public static void testThreadingAndPollingTwoThreads(int amountOfMessages) throws java.io.FileNotFoundException{
        MessageSequencer sequencer = new MessageSequencer();
        MsgReciever reciever = new MsgReciever(sequencer, amountOfMessages, 0);
        MsgReciever reciever2 = new MsgReciever(sequencer, amountOfMessages, 2);

        Thread t =         new Thread(reciever);
        Thread t2 =         new Thread(reciever2);


        t.start();
        t2.start();


        for ( int i = 0 ; i < 10; i++){
            //System.out.println("Sending msg");
            reciever.recvieveExternalMessage(i);
            reciever2.recvieveExternalMessage(i+1);
        }

        reciever.storeMessageLogToFile();

    }


    public static void testRecieverRecievingMessagesAndStoringToFile(int amountOfMessages ) throws java.io.FileNotFoundException{

        MsgReciever reciever = new MsgReciever(null, amountOfMessages, 0);
        for ( int i = 0 ; i < 100; i++){
            reciever.recvieveExternalMessage(i);
        }

        reciever.storeMessageLogToFile();

    }


    public static void testLists(){
        LinkedList<Integer> testL= new LinkedList<Integer>();
        System.out.println("Size :" + testL.size());

        testL.add(0);
        System.out.println("SIZE: " + testL.size());
        testL.add(1);
        System.out.println("SIZE: " + testL.size());

        System.out.println(testL.get(0));
    }

}
