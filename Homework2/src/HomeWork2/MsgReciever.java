package HomeWork2;

import sun.plugin2.message.Message;

import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;


public class MsgReciever implements  Runnable {

    LinkedList<Integer> internalMessageLog;
    LinkedList<Integer> externalMessageLog;

    MessageSequencer sequencer;
    int id;
    int externalRecievedMsgCount ; //If ext Msg Count is >0, we must have recieved new External Messages and broadcast them


    public MsgReciever( MessageSequencer sequencer, int id ){
        this.externalRecievedMsgCount = 0 ;
        this.sequencer = sequencer;
        this.id = id;
        this.internalMessageLog = new LinkedList<Integer>();
        this.externalMessageLog = new LinkedList<Integer>();

    }


    public void run(){
    System.out.println("Thread <"+ this.id+ "> running!");
    boolean sending;
    //Infite poll loop
    while (true)
    {


        //we must have recvd a new message -> notify Sequencer to broadcast it
        if( this.externalRecievedMsgCount > 0 ) {
                System.out.println("Reciever ID <"+this.id+"> ![NOTIFYING]! " + this.internalMessageLog.size()+" messages");
                sending = notifySequencerOfNewMessages(this.externalRecievedMsgCount, this.internalMessageLog.size());//we just store a value, so we wait for the answer of this call

            }
    }


    }


    //Recv Message from Sequencer
    public void recvieveInternalMessage( int message ){
        System.out.println("Reciever <" + this.id+"> got [INTERNAL]  message : " + message);
        this.internalMessageLog.add(message);

    }


    //Recv Message from Client
    public void recvieveExternalMessage(int message){
        //System.out.println("Reciever <" + this.id+"> got [EXTERBAL] message : " + message);
        this.externalRecievedMsgCount++;
        this.internalMessageLog.add(message);
       // System.out.println("LISTSIZE:" + this.messageLog.size());

    }

    //Send Message to Sequencer
    //We Decouple this function from all internal Variables, so no false/Double values get send
    //we need to specify the count, since the amount of messages Stored can increase while we are notifying the sequencer
    //We Also need the message amount, so we know how many Messages we Had, at the point this function was invoked
    //We will send the messages of POS(MessageAmount) - messageCount    . while decreasing MessageCount Iterativly
    private boolean notifySequencerOfNewMessages(int messageCount, int logLenth) {
        this.externalRecievedMsgCount = 0;//Reset the Internal MEsage Count, so we will take care of new messages
        while (logLenth > 0) {
            System.out.println("Reciever <" + this.id+"> notifying [INTERNAL] message : " + this.internalMessageLog.get(logLenth - messageCount));
            System.out.println("Trying to get :" + (logLenth - messageCount) + " Since logLenth : " + logLenth + " and newMsgCount :" + messageCount );
            this.sequencer.recieveInternalMessage(this.internalMessageLog.get(logLenth - messageCount));
            messageCount -- ;

        }
    return true;
    }



        //System.out.println("Reciever <" + this.id+"> sending [INTERNAL] message : " + message);




    //Store Messages in a File after Reciever Shutdown
    public void storeMessageLogToFile() throws java.io.FileNotFoundException  {

        File file = new File("/home/loan/Documents/uni/semester9/distributed_systems/Homework2/src/HomeWork2/Logs/LOG-ID-"+this.id+".txt");
        PrintWriter pw = new PrintWriter(file);
       for (int message : this.internalMessageLog){
           pw.write(Integer.toString( message ) );
           pw.write("\n");
       }
        pw.close();

    }




}
