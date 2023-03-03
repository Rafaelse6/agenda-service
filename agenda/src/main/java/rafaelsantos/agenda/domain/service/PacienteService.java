package rafaelsantos.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rafaelsantos.agenda.domain.entity.Paciente;
import rafaelsantos.agenda.domain.exception.BusinessException;
import rafaelsantos.agenda.domain.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

   public Paciente salvar(Paciente paciente){
        boolean existeCpf = false;

       Optional<Paciente> optPaciente =repository.findByCpf(paciente.getCpf());

       if(optPaciente.isPresent()){
           if(!optPaciente.get().getId().equals(paciente.getId())){
               existeCpf = true;
           }
       }

       if(existeCpf){
           throw new BusinessException("Cpf já cadastrado");
       }

       return repository.save(paciente);
   }

   public List<Paciente> listarTodos(){
        return repository.findAll();
   }

    public Optional<Paciente> buscarPorId(Long id){
        return repository.findById(id);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
