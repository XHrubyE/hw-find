/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.find.impl.actions;

import cz.muni.fi.pb162.find.ApplicationOptions;
import cz.muni.fi.pb162.find.actions.SortAction;
import cz.muni.fi.pb162.find.comparators.BasicComparator;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import java.util.List;
import cz.muni.fi.pb162.find.tools.SortFactory;
import java.util.Collections;
import java.util.LinkedList;
/**
 *
 * @author Erik Hruby
 */
public class SortActionImpl implements SortAction {
     private final ApplicationOptions opts;
     
     /**
      * 
      * @param opts application options
      */
     public SortActionImpl(ApplicationOptions opts) {
         this.opts = opts;
     }
     
    @Override
    public List<SearchEntry> sorted(List<SearchEntry> entries) {       
        BasicComparator comparator = SortFactory.create(opts.getSort());
        List<SearchEntry> newEntries = new LinkedList(entries);
        Collections.sort(newEntries, comparator);
        return newEntries;
    }
    
}
