/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.find.impl.filter;

import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.filters.BasicFilter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
/**
 *
 * @author Erik Hruby
 */
public class FileContentFilter extends BasicFilter {    
    private final String regex;
    
    /**
     * 
     * @param paths list of entries
     * @param regex regex to match
     */
    public FileContentFilter(List<SearchEntry> paths, String regex) {
        super(paths);
        this.regex = regex;
    }
    
    @Override
    public boolean filter(SearchEntry path) {
        String content = readAllBytes(path.toString());
        return content.matches(regex);
    }
    
     private static String readAllBytes(String path) {
        String content = "";
        try {
            content = new String ( Files.readAllBytes(Paths.get(path) ) );
        } catch (IOException ex) {
            throw new UncheckedIOException("File reading failed", ex);
        }
        return content;
    }
}
