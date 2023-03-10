package rafaelsantos.agenda.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String cpf;
}

