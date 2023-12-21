package org.example.Client;

public class ClientConfig {
    private static final int port= 6666;
    private static final String localhost =  "127.0.0.1"; //"192.168.0.101";

    public static int getport() {
        return port;
    }

    public static String getLocalhost() {
        return localhost;
    }
}
