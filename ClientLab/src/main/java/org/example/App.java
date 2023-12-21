package org.example;

import com.github.javafaker.Faker;
import org.example.Client.ClientConfig;
import org.example.domain.SchoolBoy;

import java.io.*;
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
            ArrayList<SchoolBoy> boys = new ArrayList<SchoolBoy>();
            for (i = 0 ; i<faker.number().numberBetween(10,15); i++){
                SchoolBoy schoolBoy = new SchoolBoy();
                boys.add(schoolBoy);
            }
            boys.stream().forEach(b-> System.out.println(b.getName() + " " + b.getMinScore() + " "+ b.getMaxScore() +" " + b.getAverageScore()));
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
                dos.writeInt(boys.size());
                for (SchoolBoy boy: boys) {
                    dos.writeUTF(boy.getName());
                    dos.flush();
                    dos.writeInt(boy.getMinScore());
                    dos.flush();
                    dos.writeInt(boy.getMaxScore());
                    dos.flush();
                    dos.writeDouble(boy.getAverageScore());
                }
                boys.clear();

                int max= dis.readInt();
                for(int k=0; k<max;k++){
                    SchoolBoy schoolBoy = new SchoolBoy();
                    schoolBoy.setName(dis.readUTF());
                    schoolBoy.setMinScore(dis.readInt());
                    schoolBoy.setMaxScore(dis.readInt());
                    schoolBoy.setAverageScore(dis.readDouble());

                    boys.add(schoolBoy);
                }
               boys.stream().forEach(b-> System.out.println(b.getName() + " " + b.getMinScore() + " "+ b.getMaxScore() +" " + b.getAverageScore()));

            }



        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
