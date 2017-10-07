package br.com.agendamento.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.agendamento.model.chain.CalculadoraTaxaChain;
import br.com.agendamento.model.chain.impl.CalculadoraTaxaTipoA;
import br.com.agendamento.model.enums.TipoTransferencia;

@Entity
@Table(name = "AGENDAMENTO")
public class Agendamento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idAgendamento;
	
	@Column(nullable=false, length=10)
	private String contaOrigem;

	@Column(nullable=false, length=10)
	private String contaDestino;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal valorTransferencia;

	@Column(precision=10, scale=2)
	private BigDecimal taxaTransferencia;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(nullable=false)
	private LocalDate dataTransferencia;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(nullable=false)
	private LocalDate dataAgendamento = LocalDate.now();
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, length=1)
	private TipoTransferencia tipoTransferencia;
	
	public void calcularTaxa() {
		CalculadoraTaxaChain calculadora = new CalculadoraTaxaTipoA();
		taxaTransferencia = calculadora.calcularTaxa(this);
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}
	
	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
	public LocalDate getDataTransferencia() {
		return dataTransferencia;
	}
	
	public void setDataTransferencia(LocalDate dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}
	
	public BigDecimal getTaxaTransferencia() {
		return taxaTransferencia;
	}
	
	public void setTaxaTransferencia(BigDecimal taxaTransferencia) {
		this.taxaTransferencia = taxaTransferencia;
	}
	
	public Long getIdAgendamento() {
		return idAgendamento;
	}

	public void setIdAgendamento(Long idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

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

	@Override
	public String toString() {
		return "Agendamento [idAgendamento=" + idAgendamento + ", contaOrigem=" + contaOrigem + ", contaDestino="
				+ contaDestino + ", valorTransferencia=" + valorTransferencia + ", taxaTransferencia="
				+ taxaTransferencia + ", dataTransferencia=" + dataTransferencia + ", dataAgendamento="
				+ dataAgendamento + ", tipoTransferencia=" + tipoTransferencia + "]";
	}
}
