import static java.nio.file.StandardCopyOption.*
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Files

/**
 * Viola is a Groovy script that one can use to keep a given DIRECTORY always
 * clean by archiving its content. It is very useful for directories that get
 * overcrowded really easily e.g. Downloads and Temp directories.
 *
 * NOTE: Please read README.md for properly setting up this script
 *
 * @author Jatinder Singh on 01, August 2013 at 12:12 AM
 */
class Viola  {
    static void main(String[] args) {
        println "Running Viola. This can take few minutes. Please wait.."

        Archiver archiver = new ViolaArchiver()
        archiver.archive()

        println "Viola has cleaned your directory!"
    }
}

class ViolaArchiver implements Archiver {
    public static final String ARCHIVES_FOLDER_NAME = "/viola/"
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd_HH-mm-ss"
    public static final String VIOLA_PROPERTIES = "viola.properties"
    public static final String ARCHIVES = "Archives"

    @Override
    void archive() {
        def props = new Properties()
        new File(VIOLA_PROPERTIES).withInputStream { 
            stream -> props.load(stream) 
        }

        def config = new ConfigSlurper().parse(props)
        def folders = config.viola.dirs

        def violaFolder = new File(getUserHome() + ARCHIVES_FOLDER_NAME)
        if(!violaFolder.exists()) { violaFolder.mkdirs() }

        folders.split(',').collect { f ->
            String fName = f.substring(f.lastIndexOf('/') + 1)
            File folder = new File(f)

            if(folder.list().length > 0) {
                String thisFolderArchivePath = getUserHome() + ARCHIVES_FOLDER_NAME + fName + ARCHIVES 

                // Creates main archive folder
                def folderArchives = new File(thisFolderArchivePath)
                if(!folderArchives.exists()) { folderArchives.mkdirs() }

                // Creates folder to store current files
                String thisArchivePath = thisFolderArchivePath + "/" + fName + getTimestamp()
                def thisArchiveFolder = new File(thisArchivePath)
                if(!thisArchiveFolder.exists()) { thisArchiveFolder.mkdirs() }

                Path source = Paths.get(f)
                Path target = Paths.get(thisArchivePath)    
                Files.move(source, target, REPLACE_EXISTING)

                // Since we moved create folder
                if(!folder.exists()) { folder.mkdirs() }
            } 
        }
    }

    String getUserHome() {
        return System.getProperty("user.home") 
    }

    String getTimestamp() {
        return new Date().format(DATE_TIME_PATTERN)
    }
}

interface Archiver {
    void archive()
}