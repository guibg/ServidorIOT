package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket;

	public static void main(String[] args) {

		try {
			Server server = new Server();
			System.out.println("Aguardando conexão");
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
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

			String mensagem = input.readUTF();
			System.out.println("Cliente:" + mensagem);
			output.writeUTF("Teste");
			output.flush();

			output.close();
			input.close();
			fecharSocket(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void fecharSocket(Socket socket) throws IOException {
		socket.close();
	}
}
