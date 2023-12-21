package org.example;

import com.github.javafaker.Faker;
import org.example.Client.ClientConfig;
import org.example.Domain.GoalsPerSeason;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Faker faker= new Faker();

        Socket socket = null;
        try{
            int i = 0;
            ArrayList<GoalsPerSeason> goals = new ArrayList<GoalsPerSeason>();
            for (i = 0 ; i<faker.number().numberBetween(10,15); i++){
                GoalsPerSeason goalsPerSeason = new GoalsPerSeason();
                goals.add(goalsPerSeason);
            }
            goals.stream().forEach(b-> System.out.println(b.getAverageGoals() + " " + b.getGoals()));
            System.out.println("~".repeat(20));

            System.out.println("Client started \n IPadress :" + ClientConfig.getLocalhost() + "\n port: " + ClientConfig.getport() );
            InetAddress inetAddress;
            inetAddress = InetAddress.getByName(ClientConfig.getLocalhost());
            socket = new Socket(inetAddress, ClientConfig.getport());
            InputStream ins = socket.getInputStream();
            OutputStream outs = socket.getOutputStream();
            DataInputStream dis = new DataInputStream(ins);
            DataOutputStream dos = new DataOutputStream(outs);

            Object object = new Object();
            while (true){
                dos.writeInt(goals.size());
                for (GoalsPerSeason goal: goals) {
                    dos.writeInt(goal.getGoals());
                    dos.flush();
                    dos.writeDouble(goal.getAverageGoals());
                    dos.flush();
                }
                goals.clear();

                int max= dis.readInt();
                for(int k=0; k<max;k++){
                    GoalsPerSeason goalsPerSeason = new GoalsPerSeason();
                    goalsPerSeason.setGoals(dis.readInt());
                    goalsPerSeason.setAverageGoals(dis.readDouble());
                    goals.add(goalsPerSeason);
                }
                goals.stream().forEach(b-> System.out.println(b.getAverageGoals() + " " + b.getGoals()));

            }



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
