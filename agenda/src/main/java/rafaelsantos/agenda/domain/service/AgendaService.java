package rafaelsantos.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rafaelsantos.agenda.domain.entity.Agenda;
import rafaelsantos.agenda.domain.entity.Paciente;
import rafaelsantos.agenda.domain.exception.BusinessException;
import rafaelsantos.agenda.domain.repository.AgendaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository repository;
    private final PacienteService pacienteService;

    public List<Agenda> listarTodos(){
        return repository.findAll();
    }

    public Optional<Agenda> buscarPorId(Long id){
        return repository.findById(id);
    }
    public Agenda salvar(Agenda agenda){
        Optional<Paciente> optPaciente = pacienteService.buscarPorId(agenda.getPaciente().getId());

        if(optPaciente.isEmpty()){
            throw new BusinessException("Paciente não encontrado");
        }

        Optional<Agenda> optHorario = repository.findByHorario(agenda.getHorario());

        if(optHorario.isPresent()){
            throw new BusinessException("Já existe agendamento para este horário");
        }

        agenda.setPaciente(optPaciente.get());
        agenda.setDataCriacao(LocalDateTime.now());

        return repository.save(agenda);
    }
}
