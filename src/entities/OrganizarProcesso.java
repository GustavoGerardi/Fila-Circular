package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrganizarProcesso {

	private List<Processo> processos = new ArrayList<>();
	private List<Processo> processosOriginais = new ArrayList<>();

	public OrganizarProcesso() {
	}

	public OrganizarProcesso(List<Processo> processos) {
		super();
		this.processos = processos;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public void addProcesso(Integer numeroProcessos) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		for (int i = 0; i < numeroProcessos; i++) {
			System.out.println("Tempo de execução do P" + (i + 1));
			Integer tempoExecucao = scanner.nextInt();
			processos.add(new Processo(tempoExecucao));
			processosOriginais.add(new Processo(tempoExecucao));
		}
	}

	public Integer quantidadeIteracoes(Integer quantum, Integer numeroProcessos) {

		int maiorValor = 0;

		for (int i = 0; i < numeroProcessos; i++) {
			int valorAuxiliar = processos.get(i).getTempoExecucao();

			if (valorAuxiliar > maiorValor) {
				maiorValor = valorAuxiliar;
			}
		}

		Double valorTratado = ((double) (maiorValor) / quantum);
		int qtdIteracoes = (int) Math.round(valorTratado);
		return qtdIteracoes;
	}

	public void evolucaoDoProcesso(Integer qtdIteracoes, Integer numeroProcessos, Integer quantum) {
		int tempoExecAuxiliar = 0;
		int turnaround = 0;
		int indexador = 0;

		for (int i = 0; i < qtdIteracoes; i++) {
			for (int j = 0; j < numeroProcessos; j++) {
				if (processos.get(j).getTempoExecucao() < quantum && processos.get(j).getTempoExecucao() > 0) {
					indexador = j + 1;
					tempoExecAuxiliar = 0;
					System.out.println("Valor inicial do P" + indexador + " = " + processos.get(j).getTempoExecucao());
					turnaround = turnaround + processos.get(j).getTempoExecucao();
					processos.get(j).setTempoExecucao(tempoExecAuxiliar);
					System.out.println("Tempo restante de execução = " + tempoExecAuxiliar);

					if (processos.get(j).getTempoExecucao() == 0) {
						processos.get(j).setTurnaround(turnaround);
						System.out.println("O P" + indexador + " foi executado completamente");
						System.out.println("==================================================");
					}
				}
				if (processos.get(j).getTempoExecucao() >= quantum) {
					indexador = j + 1;
					System.out.println("Valor inicial do P" + indexador + " = " + processos.get(j).getTempoExecucao());
					tempoExecAuxiliar = processos.get(j).getTempoExecucao() - quantum;
					turnaround = turnaround + quantum;
					processos.get(j).setTempoExecucao(tempoExecAuxiliar);
					System.out.println("Tempo restante de execução = " + tempoExecAuxiliar);

					if (processos.get(j).getTempoExecucao() == 0) {
						processos.get(j).setTurnaround(turnaround);
						System.out.println("O P" + indexador + " foi executado completamente");
						System.out.println("==================================================");
					}
				}

				if (processos.get(j).getTempoExecucao() == 1) {
					indexador = j + 1;
					System.out.println("Valor inicial do P" + indexador + " = " + processos.get(j).getTempoExecucao());
					tempoExecAuxiliar = processos.get(j).getTempoExecucao() - 1;
					turnaround = turnaround + 1;
					processos.get(j).setTempoExecucao(tempoExecAuxiliar);
					System.out.println("Tempo restante de execução = " + tempoExecAuxiliar);

					if (processos.get(j).getTempoExecucao() == 0) {
						processos.get(j).setTurnaround(turnaround);
						System.out.println("O P" + indexador + " foi executado completamente");
						System.out.println("==================================================");
					}
				}
			}
		}
	}

	public void imprimirTurnaround(Integer numeroProcessos) {
		System.out.println("Tempo de Turnaround: \n");
		for (int i = 0; i < numeroProcessos; i++) {
			System.out.println("Processo " + (i + 1) + " = " + processos.get(i).getTurnaround());
		}
		System.out.println();
	}

	public void atualizarTMR(Integer numeroProcessos) {
		List<Integer> listaTurnaround = processos.stream().map(valor -> valor.getTurnaround())
				.collect(Collectors.toList());
		Integer tmr = listaTurnaround.stream().reduce(0, (total, valor) -> total + valor);
		Double tmrFlutuante = Double.valueOf(tmr) / numeroProcessos;

		for (int i = 0; i < numeroProcessos; i++) {
			processos.get(i).setTempoMedioRetorno(tmrFlutuante);
		}
		System.out.println("(TMR) Tempo Medio de Retorno dos processos: " + tmrFlutuante + "\n");
	}

	public void atualizarTEP(Integer numeroProcessos) {
		System.out.println("(TEP) Tempo de Espera de cada Processo: \n");
		for (int i = 0; i < numeroProcessos; i++) {
			processos.get(i).setTempoEsperaProcesso(
					processos.get(i).getTurnaround() - processosOriginais.get(i).getTempoExecucao());
			System.out.println("Processo " + (i + 1) + " = " + processos.get(i).getTempoEsperaProcesso());
		}
		System.out.println();
	}

	public void atualizarTME(Integer numeroProcessos) {
		List<Integer> listaTEP = processos.stream().map(valor -> valor.getTempoEsperaProcesso())
				.collect(Collectors.toList());
		Integer tme = listaTEP.stream().reduce(0, (total, valor) -> total + valor);
		Double tmeFlutuante = Double.valueOf(tme) / numeroProcessos;
		for (int i = 0; i < numeroProcessos; i++) {
			processos.get(i).setTempoMedioEspera(tmeFlutuante);
		}
		System.out.println("(TME) Tempo Medio de Espera: " + tmeFlutuante + "\n");
	}

	public void tempoProcesso(Integer numeroProcessos) {
		System.out.println("Tempo de processamento de cada processo: \n");
		for (int i = 0; i < numeroProcessos; i++) {
			processos.get(i).setTempoProcessamento(processosOriginais.get(i).getTempoExecucao());
			System.out.println("Processo " + (i + 1) + " = " + processos.get(i).getTempoProcessamento());
		}
		List<Integer> listaProcessamentoTotal = processos.stream().map(valor -> valor.getTempoProcessamento())
				.collect(Collectors.toList());
		Integer processamentoTotal = listaProcessamentoTotal.stream().reduce(0, (total, valor) -> total + valor);
		System.out.println("\nTempo de processamento total do processador: " + processamentoTotal);
	}

	public void escolha(Integer opcao, Integer numeroProcessos) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (opcao != 0) {
			System.out.println("Selecione a opção que deseja: \n");
			System.out.println("1. Turnaround");
			System.out.println("2. Tempo médio de retorno");
			System.out.println("3. Tempo de espera de cada processo");
			System.out.println("4. Tempo de médio de espera");
			System.out.println("5. Tempo de cada processo e Tempo de processamento total");
			System.out.println("0. Sair");

			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				System.out.println(new String(new char[50]).replace("\0", "\r\n"));
				imprimirTurnaround(numeroProcessos);
				break;

			case 2:
				System.out.println(new String(new char[50]).replace("\0", "\r\n"));
				atualizarTMR(numeroProcessos);
				break;

			case 3:
				System.out.println(new String(new char[50]).replace("\0", "\r\n"));
				atualizarTEP(numeroProcessos);
				break;

			case 4:
				System.out.println(new String(new char[50]).replace("\0", "\r\n"));
				atualizarTME(numeroProcessos);
				break;
			case 5:
				System.out.println(new String(new char[50]).replace("\0", "\r\n"));
				tempoProcesso(numeroProcessos);
				break;
			case 0:
				System.out.println("Encerrado.");
				break;
			default:
				System.out.println(new String(new char[50]).replace("\0", "\r\n"));
				System.out.println("Entre com outro número válido!");
			}
		}
	}
}