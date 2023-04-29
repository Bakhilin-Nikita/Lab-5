import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class User {
    private String host;
    private int port;

    private DatagramSocket socket;

    private DatagramPacket sendingPacket;

    private DatagramPacket receivingPacket;

    private byte[] receivingDataBuffer;

    public User(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void SendMessage(String message){
        try {
            receivingDataBuffer = new byte[2048];
            byte[] data = message.getBytes();
            InetAddress address = InetAddress.getByName(host);
            // Создайте UDP-пакет
            sendingPacket = new DatagramPacket(data, data.length,address,port);
            // Отправьте UDP-пакет серверу
            socket.send(sendingPacket);
            // Создайте UDP-пакет
            receivingPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            //Получите ответ от сервера
            socket.receive(receivingPacket);
            // Выведите на экране полученные данные
            String receivedData = new String(receivingPacket.getData()).trim();
            System.out.println("Sent from the server: " + receivedData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        User sender = new User("localhost",59011);
        sender.setSocket(new DatagramSocket());
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = false;
        while (!flag) {
            System.out.println("Enter: ");
            String message = b.readLine();
            if (message.equals("exit")){System.exit(0);}
            sender.SendMessage(message);
        }
        b.close();
        // Закрыть соединение
        sender.getSocket().close();
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public DatagramSocket getSocket() {
        return socket;
    }
}


