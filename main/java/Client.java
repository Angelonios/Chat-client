import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

    private Socket socket;

    private DataInputStream input;

    private DataOutputStream output;

    Scanner scanner;

    public Client() {
        try{
            requestConnection();
        } catch (IOException error){
            error.printStackTrace();
        }
        new Thread(this).start();
        startScanner();
    }

    public void run() {
        try{
            while (true) {
                if(socket.isClosed()){
                    break;
                }
                //something has to catch foreign input into the client
                String response = input.readUTF();
                //something has to write the response on the console
                System.out.println(response);
                System.out.println("Socket status: " + socket.isClosed());
            }
        } catch (EOFException endOfFileError){

        }
        catch (IOException error){
            error.printStackTrace(); //}
        } finally {
            try {
                System.out.println("server has disconnected");
                socket.close();
                while(true){
                    requestConnection();
                    if(socket.isConnected()){
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startScanner() {
        try {
            while(true){
                scanner = new Scanner(System.in);
                String line = scanner.nextLine();
                System.out.println("Client: sending this message: " + line);
                output.writeUTF(line);
            }
        }catch (IOException error){
            error.printStackTrace();
        }
    }

    private void requestConnection() throws IOException {
        socket = new Socket("localhost", 8888);

        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
    }
}
