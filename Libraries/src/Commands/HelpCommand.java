package Commands;


import Data.SpaceMarines;

public class HelpCommand extends Command {
    public HelpCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(SpaceMarines spaceMarines, Object... args) {
        try {
            validate(args);
            return CommandManager.getCommandsInfo();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }


    }
}