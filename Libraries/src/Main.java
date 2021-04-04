import Commands.*;
import Data.SpaceMarine;
import Data.SpaceMarines;
import Exceptions.CommandAlreadyExistsException;
import Exceptions.NotFoundCommandException;
import Exceptions.RightException;
import Exceptions.SameIdException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SameIdException, RightException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, CommandAlreadyExistsException, IOException {

        try (Scanner userScanner = new Scanner(System.in)) {
            String envVariable = "";
            if (args.length == 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (args != null || args.length != 0) {
                envVariable = args[0];
            }

            SpaceMarines spaceMarines = new SpaceMarines(envVariable);
            spaceMarines.parse();

            CommandManager manager = CommandManager.getInstance(spaceMarines);

            manager.initCommand(ExitCommand.class, "exit", "Выход, без сохранения");
            manager.initCommand(InfoCommand.class, "info", "Выводит информацию о коллекции");
            manager.initCommand(HelpCommand.class, "help", "Выводит справку по коммандам");
            manager.initCommand(ShowCommand.class, "show", "Выводит все элементы коллекции");
            manager.initCommand(AddCommand.class, "add", "Добавляет элемент в коллекцию", SpaceMarine.class);
            manager.initCommand(ClearCommand.class, "clear", "Очищает коллекцию");
            manager.initCommand(UpdateCommand.class, "update", "Обновляет значение элемента по id", String.class, SpaceMarine.class);
            manager.initCommand(InsertAtIndexCommand.class, "insert_at_index", "Обновляет значение элемента по индексу", String.class, SpaceMarine.class);
            manager.initCommand(RemoveByIdCommand.class, "remove_by_id", "Удаляет элемент с заданным id", String.class);
            manager.initCommand(RemoveAtIndexCommand.class, "remove_at_index", "Удаляет элемент с заданным id", String.class);
            manager.initCommand(SaveCommand.class, "save", "Сохраняет информацию в файл");
            manager.initCommand(RemoveGreaterCommand.class, "remove_greater", "Удаляет из коллекции все элементы, превыщающий заданный", SpaceMarine.class);
            manager.initCommand(ExecuteScriptCommand.class, "execute_script", "Считывает и испольняет скрипт из файла", String.class);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Программа успешно запущена. Ввведите 'help' чтобы узнать список комманд ");


            while (true) {
                try {
                    System.out.print(">>");
                    String line = scanner.nextLine().trim();
                    String name = CommandManager.parseName(line);
                    Object[] arg = CommandManager.parseArgs(line);
                    Command command = CommandManager.getCommand(name);
                    Object[] fillableArg = CommandManager.getFillableArgs(command, scanner);
                    arg = CommandManager.concatArgs(arg, fillableArg);
                    try {
                        CommandManager.validate(command, arg);
                        System.out.println(CommandManager.execute(command, arg));
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                } catch (NotFoundCommandException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Lol");
        }
    }
}