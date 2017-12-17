package Utilities;

public class GeneralUtilities {

    /**
     * General utilities constructor
     */

    public GeneralUtilities(){

    }

    /**
     * Gets OS name
     * @return name of the OS
     */

    private String getOSname(){
        return System.getProperty("os.name");
    }

    /**
     * Gets the character separator
     * @return separator character for the OS
     */

    public String getOSseparator(){
        String sep = "";
        if (getOSname().startsWith("Windows")){
            sep = "\\";
        } else if(getOSname().startsWith("Linux")){
            sep = "/";
        }
        return sep;
    }
}
