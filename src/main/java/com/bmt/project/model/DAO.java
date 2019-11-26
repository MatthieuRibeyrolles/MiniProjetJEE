package com.bmt.project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thibault
 */
public class DAO {

    private final DataSource myDAO;
    private List<ClientEntity> lClients;
    private List<OrderEntity> lOrders;
    private List<CategoryEntity> lCategories;

    public DAO(DataSource myDAO) {
        this.myDAO = myDAO;
        this.lClients = new ArrayList<>();
        this.lOrders = new ArrayList<>();
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
        }
        return this.lClients;
    }

    public List<OrderEntity> getOrdersList() {
        String sql = "SELECT client,envoyee_le,port,destinataire,adresse_livraison,ville_livraison,region_livraison,code_postal_livraison,pays_livraison,remise FROM Commande";

        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next())
                this.lOrders.add(new OrderEntity(
                        getClientBycode(rs.getString("client")),
                        rs.getDate("envoyee_le"),
                        rs.getFloat("port"),
                        rs.getString("destinataire"),
                        rs.getString("adresse_livraison"),
                        rs.getString("ville_livraison"),
                        rs.getString("region_livraison"),
                        rs.getString("code_postal_livraison"),
                        rs.getString("pays_livraison"),
                        rs.getFloat("remise")
                ));
        } catch (SQLException e) {
        }
        return this.lOrders;
    }
    
    public List<CategoryEntity> getCategoriesList(){
        String sql = "SELECT code,libelle,description FROM Categorie";

        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next())
                this.lCategories.add(new CategoryEntity(rs.getInt("code"), rs.getString("libelle"), rs.getString("description")));
        } catch (SQLException e) {
        }
        return this.lCategories;
    }
    
    public List<ClientEntity> getClientsListWithoutDBQuery() {
        return this.lClients;
    }

    public List<OrderEntity> getOrdersListWithoutDBQuery() {
        return this.lOrders;
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
        }
        return null; // Renvoyer directement clientEntity
    }

    public ClientEntity getClientBycode(String codeC) {
        for (ClientEntity client : this.lClients)
            if (client.getCode() == codeC)
                return client;
        return null;
    }

    public boolean updateClient(ClientEntity oldC, ClientEntity newC) {
        int res = 0;
        String sql = "UPDATE Client"
                + "SET code = ?,"
                + "societe=?,"
                + "contact=?,"
                + "fonction=?,"
                + "adresse=?,"
                + "ville=?,"
                + "region=?,"
                + "code_postal=?,"
                + "pays=?,"
                + "telephone=?,"
                + "fax=?"
                + "WHERE code=?"
                + "AND contact=?";

        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, newC.getCode());
            stmt.setString(2, newC.getCompany());
            stmt.setString(3, newC.getContact());
            stmt.setString(4, newC.getRole());
            stmt.setString(5, newC.getAddress());
            stmt.setString(6, newC.getCity());
            stmt.setString(7, newC.getRegion());
            stmt.setString(8, newC.getZipCode());
            stmt.setString(9, newC.getCountry());
            stmt.setString(10, newC.getPhone());
            stmt.setString(11, newC.getFax());
            stmt.setString(12, oldC.getCode());
            stmt.setString(13, oldC.getContact());

            res = stmt.executeUpdate();

            for (ClientEntity client : this.lClients)
                if (client.getCode() == null ? oldC.getCode() == null : client.getCode().equals(oldC.getCode()))
                    client = oldC;

        } catch (SQLException e) {
        }

        return res > 0;
    }

    public boolean addClient(ClientEntity newC) {
        int res = 0;
        String sql = "INSERT INTO Client (code,societe,contact,fonction,adresse,ville,region,code_postal,pays,telephone,fax)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, newC.getCode());
            stmt.setString(2, newC.getCompany());
            stmt.setString(3, newC.getContact());
            stmt.setString(4, newC.getRole());
            stmt.setString(5, newC.getAddress());
            stmt.setString(6, newC.getCity());
            stmt.setString(7, newC.getRegion());
            stmt.setString(8, newC.getZipCode());
            stmt.setString(9, newC.getCountry());
            stmt.setString(10, newC.getPhone());
            stmt.setString(11, newC.getFax());

            res = stmt.executeUpdate();
            this.lClients.add(newC);

        } catch (SQLException e) {
        }

        return res > 0;
    }

    public boolean addOrder(OrderEntity newO) {
        int res = 0;
        String sql = "INSERT INTO Commande (client,envoyee_le,port,destinataire,adresse_livraison,ville_livraison,region_livraison,code_postal_livraison,pays_livraison,remise)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, newO.getClient().getCode());
            stmt.setDate(2, newO.getDateSent());
            stmt.setFloat(3, newO.getPort());
            stmt.setString(4, newO.getReceiver());
            stmt.setString(5, newO.getAddress());
            stmt.setString(6, newO.getCity());
            stmt.setString(7, newO.getRegion());
            stmt.setString(8, newO.getZipcode());
            stmt.setString(9, newO.getCountry());
            stmt.setFloat(10, newO.getDiscount());

            res = stmt.executeUpdate();
            this.lOrders.add(newO);

        } catch (SQLException e) {
        }

        return res > 0;
    }

//    public boolean deleteOrder(OrderEntity newO) {
//
//    }

//    public boolean updateOrder(OrderEntity newO) {
//
//    }

}
