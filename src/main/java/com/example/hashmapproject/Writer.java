package com.example.hashmapproject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/** Класс записывателя в файлы */
public class Writer
{
    /** Поле названия лог-файла */
    private String logFile = "logger.txt";

    /** Поле названия файла для пути */
    private String wayFile = "way.txt";

    /** Метод для доступа к wayFile */
    public String getWayFile()
    {
        return wayFile;
    }

    /** Метод записи в лог-файл
     * @param s строка для записи в лог-файл
     * */
    public void log(String s)
    {
        try(FileWriter writer = new FileWriter(logFile, true))
        {
            Date time = new Date();
            writer.write(String.format("%s: ", time));
            writer.write(s);
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    /** Метод начала записи в лог-файл */
    public void startLog()
    {
        try(FileWriter writer = new FileWriter(logFile, false))
        {
            writer.write("");
            writer.flush();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        try(FileWriter writer = new FileWriter(logFile, true))
        {
            Date time = new Date();
            writer.write(String.format("%s: ", time));
            writer.write("The program has started working");
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    /** Метод записи в wayFile
     * @param word строка - название элемента
     * @param wordWay строка - пути до элемента (включая элемент)
     * */
    public void way(String word, String wordWay)
    {
        try(FileWriter writer = new FileWriter(wayFile, false))
        {
            writer.write(String.format("Путь к элементу %s: ", word));
            writer.write(wordWay);
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
