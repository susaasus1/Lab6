package Commands;

import Data.SpaceMarines;

public class SaveCommand extends Command {
    public SaveCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(SpaceMarines spaceMarines, Object... args) {
        try {
            validate(args);
            spaceMarines.save();
            return "Коллекция сохранена";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
