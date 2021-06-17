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
public class MaxSizeFilter extends BasicFilter {
    private final long maxSize;
    
    /**
     * 
     * @param paths list of entries
     * @param maxSize maximum entry size
     */
    public MaxSizeFilter(List<SearchEntry> paths, long maxSize) {
        super(paths);
        this.maxSize = maxSize;
    }

    @Override
    public boolean filter(SearchEntry path) {
        long size = path.getSize();
        return size <= maxSize;
    }
    
}
