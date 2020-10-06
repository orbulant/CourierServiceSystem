package org.aaaa.FileHandler;

//Imports
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private String pathname;

//Constructor
    public FileHandler(String pathname){
        this.pathname = pathname;
    }

//Body
    //Multidimensional List with chopping
    //Chops a list into non-view smaller sub-lists of length L
    static <T> List<List<T>> chopped(List<T> list, final int L) {
        List<List<T>> parts = new ArrayList<>();
        final int N = list.size();
        for (int i = 0; i < N; i += L) {
            parts.add(new ArrayList<>(list.subList(i, Math.min(N, i + L))));
        }
        return parts;
    }

    //getContent() stores content into multidimensional arraylist
    public List<List<String>> getContent() {
        ArrayList<String> arrList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(pathname);
            BufferedReader br = new BufferedReader(fr);
            //While the input read is available (a.k.a not null)
            String str;
            while((str = br.readLine()) != null){
                arrList.add(str);
                }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Returns a List<List<String>> type of chopped array (arrList), chopped 5 times before returning
        return chopped(arrList, 5);
    }
    //getContentsinglearr() stores content into an arraylist
    public List<String> getContentsinglearr(){
        ArrayList<String> arrList = new ArrayList<>();
        try{
            FileReader fr = new FileReader(pathname);
            BufferedReader br = new BufferedReader(fr);
            //While the input read is available (a.k.a not null)
            String str;
            while((str = br.readLine()) != null){
                arrList.add(str);
            }
            br.close();
            fr.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        //Returns a List<String> type of chopped array that is called "arrList"
        return arrList;
    }

    //Gets a multidimensional arraylist and writes content into file array by array separated by comma.
    public void writeContent(List<List<String>> input) throws IOException {
        //Create a file object
        File file = new File(pathname);
        //Creates a fileWriter object
        FileWriter writer = new FileWriter(file);
        //Write content to file
        //Convert multi dimensional arraylist to static array.
        Object[] objects = input.toArray();
        //Writing array of objects separated by comma
        for(Object obj : objects){
                writer.write(obj + ",");
        }
        writer.flush();
        writer.close();
    }

    //Gets a single arraylist and writes content into file separated by comma.
    public void writeContenttosinglearr(List<String> input) throws IOException{
        //Create a file object
        File file = new File(pathname);
        //Creates a fileWriter object
        FileWriter writer = new FileWriter(file);
        //Write content to file
        //Convert multi dimensional arraylist to static array.
        Object[] objects = input.toArray();
        //Writing array of objects sepearted by comma and new line
            for(Object obj : objects){
                writer.write(obj + ", \n");
                }
        writer.flush();
        writer.close();
    }

}

