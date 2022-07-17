package interaction;

import java.io.Serializable;
import java.util.ArrayList;


public class ResponseBody implements Serializable {
    private static final long serialVersionUID = -7843504360286147017L;
    private ArrayList<String> responseList;

    public ResponseBody() {
        this.responseList = new ArrayList<>();
    }

    public void addCommandResponseBody(String response){
        responseList.add(response);
    }

    public String getData(){
        return String.join("\n", responseList);
    }
}
