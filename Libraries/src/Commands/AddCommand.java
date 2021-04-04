package Commands;


import Data.SpaceMarine;
import Data.SpaceMarines;

import java.util.Scanner;


public class AddCommand extends Command implements Fillable {

    public AddCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(SpaceMarines spaceMarines, Object... args) {
        try {
            validate(args);
            spaceMarines.offer((SpaceMarine) args[0]);
            return "\nЭлемент добавлен в коллекцию";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }

    @Override
    public Object[] fill(Scanner scanner) {
        Object[] args = new Object[1];
        args[0] = SpaceMarine.fillSpaceMarine(scanner);
        return args;
    }
}