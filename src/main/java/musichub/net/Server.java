package musichub.net;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Server, c'est une classe Singleton qui implemente l'interface IServer
 */
public class Server extends Thread implements IServer
{
	private ServerSocket serverSocket;
	int nbClients = 0;
	private static List<Socket> conversations;


	private static Server server;

	/**
	 * Renvoie une instance de Type Server
	 * @return Retourne une instance static de Type Server s'il existe, sinon on crée et on renvoie une nouvelle instance
	 */
	public static Server getInstance(){
		if( server == null ){
			server = new Server();
		}
		return server;
	}

	/**
	 * Constructeur de la classe Server sans parametre
	 */
	private Server(){ conversations = new ArrayList<>(); }

	@Override
	public void run() {
		try {
			System.out.println("Server start on port [ 6666 ]...");

			serverSocket = new ServerSocket(6666);
			while(true) {
				Socket s = serverSocket.accept();
				nbClients++;
				Conversation conversation = new Conversation(s,nbClients);
				conversation.start();
				conversations.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Diffuser un message aupres de toutes les clients,
	 * @param msg Message à diffuser
	 * @throws IOException Throw une exception si l'envoie d'un message vers un client est echoué
	 */
	public void broadcast(final String msg) throws IOException {
		System.out.println(conversations.size());
		Iterator<Socket> it = conversations.iterator();
		while( it.hasNext() ){
			new PrintWriter(it.next().getOutputStream()).println(msg);
		}
	}

	/**
	 * Lancer un Thread qui va s'ecouter sur un port et creer une Conversation pour chaque Connection
	 * @return Retourne False si le serveur est déja en ecoute, Sinon on lance le serveur pour qu'il ecoute et apres en renvoie True
	 */
	public boolean connect() {
		try{
			if(serverSocket == null) {
				this.start();
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Envoyer l'etat du serveur
	 * @return True s'il est déja connecté, False sinon
	 */
	@Override
	public boolean isConnected() {
		return serverSocket != null;
	}

	/**
	 * Retourne le nombre des client connecté au serveur
	 * @return Renvoie un entier superieur à 0 s'il y a des client deja connecte, 0 sinon
	 */
	public int getSize(){
		return conversations.size();
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

}