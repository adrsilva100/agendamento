package br.com.agendamento.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.agendamento.config.AgendamentoApplication;
import br.com.agendamento.model.dto.AgendamentoDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AgendamentoApplication.class })
@WebAppConfiguration
@Transactional
public class AgendamentoServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	private AgendamentoService agendamentoService;

	private AgendamentoDTO agendamentoDTO;
	
	@Before
	public void before(){
		agendamentoDTO = new AgendamentoDTO();
		agendamentoDTO.setContaOrigem("123456");
		agendamentoDTO.setContaDestino("654321");
		agendamentoDTO.setValorTransferencia(BigDecimal.valueOf(100l));
		agendamentoDTO.setDataTransferencia(formatter.format(LocalDate.now()));
	}	
	
	@Test
	public void realizarAgendamento(){
		agendamentoService.realizarAgendamento(agendamentoDTO);
	}

	@Test
	public void retornarListaAgendamentos(){
		agendamentoService.realizarAgendamento(agendamentoDTO);
		agendamentoService.realizarAgendamento(agendamentoDTO);
		
		Assert.assertEquals(2, agendamentoService.findAll().size());
	}
}
