package application;

import java.util.Scanner;

import entities.OrganizarProcesso;

public class Executar {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Quantos processos serao calculados?");
		int numeroProcessos = sc.nextInt();
		System.out.println("Qual o valor do quantum?");
		int quantum = sc.nextInt();

		OrganizarProcesso org = new OrganizarProcesso();

		org.addProcesso(numeroProcessos);

		int qtdIteracoes = org.quantidadeIteracoes(quantum, numeroProcessos);

		org.evolucaoDoProcesso(qtdIteracoes, numeroProcessos, quantum);
		int opcao = 1;
		org.escolha(opcao, numeroProcessos);
		
		sc.close();
	}
}
