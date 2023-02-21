import java.io.*;
import java.net.*;

public class ClientMultiThread {

    public static void main(String[] args) {
        try {
            // Connexion au serveur
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connecté au serveur : " + socket);

            // Récupération des flux d'entrée/sortie du serveur
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Lecture des chaines à envoyer au serveur
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Entrez la chaine 1 : ");
            String chaine1 = clavier.readLine();
            System.out.print("Entrez la chaine 2 : ");
            String chaine2 = clavier.readLine();

            // Envoi des chaines au serveur
            out.println(chaine1);
            out.println(chaine2);

            // Récupération de la réponse du serveur
            String reponse = in.readLine();
            System.out.println(reponse);

            // Fermeture des flux et de la connexion
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }
}
