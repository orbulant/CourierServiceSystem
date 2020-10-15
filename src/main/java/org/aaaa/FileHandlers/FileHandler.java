package org.aaaa.FileHandlers;

//Imports
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    protected String pathname;

    //Constructor
    public FileHandler(String pathname){
        this.pathname = pathname;
    }

//Body
    //Multidimensional List with chopping
    //Chops a list into non-view smaller sub-lists of length L
    private static <T> List<List<T>> chopped(List<T> list, final int L) {
        List<List<T>> parts = new ArrayList<>();
        final int N = list.size();
        for (int i = 0; i < N; i += L) {
            parts.add(new ArrayList<>(list.subList(i, Math.min(N, i + L))));
        }
        return parts;
    }

    //Stores content into multidimensional arraylist
    public List<List<String>> getContent(int chopLength) {
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
        //Returns a List<List<String>> type of chopped array (arrList), chopped 5 times before returning]
        return chopped(arrList, chopLength);
    }

    //Stores content into an arraylist
    public List<String> getContentToSingleArray(){
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

    //SEARCH
    //Returns an ArrayList of String search results
    public ArrayList<String> search(String searchable){
        int L = 0;
        ArrayList<String> result = new ArrayList<>();
        try{
            for (int i = 0; i < this.getContent(L).size(); i++) {
                if (this.getContent(L).get(i).get(0).equals(searchable)) {
                    for(int x = 0; x < 8; x++) {
                        result.add(getContent(L).get(i).get(x));
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
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
                writer.write(obj + "\n");
        }
        writer.flush();
        writer.close();
    }

    //Gets a single arraylist and writes content into file separated by comma.
    public void writeContentFromSingleArray(List<String> input) throws IOException{
        //Create a file object
        File file = new File(pathname);
        //Creates a fileWriter object
        FileWriter writer = new FileWriter(file);
        //Write content to file
        //Convert multi dimensional arraylist to static array.
        Object[] objects = input.toArray();
        //Writing array of objects separated by comma and new line
            for(Object obj : objects){
                writer.write(obj + "\n");
                }
        writer.flush();
        writer.close();
    }

    //LOGIN VERIFICATION
    // PUT THIS IN THE LOGIN CONTROLLER FORM LATER
    /*
    public boolean loginSuccess(Staff staff){
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
         int i = 0;
         while (i < this.getContent(8).size()) {
             if(this.getContent(8).get(i).get(1).equals(staff.getUsername()) && this.getContent(7).get(i).get(2).equals(staff.getPassword())){
                 staff.setRole(getContent(8).get(i).get(3));//Set Role to Staff Object
                 staff.setAccountID(getContent(8).get(i).get(0));//Set AccountID to Staff Object
                 staff.setCreatedBy(getContent(8).get(i).get(4));//Set CreatedBy to Staff Object
                 staff.setCreatedOn(LocalDateTime.parse(getContent(8).get(i).get(5), formatter));//Set CreatedOn to Staff Object
                 staff.setChangedBy(getContent(8).get(i).get(6));//Set ChangedBy to Staff Object
                 staff.setChangedOn(LocalDateTime.parse(getContent(8).get(i).get(7), formatter));//Set ChangedOn to Staff Object
                 return true;
             }
             i++;
         }
         return false;
     }

     //SETTING THE CURRENT USER OBSOLETE
     public CurrentUser currUser (boolean loginStatus, Staff staff){
         if(loginStatus){
            return new CurrentUser(staff);
        } else {
            return null;
        }
     }
     */

}

