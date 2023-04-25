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

    public User(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void SendMessage(String message){
        try {
            byte[] data = message.getBytes();
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket packet = new DatagramPacket(data, data.length,address,port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        User sender = new User("localhost",1057);
        System.out.print("Введите команду"+"\n");
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = false;
        while (!flag) {
            String message = b.readLine();
            if (message.equals("exit")){System.exit(0);}
            sender.SendMessage(message);
        }
        b.close();
    }
}


