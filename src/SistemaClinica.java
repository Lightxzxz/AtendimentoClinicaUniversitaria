
public class SistemaClinica {

    public static void main(String[] args) {
        imprimirCabecalho();

        FilaEncadeadaDePacientes fila = new FilaEncadeadaDePacientes();
        ListaDeAtendidos atendidos = new ListaDeAtendidos();

        adicionarPacientesFila(fila);
        atenderPacientes(fila, atendidos);
        consultarProximoPaciente(fila);
        exibirRelatorioAtendimentos(atendidos);
        testarPesquisas(atendidos);

        exibirRelatorioDiario(atendidos);

        exibirResumoFinal(fila, atendidos);
    }

    private static void imprimirCabecalho() {
        System.out.println("SISTEMA DE ATENDIMENTO - CLÍNICA UNIVERSITÁRIA");
        System.out.println("==================================================\n");
    }


    private static void adicionarPacientesFila(FilaEncadeadaDePacientes fila) {

        Paciente[] pacientes = {
                new Paciente("João Silva", 25, "Dor de cabeça", false),
                new Paciente("Maria Santos", 35, "Febre", false),
                new Paciente("Pedro Oliveira", 28, "Dor nas costas", false),
                new Paciente("Ana Costa", 22, "Gripe", false),
                new Paciente("Carlos Pereira", 45, "Pressão alta", true)
        };

        for (Paciente paciente : pacientes) {
            fila.enqueue(paciente);
        }

        fila.exibirFila();
    }

    private static void atenderPacientes(FilaEncadeadaDePacientes fila, ListaDeAtendidos atendidos) {

        final int PACIENTES_A_ATENDER = 3;

        for (int i = 1; i <= PACIENTES_A_ATENDER; i++) {
            System.out.println("--- Atendimento " + i + " ---");

            Paciente pacienteAtendido = fila.dequeue();

            if (pacienteAtendido != null) {
                simularConsulta(pacienteAtendido);

                atendidos.adicionarAtendido(pacienteAtendido);
            }
            System.out.println();
        }
    }


    private static void consultarProximoPaciente(FilaEncadeadaDePacientes fila) {

        Paciente proximo = fila.peek();
        if (proximo != null) {
            System.out.println(" Próximo paciente a ser atendido:");
            System.out.println("    " + proximo);
            System.out.println(" Posição na fila: 1º lugar");
        } else {
            System.out.println(" Não há pacientes na fila de espera.");
        }

        System.out.println();
        fila.exibirFila();
    }

    private static void exibirRelatorioAtendimentos(ListaDeAtendidos atendidos) {

        atendidos.exibirAtendidos();

        System.out.println(" " + atendidos.obterEstatisticas());
        System.out.println();
    }

    private static void testarPesquisas(ListaDeAtendidos atendidos) {

        String[] nomesPesquisa = {
                "Maria Santos",
                "João Silva",
                "Pedro Oliveira",
                "Ana Costa",
                "Roberto Lima",
                "carlos pereira"
        };

        System.out.println("Testando pesquisa por nome:");
        for (String nome : nomesPesquisa) {
            boolean foiAtendido = atendidos.foiAtendido(nome);
            String status = foiAtendido ? "  JÁ FOI ATENDIDO" : " NÃO FOI ATENDIDO";
            System.out.println("• " + nome + ": " + status);

            if (foiAtendido) {
                Paciente paciente = atendidos.buscarPaciente(nome);
                if (paciente != null) {
                    System.out.println("  → " + paciente);
                }
            }
        }
        System.out.println();
    }

    private static void exibirResumoFinal(FilaEncadeadaDePacientes fila, ListaDeAtendidos atendidos) {
        System.out.println("RESUMO FINAL DO DIA:");
        System.out.println("=======================");
        System.out.println("• Pacientes ainda na fila: " + fila.getTamanho());
        System.out.println("• Pacientes atendidos: " + atendidos.getTamanho());
        System.out.println("• Total de pacientes: " + (fila.getTamanho() + atendidos.getTamanho()));

        if (!fila.isEmpty()) {
            System.out.println("\n Pacientes ainda aguardando:");
            fila.exibirFila();
        } else {
            System.out.println("\n Todos os pacientes foram atendidos!");
        }

    }


    private static void simularConsulta(Paciente paciente) {
        System.out.println("  Realizando consulta de " + paciente.getNome() +
                " (Sintoma: " + paciente.getSintoma() + ")");

        String tipoConsulta;
        if (paciente.getIdade() < 30) {
            tipoConsulta = "Consulta rápida";
        } else if (paciente.getIdade() < 50) {
            tipoConsulta = "Consulta padrão";
        } else {
            tipoConsulta = "Consulta detalhada";
        }

        System.out.println(" Tipo: " + tipoConsulta + " - Paciente de " + paciente.getIdade() + " anos");
    }

    private static void exibirRelatorioDiario(ListaDeAtendidos atendidos) {
        System.out.println("--- RELATÓRIO DIÁRIO DE ATENDIMENTOS ---");

        if (atendidos.isEmpty()) {
            System.out.println("Nenhum paciente foi atendido hoje para gerar o relatório.");
            System.out.println("------------------------------------------\n");
            return;
        }

        // 1. Pega o total de pacientes atendidos
        int totalAtendidos = atendidos.getTamanho();

        // 2. Calcula a média de idade
        double mediaIdade = atendidos.calcularMediaIdade();

        // 3. Encontra o paciente mais idoso
        Paciente pacienteMaisIdoso = atendidos.encontrarPacienteMaisIdoso();

        System.out.println("Total de pacientes atendidos: " + totalAtendidos);
        System.out.printf("Média de idade: %.1f anos\n", mediaIdade); // Usando printf para formatar
        System.out.println("Paciente mais idoso: " + pacienteMaisIdoso); // Usa o toString() do Paciente
        System.out.println("------------------------------------------\n");
    }
}