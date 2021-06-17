/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.find.impl.actions;

import cz.muni.fi.pb162.find.ApplicationOptions;
import cz.muni.fi.pb162.find.actions.FilterAction;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.filters.BasicFilter;
import cz.muni.fi.pb162.find.impl.filter.FileContentFilter;
import cz.muni.fi.pb162.find.impl.filter.FileNameFilter;
import cz.muni.fi.pb162.find.impl.filter.MaxSizeFilter;
import cz.muni.fi.pb162.find.impl.filter.MinSizeFilter;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Erik Hruby
 */
public class FilterActionImpl implements FilterAction {
     private final ApplicationOptions opts;
     
     /**
      * 
      * @param opts application options
      */
     public FilterActionImpl(ApplicationOptions opts) {
         this.opts = opts;
     }
     
    @Override
    public List<SearchEntry> filter(List<SearchEntry> entries) {                                      
        List<SearchEntry> newEntries = new LinkedList(entries);
        BasicFilter filter;
        
        Long maxSize = opts.getSizeMax();
        if (maxSize != null) {
            filter = new MaxSizeFilter(newEntries, maxSize);
            newEntries = filter.filtered();
        }
        
        Long minSize = opts.getSizeMin();
        if (minSize != null) {
            filter = new MinSizeFilter(newEntries, minSize);
            newEntries = filter.filtered();
        }
        
        String contentRegex = opts.getTextRegex();
        if (contentRegex != null) {
            filter = new FileContentFilter(newEntries, contentRegex);
            newEntries = filter.filtered();                
        }
        
        String nameRegex = opts.getNameRegex();
        if (nameRegex != null) {
            filter = new FileNameFilter(newEntries, nameRegex);
            newEntries = filter.filtered();
        }
           
        return newEntries;
    }
    
}
