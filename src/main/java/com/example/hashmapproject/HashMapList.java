package com.example.hashmapproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Iterator;

/** Класс для HashMap'а */
public class HashMapList implements Iterable
{
    /** Поле HashMap c ключом типа String и значением типа HashMapList */
    private HashMap<String, HashMapList> map;

    /** Конструктор без параметров */
    public HashMapList()
    {
        map = new HashMap<String, HashMapList>();
    }

    /** Метод доступа к полю map */
    public HashMap<String, HashMapList> getMap()
    {
        return map;
    }

    /** Метод добавления нового элемента к map
     * @param key ключ нового элемента
     * @param value значение нового элемента
     * */
    public void add(String key, HashMapList value)
    {
        map.put(key, value);
    }

    /** Метод рекурсивной инициализации
     * @param level уровень рекурсии
     * */
    public HashMapList initializationList(int level)
    {
        if (level>1)
        {
            for(int i = 0; i < 11 - level; i++)
            {
                String key = generateWord();
                HashMapList value = new HashMapList();
                value = value.initializationList(level - 1);
                this.add(key, value);
            }
        }
        else
        {
            for(int i = 0; i < 11 - level; i++)
            {
                String key = generateWord();
                HashMapList value = new HashMapList();
                this.add(key, value);
            }

        }
        return this;
    }

    /** Метод для получения ObservableList из ключей */
    public ObservableList find()
    {
        ObservableList obList = FXCollections.observableArrayList();
        for (Object p: this)
        {
            String ss = (String) p;
            obList.add(ss);
        }
        return obList;
    }


    @Override
    public Iterator iterator()
    {
        return map.keySet().iterator();
    }

    /** Метод для генерации названий */
    public String generateWord()
    {
        String s = "";
        for(int i = 3; i < (int)(Math.random() * 10 + 4); i++)
        {
            int j = (int) (Math.random() * 26 + 97);
            char ch = (char) j;
            s += ch;
        }
        return s;
    }


}
