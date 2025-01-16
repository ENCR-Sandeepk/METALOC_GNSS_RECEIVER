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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tool.Tool.backslashReplace;

/**
 *
 * @author sandeepk
 */
public class Theme {

    public static void addTheme(String theme_name, String color_back, String header) throws Exception {
        FileWriter fw;
        String path = backslashReplace(System.getProperty("user.home"));

        File file1 = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.THEME_FOLDER + "/" + theme_name + ".txt");
        if (!file1.exists()) {
            file1.createNewFile();
            fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(color_back);
            pw.println(header);

            pw.flush();
            pw.close();
            fw.close();
        }
    }

    public static void loadThemeColor(String theme) throws Exception {

        switch (theme) {
            case "Theme-1":
                Variable.theme_color = "90CAF9FF";
                Variable.theme_header = "Option-1";
                break;
            case "Theme-2":
                Variable.theme_color = "90CAF9FF";
                Variable.theme_header = "Option-2";
                break;
            case "Theme-3":
                Variable.theme_color = "90CAF9FF";
                Variable.theme_header = "Option-3";
                break;
            case "Theme-4":
                Variable.theme_color = "90CAF9FF";
                Variable.theme_header = "Option-4";
                break;
            case "Theme-5":
                Variable.theme_color = "90CAF9FF";
                Variable.theme_header = "Option-5";
                break;
            case "Theme-6":
                Variable.theme_color = "90CAF9FF";
                Variable.theme_header = "Option-6";
                break;
            case "Theme-7":
                Variable.theme_color = "90CAF9FF";
                Variable.theme_header = "Option-7";
                break;
            case "Theme-8":
                Variable.theme_color = "90CAF9FF";
                Variable.theme_header = "Option-8";
                break;
            case "Theme-9":
                Variable.theme_color = "90CAF9FF";
                Variable.theme_header = "Option-9";
                break;
            default:
                readTheme(theme);
                break;
        }
    }

    public static void readTheme(String theme_name) {
        BufferedReader br = null;
        String path = backslashReplace(System.getProperty("user.home"));
        try {
            br = new BufferedReader(new FileReader(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.THEME_FOLDER + "/" + theme_name + ".txt"));
            int temp = 1;
            String tempStr;
            while ((tempStr = br.readLine()) != null) {
                switch (temp) {
                    case 1:
                        Variable.theme_color = tempStr.trim();
                        break;
                    case 2:
                        Variable.theme_header = tempStr.trim();
                        break;

                }
                temp++;
            }

        } catch (Exception e) {
        } finally {
            try {
                br.close();
            } catch (Exception ex) {
            }
        }
    }

    public static void modify_selected_theme(String theme_name) {
        try {
            String path = backslashReplace(System.getProperty("user.home"));
            FileWriter fw;
            String fileName = path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.THEME_FILE;
            File file1 = new File(fileName);
            fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(theme_name);

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

    public static boolean isDuplicateTheme(String file) {
        File dir = new File(file);
        return dir.exists();
    }

    public static boolean isPreDefineTheme(String theme) {

        return theme.trim().equalsIgnoreCase("Theme-1")
                || theme.equalsIgnoreCase("Theme-2")
                || theme.equalsIgnoreCase("Theme-3")
                || theme.equalsIgnoreCase("Theme-4")
                || theme.equalsIgnoreCase("Theme-5")
                || theme.equalsIgnoreCase("Theme-6")
                || theme.equalsIgnoreCase("Theme-7")
                || theme.equalsIgnoreCase("Theme-8")
                || theme.equalsIgnoreCase("Theme-9");
    }

    public static boolean validateTheme(String name) {

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if ((c >= '0' && c <= '9') || ((c >= 'A' && c <= 'Z')) || ((c >= 'a' && c <= 'z')) || (c == '-') || (c == '_')) {
            } else {
                return true;
            }
        }
        return false;
    }

    public static void read_selected_theme() {
        BufferedReader br = null;
        String path = backslashReplace(System.getProperty("user.home"));
        try {
            br = new BufferedReader(new FileReader(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.THEME_FILE));
            int temp = 1;
            String tempStr;
            while ((tempStr = br.readLine()) != null) {
                switch (temp) {
                    case 1:
                        Variable.theme_selected = tempStr.trim();
                        break;
                }
                temp++;
            }
        } catch (Exception e) {
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Theme.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
