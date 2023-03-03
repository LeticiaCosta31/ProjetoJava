/*
 * Alunos: Letícia G.S.Costa e Lucas Ruas Cardoso 
 * Data:09/12/2022
 * Objetivo:Aplicar conhecimentos adquiridos em sala de aula em um enunciado mais elaborado e inserido 
   no contexto mercadológico.
 */

import java.io.*;
import java.util.Scanner;

public class PokemonGo {

	//Procedimento que recebe o arquivo, lê e exibe a mochila resultante/evoluida
	public static void exibirMochila() {
		try {

			File arqMochila = new File("mochilaEvoluida.txt");
			Scanner lerMochila = new Scanner(arqMochila);

			while(lerMochila.hasNextLine()) 
			{
				System.out.println(lerMochila.nextLine());
			}


		}

		catch (IOException iex) {
			System.out.println(iex.getMessage());
		}

	}


	//Procedimento responsável por comparar elementos, evoluir a mochila do usuário e criar um arq que tenha ela como conteúdo
	public static void evoluirMochila(String mochila[], String tabela[]) {
	//Tabela e mochila são passadas no parametro com uma linha só


		try {

			FileWriter saida = new FileWriter("mochilaEvoluida.txt",true);


			String[] pokemon= null ;
			String[] evolucao = null;
	


			for (int i = 0; i < mochila.length; i++) 
			{
				//pega o vetor recebido por parametro e armazena as 3 informações da mochila(nome, quantidade, candies) em outro vetor 
				pokemon =  mochila[i].split(";");

				for (int j = 0; j < tabela.length; j++) 
				{
					//pega o vetor recebido por parametro e armazena as 3 informações da tabela evolucao (Pokemon de Origem, Número de Candies necessários e Pokemon evoluído) em outro vetor 
					evolucao = tabela[j].split(";");
					
				
					//Condicional que verifica se o nome do Pokemon que esta na mochila existe na tabela de evolução
					
					
					if (pokemon[0].equals(evolucao[0])) 
					{
						
						
						//Armazena a quantidade de candies que o usuário possui
						String candiesMochila = pokemon[2];
						
						//Armazena a quantidade de pokemons que ele deseja evoluir
						String quantidade = pokemon[1];

						//Armazena a quanidade de candies que ele precisa para evoluir
						String candiesEvolucao = evolucao[1];
						
						
						//Transforma todas essas informações em inteiro para que seja posível a comparação de candies
						int candiesMochilaNum = Integer.parseInt(candiesMochila);
						int candiesEvolucaoNum = Integer.parseInt(candiesEvolucao);
						int quantidadePokemons = Integer.parseInt(quantidade);

						
						
							
							//Condicional que verifica se a quantidade de candies que eu possuo é superior a quantiade de candies que eu preciso para evoluir
							if (candiesMochilaNum >= candiesEvolucaoNum ) 
							{
								
								int evoluidos = 0;
								
								//Enquanto os candies forem suficientes para a evolucao e o numero de pokemons evoluidos for menor que os que eu tenho na mochila, a condição será verdadeira.
								while (candiesMochilaNum >= candiesEvolucaoNum && evoluidos < quantidadePokemons) 
								{
									//Diminui a quantidade de candies que eu tenho pela quantidade de candies que eu preciso para evoluir
									candiesMochilaNum -= candiesEvolucaoNum;
									
									//Adiciona mais um pokemon evoluido 
									evoluidos++;
								}
								
								//Escreve na mochila evoluida os pokemons evoluidos, a quantidade  e o numero de candies restantes.
								saida.write(evolucao[2] + ";" + evoluidos + ";" + candiesMochilaNum + "\n");

							}
						}
						
					}
				}
					
			saida.close();
		}

		catch (IOException iex) {
			System.out.println(iex.getMessage());
		}

	}

	
	//Função responsável por receber e lê o aquivo mochila, o armazena na memória e retornar um vetor preenchido
	public static String[] carregarMochila(String[] mochila) {
		
		Scanner entrada = new Scanner(System.in);
		try {
			
			File arqMochila = new File("mochila.txt");
			Scanner lerMochila = new Scanner(arqMochila);
		
			
			String mochilaOrig = new String();

			while (lerMochila.hasNextLine()) {
				
			//armazenando o conteúdo do arquivo em uma String, separando por espaços
			mochilaOrig += lerMochila.nextLine() + " ";

			}

			//Quebrando a string bruta (mochilaOrig) nos espaços e armazeando em um vetor.
			mochila = mochilaOrig.split(" ");

		} 
		catch (IOException iex) {
			System.out.println(iex.getMessage());

		}
		
		//Retornando o vetor preenchido 
		return mochila;
	}



	//Função responsável por receber e lê o aquivo evolucao, o armazena na memória e retornar um vetor preenchido
	public static String[] tabelaEvolucao(String[]tabela) {

		try {
			
			File arqEvolucao = new File("evolucao.txt");
			Scanner ler = new Scanner(arqEvolucao);

			String temporaria= new String();

			while (ler.hasNextLine()) 
			{
				//armazenando o conteúdo do arquivo em uma String, separando por espaços
				temporaria += ler.nextLine() + " ";

			}

			//Quebrando a string bruta (temporaria) nos espaços e armazeando em um vetor.
			tabela = temporaria.split(" ");


		} catch (IOException iex) {
			System.out.println(iex.getMessage());

		}
		
		//Retornando o vetor preenchido 
		return tabela;

	}





	//Procedimento responsável por exibir menu, chamar outros procedimentos e funções.
	public static void menu() {

		Scanner entrada = new Scanner(System.in);

		int op = 0;
		String[] tabela = new String[150];
		String[] mochila = new String[150];

		//Estrutura de repetição para a exibição do menu até o usuário informar a opção 5.
		while (op != 5) {
			System.out.println("1.Carregar tabela de evolução");
			System.out.println("2.Carregar mochila");
			System.out.println("3.Evoluir mochila");
			System.out.println("4.Exibir mochila evoluída no console e gravar mochila evoluída em arquivo");
			System.out.println("5.Sair");
			System.out.println("Escolha uma opção:");
			op = entrada.nextInt();


			//Condicional responsável por chamar o procedimendo/função correspondente de cada opção do menu
			switch (op) {
			case 1:
				tabela = tabelaEvolucao(tabela);
				System.out.println("Tabela de evolução carregada!");
				System.out.println();
				break;
			case 2:
				mochila = carregarMochila(mochila);
				System.out.println("Mochila carregada!");
				System.out.println();
				break;
			case 3:
				evoluirMochila(mochila,tabela);
				System.out.println("Mochila evoluida!");
				System.out.println();
				break;
			case 4:
				exibirMochila();
				System.out.println();
				break;
			default:
				break;

			}

		}

		entrada.close();
	}

	public static void main(String[] args) {

		//Procedimento sendo chamado para exibir o menu de opções do programa.
		menu();
		System.out.println("Você saiu do programa!");

	}

}
