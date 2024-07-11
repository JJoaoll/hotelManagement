/*
 Atributos: id, hospedeId, numeroQuarto, dataCheckIn, dataCheckOut
Métodos: construtor, getters e setters
 */
public class Reserva {
    final int id;
    final int hospedeId;
    private int numeroQuarto; //pode tornar o management do hotel mais flexível
    final Date checkIn;
    final Date checkOut;
    // o status usa isStatus ao invés de getStatus
    private boolean status; // true = reservado & false = cancelado
    //adicionei status pra permitir reservas cancelarem ou não.

    public Reserva(int newId, int newHospedeId, int newNumeroQuarto, Date newCheckIn, Date newCheckOut){
        id = newId;
        hospedeId = newHospedeId;
        //numeroQuarto = newNumeroQuarto;
        checkIn = newCheckIn;
        checkOut = newCheckOut;
        status = true;
    }

    public int getId() {
        return id;
    }

    public int getHospedeId() {
        return hospedeId;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public boolean isStatus() {
        return status;
    }

    public void setNumeroQuarto(int num){
        this.numeroQuarto = num;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void print(){
        System.out.println("id: "+ id);
        System.out.println("hospedeId: "+ hospedeId);
        System.out.println("numero do quarto: "+ numeroQuarto);
        System.out.println("data de check-in"+ checkIn.toString());
        System.out.println("data de check-out: "+ checkOut.toString());
        System.out.println("----------------------------------------------");
        // omitirei o status.
    }






}
