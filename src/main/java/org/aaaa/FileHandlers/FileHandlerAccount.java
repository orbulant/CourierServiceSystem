package org.aaaa.FileHandlers;

import java.util.ArrayList;
import java.util.List;

import org.aaaa.Person;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.Models.AccountModel;


public class FileHandlerAccount extends FileHandler {
//CONSTRUCTOR
    public FileHandlerAccount(String pathname) {
        super(pathname);
    }

    public Person getAccountByID(String accountID) {
        Person person = new Person();
        List<List<String>> tempList = this.getContent(DatabasePath.Account.getDataLength());
        for(List<String> temp: tempList) {
            if(temp.get(AccountModel.AccountID.getIndex()).equals(accountID)) {
                return this.assignAccount(temp);
            }
        }
        return person;
    }

    public Person assignAccount(List<String> data) {
        return new Person(data);
    }

    ////////////////////////////////OVERRIDES SEARCH
    @Override
    public ArrayList<String> search(String searchable){
        int L = 14;
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

}
