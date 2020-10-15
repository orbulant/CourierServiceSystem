package org.aaaa.FileHandlers;

import java.util.ArrayList;

public class FileHandlerAccount extends FileHandler {
//CONSTRUCTOR
    public FileHandlerAccount(String pathname) {
        super(pathname);
    }

    @Override
    public ArrayList<String> search(String searchable){
        int L = 14;
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
}
