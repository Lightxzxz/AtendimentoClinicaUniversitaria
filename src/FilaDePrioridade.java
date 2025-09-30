public class FilaDePrioridade {
    private No inicio;
    private No fim;
    private int tamanho;

    public FilaDePrioridade() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    // Método auxiliar para verificar se um paciente tem prioridade
    private boolean temPrioridade(Paciente paciente) {
        return paciente.getIdade() > 60 || paciente.isUrgente();
    }

    public void enqueue(Paciente paciente) {
        No novoNo = new No(paciente);
        System.out.println("-> Chegou na fila: " + paciente);

        // CASO 1: A fila está vazia.
        if (inicio == null) {
            inicio = fim = novoNo;
            tamanho++;
            return;
        }

        // CASO 2: O novo paciente NÃO TEM prioridade.
        // Ele vai para o final da fila, como em uma fila normal.
        if (!temPrioridade(paciente)) {
            fim.proximo = novoNo;
            fim = novoNo;
            tamanho++;
            return;
        }

        // CASO 3: O novo paciente TEM prioridade.
        // Precisamos encontrar a posição correta para inseri-lo.

        // Se o primeiro da fila já não for prioridade, o novo paciente vira o primeiro.
        if (!temPrioridade(inicio.paciente)) {
            novoNo.proximo = inicio;
            inicio = novoNo;
            tamanho++;
            return;
        }

        // Procura pelo último paciente com prioridade na fila para inserir depois dele.
        // Isso mantém a ordem de chegada (FIFO) entre os prioritários.
        No atual = inicio;
        while (atual.proximo != null && temPrioridade(atual.proximo.paciente)) {
            atual = atual.proximo;
        }

        // Insere o novo paciente após o último prioritário encontrado.
        novoNo.proximo = atual.proximo;
        atual.proximo = novoNo;

        // Se o 'atual' era o último da fila, o novo agora é o fim.
        if (novoNo.proximo == null) {
            fim = novoNo;
        }

        tamanho++;
    }

    public Paciente dequeue() {
        if (inicio == null) {
            System.out.println("Fila vazia!");
            return null;
        }
        Paciente pacienteAtendido = inicio.paciente;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        System.out.println("Atendendo: " + pacienteAtendido.getNome());
        return pacienteAtendido;
    }

    public void exibirFila() {
        if (inicio == null) {
            System.out.println("Fila de prioridade vazia.");
            return;
        }
        System.out.println("\n===== FILA DE PRIORIDADE ATUAL =====");
        No atual = inicio;
        int posicao = 1;
        while (atual != null) {
            System.out.println(posicao + "º - " + atual.paciente);
            atual = atual.proximo;
            posicao++;
        }
        System.out.println("====================================\n");
    }
}