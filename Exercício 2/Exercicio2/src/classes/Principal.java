package classes;

import java.util.Scanner;

public class Principal {
	public static void menu() {
		System.out.println("O que deseja fazer?");
		System.out.println("[1]: Listar");
		System.out.println("[2]: Inserir");
		System.out.println("[3]: Excluir");
		System.out.println("[4]: Atualizar");
		System.out.println("[5]: Sair");
	}
	
	public static int scanInt(Scanner scanner) {
        while (true) {
            try {
            	if(scanner.hasNextInt())
            		return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Por favor, digite um número inteiro válido.");
            }
        }
    }

    public static String scanString(Scanner scanner) {
        while (true) {
            try {
            	if(scanner.hasNextLine())
                	return scanner.nextLine();
            } catch (java.util.NoSuchElementException e) {
                System.out.println("Erro de leitura da entrada. Certifique-se de que a entrada está disponível.");
            }
        }
    }
	
	public static void cases() {
		menu();
		Scanner scanner = new Scanner(System.in);
		int numero = scanInt(scanner);
		
		DAO dao = new DAO();
		dao.conectar();
		
		switch (numero) {
			case 1: 
				ClienteX[] clientes = dao.getCliente();
				System.out.println("==== Mostrar usuários === ");		
				for(int i = 0; i < clientes.length; i++) {
					System.out.println(clientes[i].imprimeLinha());
				}
				System.out.println("");
				cases();
			
			case 2:
		        System.out.println("Digite o id do cliente: ");
		        int idNew = scanInt(scanner);
		        scanner.nextLine();
		        
		        System.out.println("Digite o nome do cliente: ");
		        String nomeNew = scanString(scanner);
	
		        System.out.println("Digite a idade do cliente: ");
		        int idadeNew = scanInt(scanner);
		        scanner.nextLine();
	
		        System.out.println("Digite o carro do cliente: ");
		        String carroNew = scanString(scanner);
	
		        ClienteX cliente = new ClienteX(idNew, nomeNew, idadeNew, carroNew);
	
		        if (dao.inserirCliente(cliente)) {
		            System.out.println("Inserção com sucesso -> " + cliente.imprimeLinha());
		        }
		        System.out.println("");
		        cases();
		        
			case 3:
				System.out.println("Digite o id do cliente que deseja excluir: ");
		        int idExcluir = scanInt(scanner);
		        dao.excluirCliente(idExcluir);
		        System.out.println("Cliente excluido com sucesso!");
		        System.out.println("");
		        cases();
		        
			case 4:
				System.out.println("Digite o id do cliente que deseja atualizar: ");
		        int idAlvo = scanInt(scanner);
		        scanner.nextLine();
		        
		        System.out.println("Digite o novo id do cliente: ");
		        int idAtualiza = scanInt(scanner);
		        scanner.nextLine();
		        
		        System.out.println("Digite o novo nome do cliente: ");
		        String nomeAtualiza = scanString(scanner);
	
		        System.out.println("Digite a nova idade do cliente: ");
		        int idadeAtualiza = scanInt(scanner);
		        scanner.nextLine();
	
		        System.out.println("Digite o carro do cliente: ");
		        String carroAtualiza = scanString(scanner);

		        ClienteX clienteAtualiza = new ClienteX(idAtualiza, nomeAtualiza, idadeAtualiza, carroAtualiza);
				dao.atualizarCliente(clienteAtualiza, idAlvo);
				System.out.println("Cliente atualizado com sucesso -> " + clienteAtualiza.imprimeLinha());
				System.out.println("");
				cases();
				
			case 5:
				System.out.println("Programa encerrado.");
				System.exit(0);
				
			default:
				System.out.println("Opção invalida.");
				System.out.println("");
				cases();
			}
		dao.close();
	}
	
	public static void main(String[] args) {
		cases();
	}

}