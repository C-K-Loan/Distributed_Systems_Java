package HomeWork2;


import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws  java.io.FileNotFoundException {
       // testThreadingAndPollingTwoThreads();

//       int amountOfMessages = 100;

       testNThreads(100,5);

     }



    public static void testRandom(){
        Random r = new Random();

        for (int i = 0  ; i <= 100; i++){
            System.out.println(r.nextInt(2));
        }



    }

    /**
     * Test N Threads, where we Randomly select a Thread which will recieve a message
     * @param amountOfMessages
     * @param amountOfThreads
     */
    public static  void testNThreads(int amountOfMessages, int amountOfThreads){

        Random r = new Random();

        Thread[] recieverThreads = new Thread[amountOfThreads];
        MessageSequencer sequencer = new MessageSequencer(amountOfMessages);
        MsgReciever[] recievers =  new MsgReciever[amountOfThreads];


        for (int i= 0; i< amountOfThreads;i++){//create all reciever Threads and store them in an array
            recievers[i] = new MsgReciever(sequencer,amountOfMessages,i);
            recieverThreads[i] = new Thread(recievers[i]);

        }

        sequencer.setRecievers(recieverThreads);

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

    }

    public static void sendMessageToRandomReciever(Thread[] recievers ){


    }

    public static void testThreadingAndPollingOneThread(int amountOfMessages) throws java.io.FileNotFoundException{

        MessageSequencer sequencer = new MessageSequencer(amountOfMessages);
        MsgReciever reciever = new MsgReciever(sequencer,amountOfMessages,0);


        Thread[] recievers = new Thread[1];

        Thread t0 =         new Thread(reciever);
        recievers[0] = t0;
        sequencer.setRecievers(recievers);
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
