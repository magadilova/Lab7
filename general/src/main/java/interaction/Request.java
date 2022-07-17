package interaction;


import dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request implements Serializable {
    private static final long serialVersionUID = -3928717231356787546L;
    private String commandName;
    private ArgumentHolder argumentHolder;
    private UserDTO userDTO;

    public Request(String commandName, UserDTO userDTO) {
        this.commandName = commandName;
        this.userDTO = userDTO;
    }
}
