package com.br.hmdevelop.financeiro.repository;

import com.br.hmdevelop.financeiro.model.CartaoCredito;
import jakarta.enterprise.context.ApplicationScoped;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CartaoCreditoRepository {

    private final DataSource dataSource;

    public CartaoCreditoRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void inserir(CartaoCredito cartao) {
        String sql = """
            INSERT INTO cartoes_credito
            (descricao, bandeira, ultimos_quatro_digitos, vencimento, limite_total, limite_livre)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cartao.getDescricao());
            stmt.setString(2, cartao.getBandeira());
            stmt.setString(3, cartao.getUltimosQuatroDigitos());
            stmt.setInt(4, cartao.getVencimento());
            stmt.setBigDecimal(5, cartao.getLimiteTotal());
            stmt.setBigDecimal(6, cartao.getLimiteLivre());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cartão de crédito", e);
        }
    }

    public List<CartaoCredito> listarTodos() {
        List<CartaoCredito> lista = new ArrayList<>();
        String sql = "SELECT * FROM cartoes_credito ORDER BY id DESC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CartaoCredito c = new CartaoCredito();
                c.setId(rs.getLong("id"));
                c.setDescricao(rs.getString("descricao"));
                c.setBandeira(rs.getString("bandeira"));
                c.setUltimosQuatroDigitos(rs.getString("ultimos_quatro_digitos"));
                c.setVencimento(rs.getInt("vencimento"));
                c.setLimiteTotal(rs.getBigDecimal("limite_total"));
                c.setLimiteLivre(rs.getBigDecimal("limite_livre"));
                lista.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar cartões de crédito", e);
        }

        return lista;
    }

    public void atualizar(CartaoCredito cartao) {
        String sql = """
            UPDATE cartoes_credito SET
                descricao = ?,
                bandeira = ?,
                ultimos_quatro_digitos = ?,
                vencimento = ?,
                limite_total = ?,
                limite_livre = ?
            WHERE id = ?
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cartao.getDescricao());
            stmt.setString(2, cartao.getBandeira());
            stmt.setString(3, cartao.getUltimosQuatroDigitos());
            stmt.setInt(4, cartao.getVencimento());
            stmt.setBigDecimal(5, cartao.getLimiteTotal());
            stmt.setBigDecimal(6, cartao.getLimiteLivre());
            stmt.setLong(7, cartao.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Cartão de crédito não encontrado para atualização");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cartão de crédito", e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM cartoes_credito WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Cartão de crédito não encontrado para exclusão");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir cartão de crédito", e);
        }
    }
}