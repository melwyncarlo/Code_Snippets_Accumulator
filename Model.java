package CodeSnippetsAccumulator;

import java.io.File;
import java.util.Random;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;

/*
   Copyright 2021 Melwyn Francis Carlo <carlo.melwyn@outlook.com>

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

public class Model
{
    /* Public class variables */
        public static final int ID_LIST_CAPACITY_MINIMUM = 100;

        public static final int TITLE_FIELD_CHARS_MIN = 5;
        public static final int  CODE_FIELD_CHARS_MIN = 5;

        public static final String FILE_SEPARATOR = File.separator;

        public static final String[] languageFieldList = new String[]
        {
            "ABAP", 
            "ABC", 
            "ActionScript", 
            "Ada", 
            "Agilent VEE", 
            "Algol", 
            "Alice", 
            "Angelscript", 
            "Apex", 
            "APL", 
            "AppleScript", 
            "Arc", 
            "Arduino", 
            "ASP", 
            "AspectJ", 
            "Assembly Language", 
            "ATLAS", 
            "Augeas", 
            "AutoHotkey", 
            "AutoIt", 
            "AutoLISP", 
            "Automator", 
            "Avenue", 
            "Awk", 
            "Bash", 
            "Binary Machine Code", 
            "BASIC", 
            "Bash", 
            "bc", 
            "BCPL", 
            "BETA", 
            "BlitzMax", 
            "Boo", 
            "Bourne Shell", 
            "Bro", 
            "C", 
            "C Shell", 
            "C#", 
            "C++", 
            "C-Omega", 
            "Caml", 
            "Ceylon", 
            "CFML", 
            "cg", 
            "Ch", 
            "CHILL", 
            "CIL", 
            "Clarion", 
            "Clean", 
            "Clipper", 
            "Clojure", 
            "CLU", 
            "COBOL", 
            "Cobra", 
            "CoffeeScript", 
            "ColdFusion", 
            "COMAL", 
            "Common Lisp", 
            "Coq", 
            "CSS", 
            "cT", 
            "Curl", 
            "D", 
            "Dart", 
            "DCL", 
            "DiBOL", 
            "Dylan", 
            "E", 
            "eC", 
            "Ecl", 
            "ECMAScript", 
            "EGL", 
            "Eiffel", 
            "Elixir", 
            "Emacs Lisp", 
            "Erlang", 
            "Etoys", 
            "Euphoria", 
            "EXEC", 
            "F#", 
            "Factor", 
            "Falcon", 
            "Fancy", 
            "Fantom", 
            "Felix", 
            "Forth", 
            "FORTRAN", 
            "Fortress", 
            "Gambas", 
            "GNU Octave", 
            "Go", 
            "Google AppsScript", 
            "Gosu", 
            "Groovy", 
            "Haskell", 
            "haXe", 
            "Heron", 
            "Hexa-decimal Machine Code", 
            "HPL", 
            "HTML", 
            "HyperTalk", 
            "Icon", 
            "IDL", 
            "Inform", 
            "Informix-4GL", 
            "INTERCAL", 
            "Io", 
            "Ioke", 
            "J", 
            "J#", 
            "JADE", 
            "Java", 
            "Java FX Script", 
            "JavaScript", 
            "JSON", 
            "JScript", 
            "Julia", 
            "Korn Shell", 
            "Kotlin", 
            "LabVIEW", 
            "Ladder Logic", 
            "Lasso", 
            "Limbo", 
            "Lingo", 
            "Lisp", 
            "Logo", 
            "Logtalk", 
            "LotusScript", 
            "LPC", 
            "Lua", 
            "Lustre", 
            "M4", 
            "MAD", 
            "Magic", 
            "Magik", 
            "Malbolge", 
            "MANTIS", 
            "Maple", 
            "Mathematica", 
            "MATLAB", 
            "MAXScript", 
            "MEL", 
            "Mercury", 
            "Mirah", 
            "Miva", 
            "ML", 
            "Monkey", 
            "Modula", 
            "MOO", 
            "Moto", 
            "MS DOS Batch", 
            "MUMPS", 
            "NATURAL", 
            "Nemerle", 
            "Nimrod", 
            "NQC", 
            "NSIS", 
            "Nu", 
            "NXT G", 
            "Oberon", 
            "Object Rexx", 
            "Objective C", 
            "Objective J", 
            "OCaml", 
            "Occam", 
            "ooc", 
            "Opa", 
            "OpenCL", 
            "OpenEdge ABL", 
            "OPL", 
            "Oz", 
            "Paradox", 
            "Parrot", 
            "Pascal", 
            "Perl", 
            "PHP", 
            "Pike", 
            "PILOT", 
            "PL I", 
            "PL SQL", 
            "Pliant", 
            "PostScript", 
            "POV Ray", 
            "PowerBasic", 
            "PowerScript", 
            "PowerShell", 
            "Processing", 
            "Prolog", 
            "Puppet", 
            "Pure Data", 
            "Python", 
            "Q", 
            "R", 
            "Racket", 
            "REALBasic", 
            "REBOL", 
            "Revolution", 
            "REXX", 
            "Ruby", 
            "Rust", 
            "S", 
            "S+", 
            "SAS", 
            "Sather", 
            "Scala", 
            "Scheme", 
            "Scilab", 
            "Scratch", 
            "sed", 
            "Seed7", 
            "Self", 
            "Shell", 
            "SIGNAL", 
            "Simula", 
            "Simulink", 
            "Slate", 
            "Smalltalk", 
            "Smarty", 
            "SPARK", 
            "SPSS", 
            "SQL", 
            "SQR", 
            "Squeak", 
            "Squirrel", 
            "Standard ML", 
            "Suneido", 
            "SuperCollider", 
            "SVG", 
            "TACL", 
            "Tcl", 
            "Tex", 
            "thinBasic", 
            "TOM", 
            "Transact SQL", 
            "Turing", 
            "TypeScript", 
            "VBScript", 
            "Verilog", 
            "VHDL", 
            "VimL", 
            "Visual Basic", 
            "WebDNA", 
            "Whitespace", 
            "X10", 
            "XAML", 
            "xBase", 
            "XBase++", 
            "Xen", 
            "XML", 
            "XPL", 
            "XUL", 
            "XSLT", 
            "XQuery", 
            "yacc", 
            "Yorick", 
            "Z shell", 
            "Other" 
        };


    /* Private class variables */
        private static Random random_generator_object = new Random();

        /* private static String APP_DIR_PATH = "CodeSnippetsAccumulator"; */

        private static String APP_DIR_PATH = System.getProperty("filepath");

        private static final String APP_DATA_DIR = APP_DIR_PATH + File.separator 
                                                 + "data"       + File.separator;

        private static final int RANDOM_NUMBER_MIN = 100000000;
        private static final int RANDOM_NUMBER_MAX = 999999999;


    /* Private class functions */
        private static void checkOrCreateDir(String dirPathString)
        {
            try
            {
                if (!Files.isDirectory(Paths.get(dirPathString)))
                {
                    Files.createDirectory(Paths.get(dirPathString));
                }
            }
            catch (Exception exception_object)
            {
                System.out.println(exception_object.getMessage());
                System.exit(1);
            }
        }


    /* Public class functions */
        public static void checkOrCreateDataDirectory()
        {
            checkOrCreateDir(APP_DIR_PATH + FILE_SEPARATOR + "data");

            for (String languageField : languageFieldList)
            {
                checkOrCreateDir(APP_DIR_PATH + FILE_SEPARATOR + "data" 
                                              + FILE_SEPARATOR +  languageField.replaceAll(" ", "_"));
            }
        }

        public static void setFileContents(String filePathString, String fileContents, boolean mayCreateFile)
        {
            try
            {
                final File outFile = new File(filePathString);

                if (!outFile.exists())
                {
                    if (mayCreateFile)
                    {
                        if (!outFile.createNewFile())
                        {
                            System.out.println( "Error: Cannot create file '" + 
                                                 filePathString + 
                                                "'.");
                            System.exit(1);
                        }
                    }
                    else
                    {
                        System.out.println( "Error: File '" + 
                                             filePathString + 
                                            "' does not exist.");
                        System.exit(1);
                    }
                }

                final FileWriter writer = new FileWriter(outFile);

                writer.write(fileContents);
                writer.close();
            }
            catch (Exception exception_object)
            {
                System.out.println(exception_object.getMessage());
                System.exit(1);
            }
        }

        public static String getFileContents(String filePathString)
        {
            String fileContents = "";

            try
            {
                final Path filePath = Paths.get(filePathString);

                fileContents = new String(Files.readAllBytes(filePath.toAbsolutePath()));
            }
            catch (Exception exception_object)
            {
                System.out.println( "Error: File '" + 
                                     filePathString + 
                                    "' does not exist.");
                System.exit(1);
            }
            finally
            {
                return fileContents;
            }
        }

        public static ArrayList<String[]> searchForRequiredCode( String searchLanguage, 
                                                                 boolean isTitleSearchMode, 
                                                                 boolean isNotesSearchMode, 
                                                                 boolean isTagsSearchMode, 
                                                                 String searchText)
        {
            ArrayList<String[]> returnList = new ArrayList<String[]>();

            SearchData.idList.clear();

            SearchData.searchParameter1 = searchLanguage;
            SearchData.searchParameter2 = isTitleSearchMode;
            SearchData.searchParameter3 = isNotesSearchMode;
            SearchData.searchParameter4 = isTagsSearchMode;
            SearchData.searchParameter5 = searchText;

            for (String languageField : languageFieldList)
            {
                if (!searchLanguage.equals(languageField) && !searchLanguage.equals("All")) { continue; }

                try
                {
                    final File languageDir     = new File(APP_DATA_DIR + languageField.replaceAll(" ", "_"));
                    final String[] idFilesList = languageDir.list();

                    for (String idFile : idFilesList)
                    {
                        String[] idFileSeparated = idFile.split(FILE_SEPARATOR);
                        final String idBaseFile  = idFileSeparated [idFileSeparated.length - 1];

                        final String idTitle = getFileContents( languageDir + FILE_SEPARATOR + 
                                                                idBaseFile  + FILE_SEPARATOR + "title");

                        boolean somethingFound = false;

                        if (isTitleSearchMode)
                        {
                            if (searchText.contains(idTitle) || searchText.equals("")) { somethingFound = true; }
                        }
                        else if (isNotesSearchMode)
                        {
                            final String idNotes = getFileContents( languageDir + FILE_SEPARATOR + 
                                                                    idBaseFile  + FILE_SEPARATOR + "notes");

                            if (searchText.contains(idNotes) || searchText.equals("")) { somethingFound = true; }
                        }
                        else /* if (isTagsSearchMode) */
                        {
                            final String idTags  = getFileContents( languageDir + FILE_SEPARATOR + 
                                                                    idBaseFile  + FILE_SEPARATOR + "tags");

                            if (searchText.contains(idTags) || searchText.equals("")) { somethingFound = true; }
                        }

                        if (!somethingFound) { continue; }

                        SearchData.idList.add(Integer.parseInt(idBaseFile));

                        returnList.add(new String[] { String.valueOf(SearchData.idList.size()), idTitle, languageField });
                    }
                }
                catch (Exception exception_object)
                {
                    System.out.println(exception_object.getMessage());
                    System.exit(1);
                }
            }

            return returnList;
        }


    /* Auxiliary (inner nested) public classes */
        public static class EditWindowData
        {
            public static String language;
            public static String title;
            public static String code;
            public static String notes;
            public static String tags;

            public static int id;

            public static String saveLanguage;
            public static String saveTitle;
            public static String saveCode;
            public static String saveNotes;
            public static String saveTags;

            private static void deleteDirectoryRecursively(File rootDir)
            {
                for (File subFile : rootDir.listFiles())
                {
                    if (subFile.isDirectory())
                    {
                        deleteDirectoryRecursively(subFile);
                    }
                    subFile.delete();
                }
                rootDir.delete();
            }

            public static void delete()
            {
                File mainDir = new File(APP_DATA_DIR + language + FILE_SEPARATOR + id);

                deleteDirectoryRecursively(mainDir);
            }

            public static void reset(int newId)
            {
                id = newId;

                language = "";
                title    = "";
                code     = "";
                notes    = "";
                tags     = "";

                saveLanguage = "";
                saveTitle    = "";
                saveCode     = "";
                saveNotes    = "";
                saveTags     = "";
            }

            public static void loadDataFromFile(int fileId, String dirLanguage)
            {
                id       = fileId;
                language = dirLanguage;

                final String mainFilePath = APP_DATA_DIR 
                                          + dirLanguage + FILE_SEPARATOR 
                                          + fileId      + FILE_SEPARATOR;

                title = getFileContents(mainFilePath + "title");
                code  = getFileContents(mainFilePath + "code");
                notes = getFileContents(mainFilePath + "notes");
                tags  = getFileContents(mainFilePath + "tags");
            }

            public static void save()
            {
                String mainFilePath = APP_DATA_DIR + saveLanguage + FILE_SEPARATOR + id + FILE_SEPARATOR;

                try
                {
                    if (id == 0)
                    {
                        final String mainFileDir = APP_DATA_DIR + saveLanguage + FILE_SEPARATOR;
                        boolean fileNotCreated   = true;

                        while(fileNotCreated)
                        {
                            final int randomNumber = random_generator_object.nextInt(RANDOM_NUMBER_MAX - RANDOM_NUMBER_MIN + 1) 
                                                   + RANDOM_NUMBER_MIN;

                            final Path filePath    = Paths.get(mainFileDir + String.valueOf(randomNumber));

                            if (!Files.isDirectory(filePath))
                            {
                                mainFilePath   = mainFileDir + String.valueOf(randomNumber);
                                fileNotCreated = false;
                            }
                        }

                        Files.createDirectory(Paths.get(mainFilePath));

                        setFileContents(mainFilePath + FILE_SEPARATOR + "title", "", true);
                        setFileContents(mainFilePath + FILE_SEPARATOR + "code",  "", true);
                        setFileContents(mainFilePath + FILE_SEPARATOR + "notes", "", true);
                        setFileContents(mainFilePath + FILE_SEPARATOR + "tags",  "", true);
                    }

                    setFileContents(mainFilePath + FILE_SEPARATOR + "title", saveTitle, false);
                    setFileContents(mainFilePath + FILE_SEPARATOR + "code",  saveCode,  false);
                    setFileContents(mainFilePath + FILE_SEPARATOR + "notes", saveNotes, false);
                    setFileContents(mainFilePath + FILE_SEPARATOR + "tags",  saveTags,  false);
                }
                catch (Exception exception_object)
                {
                    System.out.println(exception_object.getMessage());
                    System.exit(1);
                }
            }

            public static boolean compare( String newLanguage, 
                                           String newTitle, 
                                           String newCode, 
                                           String newNotes, 
                                           String newTags)
            {
                saveLanguage = newLanguage;
                saveTitle    = newTitle;
                saveCode     = newCode;
                saveNotes    = newNotes;
                saveTags     = newTags;

                if (!newLanguage.equals(language)) { return false; }
                if (!newTitle.equals(title))       { return false; }
                if (!newCode.equals(code))         { return false; }
                if (!newNotes.equals(notes))       { return false; }
                if (!newTags.equals(tags))         { return false; }

                return true;
            }
        }

        public static class SearchData
        {
            private static String srNo     = null;
            private static String title    = null;
            private static String language = null;

            public static String  searchParameter1;
            public static boolean searchParameter2;
            public static boolean searchParameter3;
            public static boolean searchParameter4;
            public static String  searchParameter5;

            public static ArrayList<Integer> idList = new ArrayList<Integer>();
         
            public SearchData(String inputSrNo, String inputTitle, String inputLanguage)
            {
                srNo     = inputSrNo;
                title    = inputTitle;
                language = inputLanguage;
            }

            public String getSrNo()
            {
                return srNo;
            }

            public String getTitle()
            {
                return title;
            }

            public String getLanguage()
            {
                return language;
            }
        }
}

