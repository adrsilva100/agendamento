package br.com.agendamento.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agendamento.persistence.entity.Agendamento;

@Repository
public interface AgendamentoDAO extends JpaRepository<Agendamento, Long>{

}
