/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.find.impl.comparators;

import cz.muni.fi.pb162.find.comparators.BasicComparator;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.tools.FileTools;

/**
 *
 * @author Erik Hruby
 */
public class FileExtensionComparator implements BasicComparator {
    private final BasicComparator nextComparator;
    
    /**
     * 
     * @param nextComparator comparator
     */
    public FileExtensionComparator(BasicComparator nextComparator) {
        this.nextComparator = nextComparator;
    }
    @Override
    public BasicComparator getNextComparator() {
        return nextComparator;
    }

    @Override
    public int compare(SearchEntry entryOne, SearchEntry entryTwo) {
        String extOne = FileTools.fileExtension(entryOne.getPath());
        String extTwo = FileTools.fileExtension(entryTwo.getPath());
        int result;
        if (extOne == null || extTwo == null) {            
            long one = entryOne.getFileName().toString().length();
            long two = entryTwo.getFileName().toString().length();
            result = Long.compare(one, two);
        } else {
            result = extOne.compareTo(extTwo);
        }              
        
        if (result == 0) {
            return this.getNextComparator().compare(entryOne, entryTwo);
        }
        return result;
    }
}
