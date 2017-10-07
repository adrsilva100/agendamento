package br.com.agendamento.model.chain.impl;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.agendamento.model.chain.CalculadoraTaxaChain;
import br.com.agendamento.model.enums.TipoTransferencia;
import br.com.agendamento.persistence.entity.Agendamento;

public class CalculadoraTaxaTipoCAcimaDeQuarentaDias extends CalculadoraTaxaChain {
	
	private static final Logger logger = LoggerFactory.getLogger(CalculadoraTaxaTipoCAcimaDeQuarentaDias.class);

	private static final Long A_PARTIR_QUANTOS_DIAS = 40L;
	private static final BigDecimal VALOR_SUPERIOR = BigDecimal.valueOf(100000.00);
	private static final BigDecimal TAXA = BigDecimal.valueOf(0.02);
	
	public CalculadoraTaxaTipoCAcimaDeQuarentaDias() {
		proximo = null;
	}

	@Override
	protected boolean podeCalcularTaxa(Agendamento agendamento) {
		Long diferencaEmDias = ChronoUnit.DAYS.between(agendamento.getDataAgendamento(), agendamento.getDataTransferencia());
		
		if (diferencaEmDias > A_PARTIR_QUANTOS_DIAS && agendamento.getValorTransferencia().compareTo(VALOR_SUPERIOR) >= 0) {
			agendamento.setTipoTransferencia(TipoTransferencia.C);	
			return true;
		}
		return false;
	}

	@Override
	protected BigDecimal calcular(Agendamento agendamento) {
		BigDecimal taxaCalculada = agendamento.getValorTransferencia().multiply(TAXA);
		logger.info("Taxa de " + taxaCalculada + " foi calculada usando: " + CalculadoraTaxaTipoCAcimaDeQuarentaDias.class);
		
		return taxaCalculada;
	}
}