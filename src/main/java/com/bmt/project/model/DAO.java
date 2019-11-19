package com.bmt.project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            System.out.println("getClientList");
            int size = 0;
            ClientEntity c = null;
                while (rs.next()) {
                    this.lClients.add(
                            new ClientEntity(
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
                                    rs.getString("fax")
                            )
                    );
                }
        } catch (Exception e) {
        }
        System.out.println(String.format("Il y a %d clients", lClients.size()));
        for (ClientEntity client : lClients) {
            System.out.println(String.format("\tClient: %s", client));
        }

        return this.lClients;
    }

}
