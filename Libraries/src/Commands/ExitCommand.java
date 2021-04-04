package Commands;

import Data.SpaceMarines;

public class ExitCommand extends Command {
    public ExitCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(SpaceMarines spaceMarines, Object... args) {
        try {
            validate(args);
            System.exit(0);
            return "Программа успешно завершена!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }
}
