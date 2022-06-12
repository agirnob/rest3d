package com.tau.rest3d.users;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final String pathToFolder = "/home/agirnob/Documents/files/";

    public String[] uploadService(MultipartFile file) throws Exception {
        String absolutePath = makePath(file);
        file.transferTo(new File(absolutePath));
        System.out.println("start slicing");
        convertToStl(absolutePath);
        System.out.println("finished converting");
        return information(readfile(Objects.requireNonNull(changeSpaces(file.getOriginalFilename()))));
    }

    //TODO make following methods a new class
    public String makePath(MultipartFile file) {

        String st = Objects.requireNonNull(changeSpaces(file.getOriginalFilename()));

        System.out.println(st);

        return pathToFolder + st;
    }

    public void convertToStl(String pathToStlFile) throws Exception {
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("prusa-slicer -g --repair --load /home/agirnob/Documents/gaps.ini " + pathToStlFile);
        pr.waitFor();

    }

    public String getFile(String fileName) {
        final String regexPart = "[\\w,. -]+\\.gcode";
        String regexPattern = fileName.substring(0, fileName.length() - 4) + regexPart;
        Pattern gcodeFilePattern = Pattern.compile(regexPattern);
        File folder = new File(pathToFolder);
        File[] files = folder.listFiles();


        boolean boolMatch;
        assert files != null;
        for (File f : files) {
            Matcher matchGcode = gcodeFilePattern.matcher(f.getName());
            boolMatch = matchGcode.matches();

            if (boolMatch) {
                System.out.println("we got inside");
                return (f.getName());
            }
        }
        return "this file has not found";

    }

    private String readfile(String filename) throws Exception {
        String gcodeFileName = getFile(filename);
        File file = new File(pathToFolder + gcodeFileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuffer = new StringBuilder();//because we want it mutuable
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        return stringBuffer.toString();
    }

    public String[] information(String gCode) {
        String subStringTofindPrintTime = "estimated printing time (normal mode) = ";
        String subStringTofindCost = "filament cost = ";
        String subStringtoFindGram = "filament used [g] = ";

        int indexOfTheSubstringTime = gCode.indexOf(subStringTofindPrintTime);
        int indexOfTheSubStringCost = gCode.indexOf(subStringTofindCost);
        int indexOfTheSubStringGram = gCode.indexOf(subStringtoFindGram);


        String timeOfPrint = gCode.substring(indexOfTheSubstringTime + subStringTofindPrintTime.length(), gCode.indexOf(';', indexOfTheSubstringTime));
        String CostfPrint = gCode.substring(indexOfTheSubStringCost + subStringTofindCost.length(), gCode.indexOf(';', indexOfTheSubStringCost));
        String GramOfPrint = gCode.substring(indexOfTheSubStringGram + subStringtoFindGram.length(), gCode.indexOf(';', indexOfTheSubStringGram));

        return new String[]{timeOfPrint, CostfPrint, GramOfPrint};
    }

    public static String changeSpaces(String stringWithSpaces) {
        String stringWİthDashes = stringWithSpaces.replace(' ', '_');
        return stringWİthDashes;

    }

}
