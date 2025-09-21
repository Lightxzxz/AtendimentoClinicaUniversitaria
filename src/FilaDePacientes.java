
public class FilaDePacientes {
    private No inicio;
    private No fim;
    private int tamanho;


    public FilaDePacientes() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }


    public void enqueue(Paciente paciente) {
        No novoNo = new No(paciente);

        if (fim == null) {
            inicio = fim = novoNo;
        } else {
            fim.proximo = novoNo;
            fim = novoNo;
        }
        tamanho++;
        System.out.println(" Paciente adicionado à fila: " + paciente.getNome());
    }


    public Paciente dequeue() {
        if (inicio == null) {
            System.out.println(" Fila vazia! Nenhum paciente para atender.");
            return null;
        }

        Paciente pacienteAtendido = inicio.paciente;
        inicio = inicio.proximo;

        if (inicio == null) {
            fim = null;
        }

        tamanho--;
        System.out.println(" Paciente chamado para consulta: " + pacienteAtendido.getNome());
        return pacienteAtendido;
    }


    public Paciente peek() {
        if (inicio == null) {
            System.out.println(" Fila vazia!");
            return null;
        }
        return inicio.paciente;
    }


    public boolean isEmpty() {
        return inicio == null;
    }


    public int getTamanho() {
        return tamanho;
    }


    public void exibirFila() {
        if (inicio == null) {
            System.out.println(" Fila de espera vazia.");
            return;
        }

        System.out.println("\n FILA DE ESPERA ATUAL:");
        System.out.println("========================");
        No atual = inicio;
        int posicao = 1;

        while (atual != null) {
            System.out.println(posicao + "º - " + atual.paciente);
            atual = atual.proximo;
            posicao++;
        }
        System.out.println("Total na fila: " + tamanho + " pacientes\n");
    }


    public void limparFila() {
        inicio = null;
        fim = null;
        tamanho = 0;
        System.out.println("Fila de espera limpa!");
    }


    public boolean contemPaciente(String nomePaciente) {
        No atual = inicio;
        while (atual != null) {
            if (atual.paciente.getNome().equalsIgnoreCase(nomePaciente)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }
}