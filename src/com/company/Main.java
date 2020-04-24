package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {
    //массив с именами файлов в первой папке
    private static String[] firstFolder;
    //путь к первой папке
    private static String firstFolderWay;

    //массив с именами файлов во второй папке
    private static String[] secondFolder;
    //путь к второй папке
    private static String secondFolderWay;

    //путь к папке с результатом
    private static String resultFolder;

    public static void main(String[] args) throws IOException, InterruptedException {
	// write your code here
        String s;

       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите путь к первой папке");
        firstFolderWay = bufferedReader.readLine();
        firstFolder = giveFolderList(firstFolderWay); // получаем список файлов первой папки

        System.out.println("Введите путь к второй папку");
        secondFolderWay = bufferedReader.readLine();
        secondFolder = giveFolderList(secondFolderWay); // получаем список файлов второй папки

        System.out.println("Введите путь к папке с результатом");
        resultFolder = bufferedReader.readLine();


        try {
            // проверям, что в папках одинакое кол-во файлов
            if(firstFolder.length!=secondFolder.length){
                System.out.println("в папках разное кол-во файлов");
            }
            else { // если количество одинаковое то запускаем функцию, которая сравнивает
                for (int i = 0; i < firstFolder.length; i++) {
                    comparePhoto(firstFolder[i],secondFolder[i]);
                }
                System.out.println("Успешно!");
            }

        }

        catch (IOException e){
            System.out.println(e);
        }
    }
         //функция возвращающая массив с файлами в директории
         private static String[] giveFolderList(String s){
             File folder = new File(s);
              return folder.list();

         }

         private static void comparePhoto (String firstFile, String secondFile) throws IOException{
                //создаем название результирующего файла. Оно состоит из имени первого файло(без расширения),
             // соединенного с именем второго файла
                int a = firstFile.length();
                String resname = firstFile.substring(0,(a-4))+secondFile;

              //команда, которая будет исполнена в командной строке
             String s = "magick compare "+firstFolderWay+"\\"+firstFile+" "
                     +secondFolderWay+"\\"+secondFile+" "+resultFolder+"\\"+resname;
             ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", s);

             builder.redirectErrorStream(true);
             Process process = builder.start();
         }

    }

