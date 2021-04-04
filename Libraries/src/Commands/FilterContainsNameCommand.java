package Commands;

import Data.SpaceMarine;
import Data.SpaceMarines;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class FilterContainsNameCommand extends Command{
    public FilterContainsNameCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }
    @Override
    public String execute(SpaceMarines spaceMarines, Object... args) {
        try {
            validate(args);

            if (spaceMarines.isEmpty()) {
                return "Коллекция пустая. Данные внутри файла отсутствуют!";
            } else {

                LinkedList<SpaceMarine> space=new LinkedList<>();
                for (SpaceMarine p:spaceMarines){
                    if (p.getName().equals(args[0])){
                        space.add(p);
                    }
                }
                Object[] out = space.toArray();
                StringBuilder builder = new StringBuilder();
                if (space.size()==0){
                    return "В коллекции не найдено солдат с таким именем";
                }else {
                    for (Object o : out) {
                        builder.append(o).append("\n");
                    }
                    return builder.toString();
                }
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }
}
