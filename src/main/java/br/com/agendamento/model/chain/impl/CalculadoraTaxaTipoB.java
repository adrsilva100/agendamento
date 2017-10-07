package br.com.agendamento.model.chain.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.agendamento.model.chain.CalculadoraTaxaChain;
import br.com.agendamento.model.enums.TipoTransferencia;
import br.com.agendamento.persistence.entity.Agendamento;

public class CalculadoraTaxaTipoB extends CalculadoraTaxaChain {
	
	private static final Logger logger = LoggerFactory.getLogger(CalculadoraTaxaTipoB.class);

	private static final Long A_PARTIR_QUANTOS_DIAS = 10L;
	private static final BigDecimal TAXA = BigDecimal.valueOf(12);
	
	public CalculadoraTaxaTipoB() {
		proximo = new CalculadoraTaxaTipoCAcimaDeDezDias();
	}

	@Override
	protected boolean podeCalcularTaxa(Agendamento agendamento) {
		LocalDate dataLimiteParaTransferencia = agendamento.getDataAgendamento().plus(A_PARTIR_QUANTOS_DIAS, ChronoUnit.DAYS);
		
		if (agendamento.getDataTransferencia().isBefore(dataLimiteParaTransferencia)
				|| agendamento.getDataTransferencia().isEqual(dataLimiteParaTransferencia)) {
			
			agendamento.setTipoTransferencia(TipoTransferencia.B);	
			return true;
		}
		return false;
	}

	@Override
	protected BigDecimal calcular(Agendamento agendamento) {
		BigDecimal diferencaEmDias = new BigDecimal(ChronoUnit.DAYS.between(agendamento.getDataAgendamento(), agendamento.getDataTransferencia()));
		BigDecimal taxaCalculada = agendamento.getValorTransferencia().multiply(diferencaEmDias).add(TAXA);
		logger.info("Taxa de " + taxaCalculada + " foi calculada usando: " + CalculadoraTaxaTipoB.class);
		
		return taxaCalculada;
	}
}