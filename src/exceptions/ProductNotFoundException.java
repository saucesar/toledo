package exceptions;

public class ProductNotFoundException extends Exception{
    public String getMessage() {
        return "Produto não encontrado!";
    }
}
