package br.com.agendamento.model.chain;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.agendamento.persistence.entity.Agendamento;

public abstract class CalculadoraTaxaChain {
 
    protected CalculadoraTaxaChain proximo;
 
    public CalculadoraTaxaChain() {
    	proximo = null;
    }
 
    public BigDecimal calcularTaxa(Agendamento agendamento) {
    	if(agendamento.getDataTransferencia().isBefore(LocalDate.now())){
    		throw new UnsupportedOperationException("Tipo de transferência não encontrado");
    		
    	}else{
    		if (podeCalcularTaxa(agendamento)) {
    			return calcular(agendamento);
    			
    		} else {
    			if (proximo == null) {
    				throw new UnsupportedOperationException("Tipo de transferência não encontrado");
    			}
    			return proximo.calcularTaxa(agendamento);
    		}
    	}
    }
     
    protected abstract boolean podeCalcularTaxa(Agendamento agendamento);
    protected abstract BigDecimal calcular(Agendamento agendamento);
}
