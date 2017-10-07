package br.com.agendamento.conversor;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.agendamento.model.dto.AgendamentoDTO;

public class AgendamentoConverterTest {

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static ValidatorFactory validatorFactory;
	private static Validator validator;

	@BeforeClass
	public static void createValidator() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	@AfterClass
	public static void close() {
		validatorFactory.close();
	}

	@Test
	public void dataDeTransferenciaEqualDataDeHoje() {
		AgendamentoDTO agendamentoDTO = new AgendamentoDTO();

		agendamentoDTO.setDataTransferencia(formatter.format(LocalDate.now()));
		AgendamentoConverter.agendamentoFrom(agendamentoDTO);
	}

	@Test
	public void dataDeTransferenciaNula(){
		AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
		agendamentoDTO.setContaOrigem("123456");
		agendamentoDTO.setContaDestino("123456");
		agendamentoDTO.setValorTransferencia(BigDecimal.valueOf(1002l));
		
		Set<ConstraintViolation<AgendamentoDTO>> constraintViolations = validator.validate(agendamentoDTO);

		assertTrue(!constraintViolations.isEmpty());
	}
	
	@Test
	public void contaOrigemNula(){
		AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
		agendamentoDTO.setContaDestino("123456");
		agendamentoDTO.setValorTransferencia(BigDecimal.valueOf(1002l));
		agendamentoDTO.setDataTransferencia(formatter.format(LocalDate.now()));
		
		Set<ConstraintViolation<AgendamentoDTO>> constraintViolations = validator.validate(agendamentoDTO);

		assertTrue(!constraintViolations.isEmpty());
	}
	
	@Test
	public void contaDestinoNula(){
		AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
		agendamentoDTO.setContaOrigem("123456");
		agendamentoDTO.setValorTransferencia(BigDecimal.valueOf(1002l));
		agendamentoDTO.setDataTransferencia(formatter.format(LocalDate.now()));
		
		Set<ConstraintViolation<AgendamentoDTO>> constraintViolations = validator.validate(agendamentoDTO);

		assertTrue(!constraintViolations.isEmpty());
	}
	
	@Test
	public void valorDeTransferenciaNula(){
		AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
		agendamentoDTO.setContaDestino("123456");
		agendamentoDTO.setContaOrigem("123456");
		agendamentoDTO.setDataTransferencia(formatter.format(LocalDate.now()));
		
		Set<ConstraintViolation<AgendamentoDTO>> constraintViolations = validator.validate(agendamentoDTO);

		assertTrue(!constraintViolations.isEmpty());
	}
	
	@Test
	public void idAgendamentoPreenchido(){
		AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
		agendamentoDTO.setIdAgendamento(1l);
		agendamentoDTO.setContaDestino("123456");
		agendamentoDTO.setContaOrigem("123456");
		agendamentoDTO.setValorTransferencia(BigDecimal.valueOf(1002l));
		agendamentoDTO.setDataTransferencia(formatter.format(LocalDate.now()));
		
		Set<ConstraintViolation<AgendamentoDTO>> constraintViolations = validator.validate(agendamentoDTO);

		assertTrue(!constraintViolations.isEmpty());
	}

	@Test
	public void dataDeTransferenciaMaiorQueDataDeHoje() {
		AgendamentoDTO agendamentoDTO = new AgendamentoDTO();

		agendamentoDTO.setDataTransferencia(formatter.format(LocalDate.now().plus(10l, ChronoUnit.DAYS)));
		AgendamentoConverter.agendamentoFrom(agendamentoDTO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void dataDeTransferenciaMenorQueDataDeHoje() {
		AgendamentoDTO agendamentoDTO = new AgendamentoDTO();

		agendamentoDTO.setDataTransferencia(formatter.format(LocalDate.now().minus(10l, ChronoUnit.DAYS)));
		AgendamentoConverter.agendamentoFrom(agendamentoDTO);
	}

	@Test
	public void valorDeTransferenciaMaiorQueZero() {
		AgendamentoDTO agendamentoDTO = new AgendamentoDTO();

		agendamentoDTO.setDataTransferencia(formatter.format(LocalDate.now()));
		agendamentoDTO.setValorTransferencia(BigDecimal.valueOf(100l));
		AgendamentoConverter.agendamentoFrom(agendamentoDTO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void valorDeTransferenciaMenorQueZero() {
		AgendamentoDTO agendamentoDTO = new AgendamentoDTO();

		agendamentoDTO.setDataTransferencia(formatter.format(LocalDate.now()));
		agendamentoDTO.setValorTransferencia(BigDecimal.valueOf(0l));
		AgendamentoConverter.agendamentoFrom(agendamentoDTO);
	}
}
