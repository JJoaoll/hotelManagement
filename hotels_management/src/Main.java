import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;


/* Claro! Vou explicar o que deve ser feito no método main para testar e usar o sistema de gerenciamento de reservas de hotel:

    Adicionar Quartos: Use o método adicionarQuarto da classe Hotel para adicionar alguns quartos ao sistema. 
    Crie instâncias da classe Quarto e passe essas instâncias para o método de adicionar quarto.

    Fazer Reservas: Utilize o método fazerReserva para realizar algumas reservas. 
    Crie instâncias da classe Reserva com os detalhes da reserva (ID do hóspede, número do quarto, datas de check-in e check-out) e 
    passe essas instâncias para o método de fazer reserva.

    Verificar Disponibilidade de Quartos: Utilize o método verificarDisponibilidade para checar se um quarto específico está disponível 
    em um determinado período.

    Gerar Relatórios:
        Relatório de Ocupação: Utilize o método gerarRelatorioOcupacao para gerar e imprimir um relatório de ocupação do hotel para um 
        período específico.
        Relatório de Reservas por Hóspede: Utilize o método gerarRelatorioReservasHospede para gerar e imprimir um relatório das reservas
        de um determinado hóspede.

    Interação com o Usuário: Adicione lógica para interagir com o usuário, permitindo que ele escolha as opções de cadastrar hóspedes,
    adicionar quartos, fazer reservas, verificar disponibilidade e gerar relatórios. Isso pode ser feito usando uma interface simples de
    console com um loop que exibe um menu de opções.

  Ao implementar esses passos no método main, você terá um sistema funcional que pode ser testado e usado para gerenciar reservas de hotel.
A interação básica no console ajudará a entender como cada parte do sistema funciona e permite expandir 
as funcionalidades conforme necessário
*/

public class Main {

    static void hospedeCadastro(Hotel h){
        Scanner scn = new Scanner(System.in);

        System.out.println("Digite seu nome ");
        String nome = scn.nextLine();
        System.out.println("Digite seu telefone ");
        int telefone = scn.nextInt();
        scn.nextLine();
        System.out.println("Digite seu email ");
        String email = scn.nextLine();


        h.cadastrarHospede(nome, telefone, email);

    }

    static void adicionarQuarto(Hotel h){
        Scanner scn = new Scanner(System.in);

        System.out.println("Informe o numero do quarto");
        int numQuarto = scn.nextInt();
        scn.nextLine();
        System.out.println("Informe o tipo do quarto");
        String tipoQuarto = scn.nextLine();
        System.out.println("Informe a capacidade do quarto");
        int capacidadeQuarto = scn.nextInt();
        scn.nextLine();

        Quarto q = new Quarto(numQuarto, tipoQuarto, capacidadeQuarto);
        h.adicionarQuarto(q);
    }

    static Date lerData(String dateName){
        Scanner scn = new Scanner(System.in);

        System.out.println("Informe o dia de "+ dateName +"desejado: ");
        // como ler uma data bonitinha?
        byte myDay = scn.nextByte();
        scn.nextLine();

        System.out.println("Informe o mes de "+ dateName +"desejado: ");
        // como ler uma data bonitinha?
        byte myMon = scn.nextByte();
        scn.nextLine();


        System.out.println("Informe o ano de "+ dateName +"desejado: ");
        // como ler uma data bonitinha?
        short myYear = scn.nextByte();
        scn.nextLine();

        return new Date(myDay, myMon, myYear);
    }


    static void reservarQuarto(Hotel h){
        Scanner scn = new Scanner(System.in);

        System.out.println("Informe o id do hospede");
        int id_hospede = scn.nextInt();
        scn.nextLine();

           if((!(h.conferirHospede(id_hospede)))||(!(id_hospede > 0))){
            if(!(h.conferirHospede(id_hospede)))
                System.out.println("Id não registrado. Voltando ao menu principal...");
            else
                System.out.println("Id inválido. Voltando ao menu principal...");
            return;
        }

        String nome_hospede = h.getListaHospedes()[id_hospede - 1].getNome();

        System.out.println("Informe seu nome ");
        String nome_teste = scn.nextLine();

        if(nome_teste.equals(nome_hospede)){
            System.out.println("Confirmação fake de telefone/email concluída.");

            Date checkIn = lerData("checkIn");
            Date checkOut =lerData("checkOut");

            System.out.println("Escolha a opção desejada: ");
            System.out.println("1 - Reserva otimiziada ");
            System.out.println("2 - Reserva manual");
            int opc = scn.nextInt();
            scn.nextLine();

            switch(opc){
                case 1: // Reserva otimizada


                    Quarto[] qDisponiveis = h.quartosDisponiveis(checkIn,checkOut);
                    // Dar opção ao cliente.
                    // Por hora eu vou só registrar no primeiro quarto da lista.
                    //System.out.println(Arrays.toString(qDisponiveis));
                    Quarto meuQuarto = qDisponiveis[0];
                    h.safeReserva(id_hospede,meuQuarto.getNum(),checkIn, checkOut);


                    // perguntar intervalo de tempo apenas
                    break;
                case 2: // Reserva manual
                    System.out.println("Informe o numero do quarto desejado. ");
                    int numQ = scn.nextInt();
                    scn.nextLine();

                    h.safeReserva(id_hospede,numQ,checkIn, checkOut);

                    break;
                    // 
                default:
                    System.out.println("Opção inválida, voltando ao menu principal.");

            }

        }
        else{
            System.out.println("O nome informado não bate com o id.");
            System.out.println("Retornando ao menu.");
        }



    }
    // Verificar Disponibilidade de Quartos: Utilize o método verificarDisponibilidade para checar se um quarto específico está disponível
    //    em um determinado período.
    static void verificarDisponibilidade(Hotel h){
        Scanner scn = new Scanner(System.in);

        System.out.println("Informe o numero do quarto desejado. ");
        int numQ = scn.nextInt();
        scn.nextLine();

        if(!(h.quartoDoExist(numQ))){
            System.out.print("Erro! quarto não cadastrado.");
            System.out.print("Retornando ao menu principal...");
            return;
        }

        Date checkIn = lerData("checkIn");
        Date checkOut = lerData("checkOut");

        if(h.verificarDisponibilidade(numQ, checkIn, checkOut)){
            System.out.println("Está disponível");
        }
        else{
            System.out.println("Não está disponível");
        }
    }

    static void gerarRelatorio(Hotel h){
        Scanner scn = new Scanner(System.in);

        System.out.println("Escolha a opção desejada: ");
        System.out.println("1 - Relatorio de ocupacao p/ periodo ");
        System.out.println("2 - Relatorio de reservas p/ hospede");
        byte opc = scn.nextByte();
        scn.nextLine();

        switch(opc){
            case 1:
                Date checkIn = lerData("CheckIn");
                Date checkOut = lerData("CheckOut");

                String[] relatorioO = h.gerarRelatorioOcupacao(checkIn,checkOut);
                System.out.println("relatorio: ");
                System.out.println(Arrays.toString(relatorioO));

            case 2:
                System.out.println("Digite um id ");
                int id = scn.nextInt();
                scn.nextLine();

                System.out.println("relatorio: ");
                String[] relatorioH = h.gerarRelatorioReservasHospede(id);
                System.out.println(Arrays.toString(relatorioH));

                break;
            default:


        }
    }


    public static void main(String[] args) {

        //Instanciar o Hotel: Crie uma instância da classe Hotel.
        Hotel hotel_01 = new Hotel();
        byte opc;
        Hospede h;
        Scanner scn = new Scanner(System.in);

        do {
            System.out.println("Escolha uma das opcoes abaixo.");
            System.out.println("0 - Fechar programa");
            System.out.println("1 - Cadastrar hospedes");
            System.out.println("2 - Adicionar quartos");
            System.out.println("3 - Fazer reservas");
            System.out.println("4 - Verificar disponibilidade");
            System.out.println("5 - Gerar relatorios");

            opc = scn.nextByte();
            scn.nextLine();

            switch(opc){
                case 0:
                    System.out.println("Encerrando o programa. Obrigado.");
                    break;
                case 1:
                    hospedeCadastro(hotel_01);
                    System.out.println("Cadastro realizado com sucesso. "); //como conferir isso?                    
                    break;
                case 2:
                    adicionarQuarto(hotel_01);
                    System.out.println("Cadastro realizado com sucesso. "); //como conferir isso?                   
                    break;
                case 3:
                    reservarQuarto(hotel_01);
                    break;
                case 4:
                    verificarDisponibilidade(hotel_01);
                    break;
                case 5:
                    gerarRelatorio(hotel_01);
                    break;

                default:
                    System.out.println("Erro! Opçao invalida.");
            }

        } while (opc != 0);
        //Interação com o Usuário: Adicione lógica para interagir com o usuário, permitindo que ele escolha as opções de cadastrar hóspedes,
        //adicionar quartos, fazer reservas, verificar disponibilidade e gerar relatórios. Isso pode ser feito usando uma interface simples de
        //console com um loop que exibe um menu de opções.


        //Cadastrar Hóspedes: Utilize o método cadastrarHospede da classe Hotel para adicionar alguns hóspedes ao sistema. 
        //Você precisará criar instâncias da classe Hospede e passar essas instâncias para o método de cadastro.
    }
}

//Implementar o main!!
