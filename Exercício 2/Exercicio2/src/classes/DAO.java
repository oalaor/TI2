package classes;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "teste";
		String password = "alaor";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}

	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	
	public ClienteX[] getCliente() {
		ClienteX[] clientes = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM clientes");		
	         if(rs.next()){
	             rs.last();
	             clientes = new ClienteX[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                clientes[i] = new ClienteX(rs.getInt("id"), rs.getString("nome"), 
	                		                  rs.getInt("idade"), rs.getString("carro"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return clientes;
	}
	
	public boolean inserirCliente(ClienteX cliente) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO clientes (id, nome, idade, carro) "
					       + "VALUES ("+cliente.getId()+ ", '" + cliente.getNome() + "', '"  
					       + cliente.getIdade() + "', '" + cliente.getCarro() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirCliente(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM clientes WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarCliente(ClienteX cliente, int idAlvo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE clientes SET id = '" + cliente.getId() + "', nome = '"  
				       + cliente.getNome() + "', idade = '" + cliente.getIdade() + "', carro = '"
				       + cliente.getCarro() + "'"
					   + " WHERE id = " + idAlvo;
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
}