package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	private ServerSocket serverSocket;

	public static void main(String[] args) {

		try {
			Server server = new Server();
			System.out.println("Aguardando conexão...");
			server.criarServer(4589);
			Socket socket = server.fazerConexao();
			System.out.println("Conectado!");
			tratarConexao(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void criarServer(int porta) throws IOException {
		serverSocket = new ServerSocket(porta);
	}

	public Socket fazerConexao() throws IOException {
		Socket socket = serverSocket.accept();
		return socket;
	}

	public static void tratarConexao(Socket socket) {
		try {
			//ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			//ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			
			//BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			Scanner sc = new Scanner(socket.getInputStream());
            
			PrintStream out = new PrintStream(socket.getOutputStream());
			
			do{
				String mensagem = sc.nextLine();
				if(mensagem.equals("/sair")) {
					System.out.println("O cliente desconetou-se");
					break;
				}
				System.out.println("Cliente:" + mensagem.toString());
			}while(true);

			out.flush();

			out.close();
			sc.close();
			fecharSocket(socket);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void fecharSocket(Socket socket) throws IOException {
		socket.close();
	}
}
