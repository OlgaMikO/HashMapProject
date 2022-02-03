package com.example.hashmapproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class Controller
{
    /** Текстовые поля для каждого уровня */
    @FXML
    private TextField field1, field2, field3, field4, field5, field6, field7, field8;

    /** Поля ListView для каждого уровня */
    @FXML
    private ListView listView1, listView2, listView3, listView4, listView5, listView6, listView7, listView8;

    /** Поле HashMapList - главный Hashmap HashMap'ов */
    private HashMapList list;

    /** Поле текущего пути */
    private static Stack<String> way;

    /** Поле записывателя в файлы */
    private Writer writer = new Writer();

    /** Конструктор без параметров */
    public Controller()
    {
        listView1 = new ListView(FXCollections.observableArrayList());
        listView2 = new ListView(FXCollections.observableArrayList());
        listView3 = new ListView(FXCollections.observableArrayList());
        listView4 = new ListView(FXCollections.observableArrayList());
        listView5 = new ListView(FXCollections.observableArrayList());
        listView6 = new ListView(FXCollections.observableArrayList());
        listView7 = new ListView(FXCollections.observableArrayList());
        listView8 = new ListView(FXCollections.observableArrayList());
        way = new Stack<>();
    }

    /** Рекурсивный метод для доступа к разделам list'а
     * @param hmlist данный HashmapList
     * @param word выделенное слово
     * @param level уровен погружения в рекурсию
     **/

    public void design(HashMapList hmlist, String word, int level)
    {
        if (level > 1)
        {
            HashMapList nowList = new HashMapList();
            while (8 - level <= way.size())
            {
                way.pop();
            }
            way.push(word);
            ObservableList obList = FXCollections.observableArrayList();
            try
            {
                nowList = hmlist.getMap().get(word);
                obList = nowList.find();
                getListView(9 - level).setItems(obList);
            }
            catch(Exception e)
            {
                writer.log(e.getMessage());
//                System.out.println(e);
            }

            ObservableList finalObList = obList;
            getField(9 - level).textProperty().addListener((a, b, newValue) ->
            {
                ObservableList newObList = FXCollections.observableArrayList();
                for(Object p: finalObList)
                {
                    String s = (String) p;
                    if (s.indexOf(newValue) > -1)
                    {
                        newObList.add(s);
                    }
                }
                getListView(9 - level).setItems(newObList);
            });

            MultipleSelectionModel<String> langsSelectionModel = getListView(9 - level).getSelectionModel();
            HashMapList finalNowList = nowList;
            langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>()
            {
                public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue)
                {
                    for (int i = 9 - level + 1; i <= 8; i++)
                    {
                        try
                        {
                            getListView(i).setItems(FXCollections.observableArrayList());
                        }
                        catch (Exception e)
                        {
                            writer.log(e.getMessage());
//                            System.out.println(e);
                        }
                    }
                    design(finalNowList, newValue, level - 1);
                }
            });
        }
        else
        {
            HashMapList nowList = new HashMapList();
            ObservableList obList = FXCollections.observableArrayList();
            try
            {
                nowList = hmlist.getMap().get(word);
                obList = nowList.find();
                getListView(9 - level).setItems(obList);
            }
            catch(Exception e)
            {
                writer.log(e.getMessage());
//                System.out.println(e);
            }

            ObservableList finalObList = obList;
            getField(9 - level).textProperty().addListener((a, b, newValue) ->
            {
                ObservableList newObList = FXCollections.observableArrayList();
                for(Object p: finalObList)
                {
                    String s = (String) p;
                    if (s.indexOf(newValue) > -1)
                    {
                        newObList.add(s);
                    }
                }
                getListView(9 - level).setItems(newObList);
            });

        }

    }

    /** Метод для определения текущего текстового поля
     * @param level номер нужного текстого поля
     * */
    public TextField getField(int level)
    {
        switch (level)
        {
            case 1:
                return field1;
            case 2:
                return field2;
            case 3:
                return field3;
            case 4:
                return field4;
            case 5:
                return field5;
            case 6:
                return field6;
            case 7:
                return field7;
            case 8:
                return field8;

        }
        return new TextField();
    }

    /** Метод для определения текущего ListView
     * @param level номер нужного ListView
     * */
    public ListView getListView(int level)
    {
        switch (level)
        {
            case 1:
                return listView1;
            case 2:
                return listView2;
            case 3:
                return listView3;
            case 4:
                return listView4;
            case 5:
                return listView5;
            case 6:
                return listView6;
            case 7:
                return listView7;
            case 8:
                return listView8;

        }
        return new ListView();
    }

    /** Метод для инициализации */
    public void initialize()
    {
        list = new HashMapList();
        list = list.initializationList(8);
        ObservableList obList = FXCollections.observableArrayList();

        for (Object p: list)
        {
            String s = (String) p;
            obList.add(s);
        }
        getListView(1).setItems(obList);

        getField(1).textProperty().addListener((a, b, newValue) ->
        {
            ObservableList newObList = FXCollections.observableArrayList();
            for(Object p:obList)
            {
                String s = (String) p;
                if (s.indexOf(newValue) > -1)
                {
                    newObList.add(s);
                }
            }
            getListView(1).setItems(newObList);
        });


        MultipleSelectionModel<String> langsSelectionModel = getListView(1).getSelectionModel();
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>()
        {

            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue)
            {
                HashMapList nextList = list.getMap().get(newValue);
                for (int i = 2; i <= 8; i++)
                {
                    try
                    {
                        getListView(i).setItems(FXCollections.observableArrayList());
                    }
                    catch(Exception e)
                    {
                        writer.log(e.getMessage());
//                        System.out.println(e);
                    }
                }
                design(list, newValue, 7);
            }
        });

        writer.startLog();

    }

    /** Метод нажатия на кнопку "Save" */
    public void onClick()
    {
        while (way.peek()==null) way.pop();
        System.out.println(String.format("Путь к элементу %s: ", way.peek()));
        String s = "";
        for(String p:way)
        {
            s+=String.format("%s/", p);
        }
        s = s.substring(0, s.length()-1);
        System.out.println(s);

        writer.way(way.peek(), s);

        Stage stage = new Stage();
        BorderPane pane = new BorderPane();
        pane.setCenter(new Text(String.format("Путь к элементу %s сохранен в файле %s ", way.peek(), writer.getWayFile())));
        Scene scene = new Scene(pane, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    /** Метод для комбинации клавиш Ctrl+S
     * @param e событие вызванное нажатием клавиш
     * */
    public void key(KeyEvent e)
    {
        if (e.getCode() == KeyCode.S && e.isShortcutDown())
        {
            onClick();
        }
    }


}