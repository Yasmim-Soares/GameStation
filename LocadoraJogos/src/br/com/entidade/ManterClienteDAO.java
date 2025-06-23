/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import br.com.controle.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author yasmi
 */
public class ManterClienteDAO extends DAO {
        public void inserir(Cliente c) throws Exception {
    try {
    abrirBanco();                      
    String query = "INSERT INTO cliente(nome,telefone) "
            + "values(?,?)";
    pst=(PreparedStatement) con.prepareStatement(query);
    pst.setString(1, c.getNome());
    pst.setString(2, c.getTelefone());
    pst.execute();
    fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }
    }
    
    public void deletar(Cliente c) {
	try {
	abrirBanco();
	String query = "DELETE FROM cliente WHERE id_cliente = ?";
	pst = (PreparedStatement) con.prepareStatement(query);
	pst.setInt(1, c.getId_cliente());
	pst.execute();
	fecharBanco();	
	} 
        catch (Exception e) {
		System.out.println("Erro " + e.getMessage());
	} 
    }
    public ArrayList<Cliente> PesquisarTudo() throws Exception {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        try {
            abrirBanco();
            String query = "select * FROM cliente";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet tr = pst.executeQuery();
            Cliente l;
            while (tr.next()) {
                l = new Cliente();
                l.setId_cliente(tr.getInt("id_cliente"));
                l.setNome(tr.getString("nome"));
                l.setTelefone(tr.getString("telefone"));
                clientes.add(l);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return clientes;
    }
    public void PesquisarRegistro(Cliente l) throws Exception {
        try {
            abrirBanco();
            String query = "select * FROM cliente Where id_cliente=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, l.getId_cliente());
            ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                l.setId_cliente(tr.getInt("id_cliente"));
                l.setNome(tr.getString("nome"));
                l.setTelefone(tr.getString("telefone"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
    public void editarCliente(Cliente c) throws Exception {
        abrirBanco();
        String query = "UPDATE cliente set nome = ?, telefone = ? where id_cliente = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setString(1, c.getNome());
        pst.setString(2, c.getTelefone());
        pst.setInt(3, c.getId_cliente());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
        fecharBanco();
    }
    
    public int buscarTotalLocacoes(String nomeCliente) throws Exception {
    int total = 0;
    abrirBanco();
    String query = "SELECT totalLocacoesCliente(?) AS total";
    pst = con.prepareStatement(query);
    pst.setString(1, nomeCliente);
    ResultSet rs = pst.executeQuery();
    if (rs.next()) {
        total = rs.getInt("total");
    }
    fecharBanco();
    return total;
}

}
