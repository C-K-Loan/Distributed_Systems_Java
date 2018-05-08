package HomeWork2;


import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;


public class MsgReciever implements  Runnable {

 //   LinkedList<Integer> internalMessageLog;
//    LinkedList<Integer> externalMessageLog;

    MessageSequencer sequencer;

    int [] internalMessageLog, externalMessageLog;


    int id;
    int externalRecievedMsgCount ; //If ext Msg Count is >0, we must have recieved new External Messages and broadcast them

    int internalMessageCount,extenalMessageCount; //represents how many solts in our message array are allocated

    public MsgReciever( MessageSequencer sequencer, int internalMessageAmount, int id ){
        this.externalRecievedMsgCount = 0 ;
        this.sequencer = sequencer;
        this.id = id;

        //both can be same size
        this.internalMessageLog  = new int[internalMessageAmount];
        this.externalMessageLog = new int[internalMessageAmount];

        this.internalMessageCount = 0;
        this.externalRecievedMsgCount = 0;

        //this.internalMessageLog = new LinkedList<Integer>();
        //this.externalMessageLog = new LinkedList<Integer>();


    }


    public void run(){
    System.out.println("______________________________________Thread <"+ this.id+ "> running!_________________________________________________________");
    boolean sending;
    //Infite poll loop
    while (true)
    {


        //we must have recvd a new message -> notify Sequencer to broadcast it
        if( this.externalRecievedMsgCount > 0 ) {
                System.out.println("___________Reciever ID <"+this.id+"> ![NOTIFYING]! " + this.externalMessageLog.length +" messages____________________________");
                sending = notifySequencerOfNewMessages(this.externalRecievedMsgCount, this.externalMessageLog.length);//we just store a value, so we wait for the answer of this call
                System.out.println("_______________________Done Notifiying!____________________");
            }
    }


    }

    //Recv Message from Sequencer
    public void recvieveInternalMessage( int message ){
        System.out.println("Reciever <" + this.id+"> got [INTERNAL]  message : " + message);
         this.internalMessageLog[internalMessageCount] = message;
         this.internalMessageCount++;



    }

    //Recv Message from Client
    public void recvieveExternalMessage(int message){
        System.out.println("Reciever <" + this.id+"> got [EXTERBAL] message : " + message);
        this.externalMessageLog[this.extenalMessageCount] = message;
        this.externalRecievedMsgCount++;
        this.extenalMessageCount++;
       // System.out.println("LISTSIZE:" + this.messageLog.size());

    }

    //Send Message to Sequencer
    //We Decouple this function from all internal Variables, so no false/Double values get send
    //we need to specify the count, since the amount of messages Stored can increase while we are notifying the sequencer
    //We Also need the message amount, so we know how many Messages we Had, at the point this function was invoked
    //We will send the messages of POS(MessageAmount) - messageCount    . while decreasing MessageCount Iterativly
    private boolean notifySequencerOfNewMessages(int messageCount, int logLenth) {
        System.out.println("RECIEVER ID <" + this.id+ "> NOTFIYING");
        this.externalRecievedMsgCount = 0;//Reset the Internal MEsage Count, so we will take care of new messages

        while (logLenth > 0) {
            System.out.println("Reciever <" + this.id+"> notifying [INTERNAL] message : NR " +(logLenth - messageCount));
            System.out.println("Trying to get :" + (logLenth - messageCount) + " Since logLenth : " + logLenth + " and newMsgCount :" + messageCount );
            if ((logLenth - messageCount) >= this.externalMessageLog.length ){
                System.out.println("_____!!_!_!__!_!_!__!_!_!_!__!_!_BREAKING LOOOP WE ARE TRYING TO GET ELE " + (logLenth - messageCount));
                break;
            }
            this.sequencer.recieveInternalMessage(this.externalMessageLog[(logLenth - messageCount)]);
            messageCount -- ;

        }

    return true;

    }



        //System.out.println("Reciever <" + this.id+"> sending [INTERNAL] message : " + message);




    //Store Messages in a File after Reciever Shutdown
    public void storeMessageLogToFile() throws java.io.FileNotFoundException  {

        File file = new File("D:\\Coding\\UNI\\Semester 9\\Verteilte SYsteme Homework\\HA2\\Distributed_Systems_Java\\Homework2\\src\\HomeWork2\\Logs/LOG-ID-"+this.id+".txt");
        PrintWriter pw = new PrintWriter(file);
       for (int message : this.internalMessageLog){
           pw.write(Integer.toString( message ) );
           pw.write("\n");
       }
        pw.close();

    }




}
