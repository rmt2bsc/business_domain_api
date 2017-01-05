package loader.ldap;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dao.mapping.orm.ldap.LdapIp;

import com.api.persistence.PersistenceClient;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2String2;

/**
 * Generates multiple .ldif files containing IP Address entries to be imported
 * into a LDAP server.
 * 
 * @author Roy Terrell
 * 
 */
public class LdapFlatIpDataGenerator {

    // private static final String UNKNOWN_VALUE = "Unknown";

    /**
     * The maximum number of bytes the internal buffer holds before dumping
     * generated data to the output file.
     */
    protected static final int WRITE_BUFFER_SIZE = 2500;

    /**
     * The complete path and file name of the output file for IP Blocks.
     */
    protected static final String OUTPUT_FILENAME_BLOCKS = "C:\\tmp\\IP_BLCOKS.ldif";

    /**
     * The complete path and file name of the output file for IP Locations.
     */
    protected static final String OUTPUT_FILENAME_LOCATIONS = "C:\\tmp\\IP_LOCATIONS.ldif";

    /**
     * The base DN for all entries added
     */
    protected static final String BASE_DN = "ou=Countries,ou=IpLocations,o=RMT2BSC,dc=rmt2,dc=net";

    private String ipLocSql = "select loc_id, country, region, city, postal_code, latitude, longitude, metro_code, area_code from ip_location order by country, region, city, metro_code, area_code, postal_code";

    private String ipBlockSql = "select ip_start, ip_end, ip_loc from ip_block order by ip_loc, ip_start";

    private Connection con;

    private BufferedOutputStream bos;

    private StringBuffer buffer;

    /**
     * Creates a LdapIpDataGenerator which initializes the database connection,
     * the output file, and the country codes that are to be processed.
     */
    public LdapFlatIpDataGenerator() {
        // Get db connection
        PersistenceClient client = Rmt2OrmClientFactory
                .createOrmClientInstance();
        this.con = (Connection) client.getConnection();
    }

    private void openFile(String fileName) {
        // Setup output file
        File file = new File(fileName);
        try {
            // Create a file output stream that overwrite the file it exists.
            FileOutputStream fos = new FileOutputStream(file, false);
            this.bos = new BufferedOutputStream(fos);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the output file
     */
    private void closeFile() {
        try {
            this.bos.flush();
            this.bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The driver for processing all country codes.
     * 
     * @return The total number of IP address ranges added for all country codes
     */
    public int createLdapIpData() {
        int rows = 0;
        buffer = new StringBuffer();
        this.openFile(LdapFlatIpDataGenerator.OUTPUT_FILENAME_BLOCKS);
        rows = this.processIpBlocks();
        this.closeFile();

        buffer = new StringBuffer();
        this.openFile(LdapFlatIpDataGenerator.OUTPUT_FILENAME_LOCATIONS);
        rows = this.processIpLocations();
        this.closeFile();

        return rows;
    }

    private int processIpBlocks() {
        Statement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            System.out.println("Fetching data from the ip_block table...");
            stmt = this.con.createStatement();
            rs = stmt.executeQuery(this.ipBlockSql);
            LdapIp rec = null;
            while (rs.next()) {
                rec = new LdapIp();
                int locId = rs.getInt("ip_loc");
                BigDecimal start = rs.getBigDecimal("ip_start");
                BigDecimal end = rs.getBigDecimal("ip_end");
                rec.setIpFrom(start.toPlainString());
                rec.setIpTo(end.toPlainString());
                rec.setIpLocId(String.valueOf(locId));

                StringBuffer ipKey = new StringBuffer();
                ipKey.append("loc_id[");
                ipKey.append(rec.getIpLocId());
                ipKey.append("] Start IP[");
                ipKey.append(rec.getIpFrom());
                ipKey.append("] End IP[");
                ipKey.append(rec.getIpTo());
                ipKey.append("]");

                System.out.println("");
                System.out.println("Adding IP address block for location "
                        + ipKey + "...");
                String entry = this.buildIpBlockEntry(rec);
                buffer.append(entry);
                // Write base DN entry to file
                if (buffer.length() > WRITE_BUFFER_SIZE) {
                    this.writeDataToFile(buffer.toString());
                    buffer.delete(0, buffer.length());
                }
                count++;
            }
            if (buffer.length() > 0) {
                this.writeDataToFile(buffer.toString());
                buffer.delete(0, buffer.length());
            }
            System.out.println("Total IP Block Range records loaded: " + count);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count;
    }

    private int processIpLocations() {
        Statement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            System.out.println("Fetching data from the ip_location table...");
            stmt = this.con.createStatement();
            rs = stmt.executeQuery(this.ipLocSql);
            LdapIp rec = null;
            while (rs.next()) {
                rec = new LdapIp();
                int locId = rs.getInt("loc_id");
                BigDecimal lat = rs.getBigDecimal("latitude");
                BigDecimal lon = rs.getBigDecimal("longitude");
                rec.setIpLocId(String.valueOf(locId));
                rec.setIpLatitude(lat.toPlainString());
                rec.setIpLongitude(lon.toPlainString());
                rec.setIpCountry(rs.getString("country"));

                rec.setIpRegion(rs.getString("region"));
                rec.setIpCity(rs.getString("city"));
                rec.setIpZip(rs.getString("postal_code"));
                rec.setIpMetroCode(rs.getString("metro_code"));
                rec.setIpAreaCode(rs.getString("area_code"));

                // String temp = rs.getString("region");
                // rec.setIpRegion(RMT2String2.isNullOrEmpty(temp) ?
                // UNKNOWN_VALUE : temp);
                // temp = rs.getString("city");
                // rec.setCity(RMT2String2.isNullOrEmpty(temp) ? UNKNOWN_VALUE :
                // temp);
                // temp = rs.getString("postal_code");
                // rec.setZip(RMT2String2.isNullOrEmpty(temp) ? UNKNOWN_VALUE :
                // temp);
                // temp = rs.getString("metro_code");
                // rec.setMetroCode(RMT2String2.isNullOrEmpty(temp) ?
                // UNKNOWN_VALUE : temp);
                // temp = rs.getString("area_code");
                // rec.setAreaCode(RMT2String2.isNullOrEmpty(temp) ?
                // UNKNOWN_VALUE : temp);

                StringBuffer ipKey = new StringBuffer();
                ipKey.append("country[");
                ipKey.append(rec.getIpCountry());
                ipKey.append("] region[");
                ipKey.append(rec.getIpRegion());
                ipKey.append("] city[");
                ipKey.append(rec.getIpCity());
                ipKey.append("] area code[");
                ipKey.append(rec.getIpAreaCode());
                ipKey.append("] zip code[");
                ipKey.append(rec.getIpZip());
                ipKey.append("]");

                System.out.println("");
                System.out.println("Adding IP address location:  " + ipKey
                        + "...");
                String entry = this.buildLocationEntry(rec);
                buffer.append(entry);
                // Write base DN entry to file
                if (buffer.length() > WRITE_BUFFER_SIZE) {
                    this.writeDataToFile(buffer.toString());
                    buffer.delete(0, buffer.length());
                }
                count++;
            }
            if (buffer.length() > 0) {
                this.writeDataToFile(buffer.toString());
                buffer.delete(0, buffer.length());
            }
            System.out.println("Total IP Location records loaded: " + count);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return count;
    }

    private void writeDataToFile(String entry) {
        try {
            this.bos.write(entry.getBytes());
            this.bos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildLocationEntry(LdapIp rec) {
        StringBuffer buf = new StringBuffer();
        buf.append("dn: ipLocId=");
        buf.append(rec.getIpLocId());
        buf.append(",ou=Locations,ou=IpInfo,o=RMT2BSC,dc=rmt2,dc=net");
        buf.append("\n");
        buf.append("objectClass: top");
        buf.append("\n");
        buf.append("objectClass: RMT2IpLocation");
        buf.append("\n");
        buf.append("ipLocId: ");
        buf.append(rec.getIpLocId());
        buf.append("\n");
        buf.append("ipCountry: ");
        buf.append(rec.getIpCountry());
        buf.append("\n");

        if (!RMT2String2.isNullOrEmpty(rec.getIpAreaCode())) {
            buf.append("ipAreaCode: ");
            buf.append(rec.getIpAreaCode());
            buf.append("\n");
        }
        buf.append("ipLatitude: ");
        buf.append(rec.getIpLatitude());
        buf.append("\n");
        buf.append("ipLongitude: ");
        buf.append(rec.getIpLongitude());
        buf.append("\n");

        if (!RMT2String2.isNullOrEmpty(rec.getIpRegion())) {
            buf.append("ipRegion: ");
            buf.append(rec.getIpRegion());
            buf.append("\n");
        }
        if (!RMT2String2.isNullOrEmpty(rec.getIpCity())) {
            buf.append("ipCity: ");
            buf.append(rec.getIpCity());
            buf.append("\n");
        }

        if (!RMT2String2.isNullOrEmpty(rec.getIpMetroCode())) {
            buf.append("ipMetroCode: ");
            buf.append(rec.getIpMetroCode());
            buf.append("\n");
        }
        if (!RMT2String2.isNullOrEmpty(rec.getIpZip())) {
            buf.append("ipZip: ");
            buf.append(rec.getIpZip());
            buf.append("\n");
        }

        buf.append("\n");
        return buf.toString();
    }

    private String buildIpBlockEntry(LdapIp ip) {
        StringBuffer buf = new StringBuffer();
        String uid = ip.getIpFrom() + "-" + ip.getIpTo() + "-"
                + ip.getIpLocId();
        buf.append("dn: uid=");
        buf.append(uid);
        buf.append(",ou=Blocks,ou=IpInfo,o=RMT2BSC,dc=rmt2,dc=net");
        buf.append("\n");
        buf.append("objectClass: top");
        buf.append("\n");
        buf.append("objectClass: RMT2IpBlock");
        buf.append("\n");
        buf.append("uid: ");
        buf.append(uid);
        buf.append("\n");
        buf.append("ipRangeId: ");
        buf.append(ip.getIpLocId());
        buf.append("\n");
        buf.append("ipFrom: ");
        buf.append(ip.getIpFrom());
        buf.append("\n");
        buf.append("ipTo: ");
        buf.append(ip.getIpTo());
        buf.append("\n");

        buf.append("\n");
        return buf.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        LdapFlatIpDataGenerator loader = new LdapFlatIpDataGenerator();
        loader.createLdapIpData();
        System.out.println("LDAP generator complete");
    }
}
