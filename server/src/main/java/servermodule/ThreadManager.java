package servermodule;

import java.util.concurrent.ExecutorService;

public class ThreadManager {
    private final ExecutorService requestReceiver;

    private final ExecutorService senderResponse;

    public ThreadManager(ExecutorService requestReceiver, ExecutorService senderResponse) {
        this.requestReceiver = requestReceiver;
        this.senderResponse = senderResponse;
    }

    public void receiveRequest(Runnable receiveTask){
        requestReceiver.execute(receiveTask);
    }

    public void sendResponse(Runnable sendTask){
        senderResponse.execute(sendTask);
    }

}
