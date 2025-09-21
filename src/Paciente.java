
public class Paciente {
    private String nome;
    private int idade;
    private String sintoma;


    public Paciente(String nome, int idade, String sintoma) {
        this.nome = nome;
        this.idade = idade;
        this.sintoma = sintoma;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }


    @Override
    public String toString() {
        return String.format("Paciente: %s, %d anos, Sintoma: %s",
                nome, idade, sintoma);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Paciente paciente = (Paciente) obj;
        return nome != null ? nome.equalsIgnoreCase(paciente.nome) : paciente.nome == null;
    }
}