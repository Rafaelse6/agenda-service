package rafaelsantos.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rafaelsantos.agenda.domain.entity.Agenda;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    Optional<Agenda> findByHorario(LocalDateTime horario);
}
