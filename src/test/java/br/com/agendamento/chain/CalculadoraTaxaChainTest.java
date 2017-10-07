package br.com.agendamento.chain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.agendamento.model.enums.TipoTransferencia;
import br.com.agendamento.persistence.entity.Agendamento;

public class CalculadoraTaxaChainTest {
	
	private Agendamento agendamento;

	@Before
	public void before() {
		agendamento = new Agendamento();
		agendamento.setContaOrigem("123456");
		agendamento.setContaDestino("654321");
		agendamento.setValorTransferencia(BigDecimal.valueOf(200l));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void naoDeveCalcularTaxaParaDataDeTransferenciaMenorQueDataDeHoje() {
		agendamento.setDataTransferencia(LocalDate.now().minus(10l, ChronoUnit.DAYS));
		agendamento.calcularTaxa();
	}

	@Test
	public void calcularTaxaParaTransferenciaMesmoDia() {
		agendamento.setDataTransferencia(LocalDate.now());
		agendamento.calcularTaxa();
		
		Assert.assertTrue(agendamento.getTaxaTransferencia().compareTo(BigDecimal.valueOf(9.00)) == 0);
		Assert.assertTrue(agendamento.getTipoTransferencia().compareTo(TipoTransferencia.A) == 0);
	}
	
	@Test
	public void calcularTaxaParaTransferenciaAteDezDias() {
		agendamento.setDataTransferencia(LocalDate.now().plus(10l, ChronoUnit.DAYS));
		agendamento.calcularTaxa();
		
		Assert.assertTrue(agendamento.getTaxaTransferencia().compareTo(BigDecimal.valueOf(2012.00)) == 0);
		Assert.assertTrue(agendamento.getTipoTransferencia().compareTo(TipoTransferencia.B) == 0);
	}
	
	@Test
	public void calcularTaxaParaTransferenciaAcimaDeDezDiasEMenorQueVinteDias() {
		agendamento.setDataTransferencia(LocalDate.now().plus(20l, ChronoUnit.DAYS));
		agendamento.calcularTaxa();
		
		Assert.assertTrue(agendamento.getTaxaTransferencia().compareTo(BigDecimal.valueOf(16)) == 0);
		Assert.assertTrue(agendamento.getTipoTransferencia().compareTo(TipoTransferencia.C) == 0);
	}

	@Test
	public void calcularTaxaParaTransferenciaAcimaDeVinteDiasEMenorQueTrintaDias() {
		agendamento.setDataTransferencia(LocalDate.now().plus(30l, ChronoUnit.DAYS));
		agendamento.calcularTaxa();
		
		Assert.assertTrue(agendamento.getTaxaTransferencia().compareTo(BigDecimal.valueOf(12)) == 0);
		Assert.assertTrue(agendamento.getTipoTransferencia().compareTo(TipoTransferencia.C) == 0);
	}
	
	@Test
	public void calcularTaxaParaTransferenciaAcimaDeTrintaDiasEMenorQueQuarentaDias() {
		agendamento.setDataTransferencia(LocalDate.now().plus(40l, ChronoUnit.DAYS));
		agendamento.calcularTaxa();
		
		Assert.assertTrue(agendamento.getTaxaTransferencia().compareTo(BigDecimal.valueOf(8)) == 0);
		Assert.assertTrue(agendamento.getTipoTransferencia().compareTo(TipoTransferencia.C) == 0);
	}
	
	@Test
	public void calcularTaxaParaTransferenciaAcimaDeQuarentaDiasComValorAcimaDeCemMil() {
		agendamento.setDataTransferencia(LocalDate.now().plus(50l, ChronoUnit.DAYS));
		agendamento.setValorTransferencia(BigDecimal.valueOf(100000));
		agendamento.calcularTaxa();
		
		Assert.assertTrue(agendamento.getTaxaTransferencia().compareTo(BigDecimal.valueOf(2000)) == 0);
		Assert.assertTrue(agendamento.getTipoTransferencia().compareTo(TipoTransferencia.C) == 0);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void naoDeveCalcularTaxaParaTransferenciaAcimaDeQuarentaDiasComValorAbaixoDeCemMil() {
		agendamento.setDataTransferencia(LocalDate.now().plus(50l, ChronoUnit.DAYS));
		agendamento.setValorTransferencia(BigDecimal.valueOf(50000));
		agendamento.calcularTaxa();
		
		Assert.assertTrue(agendamento.getTaxaTransferencia().compareTo(BigDecimal.valueOf(4000)) == 0);
		Assert.assertTrue(agendamento.getTipoTransferencia().compareTo(TipoTransferencia.C) == 0);
	}
}
