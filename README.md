
General information
-------------------
In this assignment you will implement a command line utility used for file search. 
You can think of it as a simplified version of ```find``` or ```grep``` utilities known from unix.  

Your program will allow its users to search for files and directories using various filters and specify 
the ordering used to print out the result.


### Project structure
The structure of project provided as a base for your implementation should meet the following criteria.

1. Package ```cz.muni.fi.pb162.find``` contains classes and interfaces provided as part of the assignment.
  - **Do not modify or add any classes or subpackages into this package.**
2. Package  ```cz.muni.fi.pb162.find.impl``` should contain your implementation.
  - **Anything outside this package will be ignored during evaluation.**

### Names in this document
Unless fully classified name is provided, all class names are relative to  package ```cz.muni.fi.pb162.find``` or ```cz.muni.fi.pb162.find.impl``` for classes implemented as part of your solution.

### Compiling the project
The project can be compiled and packaged in the same way you already know.

```bash
$ mvn clean compile
```

The only difference is, that unlike with seminar project, this time checks for missing documentation and style violation will produce an error.
You can temporarily disable this behavior when running this command.

```bash
$ mvn clean compile -Dcheckstyle.fail=false
```

Step 1: Gather directories and files
------------------------------------
Before we start doing anything fancy (e.g. applying filters) we actually need a list of entries (files and directories) to work with.

Create a class ```impl.FSWalkResultImpl``` extending ```cz.muni.fi.pb162.find.filesystem.FSWalkResult```.  

Looking at ```FSWalkResult``` you may notice that it extends  -- ```java.nio.file.SimpleFileVisitor<Path>```. 
This class  provides the necessary methods for traversing the filesystem, stopping at each file or directory and performing some actions with it.

```SimpleFileVisitor``` is a regular class and as such provides an implementation for all its methods. However this implementation 
doesn't really do anything except looking at the visited entry and continuing the walk. This means your ```FSWalkResultImpl``` 
will have to override some of these methods.  It shouldn't be too hard to determine which.  If you are not sure, this 
task is a great candidate for a discussion with your classmates on course forums. 

Yes we know, ```SimpleFileVisitor``` was not taught at the lecture and this may seem a bit confusing. However part of 
what makes a great software engineer is the ability to study and understand documentation and code written by somebody else. 
Take a deep breath and go through the documentation for these classes. Things should start making sense pretty fast. 


Step 2: Implement Filters
-------------------------
In order to narrow the result set to only those files and directories we are interested in, we will implement a number of filters.
Filter classes should extend ```cz.muni.fi.pb162.find.filters.BasicFilter``` and should be placed inside ```cz.muni.fi.pb162.find.impl.filters```  package.
All filter classes should also have a constructor accepting the list of entries to be filtered (```List<SearchEntry>```).

Implement the following filters:



| filter class       | additional constructor parameter | description                  | Additional info |
| ------------------ | -------------- | -----------------------------------------------|-----------------|   
| FileNameFilter     | String regex   | filter based on file/directory name.           | ```String#matches(String)``` |
| MaxSizeFilter      | long maxSize   | filter based on maximum size of file/directory |                 |
| MinSizeFilter      | long minSize   | filter based on minimum size of file/directory |                 |
| FileContentFilter  | String regex   | filter based on the content of a file          | ```String#matches(String)``` is sufficient |

By examining the ```BasicFilter``` class, it should be clear that you only need to create a constructor and implement the ```filter(SearchEntry path)``` method.

Step 3: Implement Comparators
-----------------------------
The last bit of functionality we want to add to our application is the ability to sort the result of our search. This means
implementing a bunch of comparators. Comparator classes should implement ```cz.muni.fi.pb162.find.comparators.BasicComparator``` and should be placed inside ```cz.muni.fi.pb162.find.impl.comparators``` package.
All comparator classes (except ```FilePathComparator```) should also have a constructor accepting the next comparator (```BasicComparator```).
The next comparator is used if compared entries are equal. 


Implement the following comparators:


| comparator class          | description    |
| ------------------------- | -------------- |    
| FilePathComparator        | Compare entries based on their absolute path  | 
| FileNameComparator        | Compare entries based on their name.  | 
| FileExtensionComparator   | Compare entries based on their extension (files only)   | 
| FileSizeComparator        | Compare entries based on their size |


The reason why ```FilePathComparator``` doesn't accept a next comparator is that there can't be two entries with the same absolute path on the filesystem.
Step 4: Implement Actions
-------------------------
Create an implementations of ```cz.muni.fi.pb162.find.actions.FilterAction``` interface in ```impl.actions.FilterActionImpl```.
This class represents a main execution point for filtering the result of the walk over filesystem we have done earlier.  


Create an implementations of ```cz.muni.fi.pb162.find.actions.SortAction``` interface in ```impl.actions.SortActionImpl```.
This class represents a main execution point for ordering the filtered results.
  
More information can be obtained by reading provided javadoc for these classes. Also have a look at how are these used
in the code provided as part of the assignment.

Running the program
-------------------
Once you are done with your implementation you can build the program by executing

```mvn clean install```

This will build an executable jar file inside ```target``` directory. The following command will print the basic usage help.

```
java -jar find-1.0-SNAPSHOT.jar --help
```
