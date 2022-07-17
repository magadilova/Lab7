package manager;

import commands.AbstractCommand;
import commands.ExitCommand;
import commands.entry.LoginCommand;
import commands.entry.RegisterCommand;
import commands.modification.*;
import commands.view.*;
import dto.UserDTO;
import interaction.ArgumentHolder;
import interaction.Response;
import service.ProductService;
import service.UserService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CommandManager {
    private final LinkedHashMap<String, AbstractCommand> allCommands = new LinkedHashMap<>();
    private HistoryCommand historyCommand;
    private final UserService userService;
    private final ProductService productService;


    public CommandManager(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
        addCommands();
    }

    public void addCommands() {
        List<AbstractCommand> commands = Stream.of(new HelpCommand(),
                new InfoCommand(productService,userService),
                new ShowCommand(productService),
                new AddCommand(productService, userService),
                new UpdateCommand(productService, userService),
                new RemoveIDCommand(productService, userService),
                new ClearCommand(productService, userService),
                new RemoveGCommand(productService, userService),
                new RemoveLCommand(productService, userService),
                new RemovePNCommand(productService, userService),
                new FilterLCommand(productService),
                new FilterPNCommand(productService),
                new HistoryCommand(),
                new RegisterCommand(userService),
                new LoginCommand(userService),
                new ExitCommand()).collect(Collectors.toList());
        for (AbstractCommand command : commands) {
            allCommands.put(command.getName(), command);
        }
        HelpCommand help = (HelpCommand) allCommands.get("help");
        HashMap<String, String> helpText = new HashMap<>();
        allCommands.entrySet().stream().forEach(entry->{
            if (!(entry.getKey().equals("login") || entry.getKey().equals("register"))){
                helpText.put(entry.getKey(), entry.getValue().getDescription());
            }
        });
        help.setHelpText(helpText);
        historyCommand = (HistoryCommand) allCommands.get("history");
    }

    public Response executeCommand(String commandName, ArgumentHolder argumentHolder, UserDTO userDTO) {
        historyCommand.addToCommandList(userDTO, commandName);
        AbstractCommand abstractCommand = allCommands.get(commandName);
        return abstractCommand.execute(argumentHolder, userDTO);
    }




}
