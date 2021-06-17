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
public class FilePathComparator implements BasicComparator {
        
    @Override
    public BasicComparator getNextComparator() {
        return null;
    }

    @Override
    public int compare(SearchEntry entryOne, SearchEntry entryTwo) {
        return entryOne.getPath().compareTo(entryTwo.getPath());
    }
    
}
