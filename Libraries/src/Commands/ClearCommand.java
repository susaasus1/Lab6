package Commands;

import Data.SpaceMarines;

public class ClearCommand extends Command {
    public ClearCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(SpaceMarines spaceMarines, Object... args) {
        try {
            validate(args);
            spaceMarines.clear();
            return "Коллекция очищена";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }
}
