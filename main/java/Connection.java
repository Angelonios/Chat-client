import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection implements Runnable {

    private Client client;

    private Socket socket;

    private DataInputStream foreignInput;

    public Connection(Client client, Socket socket) throws IOException {
        System.out.println("Connected to server. Connection info: " + socket.toString());
        this.client = client;
        this.socket = socket;
        foreignInput = new DataInputStream(this.socket.getInputStream());
    }

    @Override
    public void run() {

    }
}
