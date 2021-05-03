package musichub.net;

/**
 * L'interface Observer
 */
public interface Observer {
    /**
     * Sert à notifier les clients ('listeners') s'il y a un changement au niveau des fichiers coté serveur
     * Exp: si un client a ajouté une nouvelle chanson, Tous les clients seront notifiés
     * @param message le message reçu par le MusicHub
     */
    public void update(final String message);
}
