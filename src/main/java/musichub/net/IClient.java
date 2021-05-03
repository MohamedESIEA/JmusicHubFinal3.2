package musichub.net;

/**
 * L'interface IClient represente la forme generale d'un client.
 *
 */
public interface IClient {
    /**
     * Etablir une connection TCP avec le serveur dont l'adresse est passé en parametre
     * @param ip l'adresse IP du serveur
     * @return Une valeur true si la connection est bien établie, false dans le cas contraire
     */
    public boolean connect(String ip);

    /**
     * Envoyer un message vers le serveur
     * NB: le client doit être connecter avant d'appeller cette méthode
     * @param msg Le message à envoyer vers le serveur
     * @return Retourne la taille du message s'il est bien envoyé, -1 s'il y a un problème (Pb: Client deconnecté)
     */
    public long sent(final String msg);

    /**
     * Recuperer le dernier message reçu par le client
     * @return Le message reçu par le client, si non renvoie null
     */
    public String receive();

    /**
     * Deconnecter le client du serveur
     */
    public void shutDown();
}
