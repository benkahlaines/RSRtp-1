import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Chaîne 1 : ");
        String chaine1 = scanner.nextLine();
        System.out.print("Chaîne 2 : ");
        String chaine2 = scanner.nextLine();

        Socket socket = new Socket("localhost", 8080);

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        System.out.println("Adresse client : " + socket.getLocalAddress() + ":" + socket.getLocalPort());
        System.out.println("Adresse serveur : " + socket.getInetAddress() + ":" + socket.getPort());
        System.out.println("Chaînes envoyées : " + chaine1 + " et " + chaine2);

        writer.println(chaine1);
        writer.println(chaine2);
        writer.flush();

        String resultat = reader.readLine();
        System.out.println("Réponse serveur : La chaîne 2 est " + (resultat.equals("True") ? "" : "non ") + "présente dans la chaîne 1");

        socket.close();
    
    }

}
