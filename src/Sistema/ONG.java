package Sistema;

public class ONG extends Organizacao {

    String name;
    public ONG(String name) {
        super(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
