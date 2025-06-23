/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import br.com.controle.Jogo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author yasmi
 */
public class ManterJogoDAO extends DAO {
    public void inserir(Jogo j) throws Exception {
    try {
    abrirBanco();                      
    String query = "INSERT INTO jogo(nome,categoria) "
            + "values(?,?)";
    pst=(PreparedStatement) con.prepareStatement(query);
    pst.setString(1, j.getNome());
    pst.setString(2, j.getCategoria());
    pst.execute();
    fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }
    }
    
    public void deletar(Jogo j) {
	try {
	abrirBanco();
	String query = "DELETE FROM jogo WHERE id_jogo = ?";
	pst = (PreparedStatement) con.prepareStatement(query);
	pst.setInt(1, j.getId_jogo());
	pst.execute();
	fecharBanco();
		
	} catch (Exception e) {
		System.out.println("Erro " + e.getMessage());
	}
    }
    public ArrayList<Jogo> PesquisarTudo() throws Exception {
        ArrayList<Jogo> jogos = new ArrayList<Jogo>();
        try {
            abrirBanco();
            String query = "select * FROM jogo";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet tr = pst.executeQuery();
            Jogo j;
            while (tr.next()) {
                j = new Jogo();
                j.setId_jogo(tr.getInt("id_jogo"));
                j.setNome(tr.getString("nome"));
                j.setCategoria(tr.getString("categoria"));
                jogos.add(j);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return jogos;
    }
    public void PesquisarRegistro(Jogo j) throws Exception {
        try {
            abrirBanco();
            String query = "select * FROM jogo Where id_jogo=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, j.getId_jogo());
            ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                j.setId_jogo(tr.getInt("id_jogo"));
                j.setNome(tr.getString("nome"));
                j.setCategoria(tr.getString("categoria"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
    public void editarJogo(Jogo j) throws Exception {
        abrirBanco();
        String query = "UPDATE jogo set nome = ?, categoria = ? where id_jogo = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setString(1, j.getNome());
        pst.setString(2, j.getCategoria());
        pst.setInt(3, j.getId_jogo());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Jogo alterado com sucesso!");
        fecharBanco();
    }
}
