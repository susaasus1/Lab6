package Commands;

import Data.SpaceMarine;
import Data.SpaceMarines;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Scanner;

public class UpdateCommand extends Command implements Fillable {

    public UpdateCommand(String name, String description, Class<?>[] argsTypes) {
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
                try {
                    Long id = Long.parseLong((String) args[0]);
                    int i = 0;
                    for (SpaceMarine p : spaceMarines) if (id == (long) p.getId()) i++;
                    if (i == 0) return "Не существует элемента с таким индетификатором";
                    else {
                        spaceMarines.update(id, (SpaceMarine) args[1]);
                        return "Элемент обновлен";
                    }
                } catch (NotFound notFound) {
                    return "Не существует элемента с таким индетификатором";
                } catch (Exception e) {
                    return "Вы ввели данные не верно.";
                }
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