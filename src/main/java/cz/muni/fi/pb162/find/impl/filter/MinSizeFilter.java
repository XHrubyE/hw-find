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
public class MinSizeFilter extends BasicFilter {
    private final long minSize;
    
    /**
     * 
     * @param paths list of entries
     * @param minSize minimum entry size
     */
    public MinSizeFilter(List<SearchEntry> paths, long minSize) {
        super(paths);
        this.minSize = minSize;
    }
    
    @Override
    public boolean filter(SearchEntry path) {
         long size = path.getSize();
         return size >= minSize;
    }
    
}
