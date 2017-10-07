package br.com.agendamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.agendamento.conversor.AgendamentoConverter;
import br.com.agendamento.model.dto.AgendamentoDTO;
import br.com.agendamento.persistence.dao.AgendamentoDAO;
import br.com.agendamento.persistence.entity.Agendamento;

@Component
public class AgendamentoService {
	
	@Autowired
	private AgendamentoDAO agendamentoDAO;
	
	public void realizarAgendamento(AgendamentoDTO agendamentoDTO){
		Agendamento agendamento = AgendamentoConverter.agendamentoFrom(agendamentoDTO);
		agendamento.calcularTaxa();
		agendamentoDAO.save(agendamento);
	}
	
	public List<AgendamentoDTO> findAll(){
		return AgendamentoConverter.listaAgendamentoDTOFrom(agendamentoDAO.findAll());
	}
}
