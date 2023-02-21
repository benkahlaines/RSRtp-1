import java.io.*;
import java.net.*;

public class ServeurMultiThread {

    public static void main(String[] args) {
        try {
            ServerSocket serveur = new ServerSocket(5000);
            System.out.println("Serveur en attente de clients...");

            while (true) {
                Socket client = serveur.accept();
                System.out.println("Nouveau client connecté : " + client);

                // Création d'un thread pour traiter le client
                Thread t = new Thread(new ClientHandler(client));
                t.start();
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }
}

class ClientHandler implements Runnable {
    private Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            // Récupération des flux d'entrée/sortie du client
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            // Récupération des chaines envoyées par le client
            String chaine1 = in.readLine();
            String chaine2 = in.readLine();

            // Vérification de la présence de chaine2 dans chaine1
            if (chaine1.contains(chaine2)) {
                out.println(chaine1 + " contient " + chaine2);
            } else {
                out.println(chaine1 + " ne contient pas " + chaine2);
            }

            // Fermeture des flux et de la connexion
            in.close();
            out.close();
            client.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }
}
