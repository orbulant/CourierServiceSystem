package org.aaaa.FileHandlers;

import java.util.List;

import org.aaaa.Enums.DatabasePath;

public class FileHandlerOrder extends FileHandler {

    public FileHandlerOrder(String pathname) {
        super(pathname);
    }

    public String getLatestID() {
        List<List<String>> tempList = this.getContent(DatabasePath.Order.getDataLength());
        return tempList.get(tempList.size() - 1).get(0);
    }
}
