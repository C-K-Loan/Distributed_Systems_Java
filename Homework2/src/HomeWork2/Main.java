package HomeWork2;


import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws  java.io.FileNotFoundException {
        testThreadingAndPollingOneThread();


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
    public static void testThreadingAndPollingOneThread(){
        MessageSequencer sequencer = new MessageSequencer();
        MsgReciever reciever = new MsgReciever(sequencer,0);

        Thread t =         new Thread(reciever);
//    t.run();
        t.start();
        for ( int i = 0 ; i < 100; i++){
            //System.out.println("Sending msg");
            reciever.recvieveExternalMessage(i);
        }
    }

    public static void testRecieverRecievingMessagesAndStoringToFile() throws java.io.FileNotFoundException{

        MsgReciever reciever = new MsgReciever(null,0);
        for ( int i = 0 ; i < 100; i++){
            reciever.recvieveExternalMessage(i);
        }

        reciever.storeMessageLogToFile();

    }

}
