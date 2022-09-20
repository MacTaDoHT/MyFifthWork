package MyFifthWork;

import java.io.*;
import java.util.ArrayList;

public class AppData {
    private String[] header;
    private int[][] data;
    private String fileName = "appData.csv";
    private String cvsSplitBy = ";";

    public AppData(String[] newHeader, int[][] newData) {
        if (validateValuesNumber(newData, newHeader.length)) {
            this.header = newHeader;
            this.data = newData;
        } else {
            throw new IllegalArgumentException("Mismatch between header and data length!");
        }
    }
    public void getDataFromCSV(String fileName, String cvsSplitBy) {
        this.fileName = fileName;
        this.cvsSplitBy = cvsSplitBy;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            header = br.readLine().split(cvsSplitBy);
            String line;
            ArrayList<String> lines = new ArrayList<>();
            while((line = br.readLine()) != null) {
                lines.add(line);
            }
            data = new int[lines.size()][header.length];
            for(int i = 0; i < lines.size(); i++) {
                data[i] = stringIntoIntArray(lines.get(i), cvsSplitBy);
                if (data[i].length != header.length) {
                    throw new IllegalArgumentException("Mismatch between header and data length!");
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    private boolean validateValuesNumber(int[][] validatedData, int expectedNumber) {
        for (int[] validatedDatum : validatedData) {
            if (validatedDatum.length != expectedNumber) {
                return false;
            }
        }
        return true;
    }
    private int[] stringIntoIntArray(String strToConvert, String cvsSplitBy) {
        String[] strArray = strToConvert.split(cvsSplitBy);
        int[] intArray = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            try {
                intArray[i] = Integer.parseInt(strArray[i]);
            } catch(NumberFormatException e) {
                System.out.println("Incorrect format. String '" + intArray[i] + "' can not be converted to int!");
            }
        }
        return intArray;
    }
    public void saveDataIntoCSV() {
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            StringBuffer line = new StringBuffer();
            for (int i = 0; i < header.length; i++) {
                line.append(header[i]);
                if (i != header.length - 1) {
                    line.append(cvsSplitBy);
                }
            }
            pw.println(line);
            for (int[] datum : data) {
                line.delete(0, line.length());
                for (int j = 0; j < datum.length; j++) {
                    line.append(datum[j]);
                    if (j != datum.length - 1) {
                        line.append(cvsSplitBy);
                    }
                }
                pw.println(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void print() {
        System.out.println();
        for (int[] datum : data) {
            for (int j = 0; j < datum.length; j++) {
                System.out.print(header[j] + ":" + datum[j]);
                if (j < datum.length - 1) {
                    System.out.print(cvsSplitBy + " ");
                }
            }
            System.out.println();
        }
    }
}