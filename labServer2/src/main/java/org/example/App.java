package org.example;

import org.example.Server.Server;
import org.example.Server.ServerConfig;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ServerSocket serverSocket= null;
        int i =0;
        try {

            InetAddress ia;
            ia = InetAddress.getByName("localhost");
            serverSocket = new ServerSocket(ServerConfig.getPort(),0,ia);
            System.out.println("Server Started");
            while (true){
                Socket socket = serverSocket.accept();
                System.err.println("Client accepted");
                new Server().setSocket(socket);
            }

        }catch (Exception e){
            System.out.println("Ошибка: " + e);
        }
        finally {
            try {
                if(serverSocket != null){
                    serverSocket.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    }
