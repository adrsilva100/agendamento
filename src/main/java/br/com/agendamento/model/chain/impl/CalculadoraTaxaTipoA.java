package br.com.agendamento.model.chain.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.agendamento.model.chain.CalculadoraTaxaChain;
import br.com.agendamento.model.enums.TipoTransferencia;
import br.com.agendamento.persistence.entity.Agendamento;

public class CalculadoraTaxaTipoA extends CalculadoraTaxaChain {
	
	private static final Logger logger = LoggerFactory.getLogger(CalculadoraTaxaTipoA.class);
	
	private static final BigDecimal VALOR_FIXO = BigDecimal.valueOf(3);
	private static final BigDecimal TAXA = BigDecimal.valueOf(0.03);

	public CalculadoraTaxaTipoA() {
		proximo = new CalculadoraTaxaTipoB();
	}

	@Override
	protected boolean podeCalcularTaxa(Agendamento agendamento) {
		if (agendamento.getDataAgendamento().isEqual(agendamento.getDataTransferencia())) {
			agendamento.setTipoTransferencia(TipoTransferencia.A);
			return true;
		}
		return false;
	}

	@Override
	protected BigDecimal calcular(Agendamento agendamento) {
		BigDecimal taxaCalculada = agendamento.getValorTransferencia().multiply(TAXA).add(VALOR_FIXO);
		logger.info("Taxa de " + taxaCalculada + " foi calculada usando: " + CalculadoraTaxaTipoA.class);
		
		return taxaCalculada;
	}
}