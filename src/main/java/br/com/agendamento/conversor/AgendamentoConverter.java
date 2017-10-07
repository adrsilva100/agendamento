package br.com.agendamento.conversor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.agendamento.model.dto.AgendamentoDTO;
import br.com.agendamento.persistence.entity.Agendamento;

public class AgendamentoConverter {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public static Agendamento agendamentoFrom(AgendamentoDTO agendamentoDTO) {
		Agendamento agendamento = new Agendamento();
		
		agendamento.setContaOrigem(agendamentoDTO.getContaOrigem());
		agendamento.setContaDestino(agendamentoDTO.getContaDestino());
		agendamento.setValorTransferencia(agendamentoDTO.getValorTransferencia());
		agendamento.setDataTransferencia(LocalDate.parse(agendamentoDTO.getDataTransferencia(), formatter));
		
		validar(agendamento);
		return agendamento;
	}
	
	private static void validar(Agendamento agendamento){
		if (agendamento.getDataTransferencia() != null && agendamento.getDataTransferencia().isBefore(agendamento.getDataAgendamento())) {
			throw new IllegalArgumentException("Data de transferencia não pode ser menor que a data atual");
		}
		if (agendamento.getValorTransferencia() != null && agendamento.getValorTransferencia().compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Valor da transferência deve ser maior do que zero");
		}
	}
	
	public static List<AgendamentoDTO> listaAgendamentoDTOFrom(List<Agendamento> agendamentos) {
		
		List<AgendamentoDTO> agendamentosDTO = new ArrayList<AgendamentoDTO>();
		
		agendamentos.forEach(item->{
			AgendamentoDTO dto = new AgendamentoDTO();
			
			dto.setIdAgendamento(item.getIdAgendamento());
			dto.setContaDestino(item.getContaDestino());
			dto.setContaOrigem(item.getContaOrigem());
			dto.setDataTransferencia(formatter.format(item.getDataTransferencia()));
			dto.setTipoTransferencia(item.getTipoTransferencia());
			dto.setValorTransferencia(item.getValorTransferencia());
			dto.setTaxaTransferencia(item.getTaxaTransferencia());
			dto.setDataAgendamento(formatter.format(item.getDataAgendamento()));
			
			agendamentosDTO.add(dto);
		});
		
		return agendamentosDTO;
	}
}
