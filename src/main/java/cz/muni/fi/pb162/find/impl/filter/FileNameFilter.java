/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.find.impl.filter;

import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.filters.BasicFilter;
import java.util.List;
/**
 *
 * @author Erik Hruby
 */
public class FileNameFilter extends BasicFilter {    
    private final String regex;
    
    /**
     * 
     * @param paths list of entries
     * @param regex regex to match
     */
    public FileNameFilter(List<SearchEntry> paths, String regex) {
        super(paths);
        this.regex = regex;
    }

    @Override
    public boolean filter(SearchEntry path) {
        String name = path.getFileName().toString();
        return name.matches(regex);
    }
    
}
