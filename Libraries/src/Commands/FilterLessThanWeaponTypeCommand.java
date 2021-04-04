package Commands;

import Data.SpaceMarine;
import Data.SpaceMarines;
import Data.Weapon;

import java.util.Arrays;
import java.util.LinkedList;

public class FilterLessThanWeaponTypeCommand extends Command{
    public FilterLessThanWeaponTypeCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }
    @Override
    public String execute(SpaceMarines spaceMarines, Object... args) {
        try {
            validate(args);

            if (spaceMarines.isEmpty()) {
                return "Коллекция пустая. Данные внутри файла отсутствуют!";
            } else {
                long idx=0;

                if ("COMBI_FLAMER".equalsIgnoreCase((String) args[0])){
                    idx=1;
                }
                if ("GRENADE_LAUNCHER".equalsIgnoreCase((String) args[0])){
                    idx=2;
                }
                if ("HEAVY_FLAMER".equals((String) args[0])){
                    idx=3;
                }
                if ("MULTI_MELTA".equalsIgnoreCase((String) args[0])){
                    idx=4;
                }
                LinkedList<SpaceMarine> space=new LinkedList<>();
                for (SpaceMarine p:spaceMarines){
                    if (p.getWeaponType().getInd()<idx){
                        space.add(p);
                    }
                }
                Object[] out = space.toArray();
                Arrays.sort(out);
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
