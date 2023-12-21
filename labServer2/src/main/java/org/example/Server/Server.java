package org.example.Server;


import org.example.domain.GoalsPerSeason;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Server extends Thread {
    private static final int port = ServerConfig.getPort();

    private Socket socket;
    public Server(){}


    public void setSocket(Socket socket) {
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
            ArrayList<GoalsPerSeason> goals = new ArrayList<>();


            while (true){
                int max= dis.readInt();
                System.out.println(max);
                for(int i=0; i<max;i++){
                GoalsPerSeason goalsPerSeason = new GoalsPerSeason();
                goalsPerSeason.setGoals(dis.readInt());
                goalsPerSeason.setAverageGoals(dis.readDouble());
                goals.add(goalsPerSeason);
                }

                goals.stream().forEach(b-> System.out.println(b.getAverageGoals() + " " + b.getGoals()));
                goals = (ArrayList<GoalsPerSeason>) goals.stream().filter(b-> b.getAverageGoals()>0.5).collect(Collectors.toList());

                sleep(3000);
                dos.writeInt(goals.size());
                for (GoalsPerSeason goal: goals) {
                    dos.writeInt(goal.getGoals());
                    dos.flush();
                    dos.writeDouble(goal.getAverageGoals());
                    dos.flush();
                }
                break;


            }

        } catch (Exception e){


        }
    }
}
