package br.com.agendamento.model.chain.impl;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.agendamento.model.chain.CalculadoraTaxaChain;
import br.com.agendamento.model.enums.TipoTransferencia;
import br.com.agendamento.persistence.entity.Agendamento;

public class CalculadoraTaxaTipoCAcimaDeDezDias extends CalculadoraTaxaChain {
	
	private static final Logger logger = LoggerFactory.getLogger(CalculadoraTaxaTipoCAcimaDeDezDias.class);

	private static final Long A_PARTIR_QUANTOS_DIAS = 10L;
	private static final Long ATE_QUANTOS_DIAS = 20L;
	private static final BigDecimal TAXA = BigDecimal.valueOf(0.08);
	
	public CalculadoraTaxaTipoCAcimaDeDezDias() {
		proximo = new CalculadoraTaxaTipoCAcimaDeVinteDias();
	}

	@Override
	protected boolean podeCalcularTaxa(Agendamento agendamento) {
		Long diferencaEmDias = ChronoUnit.DAYS.between(agendamento.getDataAgendamento(), agendamento.getDataTransferencia());
		
		if (diferencaEmDias > A_PARTIR_QUANTOS_DIAS && diferencaEmDias <= ATE_QUANTOS_DIAS) {
			agendamento.setTipoTransferencia(TipoTransferencia.C);	
			return true;
		}
		return false;
	}

	@Override
	protected BigDecimal calcular(Agendamento agendamento) {
		BigDecimal taxaCalculada = agendamento.getValorTransferencia().multiply(TAXA);
		logger.info("Taxa de " + taxaCalculada + " foi calculada usando: " + CalculadoraTaxaTipoCAcimaDeDezDias.class);
		
		return taxaCalculada;
	}
}