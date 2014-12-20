package piratenetwork;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class PirateNetwork {
    public static void main(String args[]) {        
        new Thread() {
                public void run() {
                    try {
                        System.out.println("Starting Server");
                        ServerSocket ss = new ServerSocket();
                        ss.setReuseAddress(true);
                        ss.bind(new InetSocketAddress("192.168.1.10",7077));
                        ss.accept();
                    } catch (Exception e) {
                        System.out.println("ServerSocket exception: " + e.getMessage());
                    }
                }
            }.start();

        Socket s;

        while (true) {
            s = new Socket();
            try {
                System.out.println("Client trying...");
                s.setReuseAddress(true);
                s.bind(new InetSocketAddress(7077));
                s.connect(new InetSocketAddress("192.168.0.103", 7077));
                break;
            } catch (Exception e) {
                System.out.println("Socket exception: " + e.getMessage());
                try { s.close(); } catch (IOException e1) { }
                try { Thread.sleep(1000); } catch (InterruptedException e1) { }
            }

        }

    }
}