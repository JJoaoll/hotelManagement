public class Hotel {
    private Hospede[] listaHospedes =  {};  // Seria muito mais legal se os arrays pudessem ir se incrementando cada vez q necessário.
    private Quarto[] listaQuartos = {};
    private Reserva[] listaReservas = {};

    static boolean isDateBetween(Date data, Date firstDay, Date lastDay){
        boolean b = true;

        if(lastDay.isBeforeThan(data) || data.isBeforeThan(firstDay))
            b = false;

        return b;
    }


    // eu poderia 'typar' um intervalo.
    static boolean isIntervalBetween(Date firstTest, Date lastTest, Date firstDay, Date lastDay){
        boolean b = false;
        // Date test = firstTest;

        if(firstTest.isBeforeThan(lastDay.tomorrow()) && firstDay.isBeforeThan(lastTest.tomorrow()))
            b = true;


        /*if(test.isBeforeThan(lastDay) && ){
            // Eu fiz na pior otimização possível!!!
            while(test.isBeforeThan(lastTest)){
                if(isDateBetween(test, firstDay, lastDay)){
                    b = true;
                    break;
                }
                test.tomorrowToday();
            }
        }*/
        return b;
    }

    public void cadastrarHospede(String name, int phoneNumber, String email){
        int numH = listaHospedes.length;

        Hospede listaHospedesUpdt[] = new Hospede[numH + 1];
        System.arraycopy(listaHospedes, 0, listaHospedesUpdt, 0, numH);
        listaHospedes = listaHospedesUpdt;

        Hospede h = new Hospede(numH+1, name, phoneNumber, email);
        listaHospedes[numH] = h;
    }

    public void adicionarQuarto(Quarto newRoom){
        int numQ = listaQuartos.length;

        Quarto[] listaQuartosUpdt = new Quarto[numQ + 1]; // Como q vou poder escolher o numero de um quarto??
        System.arraycopy(listaQuartos, 0, listaQuartosUpdt, 0, numQ);
        listaQuartos = listaQuartosUpdt;

        listaQuartos[numQ] = newRoom;
    }

    public void fazerReserva(Reserva newReserva){
        int numR = listaReservas.length;

        Reserva listaReservasUpdt[] = new Reserva[numR + 1];
        System.arraycopy(listaReservas, 0, listaReservasUpdt, 0, numR);
        listaReservas = listaReservasUpdt;

        listaReservas[numR] = newReserva;
    }

    // Faltou implementar algo pra lidar com reservas já fechadas ou ainda abertas.
    public void safeReserva(int hospedeId, int numQ, Date checkIn, Date checkOut){
        boolean avaiable = verificarDisponibilidade(numQ, checkIn, checkOut);
        if(avaiable){
            // Criar uma reserva r.
            int reservaID = listaReservas.length + 1; // pra começar do 1.
            Reserva r = new Reserva(reservaID, hospedeId, numQ, checkIn, checkOut);
            fazerReserva(r);
            System.out.println("Reserva realizada com sucesso "); // Espero que seja temporário. Qria um tipo maybe, poxa.
        }
        else
            System.out.println("Desculpe, o quarto já está ocupado. ");
    }

    public boolean verificarDisponibilidade(int numeroQuarto, Date checkIn, Date checkOut){
        // MAS E SE A RESERVA EM QUESTÃO FOI CANCELADA??
        boolean isAvaiable = true;
        for (Reserva i : listaReservas) {
            if(i.getNumeroQuarto() == numeroQuarto)
                if(isIntervalBetween(checkIn, checkOut, i.getCheckIn(), i.getCheckOut())){
                    isAvaiable = false;
                    break;
                }

            //falta uma forma pra comparar duas datas, também falta uma forma pra conferir,
            //através disso ,se uma data está num intervalo de datas ou se não. - Será?
        }
        return isAvaiable;
    }

    //alocarQuartoOtimizado

    public Quarto[] quartosDisponiveis(Date checkIn, Date checkOut){
        Quarto[] qDisponiveisX = new Quarto[listaQuartos.length];
        int  cont = 0;
        // Bem que poderia retornar uma lista pra atendente falar opções
        // Outra coisa é que apenas recebendo a reserva não dá pra informar qnts pessoas vão num quarto.
        for(Quarto q : listaQuartos){
            if(this.verificarDisponibilidade(q.getNum(), checkIn, checkOut)){
                qDisponiveisX[cont] = q;
                cont++;
            }
        }

        Quarto[] qDisponiveisY = new Quarto[cont];
        System.arraycopy(qDisponiveisX, 0, qDisponiveisY, 0, cont);

        return qDisponiveisY;
    }

    public boolean quartoDoExist(int numQ){
        for(Quarto q : listaQuartos){
            if(q.getNum() == numQ)
                return true;
        }
        return false;
    }

    //decidi só mostrar quais quartos estão ocupados por enquanto.
    //mudei de ideia e preferi retornar um array de Strings.
    public String[] gerarRelatorioOcupacao(Date inicio, Date fim){
        String[] relatorio = {};
        int cont = 0;
        for(Reserva i : listaReservas){
            if(isIntervalBetween(i.checkIn, i.checkOut, inicio, fim)){
                relatorio[cont] = "id: "+ i.getId() + " hospedeId: "+ i.getHospedeId() + " numero do quarto: "+ i.getNumeroQuarto()
                        + " data de check-in"+ i.getCheckIn() + " data de check-out: "+ i.getCheckOut();
                cont++;
            }
        }
        return relatorio;
    }

    //Poxa, podia ser uma matriz, né??

    public String[] gerarRelatorioReservasHospede(int hospedeId){
        String[] relatorio = {};
        int cont = 0;

        for(Reserva i : listaReservas){
            if(i.getHospedeId() == hospedeId){
                relatorio[cont] = "id: "+ i.getId() + " numero do quarto: "+ i.getNumeroQuarto()
                        + " data de check-in"+ i.getCheckIn() + " data de check-out: "+ i.getCheckOut();
                cont++;
            }
        }

        //Tbm seria legal ordenar as reservas pelas mais recentes
        return relatorio;
    }

    public boolean conferirHospede(int idHospede){
        for(Hospede h : listaHospedes){
            if(h.getId() == idHospede)
                return true;
        }
        return false;
    }

    // gerar relatorio de hospedes

    public Hospede[] getListaHospedes() {
        return listaHospedes;
    }

    public Quarto[] getListaQuartos() {
        return listaQuartos;
    }

    public Reserva[] getListaReservas() {
        return listaReservas;
    }


}
