package rafaelsantos.agenda.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String sobrenome;
    private String email;
    private String cpf;
}
