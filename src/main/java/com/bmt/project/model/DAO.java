package com.bmt.project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Thibault
 */
public class DAO {

    private final DataSource myDAO;
    private List<ClientEntity> lClients;

    public DAO(DataSource myDAO) {
        this.myDAO = myDAO;
        this.lClients = new ArrayList<>();
    }

    public List<ClientEntity> getClientsList() {

        String sql = "SELECT code, societe, contact, fonction, adresse, ville, region, code_postal, pays, telephone, fax FROM CLIENT";

        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next())
                this.lClients.add(new ClientEntity(
                        rs.getString("code"),
                        rs.getString("societe"),
                        rs.getString("contact"),
                        rs.getString("fonction"),
                        rs.getString("adresse"),
                        rs.getString("ville"),
                        rs.getString("region"),
                        rs.getString("code_postal"),
                        rs.getString("pays"),
                        rs.getString("telephone"),
                        rs.getString("fax"))
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.lClients;
    }

    public ClientEntity login(String log, String pass) {

        String sql = "SELECT * FROM Client WHERE contact=? AND code=?";

        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, log);
            stmt.setString(2, pass);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return new ClientEntity(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getString(11)
                    );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Renvoyer directement clientEntity
    }

    public boolean updateClient(ClientEntity oldC, ClientEntity newC) {
        
        int res = 0;

        String sql = "UPDATE Client"
                + "SET societe=?,"
                + "contact=?,"
                + "fonction=?,"
                + "adresse=?,"
                + "ville=?,"
                + "region=?,"
                + "code_postal=?,"
                + "pays=?,"
                + "telephone=?,"
                + "fax=?"
                + "WHERE code=?";

        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, newC.getCompany());
            stmt.setString(2, newC.getContact());
            stmt.setString(3, newC.getRole());
            stmt.setString(4, newC.getAddress());
            stmt.setString(5, newC.getCity());
            stmt.setString(6, newC.getRegion());
            stmt.setString(7, newC.getZipCode());
            stmt.setString(8, newC.getCountry());
            stmt.setString(9, newC.getPhone());
            stmt.setString(10, newC.getFax());
            stmt.setString(11, oldC.getCode());

            int i = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (res > 0)
            return true;
        return false;
    }

}
