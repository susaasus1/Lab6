import Answers.Answer;
import Answers.ErrorAnswer;
import Answers.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Receiver extends Thread {
    public static final Logger logger = Logger.getLogger(Receiver.class.getName());
    private DatagramSocket socket;
    private Interpreter interpreter;
    private Sender sender;

    public Receiver(DatagramSocket socket, Interpreter interpreter, Sender sender) {
        this.socket = socket;
        this.interpreter = interpreter;
        this.sender = sender;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                byte[] bytes = new byte[16384];
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                socket.receive(datagramPacket);

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

                Request request=(Request) objectInputStream.readObject();
                logger.info("Получен запрос от " + datagramPacket.getAddress() + ":" +
                        datagramPacket.getPort() + " - " + request.getCommand().getName() + Arrays.toString(request.getArgs()));

                try {
                    Answer answer = interpreter.interpret(request);
                    sender.send(answer, datagramPacket.getAddress(), datagramPacket.getPort());
                } catch (NullPointerException e) {
                    sender.send(new ErrorAnswer("Сервер не смог выполнить команду"), datagramPacket.getAddress(), datagramPacket.getPort());
                    logger.log(Level.SEVERE,"Error message",e);
                }

                byteArrayInputStream.close();
                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                logger.log(Level.SEVERE,"Error message",e);
            }
        }
    }
}