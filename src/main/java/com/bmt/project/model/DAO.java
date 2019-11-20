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
        } catch (Exception e) {
        }
        return this.lClients;
    }

    public boolean login(String log, String pass) {

        String sql = "SELECT * FROM Client WHERE contact=? AND code=?";

        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, log);
            stmt.setString(2, pass);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

}
