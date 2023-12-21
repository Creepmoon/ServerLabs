package org.example.Server;

import org.example.Domain.SchoolBoy;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Server extends Thread {
    private static final int port = ServerConfig.getPort();
    private int NumberClient;
    private final String Massage = "клиент прислал сообщение \n";

    private Socket socket;
    public Server(){}


    public void setSocket(int NumberClient,Socket socket) {
        this.NumberClient = NumberClient;
        this.socket = socket;

        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    @Override
    public void run() {
        try{
            InputStream ins= socket.getInputStream();
            OutputStream outs = socket.getOutputStream();
            DataInputStream dis = new DataInputStream(ins);
            DataOutputStream dos = new DataOutputStream(outs);
            ArrayList<SchoolBoy> boys = new ArrayList<>();
            ArrayList<SchoolBoy> filterboys;

            while (true){
                int max= dis.readInt();
                System.out.println(max);
                for(int i=0; i<max;i++){
                SchoolBoy schoolBoy = new SchoolBoy();
                schoolBoy.setName(dis.readUTF());
                schoolBoy.setMinScore(dis.readInt());
                schoolBoy.setMaxScore(dis.readInt());
                schoolBoy.setAverageScore(dis.readDouble());

                boys.add(schoolBoy);
                }

                boys.stream().forEach(b-> System.out.println(b.getName() + " " + b.getMinScore() + " "+ b.getMaxScore() +" " + b.getAverageScore()));
                boys = (ArrayList<SchoolBoy>) boys.stream().filter(b-> b.getAverageScore()>3.5).collect(Collectors.toList());

                sleep(3000);
                dos.writeInt(boys.size());
                for (SchoolBoy boy: boys) {
                    dos.writeUTF(boy.getName());
                    dos.flush();
                    dos.writeInt(boy.getMinScore());
                    dos.flush();
                    dos.writeInt(boy.getMaxScore());
                    dos.flush();
                    dos.writeDouble(boy.getAverageScore());
                    dos.flush();
                }
                break;


            }

        } catch (Exception e){


        }
    }
}
