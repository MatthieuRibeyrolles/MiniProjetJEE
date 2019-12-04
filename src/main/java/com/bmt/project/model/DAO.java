package com.bmt.project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private List<ProductEntity> lProducts;

    public DAO(DataSource myDAO) {
        this.myDAO = myDAO;
        this.lClients = new ArrayList<>();
        this.lOrders = new ArrayList<>();
        this.lCategories = new ArrayList<>();

    }

    public List<ClientEntity> getClientsList() {
        String sql = "SELECT code,societe,contact,fonction,adresse,ville,region,code_postal,pays,telephone,fax FROM CLIENT";

        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            this.lClients = new ArrayList<>();
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
            System.out.println(e.getMessage());
        }
        return this.lClients;
    }

    public List<OrderEntity> getOrdersList() {
        String sql = "SELECT client,envoyee_le,port,destinataire,adresse_livraison,ville_livraison,region_livraison,code_postal_livrais,pays_livraison,remise FROM Commande";

        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            this.lOrders = new ArrayList<>();
            while (rs.next())
                this.lOrders.add(new OrderEntity(
                        getClientBycode(rs.getString("client")),
                        rs.getDate("envoyee_le"),
                        rs.getFloat("port"),
                        rs.getString("destinataire"),
                        rs.getString("adresse_livraison"),
                        rs.getString("ville_livraison"),
                        rs.getString("region_livraison"),
                        rs.getString("code_postal_livrais"),
                        rs.getString("pays_livraison"),
                        rs.getFloat("remise")
                ));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return this.lOrders;
    }

    public List<CategoryEntity> getCategoriesList() {
        String sql = "SELECT code,libelle,description FROM Categorie";

        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            this.lCategories = new ArrayList<>();
            while (rs.next())
                this.lCategories.add(new CategoryEntity(rs.getInt("code"), rs.getString("libelle"), rs.getString("description")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return this.lCategories;
    }

    public List<ProductEntity> getProductsList() {
        String sql = "SELECT reference,nom,fournisseur,categorie,quantite_par_unite,prix_unitaire,unites_en_stock,unites_commandees,niveau_de_reappro,indisponible FROM Produit";

        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            this.lProducts = new ArrayList<>();
            while (rs.next())
                this.lProducts.add(
                        new ProductEntity(
                                rs.getInt("reference"),
                                rs.getString("nom"),
                                rs.getInt("fournisseur"),
                                getCategoryByCode(rs.getInt("categorie")),
                                rs.getString("quantite_par_unite"),
                                rs.getFloat("prix_unitaire"),
                                rs.getInt("unites_en_stock"),
                                rs.getInt("unites_commandees"),
                                rs.getInt("niveau_de_reappro"),
                                rs.getBoolean("indisponible")
                        )
                );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return this.lProducts;
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
            System.out.println(e.getMessage());
        }
        return null; // Renvoyer directement clientEntity
    }

    public ClientEntity getClientBycode(String codeC) {
        for (ClientEntity client : this.lClients)
            if (client.getCode() == null ? codeC == null : client.getCode().equals(codeC))
                return client;
        return null;
    }

    public boolean updateClient(ClientEntity oldC, ClientEntity newC) {
        int res = 0;
        String sql = "UPDATE Client "
                + "SET code=?,"
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
            System.out.println(e.getMessage());
        }

        return res > 0;
    }

    public boolean addClient(ClientEntity newC) {
        int res = 0;
        String sql = "INSERT INTO Client (code,societe,contact,fonction,adresse,ville,region,code_postal,pays,telephone,fax)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            System.out.println(e.getMessage());
        }

        return res > 0;
    }

    public OrderEntity addOrder(OrderEntity newO) {
        int res = 0;

        String sqlU = "INSERT INTO Commande (client,envoyee_le,port,destinataire,adresse_livraison,ville_livraison,region_livraison,code_postal_livrais,pays_livraison,remise)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmtU = con.prepareStatement(sqlU, Statement.RETURN_GENERATED_KEYS);) {

            stmtU.setString(1, newO.getClient().getCode());
            stmtU.setDate(2, newO.getDateSent());
            stmtU.setFloat(3, newO.getPort());
            stmtU.setString(4, newO.getReceiver());
            stmtU.setString(5, newO.getAddress());
            stmtU.setString(6, newO.getCity());
            stmtU.setString(7, newO.getRegion());
            stmtU.setString(8, newO.getZipcode());
            stmtU.setString(9, newO.getCountry());
            stmtU.setFloat(10, newO.getDiscount());

            res = stmtU.executeUpdate();
            if (res != 0)
                return new OrderEntity(res, newO.getClient(), newO.getDateSent(), newO.getPort(), newO.getReceiver(), newO.getAddress(), newO.getCity(), newO.getRegion(), newO.getZipcode(), newO.getCountry(), newO.getDiscount());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

//    public boolean deleteOrder(OrderEntity newO) {
//        String sql = "DELETE FROM Commande WHERE client=? AND envoyee_le=? AND port=? AND";
//    }
//    public boolean updateOrder(OrderEntity newO) {
//
//    }
    public CategoryEntity getCategoryByCode(int code) {
        for (CategoryEntity category : this.lCategories)
            if (category.getCode() == code)
                return category;
        return null;
    }

}
