package exceptions;

public class FileNotFoundException extends Exception{
    private String file;
    public FileNotFoundException(String file) {
        this.file = file;
    }
    public String getMessage(){
        return "O arquivo \""+this.file+"\" não foi encontrado!";
    }
}
