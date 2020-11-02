package org.aaaa.FileHandlers;

//Imports
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    /*
    FileHandler fields.
     */
    protected String pathname;

    /*
    Constructor
     */
    public FileHandler(String pathname){
        this.pathname = pathname;
    }

    /*
    Chopping Array Function
     */
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

    /*
    Get Content
     */
        //Stores content into multidimensional arraylist.
        public List<List<String>> getContent(int chopLength) {
            ArrayList<String> arrList = new ArrayList<>();
            try {
                FileReader fr = new FileReader(this.pathname);
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
    /*
    Search
     */
        public List<String> search (String searchable){
            int L = 0;
            ArrayList<String> result = new ArrayList<>();
            try{
                for (int i = 0; i < getContent(L).size(); i++) {
                    if (getContent(L).get(i).get(0).equals(searchable)) {
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

    /*
    Update
     */
        //Overwrites content on file if buffer.get(x).get(0) EQUALS to input.get(0).
        public void update(List<List<String>> buffer, List<String> input)  throws IOException{
            //Create a file object
            File file = new File(pathname);
            //Creates a fileWriter object
            FileWriter writer = new FileWriter(file);

            for (List<String> item : buffer) {
                if (item.get(0).equals(input.get(0))) {
                    int x = 0;
                    for(String s : input){
                        item.set(x, s);
                        x++;
                    }
                }
            }

            for(List<String> innerstuff : buffer){
                for(String s : innerstuff){
                    writer.write(s + "\n");
                }
            }
            //Writing array of objects separated by comma and new line
            writer.flush();
            writer.close();
        }

    /*
    Write Content
     */
        public void writeContent(List<List<String>> input) throws IOException {
            //Create a file object
            File file = new File(pathname);
            //Creates a fileWriter object
            FileWriter writer = new FileWriter(file);
            //Write content to file
            //Writing array of objects separated by comma
            for(List<String> innerstuff : input){
                for(String s : innerstuff){
                    writer.write(s + "\n");
                }
            }
            writer.flush();
            writer.close();
        }
    /*
    Add Content (Append)
     */
        public void addContent(List<List<String>> buffer, List<String> input) throws IOException {
            buffer.add(input);
            writeContent(buffer);
        }
}

