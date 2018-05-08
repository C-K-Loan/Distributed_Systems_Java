package HomeWork2;


import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws  java.io.FileNotFoundException {
        testThreadingAndPollingOneThread();


    }




    public static void testThreadingAndPollingOneThread() throws java.io.FileNotFoundException{
        MessageSequencer sequencer = new MessageSequencer();
        MsgReciever reciever = new MsgReciever(sequencer,0);

        Thread t =         new Thread(reciever);

        t.start();
        for ( int i = 0 ; i < 500; i++){
            //System.out.println("Sending msg");
            reciever.recvieveExternalMessage(i);
        }

        reciever.storeMessageLogToFile();

    }

    public static void testRecieverRecievingMessagesAndStoringToFile() throws java.io.FileNotFoundException{

        MsgReciever reciever = new MsgReciever(null,0);
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
