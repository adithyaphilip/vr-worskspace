package com.kr.cardboard;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class NetworkUtils {
    public final static String IP = "192.168.43.194";
    public final static int[] BASE_PORTS = {6000, 6001, 6002, 6003};
    private final static int CHOICE_PORT = 6004;
    private final static int MAX_IMG_SIZE_BYTES = 1024*1024;

    public static Bitmap[] getBaseImages() throws IOException {
        ArrayList<Bitmap> bitmaps = new ArrayList<>();

        for(int basePort: BASE_PORTS) {
            bitmaps.add(getImage(IP, basePort));
        }

        return bitmaps.toArray(new Bitmap[bitmaps.size()]);
    }

    public static Bitmap getImage(String ip, int port) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(ip, port));
        InputStream is = socket.getInputStream();
        byte[] result = getAllBytesFromStream(is, MAX_IMG_SIZE_BYTES);
        is.close();
        socket.close();

        return BitmapFactory.decodeByteArray(result, 0, result.length);
    }

    public static void informChoice(int choice) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(IP, CHOICE_PORT));
        socket.getOutputStream().write((byte) choice);
        socket.close();
    }

    public static byte[] getAllBytesFromStream(InputStream is, int maxSize) throws IOException {
        byte[] buffer = new byte[maxSize];
        int totRead = 0;
        int read = is.read(buffer, totRead, buffer.length - totRead);
        while (read != -1) {
            totRead += read;
            read = is.read(buffer, totRead, buffer.length - totRead);
        }
        byte[] filled = new byte[totRead];
        for(int i = 0; i < totRead; i++) {
            filled[i] = buffer[i];
        }
        return filled;
    }
}
