import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientUDP {

    public static void main(String[] args) {

        try {

            DatagramSocket socket = new DatagramSocket();

            InetAddress serverAddress = InetAddress.getByName("localhost");

            BufferedReader tastiera =
                    new BufferedReader(new InputStreamReader(System.in));

            while (true) {

                System.out.print("CLIENT: ");
                String messaggio = tastiera.readLine();

                byte[] bufferInvio = messaggio.getBytes();

                DatagramPacket packetInvio =
                        new DatagramPacket(
                                bufferInvio,
                                bufferInvio.length,
                                serverAddress,
                                3000);

                socket.send(packetInvio);

                byte[] bufferRicezione = new byte[1024];

                DatagramPacket packetRicevuto =
                        new DatagramPacket(bufferRicezione, bufferRicezione.length);

                socket.receive(packetRicevuto);

                String risposta = new String(
                        packetRicevuto.getData(),
                        0,
                        packetRicevuto.getLength());

                System.out.println("SERVER: " + risposta);

                if (messaggio.equalsIgnoreCase("exit"))
                    break;
            }

            socket.close();
            System.out.println("Client chiuso");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}