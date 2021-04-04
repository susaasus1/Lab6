package Commands;

import Data.SpaceMarine;
import Data.SpaceMarines;

import java.util.Iterator;
import java.util.Scanner;


public class RemoveGreaterCommand extends Command implements Fillable {
    public RemoveGreaterCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(SpaceMarines spaceMarines, Object... args) {
        try {
            validate(args);

            if (spaceMarines.isEmpty()) {
                return "Команда не может быть выполнена, т.к. коллекция пуста. " +
                        "Добавьте элементы в коллекцию с помощью команды add";
            } else {
                SpaceMarine person = (SpaceMarine) args[0];

                Iterator<SpaceMarine> iter = spaceMarines.iterator();
                while (iter.hasNext()) {
                    SpaceMarine p = iter.next();
                    if (p.getId() > person.getId()) {
                        spaceMarines.remove(p);
                        iter = spaceMarines.iterator();
                    }
                }

                return "Были удалены все элементы со значением выше " + person.getId();
            }
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