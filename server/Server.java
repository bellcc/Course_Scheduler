
import java.io.*;

public class Server {

	public static void main(String [] args) throws FileNotFoundException{

		InformationServer server = new InformationServer(8080);
		new Thread(server).start();

	}

}
