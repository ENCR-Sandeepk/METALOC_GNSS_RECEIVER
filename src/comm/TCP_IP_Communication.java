/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comm;

/**
 *
 * @author sandeepk
 */
import java.net.*;
import java.io.*;
import tool.Constant;
import tool.Variable;

/**
 * This program demonstrates a simple TCP/IP socket client that reads input from
 * the user and prints echoed message from the server.
 *
 * @author Sandeep
 */
public class TCP_IP_Communication {

    static Socket socket = null;
    static OutputStream output;
    static PrintWriter writer;
    static InputStream input;
    static BufferedReader reader;

    public static void send_command(String cmd) {
        try {
            if (Constant.DEBUG) {
                System.out.println("\n----------------------");
                System.out.println("CMD 2 : " + cmd);
            }
            Variable.got_reply_1 = false;
            Variable.reply_1 = "";
            writer.println(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void start_server(String host, int port) {
        new Thread() {
            @Override
            public void run() {
                try {
                    socket = new Socket(host, port);
                    output = socket.getOutputStream();
                    writer = new PrintWriter(output, true);
                    input = socket.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));
                    String inputLine;
                    while ((inputLine = reader.readLine()) != null) {

                        try {
                            if (inputLine.trim().length() > 3) {
                                Variable.reply_1 = inputLine.trim();
                                if (Constant.DEBUG) {
                                    System.out.println("RLY 2  = " + Variable.reply_1);
                                }
                                Variable.got_reply_1 = true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static void waitforReply() {
        long curmillies = System.currentTimeMillis();
        while (Variable.got_reply_1 == false) {
            if ((System.currentTimeMillis() - curmillies) >= Constant.WAIT_FOR_REPLY) {
                break;
            }
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
        }
    }

    public static void close_connection() {
        try {
            socket.close();
        } catch (Exception e) {
        }
    }

}
