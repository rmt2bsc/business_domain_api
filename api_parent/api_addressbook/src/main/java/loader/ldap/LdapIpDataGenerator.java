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

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.dao.mapping.orm.ldap.LdapIp;

import com.api.persistence.PersistenceClient;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2File;
import com.util.RMT2String;
import com.util.RMT2String2;

/**
 * Generates multiple .ldif files containing IP Address entries to be imported
 * into a LDAP server.
 * 
 * @author Roy Terrell
 * 
 */
public class LdapIpDataGenerator {

    private static final String UNKNOWN_VALUE = "Unknown";

    /**
     * The maximum number of bytes the internal buffer holds before dumping
     * generated data to the output file.
     */
    protected static final int WRITE_BUFFER_SIZE = 2500;

    /**
     * The complete path and file name of the output file.
     */
    protected static final String OUTPUT_FILENAME = "C:\\tmp\\09-Ip-";

    protected static final String OUTPUT_FILENAME_EXT = ".ldif";

    /**
     * The base DN for all entries added
     */
    protected static final String BASE_DN = "ou=Countries,ou=IpLocations,o=RMT2BSC,dc=rmt2,dc=net";

    private String ipLocSql = "select ip_location.loc_id, country, region, city, postal_code, latitude, longitude, metro_code, area_code from ip_location, mv_ip_count where country like '?%' and ip_location.loc_id = mv_ip_count.loc_id order by country, region, city, metro_code, area_code, postal_code";

    private String ipBlockSql = "select ip_start, ip_end from ip_block where ip_loc = ? order by ip_start";

    protected String countryCodes[];

    private Connection con;

    private BufferedOutputStream bos;

    private StringBuffer buffer;

    /**
     * Creates a LdapIpDataGenerator which initializes the database connection,
     * the output file, and the country codes that are to be processed.
     */
    public LdapIpDataGenerator() {
        this.countryCodes = this.loadCountryCodes();
        System.out.println(this.countryCodes.length);

        // Get db connection
        PersistenceClient client = Rmt2OrmClientFactory.createOrmClientInstance();
        this.con = (Connection) client.getConnection();
    }

    public void openFile(String countryCode) {
        // Setup output file
        File file = new File(OUTPUT_FILENAME + countryCode + OUTPUT_FILENAME_EXT);
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
    public void closeFile() {
        try {
            this.bos.flush();
            this.bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] loadCountryCodes() {
        ResourceBundle rb = RMT2File.loadAppConfigProperties("loader.IpCountryCodes");
        String codeList = rb.getString("codes");
        String codeArray[] = codeList.split(",");
        return codeArray;
    }

    /**
     * The driver for processing all country codes.
     * 
     * @return The total number of IP address ranges added for all country codes
     */
    public int createLdapIpData() {
        int rows = 0;
        buffer = new StringBuffer();
        for (String countryCode : this.countryCodes) {
            this.openFile(countryCode);
            rows = this.processIpLocations(countryCode);
            this.closeFile();
        }

        return rows;
    }

    private int processIpLocations(String countryCode) {
        int rows = 0;
        Statement stmt = null;
        ResultSet rs = null;
        String sqlReplacement = null;
        List<LdapIp> ipRec = new ArrayList<LdapIp>();
        try {
            System.out.println("Fetching data from the ip_location table...");
            stmt = this.con.createStatement();
            sqlReplacement = RMT2String.replace(this.ipLocSql, String.valueOf(countryCode), "?");
            rs = stmt.executeQuery(sqlReplacement);
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
                String temp = rs.getString("region");

                rec.setIpRegion(RMT2String2.isNullOrEmpty(temp) ? UNKNOWN_VALUE : temp);
                temp = rs.getString("city");
                rec.setIpCity(RMT2String2.isNullOrEmpty(temp) ? UNKNOWN_VALUE : temp);
                temp = rs.getString("postal_code");
                rec.setIpZip(RMT2String2.isNullOrEmpty(temp) ? UNKNOWN_VALUE : temp);
                temp = rs.getString("metro_code");
                rec.setIpMetroCode(RMT2String2.isNullOrEmpty(temp) ? UNKNOWN_VALUE : temp);
                temp = rs.getString("area_code");
                rec.setIpAreaCode(RMT2String2.isNullOrEmpty(temp) ? UNKNOWN_VALUE : temp);
                ipRec.add(rec);
            }
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

        System.out.println("Total IP Location records loaded: " + ipRec.size());

        String entry = null;

        try {

            String prevCountry = null;
            String prevRegion = null;
            String prevCity = null;
            String prevZip = null;
            String prevAreaCode = null;
            for (LdapIp ip : ipRec) {

                // Write Country data
                if (prevCountry == null || !prevCountry.equalsIgnoreCase(ip.getIpCountry())) {
                    entry = this.getIpCountryHeader(ip.getIpCountry());
                    prevCountry = ip.getIpCountry();
                    buffer.append(entry);
                    prevRegion = null;
                    prevCity = null;
                    prevZip = null;
                    prevAreaCode = null;
                }

                // Write Region detail data
                if (prevRegion == null || !prevRegion.equalsIgnoreCase(ip.getIpRegion())) {
                    entry = this.getIpRegion(ip);
                    prevRegion = ip.getIpRegion();
                    buffer.append(entry);
                    prevCity = null;
                    prevZip = null;
                    prevAreaCode = null;
                }

                // Write City data
                if (prevCity == null || !prevCity.equalsIgnoreCase(ip.getIpCity())) {
                    entry = this.getIpCity(ip);
                    prevCity = ip.getIpCity();
                    buffer.append(entry);
                    prevZip = null;
                    prevAreaCode = null;
                }

                // Write area code
                if (prevAreaCode == null || !prevAreaCode.equalsIgnoreCase(ip.getIpAreaCode())) {
                    entry = this.getIpAreaCode(ip);
                    prevAreaCode = ip.getIpAreaCode();
                    buffer.append(entry);
                    prevZip = null;
                }

                // Write zip code
                if (prevZip == null || !prevZip.equalsIgnoreCase(ip.getIpZip())) {
                    entry = this.getIpZipCode(ip);
                    prevZip = ip.getIpZip();
                    buffer.append(entry);
                }

                StringBuffer ipKey = new StringBuffer();

                ipKey.append("country[");
                ipKey.append(ip.getIpCountry());
                ipKey.append("] region[");
                ipKey.append(ip.getIpRegion());
                ipKey.append("] city[");
                ipKey.append(ip.getIpCity());
                ipKey.append("] area code[");
                ipKey.append(ip.getIpAreaCode());
                ipKey.append("] zip code[");
                ipKey.append(ip.getIpZip());
                ipKey.append("]");

                System.out.println("");
                System.out.println("Adding IP address block(s) for " + ipKey + "...");
                try {
                    stmt = this.con.createStatement();
                    sqlReplacement = RMT2String.replace(ipBlockSql, String.valueOf(ip.getIpLocId()), "?");
                    rs = stmt.executeQuery(sqlReplacement);
                    int blockCount = 0;
                    while (rs.next()) {
                        BigDecimal start = rs.getBigDecimal("ip_start");
                        BigDecimal end = rs.getBigDecimal("ip_end");
                        ip.setIpFrom(start.toPlainString());
                        ip.setIpTo(end.toPlainString());

                        // Write IP detail data
                        rows++;
                        blockCount++;
                        entry = this.getCityIpBlock(ip);
                        buffer.append(entry);
                    }
                    System.out.println("Sucessfully added IP address block(s) for " + ipKey + "...");
                    System.out.println("Total number of IP Address blocks added: " + blockCount);
                    System.out.println("");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    rs.close();
                    stmt.close();
                }

                // Write base DN entry to file
                if (buffer.length() > WRITE_BUFFER_SIZE) {
                    this.writeDataToFile(buffer.toString());
                    buffer.delete(0, buffer.length());
                }
            } // end for
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }

    private void writeDataToFile(String entry) {
        try {
            this.bos.write(entry.getBytes());
            this.bos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getIpCountryHeader(String country) {
        StringBuffer buf = new StringBuffer();
        buf.append("dn: ipCountryCode=");
        buf.append(country);
        buf.append(",ou=Countries,ou=IpLocations,o=RMT2BSC,dc=rmt2,dc=net");
        buf.append("\n");
        buf.append("objectClass: top");
        buf.append("\n");
        buf.append("objectClass: RMT2IpCountry");
        buf.append("\n");
        buf.append("ipCountryCode: ");
        buf.append(country);
        buf.append("\n");
        buf.append("\n");
        return buf.toString();
    }

    private String getIpRegion(LdapIp ip) {
        StringBuffer buf = new StringBuffer();
        buf.append("dn: ipRegion=");
        buf.append(ip.getIpRegion());
        buf.append(",ipCountryCode=");
        buf.append(ip.getIpCountry());
        buf.append(",ou=Countries,ou=IpLocations,o=RMT2BSC,dc=rmt2,dc=net");
        buf.append("\n");
        buf.append("objectClass: top");
        buf.append("\n");
        buf.append("objectClass: RMT2IpRegion");
        buf.append("\n");
        buf.append("ipRegion: ");
        buf.append(ip.getIpRegion());
        buf.append("\n");
        buf.append("\n");
        return buf.toString();
    }

    private String getIpCity(LdapIp ip) {
        StringBuffer buf = new StringBuffer();
        buf.append("dn: city=");
        buf.append(ip.getIpCity());
        buf.append(",ipRegion=");
        buf.append(ip.getIpRegion());
        buf.append(",ipCountryCode=");
        buf.append(ip.getIpCountry());
        buf.append(",ou=Countries,ou=IpLocations,o=RMT2BSC,dc=rmt2,dc=net");
        buf.append("\n");
        buf.append("objectClass: top");
        buf.append("\n");
        buf.append("objectClass: RMT2IpCity");
        buf.append("\n");
        buf.append("city: ");
        buf.append(ip.getIpCity());
        buf.append("\n");
        buf.append("\n");
        return buf.toString();
    }

    private String getIpAreaCode(LdapIp ip) {
        StringBuffer buf = new StringBuffer();
        buf.append("dn: areaCode=");
        buf.append(ip.getIpAreaCode());
        buf.append(",city=");
        buf.append(ip.getIpCity());
        buf.append(",ipRegion=");
        buf.append(ip.getIpRegion());
        buf.append(",ipCountryCode=");
        buf.append(ip.getIpCountry());
        buf.append(",ou=Countries,ou=IpLocations,o=RMT2BSC,dc=rmt2,dc=net");
        buf.append("\n");
        buf.append("objectClass: top");
        buf.append("\n");
        buf.append("objectClass: RMT2IpAreaCode");
        buf.append("\n");
        buf.append("areaCode: ");
        buf.append(ip.getIpAreaCode());
        buf.append("\n");
        buf.append("\n");
        return buf.toString();
    }

    private String getIpZipCode(LdapIp ip) {
        StringBuffer buf = new StringBuffer();
        buf.append("dn: zip=");
        buf.append(ip.getIpZip());
        buf.append(",areaCode=");
        buf.append(ip.getIpAreaCode());
        buf.append(",city=");
        buf.append(ip.getIpCity());
        buf.append(",ipRegion=");
        buf.append(ip.getIpRegion());
        buf.append(",ipCountryCode=");
        buf.append(ip.getIpCountry());
        buf.append(",ou=Countries,ou=IpLocations,o=RMT2BSC,dc=rmt2,dc=net");
        buf.append("\n");
        buf.append("objectClass: top");
        buf.append("\n");
        buf.append("objectClass: RMT2IpZipCode");
        buf.append("\n");
        buf.append("zip: ");
        buf.append(ip.getIpZip());
        buf.append("\n");
        buf.append("\n");
        return buf.toString();
    }

    private String getCityIpBlock(LdapIp ip) {
        StringBuffer buf = new StringBuffer();
        String uid = ip.getIpFrom() + "-" + ip.getIpTo();
        buf.append("dn: uid=");
        buf.append(uid);
        buf.append(",zip=");
        buf.append(ip.getIpZip());
        buf.append(",areaCode=");
        buf.append(ip.getIpAreaCode());
        buf.append(",city=");
        buf.append(ip.getIpCity());
        buf.append(",ipRegion=");
        buf.append(ip.getIpRegion());
        buf.append(",ipCountryCode=");
        buf.append(ip.getIpCountry());
        buf.append(",ou=Countries,ou=IpLocations,o=RMT2BSC,dc=rmt2,dc=net");
        buf.append("\n");
        buf.append("objectClass: top");
        buf.append("\n");
        buf.append("objectClass: RMT2IpBlock");
        buf.append("\n");
        buf.append("uid: ");
        buf.append(uid);
        buf.append("\n");
        buf.append("city: ");
        buf.append(ip.getIpCity());
        buf.append("\n");
        buf.append("ipRegion: ");
        buf.append(ip.getIpRegion());
        buf.append("\n");
        buf.append("ipCountryCode: ");
        buf.append(ip.getIpCountry());
        buf.append("\n");
        if (!RMT2String2.isNullOrEmpty(ip.getIpAreaCode())) {
            buf.append("areaCode: ");
            buf.append(ip.getIpAreaCode());
            buf.append("\n");
        }
        buf.append("latitude: ");
        buf.append(ip.getIpLatitude());
        buf.append("\n");
        buf.append("longitude: ");
        buf.append(ip.getIpLongitude());
        buf.append("\n");
        if (!RMT2String2.isNullOrEmpty(ip.getIpMetroCode())) {
            buf.append("metroCode: ");
            buf.append(ip.getIpMetroCode());
            buf.append("\n");
        }
        if (!RMT2String2.isNullOrEmpty(ip.getIpZip())) {
            buf.append("zip: ");
            buf.append(ip.getIpZip());
            buf.append("\n");
        }

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
        LdapIpDataGenerator loader = new LdapIpDataGenerator();
        loader.createLdapIpData();
        System.out.println("LDAP generator complete");
    }
}
