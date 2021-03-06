/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import br.senac.sp.grupoum.projectpiiv.models.Cliente;
import br.senac.sp.grupoum.projectpiiv.models.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO {

    public static boolean salvar(Cliente c) {

        boolean retorno = false;
        Connection connection = null;

        try {

            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement("INSERT INTO cliente "
                    + "(nome, sobrenome, cpf, email, senha) "
                    + "VALUES (?, ?, ?, ?, ?);");

            comando.setString(1, c.getNome());
            comando.setString(2, c.getSobreNome());
            comando.setString(3, c.getCpf());
            comando.setString(4, c.getEmail());
            comando.setString(5, c.getSenha());

            int linhasAfetadas = comando.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            } else {
                retorno = false;
            }

        } catch (ClassNotFoundException ex) {
            retorno = false;
        } catch (SQLException ex) {
            System.out.println(ex);
            retorno = false;
        }

        DbConnectionDAO.closeConnection(connection);
        return retorno;
    }

    public static boolean buscarEmail(String email) {

        Connection connection = null;

        try {
            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement("SELECT * FROM cliente WHERE email = ?", Statement.RETURN_GENERATED_KEYS);
            comando.setString(1, email);

            ResultSet rs = comando.executeQuery();

            int cont = 0;

            while (rs.next()) {
                cont++;
            }

            if (cont == 0) {
                DbConnectionDAO.closeConnection(connection);
                return false;
            } else {
                DbConnectionDAO.closeConnection(connection);
                return true;
            }

        } catch (ClassNotFoundException ex) {
            DbConnectionDAO.closeConnection(connection);
            return false;
        } catch (SQLException ex) {
            DbConnectionDAO.closeConnection(connection);
            System.out.println(ex);
            return false;
        }

    }

    /*  public static boolean validacaoNome(String nome, String sobreNome) {
        int contarLetra = 0;
        int contarPalavra = 0;
        for (int i = 0; i < nome.length(); i++) {
            char letra = nome.charAt(i);
            if (contarPalavra >= 2) {
                return true;
            } else if (letra != ' ') {
                contarLetra++;
            } else if (contarLetra < 3) {
                return false;
            } else {
                contarPalavra++;
                contarLetra = 0;
            }
        }
        return true;
    }
     */
    public static boolean buscarCpf(String cpf) throws ClassNotFoundException {

        Connection connection = null;

        try {
            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement("SELECT * FROM cliente WHERE cpf = ?", Statement.RETURN_GENERATED_KEYS);
            comando.setString(1, cpf);

            ResultSet rs = comando.executeQuery();

            int cont = 0;

            while (rs.next()) {
                cont++;
            }

            if (cont == 0) {
                DbConnectionDAO.closeConnection(connection);
                return false;
            } else {
                DbConnectionDAO.closeConnection(connection);
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public static Cliente autenticar(String usuario, String senha) {

        Connection connection = null;

        try {
            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement("SELECT * FROM cliente"
                    + " WHERE email = ? and senha = ?;");
            comando.setString(1, usuario);
            comando.setString(2, senha);

            ResultSet rs = comando.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                rs.beforeFirst();
                while (rs.next()) {
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setSobreNome(rs.getString("sobrenome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setEmail(rs.getString("Email"));
                    cliente.setSenha(rs.getString("Senha"));
                }
                DbConnectionDAO.closeConnection(connection);

                return cliente;
            } else {
                return null;
            }

        } catch (ClassNotFoundException ex) {
            return null;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }

    }

    public static boolean editar(Cliente cliente) {
        boolean retorno = false;
        Connection connection = null;

        try {
            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement(" UPDATE cliente"
                    + " SET nome = ? ,"
                    + " sobrenome = ? ,"
                    + " WHERE cpf = ? ", Statement.RETURN_GENERATED_KEYS);
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getSobreNome());
            comando.setString(3, cliente.getCpf());

            int linhasAfetadas = comando.executeUpdate();

            retorno = linhasAfetadas > 0;

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        DbConnectionDAO.closeConnection(connection);
        return retorno;
    }

    public static Cliente pesquisarPorId(int id) {

        Connection connection = null;

        try {
            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?");
            comando.setInt(1, id);
            ResultSet rs = comando.executeQuery();

            Cliente cliente = new Cliente();

            while (rs.next()) {
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobreNome(rs.getString("sobrenome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
            }

            DbConnectionDAO.closeConnection(connection);
            return cliente;

        } catch (ClassNotFoundException ex) {
            return null;

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static boolean cadastrarEndereco(Endereco endereco) {

        if (endereco.getTipo().equals("Ambos Endereços")) {
            if (cadastrarEnderecoPorTipo(endereco, "Endereço Fatura") && cadastrarEnderecoPorTipo(endereco, "Endereço Entrega")) {
                return true;
            }
        } else {
            return cadastrarEnderecoPorTipo(endereco, "");

        }
        return false;
    }

    public static boolean cadastrarEnderecoPorTipo(Endereco endereco, String tipo) {

        boolean retorno = false;
        Connection connection = null;

        try {

            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement("INSERT INTO enderecos "
                    + "(id_cliente, CEP, rua, numero, complemento, bairro, cidade, estado, tipo)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
            comando.setInt(1, endereco.getCliente().getIdCliente());
            comando.setString(2, endereco.getCep());
            comando.setString(3, endereco.getRua());
            comando.setString(4, endereco.getNumero());
            comando.setString(5, endereco.getComplemento());
            comando.setString(6, endereco.getBairro());
            comando.setString(7, endereco.getCidade());
            comando.setString(8, endereco.getEstado());
            if (tipo.isEmpty()) {
                comando.setString(9, endereco.getTipo());
            } else {
                comando.setString(9, tipo);
            }

            int linhasAfetadas = comando.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            } else {
                retorno = false;
            }

        } catch (ClassNotFoundException ex) {
            retorno = false;
        } catch (SQLException ex) {
            System.out.println(ex);
            retorno = false;
        }

        DbConnectionDAO.closeConnection(connection);
        return retorno;
    }

    public static ArrayList<Endereco> enderecoPorCliente(Cliente cliente) {
        ArrayList<Endereco> endereco = new ArrayList<Endereco>();
        Connection connection = null;

        try {
            connection = DbConnectionDAO.openConnection();

            PreparedStatement comando = connection.prepareStatement("SELECT * FROM enderecos WHERE id_cliente = ?");
            comando.setInt(1, cliente.getIdCliente());
            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                Endereco end = new Endereco();
                end.setId(rs.getInt("id_enderecos"));
                end.setCep(rs.getString("CEP"));
                end.setRua(rs.getString("rua"));
                end.setNumero(rs.getString("numero"));
                end.setComplemento(rs.getString("complemento"));
                end.setBairro(rs.getString("bairro"));
                end.setCidade(rs.getString("cidade"));
                end.setEstado(rs.getString("estado"));
                end.setTipo(rs.getString("tipo"));
                endereco.add(end);
            }

            DbConnectionDAO.closeConnection(connection);
            return endereco;

        } catch (ClassNotFoundException ex) {
            return null;

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static Endereco pesquisarPorId2(int id) {

        Connection connection = null;

        try {
            connection = DbConnectionDAO.openConnection();
            Endereco end = new Endereco();

            PreparedStatement comando = connection.prepareStatement("SELECT * FROM venda WHERE id_endereco = ?");
            comando.setInt(1, id);
            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                end.setId(rs.getInt("id_endereco"));
                end.setCep(rs.getString("cep"));
                end.setRua(rs.getString("rua"));
                end.setNumero(rs.getString("numero"));
                end.setComplemento(rs.getString("complemento"));
                end.setBairro(rs.getString("bairro"));
                end.setCidade(rs.getString("cidade"));
                end.setEstado(rs.getString("estado"));
                end.setTipo(rs.getString("tipo"));

            }

            while (rs.next()) {
                Endereco enderecos = ClienteDAO.pesquisarPorId2(rs.getInt("id_endereco"));

            }

            DbConnectionDAO.closeConnection(connection);
            return end;

        } catch (ClassNotFoundException ex) {
            return null;

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static boolean editarEndereco(Endereco endereco) {
        boolean retorno = false;
        Connection connection = null;

        try {
            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement(" UPDATE endereco"
                    + " SET CEP = ? ,"
                    + " rua = ? ,"
                    + " numero = ? ,"
                    + " complemento = ? ,"
                    + " bairro = ? ,"
                    + " cidade = ? ,"
                    + " estado = ? ,"
                    + " tipo = ? ,"
                    + " WHERE id_endereco = ? ", Statement.RETURN_GENERATED_KEYS);
            comando.setString(1, endereco.getCep());
            comando.setString(2, endereco.getRua());
            comando.setString(3, endereco.getNumero());
            comando.setString(4, endereco.getComplemento());
            comando.setString(5, endereco.getBairro());
            comando.setString(6, endereco.getCidade());
            comando.setString(7, endereco.getEstado());
            comando.setString(8, endereco.getTipo());

            int linhasAfetadas = comando.executeUpdate();

            retorno = linhasAfetadas > 0;

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        DbConnectionDAO.closeConnection(connection);
        return retorno;
    }

}