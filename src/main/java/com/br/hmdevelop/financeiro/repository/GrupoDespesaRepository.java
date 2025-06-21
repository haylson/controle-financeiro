package com.br.hmdevelop.financeiro.repository;

import com.br.hmdevelop.financeiro.model.GrupoDespesa;
import jakarta.enterprise.context.ApplicationScoped;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GrupoDespesaRepository {

    private final DataSource dataSource;

    public GrupoDespesaRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(GrupoDespesa grupo) {
        String sql = "INSERT INTO grupo_despesa (descricao) VALUES (?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, grupo.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir grupo de despesa", e);
        }
    }

    public List<GrupoDespesa> listarTodos() {
        String sql = "SELECT * FROM grupo_despesa ORDER BY id DESC";
        List<GrupoDespesa> lista = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                GrupoDespesa grupo = new GrupoDespesa();
                grupo.setId(rs.getLong("id"));
                grupo.setDescricao(rs.getString("descricao"));
                lista.add(grupo);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar grupos de despesa", e);
        }

        return lista;
    }

    public void atualizar(GrupoDespesa grupo) {
        String sql = "UPDATE grupo_despesa SET descricao = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, grupo.getDescricao());
            stmt.setLong(2, grupo.getId());
            int linhas = stmt.executeUpdate();
            if (linhas == 0) throw new RuntimeException("Grupo não encontrado para atualização");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar grupo de despesa", e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM grupo_despesa WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhas = stmt.executeUpdate();
            if (linhas == 0) throw new RuntimeException("Grupo não encontrado para exclusão");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir grupo de despesa", e);
        }
    }
}
