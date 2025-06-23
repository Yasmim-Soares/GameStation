/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import br.com.controle.Locacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author yasmi
 */
public class ManterLocacaoDAO extends DAO{
    public void inserir(Locacao l) throws Exception {
    try {
    abrirBanco();                      
    String query = "INSERT INTO locacao(nome_jogo,nome_cliente,data_locacao,data_devolucao) "
            + "values(?,?,?,?)";
    pst=(PreparedStatement) con.prepareStatement(query);
    pst.setString(1, l.getNome_jogo());
    pst.setString(2, l.getNome_cliente());
    pst.setDate(3, java.sql.Date.valueOf(l.getData_locacao()));
    pst.setDate(4, java.sql.Date.valueOf(l.getData_devolucao()));
    pst.execute();
    fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }
    }
    
    public void deletar(Locacao l) {
	try {
	abrirBanco();
	String query = "DELETE FROM locacao WHERE id_locacao = ?";
	pst = (PreparedStatement) con.prepareStatement(query);
	pst.setInt(1, l.getId_locacao());
	pst.execute();
	fecharBanco();
		
	} catch (Exception e) {
		System.out.println("Erro " + e.getMessage());
	}
    }
    public ArrayList<Locacao> PesquisarTudo() throws Exception {
        ArrayList<Locacao> locacoes = new ArrayList<Locacao>();
        try {
            abrirBanco();
            String query = "select * FROM locacao";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet tr = pst.executeQuery();
            Locacao l;
            while (tr.next()) {
                l = new Locacao();
                l.setId_locacao(tr.getInt("id_locacao"));
                l.setNome_jogo(tr.getString("nome_jogo"));
                l.setNome_cliente(tr.getString("nome_cliente"));
                l.setData_locacao(tr.getDate("data_locacao").toLocalDate());
                l.setData_devolucao(tr.getDate("data_devolucao").toLocalDate());
                locacoes.add(l);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return locacoes;
    }
    public void PesquisarRegistro(Locacao l) throws Exception {
        try {
            abrirBanco();
            String query = "select * FROM locacao Where id_locacao=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, l.getId_locacao());
            ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                l.setNome_jogo(tr.getString("nome_jogo"));
                l.setNome_cliente(tr.getString("nome_cliente"));
                l.setData_locacao(tr.getDate("data_locacao").toLocalDate());
                l.setData_devolucao(tr.getDate("data_devolucao").toLocalDate());
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
    public void editarCliente(Locacao l) throws Exception {
        abrirBanco();
        String query = "UPDATE locacao set data_locacao = ?, data_devolucao = ?, nome_jogo = ?, nome_cliente=? where id_locacao = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setDate(1, java.sql.Date.valueOf(l.getData_locacao()));
        pst.setDate(2, java.sql.Date.valueOf(l.getData_devolucao()));
        pst.setString(3, l.getNome_jogo());
        pst.setString(4, l.getNome_cliente());
        pst.setInt(5, l.getId_locacao());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Locação alterada com sucesso!");
        fecharBanco();
    }
    

}
