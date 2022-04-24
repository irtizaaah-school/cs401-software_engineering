import java.io.*;
import java.net.*;

// Server class
class Server {
	public static void main(String[] args)
	{
		ServerSocket server = null;

		try {

			// server is listening on port 1234
			server = new ServerSocket(1234);
			server.setReuseAddress(true);

			// running infinite loop for getting
			// client request
			while (true) {

				// socket object to receive incoming client
				// requests
				Socket client = server.accept();

				// Displaying that new client is connected
				// to server
				System.out.println("New client connected"
								+ client.getInetAddress()
										.getHostAddress());

				// create a new thread object
				ClientHandler clientSock
					= new ClientHandler(client);

				// This thread will handle the client
				// separately
				new Thread(clientSock).start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (server != null) {
				try {
					server.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ClientHandler class
	private static class ClientHandler implements Runnable {
		private final Socket clientSocket;

		// Constructor
		public ClientHandler(Socket socket)
		{
			this.clientSocket = socket;
		}

		public void run()
		{
			ObjectOutputStream outStream = null;
			ObjectInputStream inStream = null;
			try {
					
				
				
				outStream = new ObjectOutputStream(clientSocket.getOutputStream());
				inStream = new ObjectInputStream(clientSocket.getInputStream());
				// create the 
				SocketMessage sMessage = new SocketMessage();
				
				Boolean terminate = false;
				
				while(!terminate) {
				sMessage = (SocketMessage)inStream.readObject();
				sMessage = new SocketMessage();
				outStream.writeObject(sMessage);
				
				// terminate = true;
				}
				
				inStream.close();
				outStream.close();
				clientSocket.close();
			
			}
			catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					if (outStream != null) {
						outStream.close();
					}
					if (inStream != null) {
						inStream.close();
						clientSocket.close();
					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}