package Commands;


import Data.SpaceMarines;
import Exceptions.CommandAlreadyExistsException;
import Exceptions.NotFoundCommandException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public final class CommandManager {
    private static CommandManager instance;
    private static SpaceMarines spaceMarines;
    private static ArrayList<Command> commands;

    private CommandManager(SpaceMarines spaceMarines) {
        CommandManager.spaceMarines = spaceMarines;
    }

    public static CommandManager getInstance(SpaceMarines spaceMarines) {
        if (instance == null) {
            commands = new ArrayList<>();
            instance = new CommandManager(spaceMarines);
        }

        return instance;
    }

    /**
     * TODO: complete javadoc
     *
     * @param clazz
     * @param name
     * @param description
     * @throws CommandAlreadyExistsException В случае если команда с таким именем уже существует
     * @throws NoSuchMethodException В случае если класс команды будет не найден
     * @throws IllegalAccessException В случае если класс не доступен
     * @throws InvocationTargetException В случае если базовый конструкор выдает исключение
     * @throws InstantiationException В случае если указываемый класс абстрактный
     */
    public void initCommand(Class<? extends Command> clazz, String name, String description, Class<?>... argsTypes) throws CommandAlreadyExistsException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Command command : commands) {
            if (command.getName().equals(name)) {
                throw new CommandAlreadyExistsException("Команда с иминем " + name + " уже существует");
            }
        }

        Command command = clazz.getConstructor(String.class, String.class, Class[].class)
                .newInstance(name, description, argsTypes);
        commands.add(command);
    }

    /**
     * Считывает параметры команды из строки
     *
     * @param args строка, которая содержит параметры команды
     * @return параметры команды
     */
    public static String[] parseArgs(String args) {
        String[] splitted = args.split(" ", 2);
        return splitted.length > 1 ? splitted[1].split(" ") : new String[]{};
    }

    /**
     * Считвыает имя команды из строки
     *
     * @param command строка, которая содержит имя команды
     * @return имя команды
     */
    public static String parseName(String command) {
        return command.split(" ")[0];
    }

    public static Command getCommand(String name) throws NotFoundCommandException {
        for (Command cmd : commands) {
            if (cmd.getName().equals(name)) {
                return cmd;
            }
        }
        throw new NotFoundCommandException("Команда '" + name + "' не была инициализирована.");
    }

    public static String getCommandsInfo() {
        StringBuilder builder = new StringBuilder();
        for (Command cmd : commands) {
            builder.append(cmd.toString()).append("\n");
        }

        return builder.toString().trim();
    }

    public static String execute(Command command, Object[] args) {
        return command.execute(spaceMarines, args);
    }

    public static String execute(String commandName, Object[] args) throws NotFoundCommandException {
        Command command = getCommand(commandName);
        return command.execute(spaceMarines, args);
    }

    public static Object[] getFillableArgs(Command command, Scanner scanner) {
        if (command instanceof Fillable) {
            return ((Fillable) command).fill(scanner);
        }
        return new Object[]{};
    }

    public static Object[] concatArgs(Object[] args, Object[] fillableArgs) {
        return Stream.concat(Arrays.stream(args), Arrays.stream(fillableArgs)).toArray(Object[]::new);
    }

    public static void validate(Command command, Object[] args) {
        command.validate(args);
    }

    public static void validate(String commandName, Object[] args) throws NotFoundCommandException {
        Command command = getCommand(commandName);
        command.validate(args);
    }
}
