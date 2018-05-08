package HomeWork2;

public class MessageSequencer {

    public MessageSequencer(){

    }


    public void recieveInternalMessage(int message){
        System.out.println("{SEQUENCER} GOT NOTIFIED FOR MSG: " + message);
    }
}
