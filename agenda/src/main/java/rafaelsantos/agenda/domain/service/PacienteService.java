package rafaelsantos.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rafaelsantos.agenda.domain.entity.Paciente;
import rafaelsantos.agenda.domain.exception.BusinessException;
import rafaelsantos.agenda.domain.exception.DatabaseException;
import rafaelsantos.agenda.domain.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {
        boolean existeCpf = false;

        Optional<Paciente> optPaciente = repository.findByCpf(paciente.getCpf());

        if (optPaciente.isPresent()) {
            if (!optPaciente.get().getId().equals(paciente.getId())) {
                existeCpf = true;
            }
        }

        if (existeCpf) {
            throw new BusinessException("Cpf já cadastrado");
        }

        return repository.save(paciente);
    }

    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id);

    }

    public Paciente alterar(Long id, Paciente paciente) {
        try{Optional<Paciente> optPaciente = this.buscarPorId(id);

        if (optPaciente.isEmpty()) {
            throw new BusinessException("Paciente não cadastrado!");
        }

        paciente.setId(id);

        return salvar(paciente);}
        catch (BusinessException e){
            throw new BusinessException("Id não encontrado" + id);
        }
    }

    public void deletar(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new BusinessException("Id não encontrado: " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade");
        }
    }
}
