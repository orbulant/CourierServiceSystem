package org.aaaa.FileHandlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.aaaa.Person;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.Models.AccountModel;


public class FileHandlerAccount extends FileHandler {
    /*
    Constructor
     */
        public FileHandlerAccount(String pathname) {
            super(pathname);
        }

    /*
    Get Account By ID
     */
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

    /*
    Assign Account
     */
        public Person assignAccount(List<String> data) {
            return new Person(data);
        }


    /*
    Write account.txt
     */
        public void writeAccountFile(List<Person> buffer) throws IOException {
            //Create a file object
            File file = new File(pathname);
            //Creates a fileWriter object
            FileWriter writer = new FileWriter(file);
            //Write content to file
            //Writing array of objects separated by comma
            for(Person innerstuff : buffer){
                writer.write(innerstuff.getAccountID() + "\n");
                writer.write(innerstuff.getNric()+ "\n");
                writer.write(innerstuff.getName() + "\n");
                writer.write(innerstuff.getContactNum()+ "\n");
                writer.write(innerstuff.getDob().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n");
                writer.write(innerstuff.getHousenum()+ "\n");
                writer.write(innerstuff.getFulladdress().getAddress() + "\n");
                writer.write(innerstuff.getFulladdress().getCity()+ "\n");
                writer.write(innerstuff.getFulladdress().getPostcode() + "\n");
                writer.write(innerstuff.getFulladdress().getState() + "\n");
                writer.write(innerstuff.getFulladdress().getCountry()+ "\n");
                writer.write(innerstuff.getCreatedBy() + "\n");
                writer.write(innerstuff.getCreatedOn()+ "\n");
                writer.write(innerstuff.getChangedBy() + "\n");
                writer.write(innerstuff.getChangedOn()+ "\n");
            }
            writer.flush();
            writer.close();
        }
    /*
    Update Account
     */
    public void update(Person person) throws IOException{
        //Create a file object
        File file = new File(pathname);
        //Creates a fileWriter object
        FileWriter writer = new FileWriter(file);
        //Creates a multi-dimensional list named buffer.
        List<List<String>> buffer = getContent(DatabasePath.Account.getDataLength());

        for (List<String> item : buffer) {
            if (person.getAccountID().equals(item.get(0))){
                item.set(0, person.getAccountID());
                item.set(1, person.getName());
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



}
