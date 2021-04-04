package Commands;

import Data.SpaceMarines;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class RemoveByIdCommand extends Command {
    public RemoveByIdCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(SpaceMarines spaceMarines, Object... args) {
        try {
            validate(args);
            try {
                long id = Long.parseLong((String) args[0]);
                spaceMarines.remove(id);
                return "Запись с индетификатором " + id + " была удалена";
            } catch (NotFound notFound) {
                return "Не существует элемента с таким индетификатором";
            } catch (Exception e) {
                return "Вы ввели данные не верно.";
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }
}