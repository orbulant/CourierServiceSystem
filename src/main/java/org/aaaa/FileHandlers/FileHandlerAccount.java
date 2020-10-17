package org.aaaa.FileHandlers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.aaaa.Address;
import org.aaaa.Person;
import org.aaaa.Enums.DatabasePath;


public class FileHandlerAccount extends FileHandler {
//CONSTRUCTOR
    public FileHandlerAccount(String pathname) {
        super(pathname);
    }

    public Person getAccountByID(String accountID) {
        Person person = new Person();
        List<List<String>> tempList = this.getContent(DatabasePath.Account.getDataLength());
        for(List<String> temp: tempList) {
            if(temp.get(0) == accountID) {
                this.assignAccount(temp);
            }
        }
        return person;
    }

    public Person assignAccount(List<String> account) {
        Person person = new Person();
        person.setAccountID(account.get(0));
        person.setNric(account.get(1));
        person.setName(account.get(2));
        person.setContactNum(account.get(3));
        person.setDob(LocalDate.parse(account.get(4)));
        person.setHousenum(account.get(5));
        person.setCreatedBy(account.get(11));
        person.setChangedBy(account.get(13));
        if(account.get(12) != "") {
            person.setCreatedOn(LocalDateTime.parse(account.get(12)));
        }
        if(account.get(14) != "") {
            person.setChangedOn(LocalDateTime.parse(account.get(14)));
        }

        Address address = new Address();
        address.setAddress(account.get(6));
        address.setCity(account.get(7));
        address.setPostcode(account.get(8));
        address.setState(account.get(9));
        address.setCountry(account.get(10));
        person.setFulladdress(address);
        return person;
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
