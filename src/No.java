
public class No {
    Paciente paciente;
    No proximo;


    public No(Paciente paciente) {
        this.paciente = paciente;
        this.proximo = null;
    }


    public No(Paciente paciente, No proximo) {
        this.paciente = paciente;
        this.proximo = proximo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }


    public boolean temProximo() {
        return proximo != null;
    }
}