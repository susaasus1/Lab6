package Commands;

import Data.SpaceMarines;
import Exceptions.NotFoundCommandException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ExecuteScriptCommand extends Command {
    public ExecuteScriptCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(SpaceMarines spaceMarines, Object... args) {
        try {
            validate(args);
            File file = new File((String) args[0]);
            if (!file.exists()) return "Скрипта не существует";
            else if (file.exists() && !file.canRead())
                return "Скрипт невозможно прочитать, проверьте права файла(права на чтение)";
            else if (file.exists() && !file.canExecute())
                return "Скрипт невозможно выполнить, проверьте права файла (права на выполнение)";
            else {
                try {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {

                        String line = scanner.nextLine().trim();
                        System.out.println(">>" + line);
                        List<String> collection = Arrays.asList(line.split(" "));
                            if (collection.size() == 1) {
                                Command cmd = CommandManager.getCommand(collection.get(0));
                                System.out.println(CommandManager.execute(cmd, CommandManager.parseArgs(collection.get(0))));
                            } else {
                                Command cmd = CommandManager.getCommand(collection.get(0));
                                System.out.println(CommandManager.execute(cmd, CommandManager.parseArgs(collection.get(1))));
                                System.out.println(collection.get(0));
                                System.out.println(collection.get(1));
                            }


                    }
                    scanner.close();
                    return "Скрипт был выполнен";
                } catch (FileNotFoundException e) {
                    return "Скрипта не существует";
                } catch (NotFoundCommandException e) {
                    return "Команда не найдена";
                }
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }

    @Override
    public void validate(Object... args) throws IllegalArgumentException {
        super.validate(args);
    }
}