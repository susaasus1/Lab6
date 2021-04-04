package Commands;

import Data.SpaceMarine;
import Data.SpaceMarines;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class PrintFieldHeightCommand extends Command{
    public PrintFieldHeightCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }
    @Override
    public String execute(SpaceMarines spaceMarines, Object... args) {
        try {
            validate(args);

            if (spaceMarines.isEmpty()) {
                return "Коллекция пустая. Данные внутри файла отсутствуют!";
            } else {
                LinkedList<Double> height=new LinkedList<>();
                for (SpaceMarine p:spaceMarines){
                    height.add(p.getHeight());
                }
                Object[] out = height.toArray();
                Arrays.sort(out, Collections.reverseOrder());
                StringBuilder builder = new StringBuilder();
                for (Object o : out) {
                    builder.append(o).append("\n");
                }
                return builder.toString();

            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }
}
