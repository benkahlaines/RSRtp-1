import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Le serveur écoute sur le port 8080");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connecté : " + socket.getInetAddress() + ":" + socket.getPort());

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String chaine1 = reader.readLine();
            String chaine2 = reader.readLine();
            System.out.println("Chaînes reçues : " + chaine1 + " et " + chaine2);

            boolean resultat = chaine1.contains(chaine2);
            writer.println(resultat ? "True" : "False");
            writer.flush();

            socket.close();
        }
    }
}