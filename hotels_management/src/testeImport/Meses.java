package testeImport;

public class Meses {
    final int jan = 31;
    final int fev = 27;
    final int mar = 30;

    public int getJan() {
        return jan;
    }

    public int getMar() {
        return mar;
    }

    public int getFev() {
        return fev;
    }

    public Meses() {

    }

    public void escrevaJan(){
        System.out.println("January: " + jan);
    }
}
