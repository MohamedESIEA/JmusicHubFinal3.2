package musichub.net;


/**
 * L'interface IServer represente la forme generale d'un serveur.
 *
 */
public interface IServer
{
	/**
	 * Lancer le serveur pour qu'il ecoute sur un port et une adresse IP
	 * @return Retourne False si le serveur est déja en ecoute, Sinon on lance le serveur pour qu'il écoute et apres on renvoie True
	 */
	public boolean connect();

	/**
	 * Envoyer l'etat du serveur
	 * @return True s'il est déja connecté, False sinon
	 */
	public boolean isConnected();

} 