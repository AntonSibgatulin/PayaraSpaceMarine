package my.compary.database;

import my.compary.psixol.*;
import my.compary.conf.SpaceMarineFilterRequest;
import my.compary.hibernate.HibernateUtils;
import my.compary.psixol.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class DatabaseModel {
    private String baseUrl = null;
    public static final Long default_long = 4L;

    public DatabaseModel() {

    }


    public SpaceMarine getSpaceMarineById(Long id) {


        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from SpaceMarine s WHERE s.id = " + id);
        SpaceMarine spaceMarine = (SpaceMarine) query.uniqueResult();
        session.getTransaction().commit();


        if (spaceMarine == null) return null;


        Coordinates coordinates = coordinates(id);
        Chapter chapter = chapter(id);
        StarShip starShip = spaceMarine.getStarshipId() != 0 ? starShip(spaceMarine.getStarshipId()) : null;

        spaceMarine.setCategorys(AstartesCategory.valueOf(spaceMarine.getCategory()));
        spaceMarine.setWeaponTypes(Weapon.valueOf(spaceMarine.getWeaponType()));
        spaceMarine.setMeleeWeapons(MeleeWeapon.valueOf(spaceMarine.getMeleeWeapon()));

        spaceMarine.setStarShip(starShip);
        spaceMarine.setChapter(chapter);
        spaceMarine.setCoordinates(coordinates);

        spaceMarine.loadTime();

        return spaceMarine;
    }


    public Coordinates coordinates(Long id) {
        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Coordinates s WHERE s.id = " + id);
        Coordinates coordinates = (Coordinates) query.uniqueResult();
        session.getTransaction().commit();
        ;
        return coordinates;
    }


    public Chapter chapter(Long id) {
        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Chapter s WHERE s.id = " + id);
        Chapter chapter = (Chapter) query.uniqueResult();
        session.getTransaction().commit();
        return chapter;
    }


    public StarShip starShip(Long id) {
       /* Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from StarShip s WHERE s.id = " + id);
        StarShip starShip = (StarShip) query.uniqueResult();
        session.getTransaction().commit();

        if(starShip==null)return starShip(default_long);
        starShip.setStarShipTypes(StarShipType.valueOf(starShip.starShipType));

        */
        String x = null;
        try {
            x = getStarShip(id);
            System.out.println("STARSHIP " + x);

        } catch (IOException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
        }
        if (x == null || x.contains("<action>")) {
            try {
                x = getStarShip(default_long);
                System.out.println("STARSHIP " + x);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }

        }
        System.out.println("STARSHIP " + x);

        StarShip employee = null;
        InputStream is = new ByteArrayInputStream(x.getBytes(StandardCharsets.UTF_8));
        JAXBContext jaxbContext;

        try {
            jaxbContext = JAXBContext.newInstance(StarShip.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            employee = (StarShip) jaxbUnmarshaller.unmarshal(is);


        } catch (JAXBException e) {
            e.printStackTrace();

        }
        employee.setStarShipTypes(StarShipType.valueOf(employee.getStarShipType()));
        return employee;
    }


    public void deleteSpaceMarine(SpaceMarine spaceMarine) {
        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from SpaceMarine s WHERE s.id = " + spaceMarine.getId());
        query.executeUpdate();
        session.getTransaction().commit();

        deleteChapter(spaceMarine);
        deleteCoordinates(spaceMarine);

        // return starShip;

    }

    public void deleteChapter(SpaceMarine spaceMarine) {
        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Chapter s WHERE s.id = " + spaceMarine.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public void deleteCoordinates(SpaceMarine spaceMarine) {
        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Coordinates s WHERE s.id = " + spaceMarine.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }


    public void saveSpaceMarine(SpaceMarine spaceMarine) {
        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        session.save(spaceMarine);
        session.getTransaction().commit();
        spaceMarine.getChapter().setId(spaceMarine.getId());

        spaceMarine.getCoordinates().setId(spaceMarine.getId());


        saveCoordinates(spaceMarine.getCoordinates());
        saveChapter(spaceMarine.getChapter());
    }


    public void saveCoordinates(Coordinates coordinates) {
        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        session.save(coordinates);
        session.getTransaction().commit();
    }

    public void saveChapter(Chapter chapter) {
        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        session.save(chapter);
        session.getTransaction().commit();
    }

    public void updateSpaceMarine(SpaceMarine spaceMarine) {
        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        session.update(spaceMarine);

        session.getTransaction().commit();

        spaceMarine.getChapter().setId(spaceMarine.getId());

        spaceMarine.getCoordinates().setId(spaceMarine.getId());


        updateCoordinates(spaceMarine.getCoordinates());
        updateChapter(spaceMarine.getChapter());

    }


    public void updateCoordinates(Coordinates coordinates) {
        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        session.update(coordinates);
        session.getTransaction().commit();
    }

    public void updateChapter(Chapter chapter) {
        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        session.update(chapter);
        session.getTransaction().commit();
    }


    public String search(SpaceMarineFilterRequest spaceMarineFilterRequest) throws IOException, JSONException {

        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        Query query = searchQuery(session, spaceMarineFilterRequest);

        query.setFirstResult(spaceMarineFilterRequest.getLimit() * (spaceMarineFilterRequest.getPage() - 1));

        query.setMaxResults(spaceMarineFilterRequest.getLimit());

        List<SpaceMarine> spaceMarines =  query.list();


        session.getTransaction().commit();

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        String xml = "<SpaceMarines>";
        for (SpaceMarine spaceMarine : spaceMarines) {
            if (spaceMarine == null) continue;


            Coordinates coordinates = coordinates(spaceMarine.getId());
            Chapter chapter = chapter(spaceMarine.getId());
            StarShip starShip = starShip(spaceMarine.getStarshipId());

            spaceMarine.setCategorys(AstartesCategory.valueOf(spaceMarine.getCategory()));
            spaceMarine.setWeaponTypes(Weapon.valueOf(spaceMarine.getWeaponType()));
            spaceMarine.setMeleeWeapons(MeleeWeapon.valueOf(spaceMarine.getMeleeWeapon()));

            spaceMarine.setStarShip(starShip);
            spaceMarine.setChapter(chapter);
            spaceMarine.setCoordinates(coordinates);

            spaceMarine.loadTime();

            xml += spaceMarine.toSearchXML();
        }
        // jsonObject.put("SpaceMarines",jsonArray);


        xml += "</SpaceMarines>";
        return xml;//XML.toString(xml);
    }


    public Query searchQuery(Session session, SpaceMarineFilterRequest spaceMarineFilterRequest) {

        String sortBy = spaceMarineFilterRequest.getSortBy();
        if (!sortBy.equals("name") && !sortBy.equals("health") &&
                !sortBy.equals("creationDate") &&
                !sortBy.equals("category") &&
                !sortBy.equals("weaponType") &&
                !sortBy.equals("meleeWeapon")) {
            sortBy = "s.name";
        } else {
            sortBy = "s." + sortBy;
        }
        Query query = null;

        if (spaceMarineFilterRequest.getHealth() != null) {
            query = session.createQuery("from SpaceMarine s WHERE s.health > " + spaceMarineFilterRequest.getHealth() + " AND s.health < " + spaceMarineFilterRequest.getMaxhealth() + " ORDER BY " + sortBy + " DESC");
        } else if (spaceMarineFilterRequest.getName() != null) {
            query = session.createQuery("from SpaceMarine s WHERE s.name=:name ORDER BY " + sortBy + " DESC");
            query.setString("name", spaceMarineFilterRequest.getName());

        } else if (spaceMarineFilterRequest.getCreationDate() != null) {
            Instant instant = Instant.parse(spaceMarineFilterRequest.getCreationDate());
            long millisecondsSinceUnixEpoch = instant.toEpochMilli();

            query = session.createQuery("from SpaceMarine s WHERE s.creatinoDateUnix > " + millisecondsSinceUnixEpoch + " ORDER BY " + sortBy + " DESC");

        } else if (spaceMarineFilterRequest.getCategory() != null) {

            query = session.createQuery("from SpaceMarine s WHERE s.category = :category" + " ORDER BY " + sortBy + " DESC");
            query.setString("category", spaceMarineFilterRequest.getCategory());

        } else if (spaceMarineFilterRequest.getWeaponType() != null) {
            query = session.createQuery("from SpaceMarine s WHERE s.weaponType = :weaponType" + " ORDER BY " + sortBy + " DESC");
            query.setString("weaponType", spaceMarineFilterRequest.getWeaponType());

        } else if (spaceMarineFilterRequest.getMeleeWeapon() != null) {
            query = session.createQuery("from SpaceMarine s WHERE s.meleeWeapon = :meleeWeapon" + " ORDER BY " + sortBy + " DESC");
            query.setString("meleeWeapon", spaceMarineFilterRequest.getMeleeWeapon());

        } else {
            query = session.createQuery("from SpaceMarine s WHERE s.health > 10 ORDER BY " + sortBy + " DESC");

        }

        //  if(spaceMarineFilterRequest.get)
        return query;
    }


    public String getListLessThenCurrent(List<Weapon> list) {


        String all = "";
        int i = 0;
        for (Weapon weapon : list) {
            if (i == 0) {
                i = 1;
                all += " s.weaponType = '" + weapon.toString() + "'";
            } else {
                all += " OR s.weaponType = '" + weapon.toString() + "'";
            }
        }

        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from SpaceMarine s WHERE " + all);
        List<SpaceMarine> spaceMarines = (List<SpaceMarine>) query.list();


        session.getTransaction().commit();

        String xml = "<SpaceMarines>";
        if (spaceMarines.size() == 0) {
            return null;
        }
        for (SpaceMarine spaceMarine : spaceMarines) {
            if (spaceMarine == null) continue;


            Coordinates coordinates = coordinates(spaceMarine.getId());
            Chapter chapter = chapter(spaceMarine.getId());
            StarShip starShip = starShip(spaceMarine.getStarshipId());

            spaceMarine.setCategorys(AstartesCategory.valueOf(spaceMarine.getCategory()));
            spaceMarine.setWeaponTypes(Weapon.valueOf(spaceMarine.getWeaponType()));
            spaceMarine.setMeleeWeapons(MeleeWeapon.valueOf(spaceMarine.getMeleeWeapon()));

            spaceMarine.setStarShip(starShip);
            spaceMarine.setChapter(chapter);
            spaceMarine.setCoordinates(coordinates);

            spaceMarine.loadTime();

            xml += spaceMarine.toSearchXML();
        }
        // jsonObject.put("SpaceMarines",jsonArray);


        xml += "</SpaceMarines>";
        return xml;//XML.toString(xml);
    }


    public List<Chapter> getChapterById(Long marines) {

        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Chapter s WHERE s.marinesCount > " + marines);


        List<Chapter> list = query.list();

        session.getTransaction().commit();
        return list;

    }

    public SpaceMarine getSpaceMarineMinimalByMeleeWeapon(MeleeWeapon meleeWeapon) {
        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from SpaceMarine s WHERE s.meleeWeapon = :meleeWeapon AND  s.weaponType = 'BOLTGUN'");
        query.setString("meleeWeapon", meleeWeapon.toString());
        query.setMaxResults(1);

        SpaceMarine spaceMarine = (SpaceMarine) query.uniqueResult();
        session.getTransaction().commit();


        if (spaceMarine == null) return null;


        Coordinates coordinates = coordinates(spaceMarine.getId());
        Chapter chapter = chapter(spaceMarine.getId());
        StarShip starShip = starShip(spaceMarine.getStarshipId());

        spaceMarine.setCategorys(AstartesCategory.valueOf(spaceMarine.getCategory()));
        spaceMarine.setWeaponTypes(Weapon.valueOf(spaceMarine.getWeaponType()));
        spaceMarine.setMeleeWeapons(MeleeWeapon.valueOf(spaceMarine.getMeleeWeapon()));

        spaceMarine.setStarShip(starShip);
        spaceMarine.setChapter(chapter);
        spaceMarine.setCoordinates(coordinates);

        spaceMarine.loadTime();
        return spaceMarine;
    }


    public SpaceMarine getSpaceMarineMinimalByMeleeWeapon(Weapon weapon) {
        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from SpaceMarine s WHERE s.weaponType = :meleeWeapon ORDER BY s.health ASC");
        query.setString("meleeWeapon", weapon.toString());
        query.setMaxResults(1);

        SpaceMarine spaceMarine = (SpaceMarine) query.uniqueResult();
        session.getTransaction().commit();


        if (spaceMarine == null) return null;


        Coordinates coordinates = coordinates(spaceMarine.getId());
        Chapter chapter = chapter(spaceMarine.getId());
        StarShip starShip = starShip(spaceMarine.getStarshipId());

        spaceMarine.setCategorys(AstartesCategory.valueOf(spaceMarine.getCategory()));
        spaceMarine.setWeaponTypes(Weapon.valueOf(spaceMarine.getWeaponType()));
        spaceMarine.setMeleeWeapons(MeleeWeapon.valueOf(spaceMarine.getMeleeWeapon()));

        spaceMarine.setStarShip(starShip);
        spaceMarine.setChapter(chapter);
        spaceMarine.setCoordinates(coordinates);

        spaceMarine.loadTime();
        return spaceMarine;
    }


    public SpaceMarine getSpaceMarineMinimalByMeleeWeapon() {
        Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from SpaceMarine s ORDER BY s.health ASC");
        //query.setString("meleeWeapon",weapon.toString());
        query.setMaxResults(1);

        SpaceMarine spaceMarine = (SpaceMarine) query.uniqueResult();
        session.getTransaction().commit();


        if (spaceMarine == null) return null;


        Coordinates coordinates = coordinates(spaceMarine.getId());
        Chapter chapter = chapter(spaceMarine.getId());
        StarShip starShip = starShip(spaceMarine.getStarshipId());

        spaceMarine.setCategorys(AstartesCategory.valueOf(spaceMarine.getCategory()));
        spaceMarine.setWeaponTypes(Weapon.valueOf(spaceMarine.getWeaponType()));
        spaceMarine.setMeleeWeapons(MeleeWeapon.valueOf(spaceMarine.getMeleeWeapon()));

        spaceMarine.setStarShip(starShip);
        spaceMarine.setChapter(chapter);
        spaceMarine.setCoordinates(coordinates);

        spaceMarine.loadTime();
        return spaceMarine;
    }


    public String getStarShip(Long id) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {


        if(baseUrl==null) {

            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(new File("configure.json")));
            String all1 = "";
            String pie1 = null;
            while ((pie1 = bufferedReader1.readLine()) != null) {

                all1 += pie1;

            }
            baseUrl = "https://localhost:9696";
            try {
                JSONObject jsonObject = new JSONObject(all1);
                baseUrl = jsonObject.getString("host");

            } catch (JSONException e) {
                // e.printStackTrace(); ignore
            }

            if (baseUrl.endsWith("/")) {
                baseUrl = baseUrl.substring(0, baseUrl.length() - 2);

            }


        }


        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
            public void checkClientTrusted(X509Certificate[] certs, String authType) { }
            public void checkServerTrusted(X509Certificate[] certs, String authType) { }

        } };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());


        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) { return true; }
        };

        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);


        URL url = new URL(baseUrl+"/starships/get/"+id);
        URLConnection con = url.openConnection();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String all = "";
        String pie = null;
        while((pie = bufferedReader.readLine())!=null){
            all+=pie;
        }

        return all;

    }


    public void enter(SpaceMarine spaceMarine, Long id) {
        spaceMarine.setStarshipId(id);
        org.hibernate.classic.Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        session.update(spaceMarine);
        session.getTransaction().commit();

    }


    public void unloadAll(Long id) {


        org.hibernate.classic.Session session = HibernateUtils.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from SpaceMarine s where s.starshipId = " + id);
        List<SpaceMarine> spaceMarines = query.list();

        session.getTransaction().commit();


        for (SpaceMarine spaceMarine : spaceMarines) {
            spaceMarine.setStarshipId(0L);
            session = HibernateUtils.sessionFactory.openSession();
            session.beginTransaction();
            session.update(spaceMarine);
            session.getTransaction().commit();

        }


    }

}
