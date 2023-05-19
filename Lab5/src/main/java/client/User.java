package client;

import server.object.LabWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
import java.net.*;
import java.util.HashSet;

public class User {

    private SendObject sendObject;
    private String host;
    private int port;

    private static DatagramSocket socket;

    private DatagramPacket sendingPacket;

    private DatagramPacket receivingPacket;

    private byte[] receivingDataBuffer;

    public User(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void SendMessage(String message) throws ClassNotFoundException, IOException, InterruptedException {
        InetAddress address = InetAddress.getByName(this.host);
        socket.connect(address, port);
        receivingDataBuffer = new byte[2048];
        byte[] data = message.trim().getBytes();
        // Создайте UDP-пакет
        sendingPacket = new DatagramPacket(data, data.length, address, port);
        // Отправьте UDP-пакет серверу
        try {
            socket.send(sendingPacket);
        } catch (SocketException e) {
            System.out.println("Server do not respond");
            System.exit(0);
        }
        // Создайте UDP-пакет
        receivingPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
        //Получите ответ от сервера
        try {
            socket.receive(receivingPacket);
        } catch (SocketException e) {
            System.out.println("Server do not respond(");
            socket.close();
            System.exit(0);
        }

        if (message.equals("show")) {
            if (SerializationManager.deserialize(receivingPacket.getData()).toString().trim().equals("[]"))
                System.out.println("Коллекция пустая!");
            else
                System.out.println(SerializationManager.deserialize(receivingPacket.getData()).toString().trim());
        } else {
            String receivedData = new String(receivingPacket.getData());
            System.out.println(receivedData.trim());
        }

    }

    public static void main(String[] args) throws IOException {
        User sender = new User("localhost", 50025);
        sender.setSocket(new DatagramSocket());
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = false;

        while (!flag) {
            System.out.println("Enter command: ");
            String message = b.readLine().trim();

            if (!message.isEmpty()) {
                if (message.equals("exit")) {
                    System.exit(0);
                } else if (message.equals("add")) {
                    sender.sendObject = new SendObject(sender.getLabs());
                    sender.sendLabWorkObject();
                } else if (message.equals("save")) {
                    System.out.println("Command save is not available!");
                } else {
                    try {
                        sender.SendMessage(message);
                    } catch (ClassNotFoundException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        b.close();
        // Закрыть соединение
        sender.getSocket().close();
    }

    private void sendLabWorkObject() throws IOException {
        sendObject.start();

        InetAddress address = InetAddress.getByName(this.host);
        socket.connect(address, port);
        receivingDataBuffer = new byte[2048];
        byte[] data = SerializationManager.serialize(sendObject.getLabWork());
        // Создайте UDP-пакет
        sendingPacket = new DatagramPacket(data, data.length, address, port);
        // Отправьте UDP-пакет серверу
        socket.send(sendingPacket);
    }

    private HashSet<LabWork> getLabs() throws IOException {

        InetAddress address = InetAddress.getByName(this.host);
        socket.connect(address, port);
        receivingDataBuffer = new byte[2048];
        byte[] data = "show".getBytes();
        // Создайте UDP-пакет
        sendingPacket = new DatagramPacket(data, data.length, address, port);
        // Отправьте UDP-пакет серверу
        socket.send(sendingPacket);
        // Создайте UDP-пакет
        receivingPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
        try {
            socket.receive(receivingPacket);
            data = "add".getBytes();
            sendingPacket = new DatagramPacket(data, data.length, address, port);
            socket.send(sendingPacket);
        } catch (PortUnreachableException e) {
            System.out.println("Server do not respond!");
        }
        try {
            if (SerializationManager.deserialize(receivingPacket.getData()) == null)
                return null;
            HashSet<LabWork> labs = SerializationManager.deserialize(receivingPacket.getData());
            return labs;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

}


