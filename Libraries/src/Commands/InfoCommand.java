package Commands;

import Data.SpaceMarines;

public class InfoCommand extends Command {
    public InfoCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(SpaceMarines spaceMarines, Object... args) {
        try {
            validate(args);
            return spaceMarines.toString();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }
}
