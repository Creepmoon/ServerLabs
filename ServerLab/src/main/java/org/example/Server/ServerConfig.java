package org.example.Server;

public class ServerConfig {

    private static final String IP= "localhost";

    private static final int Port = 6666;

    public static String getIP() {
        return IP;
    }

    public static int getPort() {
        return Port;
    }
}
