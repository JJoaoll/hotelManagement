public class Quarto {
    final int num;
    private String type;
    private int capacity;
    private String status;

    public Quarto(int new_num, String new_type, int new_capacity){
        num = new_num;
        type = new_type;
        capacity = new_capacity;
        //status = new_status;
    }

    public int getNum() {
        return num;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void printQuarto(){
        System.out.println("Número do quarto: " + num);
        System.out.println("Type: " + type);
        System.out.println("Capacity: " + capacity);


    }




}
