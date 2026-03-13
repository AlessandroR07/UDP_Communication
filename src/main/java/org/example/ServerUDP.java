import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP {

    public static void main(String[] args) {

        try {

            DatagramSocket socket = new DatagramSocket(3000);
            System.out.println("Server UDP in ascolto sulla porta 3000");

            byte[] buffer = new byte[1024];

            while (true) {

                DatagramPacket packetRicevuto =
                        new DatagramPacket(buffer, buffer.length);

                socket.receive(packetRicevuto);

                String messaggio = new String(
                        packetRicevuto.getData(),
                        0,
                        packetRicevuto.getLength());

                System.out.println("CLIENT: " + messaggio);

                // echo (rimanda lo stesso messaggio)
                byte[] risposta = messaggio.getBytes();

                DatagramPacket packetInvio =
                        new DatagramPacket(
                                risposta,
                                risposta.length,
                                packetRicevuto.getAddress(),
                                packetRicevuto.getPort());

                socket.send(packetInvio);

                if (messaggio.equalsIgnoreCase("exit"))
                    break;
            }

            socket.close();
            System.out.println("Server chiuso");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}