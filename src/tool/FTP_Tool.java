/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import org.apache.commons.net.ftp.FTPClient;
import static tool.Tool.backslashReplace;

/**
 *
 * @author sandeepk
 */
public class FTP_Tool {

    static Thread thread = null;
    static FTP_File_Upload fTP_File_Upload;
    static InputStream inputStream = null;
    static FTPClient ftpClient;
    static File processCheck;

    public static void start_upload() {

        new Thread() {
            @Override
            public void run() {
                Thread.currentThread().setName("Ftp Upload");
                while (true) {

                    try {

                        if (read_ftp_flag() && Variable.ftp_upload_enable.equalsIgnoreCase("true")) {

                            write_ftp_flag(false);

                            String all_files[] = Tool.serachAllFilesInADirectory("" + Variable.storage_location + Constant.COMPANY_FOLDER + "/" + Constant.ROOT_FOLDER + "/" + Constant.CSV_TO_UPLOAD_FOLDER, ".csv");

                            try {

                                if (Variable.ftp_url.equals("0.0.0.0")
                                        || Variable.ftp_port == null || Variable.ftp_port.trim().length() == 0
                                        || Variable.ftp_user == null || Variable.ftp_user.trim().length() == 0
                                        || Variable.ftp_password == null || Variable.ftp_password.trim().length() == 0) {
                                    try {
                                        Thread.sleep(1000);
                                    } catch (Exception e) {
                                    }
                                } else {

                                    if (all_files.length > 0 && all_files[0] != null && all_files[0].trim().length() > 0) {

                                        try {
                                            Thread.sleep(200);
                                        } catch (Exception e) {
                                        }
                                        Arrays.sort(all_files);
                                        for (String all_file : all_files) {

                                            processCheck = new File(Variable.storage_location + Constant.COMPANY_FOLDER + "/" + Constant.ROOT_FOLDER + "/" + Constant.CSV_TO_UPLOAD_FOLDER + "/" + all_file);
                                            if (processCheck.getParentFile().canWrite()) {

                                                Variable.is_uploaded = true;
                                                Variable.upload_watch = false;

                                                fTP_File_Upload = new FTP_File_Upload(inputStream, ftpClient, Variable.storage_location + Constant.COMPANY_FOLDER + "/" + Constant.ROOT_FOLDER + "/" + Constant.CSV_TO_UPLOAD_FOLDER + "/" + all_file, all_file);
                                                thread = new Thread(fTP_File_Upload);
                                                thread.start();
                                                thread.setName("FTP_File_Upload");

//                                                uploadFiles(Variable.storage_location + Constant.COMPANY_FOLDER + "/" + Constant.ROOT_FOLDER + "/" + Constant.CSV_FOLDER + "/" + all_file, all_file);
                                                upload_watch();

                                                if (Variable.is_uploaded) {

                                                    if (Variable.file_upload_action.equalsIgnoreCase("Archive")) {
                                                        Tool.moveToArchive(Variable.storage_location + Constant.COMPANY_FOLDER + "/" + Constant.ROOT_FOLDER + "/" + Constant.CSV_TO_UPLOAD_FOLDER + "\\"
                                                                + all_file);
                                                    } else {
                                                        Tool.delete_file(Variable.storage_location + Constant.COMPANY_FOLDER + "/" + Constant.ROOT_FOLDER + "/" + Constant.CSV_TO_UPLOAD_FOLDER + "/" + all_file);
                                                    }
                                                } else {
                                                    close_connection();
                                                    Thread.sleep(100);
                                                    break;
                                                }
                                            }
                                        }
                                    } else {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (Exception e) {
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception ee) {
                                }
                            }
                            write_ftp_flag(true);
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                        } else {
                            try {
                                if ((System.currentTimeMillis() - Variable.last_upload_attempt) > 300000) {
                                    write_ftp_flag(true);
                                }
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                        }
                    } catch (Exception e) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception ee) {
                        }
                    }

                }
            }
        }.start();

    }

    public static void close_connection() {
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
        try {
            thread.interrupt();
        } catch (Exception e) {
        }
    }

    public static void upload_watch() {

        long curmillies = System.currentTimeMillis();
        while (!Variable.upload_watch) {
            if ((System.currentTimeMillis() - curmillies) >= Constant.FTP_TIMEOUT) {
                Variable.is_uploaded = false;
                break;
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
        try {
            thread.interrupt();
        } catch (Exception e) {
        }
    }

    public static void write_ftp_flag(boolean value) {
        FileWriter fw;
        String configFilePath = backslashReplace(System.getProperty("user.home"));

        File dir = new File(configFilePath + Constant.COMPANY_FOLDER + Constant.FTP_FLAG_FOLDER);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        configFilePath = configFilePath + Constant.COMPANY_FOLDER + Constant.FTP_FLAG_FOLDER + Constant.FTP_FLAG_FILE;
        try {
            File file1 = new File(configFilePath);
            file1.createNewFile();
            fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);
            // Write to file for the first row
            pw.println(value);
            pw.println("" + System.currentTimeMillis());
            // Flush the output to the file
            pw.flush();
            // Close the Print Writer
            pw.close();
            // Close the File Writer
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean read_ftp_flag() {
        boolean return_value = true;
        try {
            String configFilePath = backslashReplace(System.getProperty("user.home"));
            configFilePath = configFilePath + Constant.COMPANY_FOLDER + Constant.FTP_FLAG_FOLDER + Constant.FTP_FLAG_FILE;

            if (new File(configFilePath).exists()) {
                String tempStr = "";
                BufferedReader br = new BufferedReader(new FileReader(configFilePath));
                if ((tempStr = br.readLine()) != null) {
                    return_value = tempStr.trim().equalsIgnoreCase("true");
                }

                try {
                    if ((tempStr = br.readLine()) != null) {
                        Variable.last_upload_attempt = Long.parseLong(tempStr.trim());
                    }
                } catch (Exception e) {
                    Variable.last_upload_attempt = 0;
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return return_value;
    }

//    public static void uploadFiles(String file, String file_name) {
//
//        ftpClient = new FTPClient();
//
//        try {
//
//            ftpClient.setConnectTimeout(120000);
//
//            ftpClient.connect(Variable.ftp_url, Integer.parseInt(Variable.ftp_port));
//
//            ftpClient.setDataTimeout(120000);
//
//            ftpClient.login(Variable.ftp_user, Variable.ftp_password);
//
//            ftpClient.setBufferSize(1024 * 1024);
//            ftpClient.enterLocalPassiveMode();
//
//            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//
//            File firstLocalFile = new File(file);
//
//            inputStream = new FileInputStream(firstLocalFile);
//
//            ftpClient.storeFile(file_name, inputStream);
//
//        } catch (Exception ex) {
//            Variable.is_uploaded = false;
//            ex.printStackTrace();
//        } finally {
//            try {
//                ftpClient.logout();
//                ftpClient.disconnect();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            try {
//                inputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            Variable.upload_watch = true;
//        } catch (Exception e) {
//        }
//
//        try {
//            Thread.sleep(100);
//        } catch (Exception e) {
//        }
//
//    }
}
