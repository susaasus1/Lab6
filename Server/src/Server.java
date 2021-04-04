import Data.SpaceMarines;
import Exceptions.CommandAlreadyExistsException;
import Exceptions.RightException;
import Exceptions.SameIdException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Server {
    public static int SERVER_PORT = 12421;
    public static final Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) throws SameIdException, InvocationTargetException, IllegalAccessException, InstantiationException, RightException, NoSuchMethodException, CommandAlreadyExistsException, IOException {
        Server server = new Server();
        String envVariable="";
        if (args.length==0){
            throw new ArrayIndexOutOfBoundsException();
        }
        if (args!=null || args.length!=0){
            envVariable=args[0];
        }
        SpaceMarines spaceMarines=new SpaceMarines(args[0]);
        server.launch(spaceMarines);
    }

    public Server() {
    }

    public void launch(SpaceMarines p) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, CommandAlreadyExistsException, SameIdException, RightException, IOException {
        DatagramSocket socket = setSocket();

        if (socket != null) {
            logger.info("Сервер запущен");


            Scanner scanner = new Scanner(System.in);
            Interpreter interpreter = new Interpreter(p);
            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket, interpreter, sender);

            sender.setDaemon(true);
            receiver.setDaemon(true);

            sender.start();
            receiver.start();

            shutDownHook();
            while (true) {
                interpreter.askCommand(scanner);
            }
        }
    }

    public DatagramSocket setSocket() {
        try {
            return new DatagramSocket(SERVER_PORT);
        } catch (SocketException e) {
            e.printStackTrace();

        }
        return null;
    }

    private void shutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Сервер остановлен");
        }));
    }
}
