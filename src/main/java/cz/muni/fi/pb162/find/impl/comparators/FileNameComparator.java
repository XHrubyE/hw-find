/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.find.impl.comparators;

import cz.muni.fi.pb162.find.comparators.BasicComparator;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;

/**
 *
 * @author Erik Hruby
 */
public class FileNameComparator implements BasicComparator {
    private final BasicComparator nextComparator;
    
    /**
     * 
     * @param nextComparator comparator
     */
    public FileNameComparator(BasicComparator nextComparator) {
        this.nextComparator = nextComparator;
    }

    @Override
    public BasicComparator getNextComparator() {
        return nextComparator;
    }

    @Override
    public int compare(SearchEntry entryOne, SearchEntry entryTwo) {
        String nameOne = entryOne.getFileName().toString();
        String nameTwo = entryTwo.getFileName().toString();        
        int result = nameOne.compareTo(nameTwo);
        if (result == 0) {
            return this.getNextComparator().compare(entryOne, entryTwo);
        }
        return result;
    }
    
}
