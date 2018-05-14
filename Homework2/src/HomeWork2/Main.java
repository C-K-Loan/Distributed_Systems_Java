package HomeWork2;


import java.awt.event.WindowAdapter;
import java.io.InterruptedIOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws  java.io.FileNotFoundException {
       // testThreadingAndPollingTwoThreads();

//       int amountOfMessages = 100;

      testNThreads(40,3);
     //   testNThreadsRandomNums(100, 2);
       //     testThreadingAndPollingOneThread(100);
     }



    public static void testNThreadsRandomNums(){
        Random r = new Random();

        for (int i = 0  ; i <= 100; i++){
            System.out.println(r.nextInt(2));
        }



    }


    public static  void testNThreadsRandomNums(int amountOfMessages, int amountOfThreads) throws java.io.FileNotFoundException{

        Random r = new Random();

        Thread[] recieverThreads = new Thread[amountOfThreads];
        MessageSequencer sequencer = new MessageSequencer(amountOfMessages);
        MsgReciever[] recievers =  new MsgReciever[amountOfThreads];


        for (int i= 0; i< amountOfThreads;i++){//create all reciever Threads and store them in an array
            recievers[i] = new MsgReciever(sequencer,amountOfMessages,i);
            recieverThreads[i] = new Thread(recievers[i]);

        }

        sequencer.setRecievers(recieverThreads,recievers);

        for(int i = 0; i < amountOfThreads; i++){
            recieverThreads[i].setPriority(10);
        }

        for(int i = 0; i < amountOfThreads; i++){
            recieverThreads[i].start();
        }


        List<Integer> valuesSend = new LinkedList<>();
        for (int i = 0 ; i < amountOfMessages; i++){//send msg to recvers

            //select Random Number between 0 and AmountOfThreads to select a thread randomly who recieves a message
            //sendMessageToRandomReciever(recievers);
            valuesSend.add(r.nextInt(100));
            recievers[r.nextInt(amountOfThreads)].recvieveExternalMessage(valuesSend.get(i));


        }
        for(int i = 0; i < amountOfThreads; i++){
            recievers[i].storeMessageLogToFile();
        }


        // TODO BUGGY
        System.out.println("VALUES SEND :" );
        String printString = " ";
        for (int val : valuesSend){
            System.out.println("Adding: " + val );
            printString.concat(Integer.toString(val )+ ",  " );
            System.out.println("String is "+ printString);
        }

        System.out.println("Done building!");
        System.clearProperty(printString);
    }

    /**
     *
     * Test N Threads, where we Randomly select a Thread which will recieve a message
     * @param amountOfMessages
     * @param amountOfThreads
     */
    public static  void testNThreads(int amountOfMessages, int amountOfThreads)throws java.io.FileNotFoundException{

        Random r = new Random();

        Thread[] recieverThreads = new Thread[amountOfThreads];
        MessageSequencer sequencer = new MessageSequencer(amountOfMessages);
        MsgReciever[] recievers =  new MsgReciever[amountOfThreads];
        Thread sequencerThread = new Thread (sequencer);

        for (int i= 0; i< amountOfThreads;i++){//create all reciever Threads and store them in an array
            recievers[i] = new MsgReciever(sequencer,amountOfMessages,i);
            recieverThreads[i] = new Thread(recievers[i]);

        }

        sequencer.setRecievers(recieverThreads,recievers);

        sequencerThread.setPriority(10);
        sequencerThread.start();
        for(int i = 0; i < amountOfThreads; i++){
            recieverThreads[i].setPriority(10);
        }

        for(int i = 0; i < amountOfThreads; i++){
            recieverThreads[i].start();
        }

        for (int i = 0 ; i < amountOfMessages; i++){//send msg to recvers

            //select Random Number between 0 and AmountOfThreads to select a thread randomly who recieves a message
           //sendMessageToRandomReciever(recievers);
            recievers[r.nextInt(amountOfThreads)].recvieveExternalMessage(i);


        }
        for(int i = 0; i < amountOfThreads; i++){
            recievers[i].storeMessageLogToFile();
        }

    }

    public static void sendMessageToRandomReciever(Thread[] recievers ){


    }

    public static void testThreadingAndPollingOneThread(int amountOfMessages) throws java.io.FileNotFoundException{
System.out.println("THIS TEST IS BUGGY _______________________ FUX!");
        MessageSequencer sequencer = new MessageSequencer(amountOfMessages);
        MsgReciever reciever = new MsgReciever(sequencer,amountOfMessages,0);


        Thread[] recievers = new Thread[1];

        Thread t0 =         new Thread(reciever);
        recievers[0] = t0;
        sequencer.setRecievers(recievers, null);
        t0.setPriority(10);
        t0.start();



        for ( int i = 0 ; i < amountOfMessages; i++){
            System.out.println("Sending msg"+ i);
            System.out.println("ALVIE STATUS: " + t0.isAlive());
            reciever.recvieveExternalMessage(i);
        }

        reciever.storeMessageLogToFile();

    }

    public static void testThreadingAndPollingTwoThreads(int amountOfMessages) throws java.io.FileNotFoundException{
        MessageSequencer sequencer = new MessageSequencer(amountOfMessages);
        MsgReciever reciever = new MsgReciever(sequencer, amountOfMessages, 0);
        MsgReciever reciever2 = new MsgReciever(sequencer, amountOfMessages, 2 );

        Thread t0 =         new Thread(reciever);
        Thread t1 =         new Thread(reciever2);

        Thread[] recievers = new Thread[2];
        recievers[0] = t0;
        recievers[1] = t1;

        t1.setPriority(10);
        t0.setPriority(10);
        t1.start();
        t0.start();


        for ( int i = 0 ; i < amountOfMessages; i++){
            //System.out.println("Sending msg");
            reciever.recvieveExternalMessage(i);
            reciever2.recvieveExternalMessage(i+1);
        }

        reciever.storeMessageLogToFile();

    }


    public static void testRecieverRecievingMessagesAndStoringToFile(int amountOfMessages ) throws java.io.FileNotFoundException{

        MsgReciever reciever = new MsgReciever(null, amountOfMessages, 0);
        for ( int i = 0 ; i < amountOfMessages; i++){
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
