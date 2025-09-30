public class FilaCircularDePacientes {
    private Paciente[] pacientes;
    private int inicio;
    private int fim;
    private int tamanho;
    private int capacidade;

    public FilaCircularDePacientes(int capacidade) {
        this.capacidade = capacidade;
        this.pacientes = new Paciente[capacidade];
        this.inicio = 0; // Aponta para o primeiro elemento da fila
        this.fim = 0;    // Aponta para a próxima posição livre
        this.tamanho = 0;
    }

    public boolean isFull() {
        return tamanho == capacidade;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public void enqueue(Paciente paciente) {
        if (isFull()) {
            System.out.println("ERRO: A fila está cheia. Não foi possível adicionar " + paciente.getNome());
            return;
        }

        // Coloca o paciente na próxima posição livre (apontada por 'fim')
        pacientes[fim] = paciente;

        // A mágica circular: move o 'fim' para a próxima posição,
        // dando a volta para 0 se chegar ao final do array.
        fim = (fim + 1) % capacidade;

        tamanho++;
        System.out.println("Paciente adicionado à fila circular: " + paciente.getNome());
    }

    public Paciente dequeue() {
        if (isEmpty()) {
            System.out.println("Fila vazia! Nenhum paciente para atender.");
            return null;
        }

        // Pega o paciente do início da fila
        Paciente pacienteAtendido = pacientes[inicio];

        // Limpa a referência para ajudar o Garbage Collector
        pacientes[inicio] = null;

        // A mágica circular: move o 'inicio' para a próxima posição,
        // dando a volta para 0 se chegar ao final do array.
        inicio = (inicio + 1) % capacidade;

        tamanho--;
        System.out.println("Paciente chamado para consulta: " + pacienteAtendido.getNome());
        return pacienteAtendido;
    }

    public Paciente peek() {
        if (isEmpty()) {
            return null;
        }
        return pacientes[inicio];
    }

    public int getTamanho() {
        return tamanho;
    }

    public void exibirFila() {
        if (isEmpty()) {
            System.out.println("Fila de espera circular vazia.");
            return;
        }

        System.out.println("\nFILA DE ESPERA CIRCULAR ATUAL:");
        System.out.println("===============================");

        // Itera do início da fila, dando a volta se necessário
        for (int i = 0; i < tamanho; i++) {
            int indiceReal = (inicio + i) % capacidade;
            System.out.println((i + 1) + "º - " + pacientes[indiceReal]);
        }
        System.out.println("Total na fila: " + tamanho + " pacientes\n");
    }
}