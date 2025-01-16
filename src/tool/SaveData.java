/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

/**
 *
 * @author Sandeep K
 */
public class SaveData {

    public static String getHeader_dynamic() {
        String header = "DATE/TIME";
        try {

            if (Variable.ROVER_ID_1.equalsIgnoreCase("true")) {
                header = header + ",ROVER_ID";
            }
            if (Variable.BASE_ID_2.equalsIgnoreCase("true")) {
                header = header + ",BASE_ID";
            }

            if (!Variable.dynamic_scanning_status && !Variable.trigger_scanning_status) {
                if (Variable.GNSS_TIME_3.equalsIgnoreCase("true")) {
                    header = header + ",GNSS_TIME";
                }
                if (Variable.RMS_VALUE_4.equalsIgnoreCase("true")) {
                    header = header + ",RMS_VALUE";
                }
                if (Variable.LATTITUDE_5.equalsIgnoreCase("true")) {
                    header = header + ",LATTITUDE";
                }
                if (Variable.LONGITUDE_6.equalsIgnoreCase("true")) {
                    header = header + ",LONGITUDE";
                }
            }

            if (Variable.dEASTING_7.equalsIgnoreCase("true")) {
                header = header + ",dEASTING(" + Variable.reporting_unit + ")";
            }
            if (Variable.dNORTHING_8.equalsIgnoreCase("true")) {
                header = header + ",dNORTHING(" + Variable.reporting_unit + ")";
            }
            if (Variable.dALTITUDE_9.equalsIgnoreCase("true")) {
                header = header + ",dALTITUDE(" + Variable.reporting_unit + ")";
            }

            if (!Variable.dynamic_scanning_status && !Variable.trigger_scanning_status) {
                if (Variable.RATIO_FACTOR_10.equalsIgnoreCase("true")) {
                    header = header + ",RATIO_FACTOR";
                }
                if (Variable.UNKNOWN_11.equalsIgnoreCase("true")) {
                    header = header + ",UNKNOWN";
                }
                if (Variable.GPS_12.equalsIgnoreCase("true")) {
                    header = header + ",GPS";
                }
                if (Variable.BEIDOU_13.equalsIgnoreCase("true")) {
                    header = header + ",BEIDOU";
                }
                if (Variable.GLONASS_14.equalsIgnoreCase("true")) {
                    header = header + ",GLONASS";
                }
                if (Variable.GALILEO_15.equalsIgnoreCase("true")) {
                    header = header + ",GALILEO";
                }
            }

            return header;
        } catch (Exception e) {
            e.printStackTrace();
            return "DATE/TIME,ROVER_ID,BASE_ID,GNSS_TIME,RMS_VALUE,LATTITUDE,LONGITUDE,dEASTING,dNORTHING,dALTITUDE,RATIO_FACTOR,UNKNOWN,GPS,BEIDOU,GLONASS,GALILEO";
        }
    }

    public static String getHeader_static() {
        String header = "DATE/TIME";
        try {

            if (Variable.ROVER_ID_1.equalsIgnoreCase("true")) {
                header = header + ",ROVER_ID";
            }
            if (Variable.BASE_ID_2.equalsIgnoreCase("true")) {
                header = header + ",BASE_ID";
            }

            if (Variable.GNSS_TIME_3.equalsIgnoreCase("true")) {
                header = header + ",GNSS_TIME";
            }
            if (Variable.RMS_VALUE_4.equalsIgnoreCase("true")) {
                header = header + ",RMS_VALUE";
            }
            if (Variable.LATTITUDE_5.equalsIgnoreCase("true")) {
                header = header + ",LATTITUDE";
            }
            if (Variable.LONGITUDE_6.equalsIgnoreCase("true")) {
                header = header + ",LONGITUDE";
            }

            if (Variable.dEASTING_7.equalsIgnoreCase("true")) {
                header = header + ",dEASTING(" + Variable.reporting_unit + ")";
            }
            if (Variable.dNORTHING_8.equalsIgnoreCase("true")) {
                header = header + ",dNORTHING(" + Variable.reporting_unit + ")";
            }
            if (Variable.dALTITUDE_9.equalsIgnoreCase("true")) {
                header = header + ",dALTITUDE(" + Variable.reporting_unit + ")";
            }

            if (Variable.RATIO_FACTOR_10.equalsIgnoreCase("true")) {
                header = header + ",RATIO_FACTOR";
            }
            if (Variable.UNKNOWN_11.equalsIgnoreCase("true")) {
                header = header + ",UNKNOWN";
            }
            if (Variable.GPS_12.equalsIgnoreCase("true")) {
                header = header + ",GPS";
            }
            if (Variable.BEIDOU_13.equalsIgnoreCase("true")) {
                header = header + ",BEIDOU";
            }
            if (Variable.GLONASS_14.equalsIgnoreCase("true")) {
                header = header + ",GLONASS";
            }
            if (Variable.GALILEO_15.equalsIgnoreCase("true")) {
                header = header + ",GALILEO";
            }

            return header;
        } catch (Exception e) {
            e.printStackTrace();
            return "DATE/TIME,ROVER_ID,BASE_ID,GNSS_TIME,RMS_VALUE,LATTITUDE,LONGITUDE,dEASTING,dNORTHING,dALTITUDE,RATIO_FACTOR,UNKNOWN,GPS,BEIDOU,GLONASS,GALILEO";
        }
    }

}
