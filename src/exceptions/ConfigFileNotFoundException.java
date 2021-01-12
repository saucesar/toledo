package exceptions;

public class ConfigFileNotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "Arquivo de configuração não econtrado!";
    }
}
