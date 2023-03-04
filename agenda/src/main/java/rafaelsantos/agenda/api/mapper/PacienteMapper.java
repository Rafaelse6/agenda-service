package rafaelsantos.agenda.api.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import rafaelsantos.agenda.api.request.PacienteRequest;
import rafaelsantos.agenda.api.response.PacienteResponse;
import rafaelsantos.agenda.domain.entity.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PacienteMapper {

    private final ModelMapper mapper;

    public Paciente toPaciente(PacienteRequest request) {
        return mapper.map(request, Paciente.class);
    }

    public PacienteResponse toPacienteResponse(Paciente paciente) {
        return mapper.map(paciente, PacienteResponse.class);
    }

    public List<PacienteResponse> toPacienteReponseList(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(this::toPacienteResponse)
                .collect(Collectors.toList());
    }
}
