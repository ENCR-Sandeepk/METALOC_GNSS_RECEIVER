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
public class DataServices {

    public static float[] average_faf_array(int r_index) {
        float sum_dEASTING = 0;
        float sum_dNORTHING = 0;
        float sum_dALTITUDE = 0;

        try {
            float[][][] fafArray = Variable.faf_array;
            int filterSize = Variable.faf_filter_size;

            for (int i = 0; i < filterSize; i++) {
                sum_dEASTING += fafArray[r_index][i][0];
                sum_dNORTHING += fafArray[r_index][i][1];
                sum_dALTITUDE += fafArray[r_index][i][2];
            }

            sum_dEASTING /= filterSize;
            sum_dNORTHING /= filterSize;
            sum_dALTITUDE /= filterSize;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new float[]{sum_dEASTING, sum_dNORTHING, sum_dALTITUDE};
    }

    public static float[] calculate_final_values(int r_index) {
        float sum_dEASTING = 0;
        float sum_dNORTHING = 0;
        float sum_dALTITUDE = 0;

        try {
            float[][][] mafArray = Variable.maf_array;
            int bucketSize = Variable.maf_bucket_size;

            for (int i = 0; i < bucketSize; i++) {
                sum_dEASTING += mafArray[r_index][i][0];
                sum_dNORTHING += mafArray[r_index][i][1];
                sum_dALTITUDE += mafArray[r_index][i][2];
            }

            sum_dEASTING /= bucketSize;
            sum_dNORTHING /= bucketSize;
            sum_dALTITUDE /= bucketSize;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new float[]{sum_dEASTING, sum_dNORTHING, sum_dALTITUDE};
    }

    public static void fill_faf_and_maf_array(String dEasting, String dNorthing, String dAltitude, int r_index) {
        try {

            // Put New Avlues into array
            if (Variable.faf_array_over[r_index]) {
                try {
                    Variable.faf_array[r_index][Variable.faf_array_index[r_index]][0] = Float.parseFloat(dEasting);
                    Variable.faf_array[r_index][Variable.faf_array_index[r_index]][1] = Float.parseFloat(dNorthing);
                    Variable.faf_array[r_index][Variable.faf_array_index[r_index]][2] = Float.parseFloat(dAltitude);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    for (int i = Variable.faf_array_index[r_index]; i < Constant.MAX_FAF; i++) {
                        Variable.faf_array[r_index][i][0] = Float.parseFloat(dEasting);
                        Variable.faf_array[r_index][i][1] = Float.parseFloat(dNorthing);
                        Variable.faf_array[r_index][i][2] = Float.parseFloat(dAltitude);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // FAF Array Averaging
            float[] result = DataServices.average_faf_array(r_index);

            if (result != null) {

                float averaged_dEASTING = result[0];
                float averaged_dNORTHING = result[1];
                float averaged_dALTITUDE = result[2];

                // Moving Averaging
                if (Variable.maf_array_over[r_index]) {
                    try {
                        Variable.maf_array[r_index][Variable.maf_array_index[r_index]][0] = averaged_dEASTING;
                        Variable.maf_array[r_index][Variable.maf_array_index[r_index]][1] = averaged_dNORTHING;
                        Variable.maf_array[r_index][Variable.maf_array_index[r_index]][2] = averaged_dALTITUDE;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        for (int i = Variable.maf_array_index[r_index]; i < Constant.MAX_MAF; i++) {
                            Variable.maf_array[r_index][i][0] = averaged_dEASTING;
                            Variable.maf_array[r_index][i][1] = averaged_dNORTHING;
                            Variable.maf_array[r_index][i][2] = averaged_dALTITUDE;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            Variable.faf_array_index[r_index]++;
            if (Variable.faf_array_index[r_index] >= Variable.faf_filter_size) {
                Variable.faf_array_index[r_index] = 0;
                Variable.faf_array_over[r_index] = true;

                Variable.maf_array_index[r_index]++;

                if (Variable.maf_array_index[r_index] >= Variable.maf_bucket_size) {
                    Variable.maf_array_index[r_index] = 0;
                    Variable.maf_array_over[r_index] = true;
                }
            }

            if (Variable.base_line_count[r_index] <= Variable.base_line_duration) {
                Variable.base_line_count[r_index]++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getData_static(String[] data, int rover_index) {
        StringBuilder process_data = new StringBuilder();

        try {
            if (Variable.ROVER_ID_1.equalsIgnoreCase("true")) {
                process_data.append(",").append(data[0]);
            }
            if (Variable.BASE_ID_2.equalsIgnoreCase("true")) {
                process_data.append(",").append(data[1]);
            }
            if (Variable.GNSS_TIME_3.equalsIgnoreCase("true")) {
                process_data.append(",").append(data[2]);
            }
            if (Variable.RMS_VALUE_4.equalsIgnoreCase("true")) {
                process_data.append(",").append(data[3]);
            }
            if (Variable.LATTITUDE_5.equalsIgnoreCase("true")) {
                process_data.append(",").append(data[4].replace("N", ""));
            }
            if (Variable.LONGITUDE_6.equalsIgnoreCase("true")) {
                process_data.append(",").append(data[5].replace("E", ""));
            }
            if (Variable.dEASTING_7.equalsIgnoreCase("true")) {
                process_data.append(",").append(formatValue(data[6]));
            }
            if (Variable.dNORTHING_8.equalsIgnoreCase("true")) {
                process_data.append(",").append(formatValue(data[7]));
            }
            if (Variable.dALTITUDE_9.equalsIgnoreCase("true")) {
                process_data.append(",").append(formatValue(data[8]));
            }
            if (Variable.RATIO_FACTOR_10.equalsIgnoreCase("true")) {
                process_data.append(",").append(data[9]);
            }
            if (Variable.UNKNOWN_11.equalsIgnoreCase("true")) {
                process_data.append(",").append(data[10]);
            }
            if (Variable.GPS_12.equalsIgnoreCase("true")) {
                process_data.append(",").append(data[11]);
            }
            if (Variable.BEIDOU_13.equalsIgnoreCase("true")) {
                process_data.append(",").append(data[12]);
            }
            if (Variable.GLONASS_14.equalsIgnoreCase("true")) {
                process_data.append(",").append(data[13]);
            }
            if (Variable.GALILEO_15.equalsIgnoreCase("true")) {
                process_data.append(",").append(data[14]);
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return process_data.toString();
    }

    public static String getData_dynamic(String[] data, int rover_index) {
        StringBuilder process_data = new StringBuilder();

        try {
            if (Variable.ROVER_ID_1.equalsIgnoreCase("true")) {
                process_data.append(",").append(data[0]);
            }
            if (Variable.BASE_ID_2.equalsIgnoreCase("true")) {
                process_data.append(",").append(data[1]);
            }

            if (!Variable.dynamic_scanning_status && !Variable.trigger_scanning_status) {
                if (Variable.GNSS_TIME_3.equalsIgnoreCase("true")) {
                    process_data.append(",").append(data[2]);
                }
                if (Variable.RMS_VALUE_4.equalsIgnoreCase("true")) {
                    process_data.append(",").append(data[3]);
                }
                if (Variable.LATTITUDE_5.equalsIgnoreCase("true")) {
                    process_data.append(",").append(data[4].replace("N", ""));
                }
                if (Variable.LONGITUDE_6.equalsIgnoreCase("true")) {
                    process_data.append(",").append(data[5].replace("E", ""));
                }
            }

            if (Variable.dEASTING_7.equalsIgnoreCase("true")) {
                process_data.append(",").append(formatValue(data[6]));
            }
            if (Variable.dNORTHING_8.equalsIgnoreCase("true")) {
                process_data.append(",").append(formatValue(data[7]));
            }
            if (Variable.dALTITUDE_9.equalsIgnoreCase("true")) {
                process_data.append(",").append(formatValue(data[8]));
            }

            if (!Variable.dynamic_scanning_status && !Variable.trigger_scanning_status) {
                if (Variable.RATIO_FACTOR_10.equalsIgnoreCase("true")) {
                    process_data.append(",").append(data[9]);
                }
                if (Variable.UNKNOWN_11.equalsIgnoreCase("true")) {
                    process_data.append(",").append(data[10]);
                }
                if (Variable.GPS_12.equalsIgnoreCase("true")) {
                    process_data.append(",").append(data[11]);
                }
                if (Variable.BEIDOU_13.equalsIgnoreCase("true")) {
                    process_data.append(",").append(data[12]);
                }
                if (Variable.GLONASS_14.equalsIgnoreCase("true")) {
                    process_data.append(",").append(data[13]);
                }
                if (Variable.GALILEO_15.equalsIgnoreCase("true")) {
                    process_data.append(",").append(data[14]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return process_data.toString();
    }

    private static String formatValue(String value) {
        int decimalDigits;
        switch (Variable.reporting_unit.toLowerCase()) {
            case "mm":
                decimalDigits = 1;
                break;
            case "cm":
            case "inch":
                decimalDigits = 2;
                break;
            default:
                decimalDigits = 4;
                break;
        }
        return Tool.setDecimalDigitsWithoutE(value, decimalDigits);
    }

    /**
     * Returns the conversion factor based on the reporting unit.
     *
     * @param reportingUnit
     * @return
     */
    public static float getConversionFactor(String reportingUnit) {
        switch (reportingUnit.toLowerCase()) {
            case "mm":
                return 1000.0f;
            case "cm":
                return 100.0f;
            case "inch":
                return 39.3701f;
            case "feet":
                return 3.28084f;
            default:
                return 1.0f; // Default to no conversion if unit is not recognized
        }
    }

    /**
     * Converts the value to the target unit using the conversion factor.
     *
     * @param value
     * @param conversionFactor
     * @return
     */
    public static String convertUnit(String value, float conversionFactor) {
        float floatValue = Float.parseFloat(value);
        return String.valueOf(floatValue * conversionFactor);
    }
}
