package HomeWork2;

import java.util.Random;

public class MsgSender {

    MsgReciever[] recievers;
    Random randomGen;
    public MsgSender(MsgReciever[] recievers){

        this.recievers = recievers;
        this.randomGen = new Random();


    }

    //Send a Random Message to a Reciever
    public void sendRndMessageToReciever(int recieverID){

        this.recievers[recieverID].recvieveExternalMessage(this.randomGen.nextInt());


    }


}
