/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author sandeepk
 */
public class FTP_File_Upload implements Runnable {

    boolean is_file_uploaded = false;
    String file;
    String file_name;
    FTPClient ftpClient;
    InputStream inputStream = null;

    public FTP_File_Upload(InputStream inputStream, FTPClient ftpClient, String file, String file_name) {
        this.inputStream = inputStream;
        this.ftpClient = ftpClient;
        this.file = file;
        this.file_name = file_name;
    }

    private void upload_watch() {
        new Thread() {
            @Override
            public void run() {
                try {
                    is_file_uploaded = false;
                    long curmillies = System.currentTimeMillis();
                    while (!is_file_uploaded) {
                        if ((System.currentTimeMillis() - curmillies) >= Constant.FTP_TIMEOUT) {
                            Variable.is_uploaded = false;
                            try {
                                inputStream.close();
                            } catch (Exception e) {
                            }
                            try {
                                ftpClient.logout();
                                ftpClient.disconnect();
                            } catch (Exception ex) {
                            }
                            break;
                        }
                        try {
                            Thread.sleep(20);
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e) {
                }
            }
        }.start();
    }

    @Override
    public void run() {
        try {

            upload_watch();
            ftpClient = new FTPClient();

            try {

                ftpClient.setConnectTimeout(120000);

                ftpClient.connect(Variable.ftp_url, Integer.parseInt(Variable.ftp_port));

                ftpClient.setDataTimeout(120000);

                ftpClient.login(Variable.ftp_user, Variable.ftp_password);

                ftpClient.setBufferSize(1024 * 1024);
                ftpClient.enterLocalPassiveMode();

                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                File firstLocalFile = new File(file);

                inputStream = new FileInputStream(firstLocalFile);

                Variable.is_uploaded = ftpClient.storeFile(file_name, inputStream);

            } catch (Exception ex) {
                Variable.is_uploaded = false;
                ex.printStackTrace();
            } finally {

                try {
                    ftpClient.logout();
                    ftpClient.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                is_file_uploaded = true;
                Variable.upload_watch = true;
            } catch (Exception e) {
            }

            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
