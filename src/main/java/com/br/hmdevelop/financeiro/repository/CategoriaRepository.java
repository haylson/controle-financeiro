package com.br.hmdevelop.financeiro.repository;

import com.br.hmdevelop.financeiro.model.Categoria;
import jakarta.enterprise.context.ApplicationScoped;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@ApplicationScoped
public class CategoriaRepository {

    private final DataSource dataSource;

    public CategoriaRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(Categoria categoria) {
        String sql = "INSERT INTO categoria (descricao, categoria_pai_id) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getDescricao());

            if (categoria.getCategoriaPaiId() != null) {
                stmt.setLong(2, categoria.getCategoriaPaiId());
            } else {
                stmt.setNull(2, Types.BIGINT);
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir categoria", e);
        }
    }

    public void atualizar(Categoria categoria) {
        String sql = "UPDATE categoria SET descricao = ?, categoria_pai_id = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getDescricao());

            if (categoria.getCategoriaPaiId() != null) {
                stmt.setLong(2, categoria.getCategoriaPaiId());
            } else {
                stmt.setNull(2, Types.BIGINT);
            }

            stmt.setLong(3, categoria.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar categoria", e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM categoria WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar categoria", e);
        }
    }

    public Categoria buscarPorId(Long id) {
        String sql = "SELECT * FROM categoria WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearCategoria(rs);
                }
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar categoria", e);
        }
    }

    public List<Categoria> listarTodos() {
        String sql = "SELECT * FROM categoria ORDER BY id";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<Categoria> categorias = new ArrayList<>();

            while (rs.next()) {
                categorias.add(mapearCategoria(rs));
            }

            return categorias;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar categorias", e);
        }
    }

    /**
     * Lista as categorias em forma de árvore, montando os filhos dentro de cada pai.
     */
    public List<Categoria> listarHierarquia() {
        List<Categoria> todas = listarTodos();

        Map<Long, Categoria> mapa = new HashMap<>();
        List<Categoria> raizes = new ArrayList<>();

        // Popula mapa por ID
        for (Categoria c : todas) {
            mapa.put(c.getId(), c);
        }

        // Organiza a hierarquia
        for (Categoria c : todas) {
            if (c.getCategoriaPaiId() != null) {
                Categoria pai = mapa.get(c.getCategoriaPaiId());
                if (pai != null) {
                    pai.getFilhos().add(c);
                }
            } else {
                raizes.add(c); // é raiz
            }
        }

        return raizes;
    }

    private Categoria mapearCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setId(rs.getLong("id"));
        c.setDescricao(rs.getString("descricao"));

        long categoriaPaiId = rs.getLong("categoria_pai_id");
        if (!rs.wasNull()) {
            c.setCategoriaPaiId(categoriaPaiId);
        }

        c.setFilhos(new ArrayList<>()); // inicializa lista para montagem da árvore
        return c;
    }
}
