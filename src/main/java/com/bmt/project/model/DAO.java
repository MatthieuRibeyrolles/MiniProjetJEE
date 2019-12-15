package com.bmt.project.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

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
    private List<LineEntity> lLines;

    public DAO(DataSource myDAO) {
        this.myDAO = myDAO;
        this.lClients = new ArrayList<>();
        this.lOrders = new ArrayList<>();
        this.lCategories = new ArrayList<>();
        this.lProducts = new ArrayList<>();
    }

    public List<ClientEntity> getClientsList() {
        if (this.lClients.isEmpty()) {
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return this.lClients;
    }

    public List<OrderEntity> getOrdersList() {
        if (this.lOrders.isEmpty()) {
            String sql = "SELECT client,envoyee_le,port,destinataire,adresse_livraison,ville_livraison,region_livraison,code_postal_livrais,pays_livraison,remise FROM Commande";
            try (Connection con = this.myDAO.getConnection();
                    PreparedStatement stmt = con.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery()) {
                this.lOrders = new ArrayList<>();
                while (rs.next())
                    this.lOrders.add(new OrderEntity(
                            getClientByCode(rs.getString("client")),
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return this.lOrders;
    }

    public List<CategoryEntity> getCategoriesList() {
        if (this.lCategories.isEmpty()) {
            String sql = "SELECT code,libelle,description FROM Categorie";
            try (Connection con = this.myDAO.getConnection();
                    PreparedStatement stmt = con.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery()) {
                this.lCategories = new ArrayList<>();
                while (rs.next())
                    this.lCategories.add(
                            new CategoryEntity(
                                    rs.getInt("code"),
                                    rs.getString("libelle"),
                                    rs.getString("description")
                            )
                    );
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return this.lCategories;
    }

    public List<ProductEntity> getProductsList() {
        if (this.lProducts.isEmpty()) {
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return this.lProducts;
    }

    public List<LineEntity> getLinesList() {
        if (this.lLines.isEmpty()) {
            String sql = "SELECT * FROM Ligne";
            try (Connection con = this.myDAO.getConnection();
                    PreparedStatement stmt = con.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next())
                    this.lLines.add(new LineEntity(getOrderByCode(rs.getInt(1)), getProductByCode(rs.getInt(2)), rs.getInt(3)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return this.lLines;
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ClientEntity getClientByCode(String codeC) {
        if (this.lClients.isEmpty())
            getClientsList();
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
            if (res > 0)
                for (ClientEntity client : this.lClients)
                    if (client.getCode() == null ? oldC.getCode() == null : client.getCode().equals(oldC.getCode()))
                        client = newC;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res > 0;
    }

    public ClientEntity addClient(ClientEntity newC) {
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
            int res = stmt.executeUpdate();
            if (res > 0) {
                this.lClients.add(newC);
                return newC;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public OrderEntity addOrder(OrderEntity newO) {
        int res;
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
                return new OrderEntity(
                        res,
                        newO.getClient(),
                        newO.getDateSent(),
                        newO.getPort(),
                        newO.getReceiver(),
                        newO.getAddress(),
                        newO.getCity(),
                        newO.getRegion(),
                        newO.getZipcode(),
                        newO.getCountry(),
                        newO.getDiscount()
                );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public CategoryEntity getCategoryByCode(int code) {
        if (this.lCategories.isEmpty())
            getCategoriesList();
        for (CategoryEntity category : this.lCategories)
            if (category.getCode() == code)
                return category;
        return null;
    }

    public OrderEntity getOrderByCode(int code) {
        if (this.lOrders.isEmpty())
            getOrdersList();
        for (OrderEntity order : this.lOrders)
            if (order.getNum() == code)
                return order;
        return null;
    }

    public ProductEntity getProductByCode(int code) {
        if (this.lProducts.isEmpty())
            getProductsList();
        for (ProductEntity product : this.lProducts)
            if (product.getReference() == code)
                return product;
        return null;
    }

    public List<OrderEntity> getOrderListByClient(String codeC) {
        List<OrderEntity> tmp = new ArrayList();
        if (this.lOrders.isEmpty()) {
            getClientsList();
            getOrdersList();
        }
        for (OrderEntity order : this.lOrders)
            if (order.getClient().getCode().equals(codeC))
                tmp.add(order);
        return tmp;
    }

    public boolean updateOrder(OrderEntity oldO, OrderEntity newO) {
        int res = 0;
        String sql = "UPDATE Commande "
                + "SET envoyee_le=?,"
                + "port=?"
                + "destinataire=?"
                + "adresse_livraison=?"
                + "ville_livraison=?"
                + "region_livraison"
                + "code_postal_livrais"
                + "pays_livraison"
                + "remise"
                + "WHERE client=?,"
                + "AND Numero=?";
        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, newO.getDateSent());
            stmt.setFloat(2, newO.getPort());
            stmt.setString(3, newO.getReceiver());
            stmt.setString(4, newO.getAddress());
            stmt.setString(5, newO.getCity());
            stmt.setString(6, newO.getRegion());
            stmt.setString(7, newO.getZipcode());
            stmt.setString(8, newO.getCountry());
            stmt.setFloat(9, newO.getDiscount());
            stmt.setString(10, oldO.getClient().getCode());
            stmt.setInt(11, oldO.getNum());
            res = stmt.executeUpdate();
            if (res > 0)
                for (OrderEntity order : this.lOrders)
                    if (order.getNum() == oldO.getNum())
                        order = newO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res > 0;
    }

    public LineEntity addLineToCommand(LineEntity newL) {
        String sql = "INSERT INTO Ligne(commande,produit,quantite) VALUES (?,?,?)";
        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, newL.getOrder().getNum());
            stmt.setInt(2, newL.getProduct().getReference());
            stmt.setInt(3, newL.getQty());
            int res = stmt.executeUpdate();
            if (res != 0) {
                this.lLines.add(newL);
                return newL;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Map<String, Float> getRevenuesByCountryBetweenDates(Date d1, Date d2) {
        java.util.Date dateU1 = new java.util.Date(d1.getTime());
        java.util.Date dateU2 = new java.util.Date(d2.getTime());
        Map<String, Float> rev = new HashMap();
        if (this.lLines.isEmpty()) {
            getClientsList();
            getOrdersList();
            getProductsList();
            getLinesList();
        }
        for (LineEntity line : this.lLines) {
            java.util.Date orderDate = new java.util.Date(line.getOrder().getDateSent().getTime());
            if (orderDate.after(dateU1) && orderDate.before(dateU2)) {
                String country = line.getOrder().getCity();
                float oldPrice = 0f;
                float prodPrice = line.getProduct().getPrice();
                int qty = line.getQty();
                if (rev.get(country) != null)
                    oldPrice = rev.get(country);
                rev.put(country, oldPrice + (prodPrice * qty));
            }
        }
        return rev;
    }

    public Map<String, Float> getRevenuesByCategoryBetweenDates(Date d1, Date d2) {
        java.util.Date dateU1 = new java.util.Date(d1.getTime());
        java.util.Date dateU2 = new java.util.Date(d2.getTime());
        Map<String, Float> rev = new HashMap();
        if (this.lLines.isEmpty()) {
            getClientsList();
            getOrdersList();
            getProductsList();
            getLinesList();
        }
        for (LineEntity line : this.lLines) {
            java.util.Date orderDate = new java.util.Date(line.getOrder().getDateSent().getTime());
            if (orderDate.after(dateU1) && orderDate.before(dateU2)) {
                String cat = line.getProduct().getCategory().getWording();
                float oldPrice = 0f;
                float prodPrice = line.getProduct().getPrice();
                int qty = line.getQty();
                if (rev.get(cat) != null)
                    oldPrice = rev.get(cat);
                rev.put(cat, oldPrice + (prodPrice * qty));
            }
        }
        return rev;
    }

    public Map<String, Float> getRevenuesByClientBetweenDates(Date d1, Date d2) {
        java.util.Date dateU1 = new java.util.Date(d1.getTime());
        java.util.Date dateU2 = new java.util.Date(d2.getTime());
        Map<String, Float> rev = new HashMap();
        if (this.lLines.isEmpty()) {
            getClientsList();
            getOrdersList();
            getProductsList();
            getLinesList();
        }
        for (LineEntity line : this.lLines) {
            java.util.Date orderDate = new java.util.Date(line.getOrder().getDateSent().getTime());
            if (orderDate.after(dateU1) && orderDate.before(dateU2)) {
                String client = line.getOrder().getClient().getContact();
                float oldPrice = 0f;
                float prodPrice = line.getProduct().getPrice();
                int qty = line.getQty();
                if (rev.get(client) != null)
                    oldPrice = rev.get(client);
                rev.put(client, oldPrice + (prodPrice * qty));
            }
        }
        return rev;
    }

    public List<LineEntity> getLineListByOrder(OrderEntity order) {
        List<LineEntity> tmp = new ArrayList();
        if (this.lLines.isEmpty()) {
            getClientsList();
            getOrdersList();
            getProductsList();
            getLinesList();
        }
        for (LineEntity line : lLines)
            if (line.getOrder().equals(order))
                tmp.add(line);
        return tmp;
    }

    public boolean updateLine(LineEntity oldL, LineEntity newL) {
        int res = 0;
        String sql = "UPDATE Ligne SET quantite=? WHERE commande=? AND produit=?";
        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, newL.getQty());
            stmt.setInt(2, oldL.getOrder().getNum());
            stmt.setInt(3, oldL.getProduct().getReference());
            res = stmt.executeUpdate();
            if (res > 0)
                for (LineEntity line : this.lLines)
                    if (line.getOrder().equals(oldL.getOrder()) && line.getProduct().equals(oldL.getProduct()))
                        line = newL;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res > 0;
    }

    public boolean deleteLine(LineEntity line) {
        int res = 0;
        String sql = "DELETE FROM Ligne WHERE commande=? AND produit=?";
        try (Connection con = this.myDAO.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, line.getOrder().getNum());
            stmt.setInt(2, line.getProduct().getReference());
            res = stmt.executeUpdate();
            if (res > 0)
                for (LineEntity lineE : this.lLines)
                    if (lineE.getOrder().equals(line.getOrder()) && lineE.getProduct().equals(line.getProduct()))
                        this.lLines.remove(lineE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res > 0;
    }
    
}
