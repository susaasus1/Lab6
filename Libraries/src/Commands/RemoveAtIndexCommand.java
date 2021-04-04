package Commands;

import Data.SpaceMarine;
import Data.SpaceMarines;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class RemoveAtIndexCommand extends Command{
    public RemoveAtIndexCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }
    @Override
    public String execute(SpaceMarines spaceMarines, Object... args) {
        try {
            validate(args);
            try {
                int i=0;
                int index = Integer.parseInt((String) args[0]);
                Long id=0L;
                for (SpaceMarine p:spaceMarines){
                    i++;
                    if (index==i){
                        id=p.getId();
                    }
                }
                spaceMarines.remove(id);
                return "Запись с индексом " + index + " была удалена";
            } catch (NotFound notFound) {
                return "Не существует элемента с таким индексом";
            } catch (Exception e) {
                return "Вы ввели данные не верно.";
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }
}
