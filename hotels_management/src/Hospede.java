    public class Hospede{
        final int id;
        private String nome;
        private int telefone;
        private String email;

        public Hospede(int id_fixo, String nome_inical, int telefone_inicial, String email_inicial){

            id = id_fixo;
            // could be nice if the id usage verify was here. Anyway, meanwhile i will do it on the main class.

            nome = nome_inical;
            telefone = telefone_inicial;
            email = email_inicial;
        }

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public int getTelefone() {
            return telefone;
        }

        public String getEmail() {
            return email;
        }

        public void setNome(String new_nome) {
            this.nome = new_nome;
        }

        public void setTelefone(int new_telefone) {
            this.telefone = new_telefone;
        }

        public void setEmail(String new_email) {
            this.email = new_email;
        }

        public void printHospede(){
            System.out.println("Id: "+ id);
            System.out.println("Nome: " + nome);
            System.out.println("Telefone: " + telefone);
            System.out.println("Email: " + email);
        }

    }
