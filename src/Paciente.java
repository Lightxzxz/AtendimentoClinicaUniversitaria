
public class Paciente {
    private String nome;
    private int idade;
    private String sintoma;
    private boolean urgente; // NOVO CAMPO


    public Paciente(String nome, int idade, String sintoma, boolean urgente) {
        this.nome = nome;
        this.idade = idade;
        this.sintoma = sintoma;
        this.urgente = urgente;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getSintoma() {
        return sintoma;
    }
    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public String toString() {
        String statusUrgencia = urgente ? " [URGENTE]" : "";
        return String.format("Paciente: %s, %d anos, Sintoma: %s%s",
                nome, idade, sintoma, statusUrgencia);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Paciente paciente = (Paciente) obj;
        return nome != null ? nome.equalsIgnoreCase(paciente.nome) : paciente.nome == null;
    }
}