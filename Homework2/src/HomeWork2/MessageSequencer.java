package HomeWork2;

public class MessageSequencer implements  Runnable {

    int [] internalmessageLog;
    int [] internalMessageQue;
    int lastMessageSend;
    Thread[] recieverThreads;
    MsgReciever[] recievers;
    int messageAmount;
    int messageCount;
    int messagesToBroadCast;
    int messagesAlreadyBoadcasted;

    public MessageSequencer( int messageAmount){
         this.messageAmount = messageAmount;
        this.internalmessageLog = new int [messageAmount];
        this.internalmessageLog = new int [messageAmount];
        this.messageCount = 0 ;
        this.messagesToBroadCast = 0;
        this.messagesAlreadyBoadcasted = 0;
        this.lastMessageSend= 0;

    }


    public void setRecievers(Thread[]recieverThreads , MsgReciever[] recievers  ){
        this.recieverThreads =  recieverThreads;
        this.recievers = recievers;
    }

    public void recieveInternalMessage(int message){
        this.internalmessageLog[this.messageCount] = message;
        this.messageCount ++ ;
        this.messagesToBroadCast++;
        System.out.println("{SEQUENCER} GOT NOTIFIED FOR MSG: " + message + " AND I HAVE AMOUNT MSG:" + this.messageCount);
    }


    //main loop, where we check for new Messages and broadcast them
    public void run(){


        //infinite poll loop
        while (true){
             if(this.messagesToBroadCast>0) {//there are new messages to broadcast !
                 //broadCastMessages(this.messagesToBroadCast);

            }
        }


    }

    /**
     * We keep track of which is the last index that we have send. We then increase the LastMessageSend Counter by MessagesTobroadcast
      * @param messagesToBroadCast How many msg to broadcast
      */
    private void broadCastMessages(int messagesToBroadCast ){
        this.messagesToBroadCast = 0;
        System.out.println("{SEQUENCER} Broadcasting : " + messagesToBroadCast + " Messages!");
        while(messagesToBroadCast >0 && lastMessageSend< internalmessageLog.length){
            broadCastValue(this.internalmessageLog[lastMessageSend]);
            this.lastMessageSend++;
            messagesToBroadCast--;
        }
        System.out.println("done broadcasting");

    }

    /**
     * Broadcast one Value to every thrad
     * @param val the value to broadcast
     */
    private void broadCastValue(int val){

        for (MsgReciever reciever : this.recievers){
            reciever.recvieveInternalMessage(val);
        }

    }
}
