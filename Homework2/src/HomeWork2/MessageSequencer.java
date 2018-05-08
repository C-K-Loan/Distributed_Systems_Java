package HomeWork2;

public class MessageSequencer implements  Runnable {

    int [] internalmessageLog;
    int [] internalMessageQue;
    Thread[] recievers;
    int messageAmount;
    int messageCount;

    public MessageSequencer( int messageAmount){
         this.messageAmount = messageAmount;
        this.internalmessageLog = new int [messageAmount];
        this.internalmessageLog = new int [messageAmount];
        this.messageCount = 0 ;
    }


    public void setRecievers(Thread[]recievers){
        this.recievers =  recievers;
    }
    public void recieveInternalMessage(int message){
        System.out.println("{SEQUENCER} GOT NOTIFIED FOR MSG: " + message);
        this.internalmessageLog[this.messageCount] = message;
        this.messageCount ++ ;


    }


    //main loop, where we check for new Messages and broadcast them
    public void run(){


        //infinite poll loop
        while (true){

        }


    }

}
