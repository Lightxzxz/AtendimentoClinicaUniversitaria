
public class ListaDeAtendidos {
    private Paciente[] atendidos;
    private int tamanho;
    private int capacidade;

    private static final int CAPACIDADE_INICIAL = 10;
    private static final int FATOR_CRESCIMENTO = 2;


    public ListaDeAtendidos() {
        this.capacidade = CAPACIDADE_INICIAL;
        this.atendidos = new Paciente[capacidade];
        this.tamanho = 0;
    }


    public ListaDeAtendidos(int capacidadeInicial) {
        this.capacidade = capacidadeInicial > 0 ? capacidadeInicial : CAPACIDADE_INICIAL;
        this.atendidos = new Paciente[this.capacidade];
        this.tamanho = 0;
    }


    private void redimensionar() {
        if (tamanho >= capacidade) {
            capacidade *= FATOR_CRESCIMENTO;
            Paciente[] novoArray = new Paciente[capacidade];


            for (int i = 0; i < tamanho; i++) {
                novoArray[i] = atendidos[i];
            }

            atendidos = novoArray;
            System.out.println(" Array redimensionado para capacidade: " + capacidade);
        }
    }


    public void adicionarAtendido(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Não é possível adicionar paciente nulo!");
            return;
        }

        redimensionar();
        atendidos[tamanho] = paciente;
        tamanho++;
        System.out.println(" Paciente adicionado à lista de atendidos: " + paciente.getNome());
    }


    public void exibirAtendidos() {
        if (tamanho == 0) {
            System.out.println(" Nenhum paciente foi atendido hoje.");
            return;
        }

        System.out.println("\n PACIENTES ATENDIDOS HOJE:");
        System.out.println("==============================");
        for (int i = 0; i < tamanho; i++) {
            System.out.println((i + 1) + ". " + atendidos[i]);
        }
        System.out.println("Total de atendidos: " + tamanho + " pacientes\n");
    }


    public boolean foiAtendido(String nomePaciente) {
        if (nomePaciente == null || nomePaciente.trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < tamanho; i++) {
            if (atendidos[i].getNome().equalsIgnoreCase(nomePaciente.trim())) {
                return true;
            }
        }
        return false;
    }


    public Paciente obterPaciente(int indice) {
        if (indice < 0 || indice >= tamanho) {
            System.out.println(" Índice inválido: " + indice);
            return null;
        }
        return atendidos[indice];
    }


    public int getTamanho() {
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }


    public void limparLista() {
        for (int i = 0; i < tamanho; i++) {
            atendidos[i] = null;
        }
        tamanho = 0;
        System.out.println(" Lista de atendidos limpa!");
    }


    public Paciente buscarPaciente(String nomePaciente) {
        if (nomePaciente == null || nomePaciente.trim().isEmpty()) {
            return null;
        }

        for (int i = 0; i < tamanho; i++) {
            if (atendidos[i].getNome().equalsIgnoreCase(nomePaciente.trim())) {
                return atendidos[i];
            }
        }
        return null;
    }

    public String obterEstatisticas() {
        double percentualUso = capacidade > 0 ? (tamanho * 100.0 / capacidade) : 0;
        return String.format("Lista: %d/%d pacientes (%.1f%% de uso)",
                tamanho, capacidade, percentualUso);
    }
}