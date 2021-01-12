package models;

public class Product {
    private String code;
    private String description;
    private String fileLine;
    private double price;
    private int daysOfValidity;
    private boolean withDate;

    public Product(String fileLine) {
        this.code = String.valueOf(Integer.parseInt(fileLine.substring(5, 9)));
        this.price = Double.parseDouble(fileLine.substring(9, 13) + "." + fileLine.substring(13, 15));
        this.daysOfValidity = Integer.parseInt(fileLine.substring(16, 18));
        this.description = fileLine.substring(18, 68).trim();
        this.withDate = (fileLine.substring(84, 86).equals("11"));
        this.fileLine = fileLine;
    }

    @Override
    public String toString() {
        return "CÓDIGO: "+this.code+
                " DESCRIÇÃO: "+this.description+
                " PREÇO: R$ "+this.price+
                " VALIDADES: "+this.daysOfValidity+" DIA(S)"+
                " INCLUIR DATA: "+this.withDate;
    }

    public String getDescription() { return description; }

    public int getDaysOfValidity() { return daysOfValidity; }

    public void setCode(String cd) {
        this.code = cd;

        while(cd.length() < 4){ cd = ("0"+cd); }

        String partOne = this.fileLine.substring(0, 5);
        String partTwo = this.fileLine.substring(9);
        this.fileLine = partOne+cd+partTwo;
    }

    public void setDaysOfValidity(int daysOfValidity) {
        String days = String.valueOf(daysOfValidity);

        while(days.length() < 3){ days = ("0"+days); }

        String partOne = this.fileLine.substring(0, 15);
        String partTwo = this.fileLine.substring(18);
        this.fileLine = partOne+days+partTwo;
        this.daysOfValidity = daysOfValidity;
    }

    public String getFileLine() { return this.fileLine; }

    public String getCode() { return code; }

    public void setDescription(String desc) {
        while (desc.length() > 49){
            desc = desc.substring(0, desc.length()-1);
        }
        while (desc.length() <= 49){
            desc += " ";
        }

        String partOne = this.fileLine.substring(0, 18);
        String partTwo = this.fileLine.substring(68);
        this.fileLine = partOne+desc+partTwo;
        this.description = desc;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) {
        String pricestr = String.format("%.2f",price).replace('.', ' ').replaceAll("\\s","");

        while(pricestr.length() < 6){ pricestr = ("0" + pricestr); }

        String partOne = this.fileLine.substring(0, 9);
        String partTwo = this.fileLine.substring(15);
        this.fileLine = partOne+pricestr+partTwo;
        this.price = price;
    }

    public boolean isWithDate() {
        return withDate;
    }

    public void setWithDate(boolean withDate) {
        String partOne = this.fileLine.substring(0, 84);
        String partTwo = this.fileLine.substring(86);

        this.fileLine = partOne+(withDate ? "11" : "10")+partTwo;
        this.withDate = withDate;
    }
}
