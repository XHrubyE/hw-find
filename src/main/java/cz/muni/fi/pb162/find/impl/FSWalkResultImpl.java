/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb162.find.impl;

import cz.muni.fi.pb162.find.ApplicationOptions;
import cz.muni.fi.pb162.find.filesystem.DirEntry;
import cz.muni.fi.pb162.find.filesystem.FSWalkResult;
import cz.muni.fi.pb162.find.filesystem.FileEntry;
import cz.muni.fi.pb162.find.filesystem.SearchEntry;
import cz.muni.fi.pb162.find.filesystem.SearchEntry.Types;
import cz.muni.fi.pb162.find.tools.FileTools;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Erik Hruby
 */
public class FSWalkResultImpl extends FSWalkResult {    
    private final List<SearchEntry> entries;
    
    /**
     * 
     * @param options application optios
     */
    public FSWalkResultImpl(ApplicationOptions options) {
        super(options);
        entries = new LinkedList<>();
    }
    
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {        
        addEntry(dir, Types.DIR);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {        
        addEntry(file, Types.FILE);
        return FileVisitResult.CONTINUE;
    }
    
    private void addEntry(Path dir, Types type) {
        SearchEntry entry;
        switch(type){
            case DIR:
                entry = new DirEntry(dir);
                break;
            case FILE:
                entry = new FileEntry(dir);
                break;
            default:
                entry = new DirEntry(dir);
        }
        entry.setSize(FileTools.dirSize(dir));
        entries.add(entry);
    }
        
    @Override
    public List<SearchEntry> getDirectories() {
        return getEntries(Types.DIR);
    }

    @Override
    public List<SearchEntry> getFiles() {        
        return getEntries(Types.FILE);
    }
    
    private List<SearchEntry> getEntries(Types type) {
        List<SearchEntry> newEntries = new LinkedList<>();
        entries.stream()
               .filter((entry) -> (entry.getType() == type))
               .forEachOrdered((entry) -> {
                   newEntries.add(entry);
               });
        return newEntries;
    }
}
