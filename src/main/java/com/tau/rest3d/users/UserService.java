package com.tau.rest3d.users;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final String pathToFolder = "/home/agirnob/Documents/files/";
    private final String regexPart = "[\\w,.-]+\\.gcode";

    public String[] uploadService(MultipartFile file) throws Exception {
        String absolutePath = makePath(file);
        file.transferTo(new File(absolutePath));
        System.out.println("start slicing");
        //convertToStl(absolutePath);
        System.out.println("finished converting");
        return information(readfile(file.getOriginalFilename()));
    }

    private String makePath(MultipartFile file) {

        String pathToStlFile = pathToFolder + file.getOriginalFilename();
        return pathToStlFile;
    }

    private void convertToStl(String pathToStlFile) throws Exception {
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("prusa-slicer -g --repair --load /home/agirnob/Documents/gaps.ini " + pathToStlFile);
        pr.waitFor();
        int exitVal = pr.exitValue();
    }

    private String getFile(String fileName) throws Exception {
        String regexPattern = fileName.substring(0, fileName.length() - 4) + regexPart;
        Pattern gcodeFilePattern = Pattern.compile(regexPattern);
        File folder = new File(pathToFolder);
        File[] files = folder.listFiles();

        boolean boolMatch = false;
        for (File f : files) {
            Matcher matchGcode = gcodeFilePattern.matcher(f.getName());
            boolMatch = matchGcode.matches();

            if (boolMatch) {
                System.out.println("we got inside");
                return (f.getName());
            }
        }
        return "file not found";

    }

    private String readfile(String filename) throws Exception {
        String gcodeFileName = getFile(filename);
        File file = new File(pathToFolder + gcodeFileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuffer stringBuffer = new StringBuffer();//because we want it mutuable
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        return stringBuffer.toString();
    }

    private String[] information(String gCode) {
        String subStringTofindPrintTime = "estimated printing time (normal mode) = ";
        String subStringTofindCost = "filament cost = ";
        String subStringtoFindGram = "filament used [g] = ";

        int indexOfTheSubstringTime = gCode.indexOf(subStringTofindPrintTime);
        int indexOfTheSubStringCost = gCode.indexOf(subStringTofindCost);
        int indexOfTheSubStringGram = gCode.indexOf(subStringtoFindGram);


        String timeOfPrint = gCode.substring(indexOfTheSubstringTime + subStringTofindPrintTime.length(),gCode.indexOf(';',indexOfTheSubstringTime));
        String CostfPrint = gCode.substring(indexOfTheSubStringCost + subStringTofindCost.length(), gCode.indexOf(';',indexOfTheSubStringCost));
        String GramOfPrint = gCode.substring(indexOfTheSubStringGram + subStringtoFindGram.length(),gCode.indexOf(';',indexOfTheSubStringGram));

        String[] strArray = {timeOfPrint,CostfPrint,GramOfPrint};
        return strArray;
    }

}
