package br.com.agendamento.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import br.com.agendamento.model.enums.TipoTransferencia;

public class AgendamentoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String VALIDACAO_CONTA_REGEX = "^\\d{6}$";

	@Null(message="Campo id do agendamento deve ser nulo")
	private Long idAgendamento;
	
	@Pattern(regexp = VALIDACAO_CONTA_REGEX, message="Conta origem deve conter 6 dígitos numéricos")
	@NotNull(message="Campo conta origem não pode ser nulo")
	private String contaOrigem;
	
	@Pattern(regexp = VALIDACAO_CONTA_REGEX, message="Conta origem deve conter 6 dígitos numéricos")
	@NotNull(message="Campo conta destino não pode ser nulo")
	private String contaDestino;
	
	@NotNull(message="Campo valor da transferência não pode ser nulo")
	private BigDecimal valorTransferencia;
	
	@NotNull(message="Campo data da tranferência não pode ser nulo")
	private String dataTransferencia;
	
	private String dataAgendamento;
	private BigDecimal taxaTransferencia;
	private TipoTransferencia tipoTransferencia;

	public String getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public BigDecimal getValorTransferencia() {
		return valorTransferencia;
	}

	public void setValorTransferencia(BigDecimal valorTransferencia) {
		this.valorTransferencia = valorTransferencia;
	}

	public TipoTransferencia getTipoTransferencia() {
		return tipoTransferencia;
	}

	public void setTipoTransferencia(TipoTransferencia tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
	}

	public Long getIdAgendamento() {
		return idAgendamento;
	}

	public void setIdAgendamento(Long idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	public BigDecimal getTaxaTransferencia() {
		return taxaTransferencia;
	}

	public void setTaxaTransferencia(BigDecimal taxaTransferencia) {
		this.taxaTransferencia = taxaTransferencia;
	}

	public String getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(String dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public String getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	@Override
	public String toString() {
		return "AgendamentoDTO [idAgendamento=" + idAgendamento + ", contaOrigem=" + contaOrigem + ", contaDestino="
				+ contaDestino + ", valorTransferencia=" + valorTransferencia + ", dataTransferencia="
				+ dataTransferencia + ", dataAgendamento=" + dataAgendamento + ", taxaTransferencia="
				+ taxaTransferencia + ", tipoTransferencia=" + tipoTransferencia + "]";
	}
}
