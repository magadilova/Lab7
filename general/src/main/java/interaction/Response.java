package interaction;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class Response implements Serializable {
    private static final long serialVersionUID = 6893382565287633993L;
    private ResponseBody responseBody;
    private ResponseCode responseCode;

    public Response(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public Response(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
