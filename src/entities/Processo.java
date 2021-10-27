package entities;

public class Processo {

	private Integer tempoExecucao;
	private Integer turnaround;
	private Double tempoMedioRetorno;
	private Double tempoMedioEspera;
	private Integer tempoProcessamento;
	private Integer tempoEsperaProcesso;

	public Processo() {

	}

	public Processo(Integer tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}

	public Integer getTempoExecucao() {
		return tempoExecucao;
	}

	public void setTempoExecucao(Integer tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}

	public Integer getTurnaround() {
		return turnaround;
	}

	public void setTurnaround(Integer turnaround) {
		this.turnaround = turnaround;
	}

	public Double getTempoMedioRetorno() {
		return tempoMedioRetorno;
	}

	public void setTempoMedioRetorno(Double tempoMedioRetorno) {
		this.tempoMedioRetorno = tempoMedioRetorno;
	}

	public Double getTempoMedioEspera() {
		return tempoMedioEspera;
	}

	public void setTempoMedioEspera(Double tempoMedioEspera) {
		this.tempoMedioEspera = tempoMedioEspera;
	}

	public Integer getTempoProcessamento() {
		return tempoProcessamento;
	}

	public void setTempoProcessamento(Integer tempoProcessamento) {
		this.tempoProcessamento = tempoProcessamento;
	}

	public Integer getTempoEsperaProcesso() {
		return tempoEsperaProcesso;
	}

	public void setTempoEsperaProcesso(Integer tempoEsperaProcesso) {
		this.tempoEsperaProcesso = tempoEsperaProcesso;
	}
}