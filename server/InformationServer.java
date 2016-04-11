
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class InformationServer implements Runnable {

	public int serverPort            = 8080;
	public ServerSocket serverSocket = null;
	public boolean isStopped         = false;
	public Thread runningThread      = null;

	public InformationServer(int port) {

		this.serverPort = port;

	}

	public void run() {

		synchronized(this) {

			this.runningThread = Thread.currentThread();

		}

		openServerSocket();

		while(!this.isStopped) {

			Socket clientSocket = null;
		
			try {

				clientSocket = this.serverSocket.accept();

			} catch (IOException e) {

				if(this.isStopped) {

					System.out.println("The server has been stopped.");
					return;

				}

			}

			new Thread(new WorkerRunnable(clientSocket)).start();

		}

		System.out.println("The server has been stopped.");

	}

	public synchronized void stop() {

		this.isStopped = true;

		try {

			this.serverSocket.close();

		} catch (IOException e) {

			System.out.println("Error closing server.");

		}

	}

	private void openServerSocket() {

		try {

			this.serverSocket = new ServerSocket(this.serverPort);

		} catch (IOException e) {

			System.out.println("Error opening server.");

		}

	}

}
