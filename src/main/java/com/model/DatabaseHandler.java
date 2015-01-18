package com.model;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class DatabaseHandler {

    private static final String JNDI = "java:jboss/datasources/PostgreSQLDS";
    private static DatabaseHandler databaseHandler;
    private static InitialContext context;
    private static DataSource dataSource;
    private Connection conn;
    private static Collection<Item> items;

    public static DatabaseHandler initialize() {
        if (databaseHandler == null) return new DatabaseHandler();
        else return databaseHandler;
    }

    private DatabaseHandler() {
        try {
            context = new InitialContext();
            dataSource = (DataSource) this.context.lookup(JNDI);
            databaseHandler = this;
            //log.info("Created DatabaseHandler");
        } catch (NamingException e) {
            //log.error(e);
        }
    }

    private boolean connect() throws SQLException {
        //log.info("Trying to connect to datasource...");
        this.conn = dataSource.getConnection();
        //log.info("Set connection success");
        return conn != null;
    }


    public Collection<Item> getItemByPattern(String pattern) {
        pattern = pattern.toLowerCase();
        Collection<Item> items = new ArrayList<Item>();
        Collection<Item> all = getTempData();
        for (Item item : all) {
            String fName = item.getFirstName();
            String lName = item.getLastName();
            if (fName.equals(pattern) || lName.equals(pattern)) {
                items.add(item);
            }
        }
        for (Item item : all) {
            String fName = item.getFirstName().toLowerCase();
            String lName = item.getLastName().toLowerCase();
            if ((fName).startsWith(pattern) || (lName).startsWith(pattern)) {
                if (!items.contains(item))
                    items.add(item);
            }
        }
        if (pattern.length() > 2) {
            for (Item item : all) {
                String fName = item.getFirstName().toLowerCase();
                String lName = item.getLastName().toLowerCase();
                if ((fName + lName).contains(pattern)) {
                    if (!items.contains(item))
                        items.add(item);
                }
            }
        }

        return items;
    }

    public void updateItem(int id, Item item) {

        System.out.println(item.getFirstName() + " " + item.getLastName());

        Item resultItem = null;
        for (Item i : items) {
            if (id == i.getId()) {
                i = item;
                resultItem = i;
                break;
            }
        }
        boolean re = items.remove(resultItem);
        System.out.println(re);
        items.add(item);
        //update(items);
        for (Item i : items) {
            if (id == i.getId()) {
                System.out.println(item.getFirstName() + " " + item.getLastName());
                break;
            }
        }
    }

    public Item getItemById(int id) {
        Collection<Item> items = getTempData();
        Item resultItem = null;
        for (Item i : items) {
            if (id == i.getId()) {
                resultItem = i;
                break;
            }
        }
        return resultItem;
    }

    private Collection<Item> getTempData() {
        if(items == null) {
            System.out.println("Reinstall");
            items = new HashSet<Item>();
            items.add(new Item(1, "Johann Sebastian", "Bach", "Baroque"));
            items.add(new Item(2, "Arcangelo", "Corelli", "Baroque"));
            items.add(new Item(3, "George Frideric", "Handel", "Baroque"));
            items.add(new Item(4, "Henry", "Purcell", "Baroque"));
            items.add(new Item(5, "Jean-Philippe", "Rameau", "Baroque"));
            items.add(new Item(6, "Domenico", "Scarlatti", "Baroque"));
            items.add(new Item(7, "Antonio", "Vivaldi", "Baroque"));
            items.add(new Item(8, "Ludwig van", "Beethoven", "Classical"));
            items.add(new Item(9, "Johannes", "Brahms", "Classical"));
            items.add(new Item(10, "Francesco", "Cavalli", "Classical"));
            items.add(new Item(11, "Fryderyk Franciszek", "Chopin", "Classical"));
            items.add(new Item(12, "Antonin", "Dvorak", "Classical"));
            items.add(new Item(13, "Franz Joseph", "Haydn", "Classical"));
            items.add(new Item(14, "Gustav", "Mahler", "Classical"));
            items.add(new Item(15, "Wolfgang Amadeus", "Mozart", "Classical"));
            items.add(new Item(16, "Johann", "Pachelbel", "Classical"));
            items.add(new Item(17, "Gioachino", "Rossini", "Classical"));
            items.add(new Item(18, "Dmitry", "Shostakovich", "Classical"));
            items.add(new Item(19, "Richard", "Wagner", "Classical"));
            items.add(new Item(20, "Louis-Hector", "Berlioz", "Romantic"));
            items.add(new Item(21, "Georges", "Bizet", "Romantic"));
            items.add(new Item(22, "Cesar", "Cui", "Romantic"));
            items.add(new Item(23, "Claude", "Debussy", "Romantic"));
            items.add(new Item(24, "Edward", "Elgar", "Romantic"));
            items.add(new Item(25, "Gabriel", "Faure", "Romantic"));
            items.add(new Item(26, "Cesar", "Franck", "Romantic"));
            items.add(new Item(27, "Edvard", "Grieg", "Romantic"));
            items.add(new Item(28, "Nikolay", "Rimsky-Korsakov", "Romantic"));
            items.add(new Item(29, "Franz Joseph", "Liszt", "Romantic"));
            items.add(new Item(30, "Felix", "Mendelssohn", "Romantic"));
            items.add(new Item(31, "Giacomo", "Puccini", "Romantic"));
            items.add(new Item(32, "Sergei", "Rachmaninoff", "Romantic"));
            items.add(new Item(33, "Camille", "Saint-Saens", "Romantic"));
            items.add(new Item(34, "Franz", "Schubert", "Romantic"));
            items.add(new Item(35, "Robert", "Schumann", "Romantic"));
            items.add(new Item(36, "Jean", "Sibelius", "Romantic"));
            items.add(new Item(37, "Bedrich", "Smetana", "Romantic"));
            items.add(new Item(38, "Richard", "Strauss", "Romantic"));
            items.add(new Item(39, "Pyotr Il'yich", "Tchaikovsky", "Romantic"));
            items.add(new Item(40, "Guiseppe", "Verdi", "Romantic"));
            items.add(new Item(41, "Bela", "Bartok", "Post-Romantic"));
            items.add(new Item(42, "Leonard", "Bernstein", "Post-Romantic"));
            items.add(new Item(43, "Benjamin", "Britten", "Post-Romantic"));
            items.add(new Item(44, "John", "Cage", "Post-Romantic"));
            items.add(new Item(45, "Aaron", "Copland", "Post-Romantic"));
            items.add(new Item(46, "George", "Gershwin", "Post-Romantic"));
            items.add(new Item(47, "Sergey", "Prokofiev", "Post-Romantic"));
            items.add(new Item(48, "Maurice", "Ravel", "Post-Romantic"));
            items.add(new Item(49, "Igor", "Stravinsky", "Post-Romantic"));
            items.add(new Item(50, "Carl", "Orff", "Post-Romantic"));
        }
        return items;
    }

    public void update(Collection<Item> nItems) {
        items = nItems;
    }
}
