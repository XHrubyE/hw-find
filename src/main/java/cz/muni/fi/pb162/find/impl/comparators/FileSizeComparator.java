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
public class FileSizeComparator implements BasicComparator {
    private final BasicComparator nextComparator;
    
    /**
     * 
     * @param nextComparator comparator
     */
    public FileSizeComparator(BasicComparator nextComparator) {
        this.nextComparator = nextComparator;
    }
    @Override
    public BasicComparator getNextComparator() {
        return nextComparator;
    }

    @Override
    public int compare(SearchEntry entryOne, SearchEntry entryTwo) {
        int result = Long.compare(entryOne.getSize(), entryTwo.getSize());
        if (result == 0) {
            return getNextComparator().compare(entryOne, entryTwo);
        }
        return result;
    }
    
}
