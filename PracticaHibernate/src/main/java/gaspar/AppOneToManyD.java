package gaspar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.Set;

public class AppOneToManyD {

    public static void main(String[] args) {
        saveFabricante();     //Hecho
        //getFabricante();      //
        //loadFabricante();     //
        //updateFabricante();   //
        //deleteFabricante();   //
        //saveOrUpdateFabricante();
        //queryClass();
    }

    private static void saveFabricante() {
        try {
            //Obtenemos el SessionFactory
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            //Abrimos la sesión mediante el SessionFactory
            Session session = sessionFactory.openSession();

            Transaction tx = session.beginTransaction();

            //Creamos el objeto FabricanteSmart y le pasamos su smart
            FabricanteSmart fab = new FabricanteSmart(1, "BQ", "1997", null);
            Set<Smartphone> smartphones = new HashSet<>();
            Smartphone smart = new Smartphone(1, fab, "M5", "5", 250);
            smartphones.add(smart);
            smart = new Smartphone(2, fab, "X2 PRO", "5,6", 350);
            smartphones.add(smart);

            fab.setSmartphones(smartphones);//Le pasamos el hashset de smartphones

            session.save(fab);  //Guardamos el objeto en la base de datos

            tx.commit();
            session.close();
            sessionFactory.close();
        } catch (PersistenceException e) {
            System.out.println("Clave primaria duplicada.");
        }
    }

    public static void getFabricante() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        //Obtenemos el objeto con el método get y lo imprimimos (con su sede)
        try {
            FabricanteSede fab = session.get(FabricanteSede.class, 1);
            System.out.println("Fabricante: " + fab.getNombre());
            System.out.println("Sede: " + fab.getSede().toString());
        } catch (NullPointerException e) {
            System.out.println("El identificador no ha sido encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void loadFabricante() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        //Obtenemos el objeto con el método get y lo imprimimos (con su sede)
        try {
            FabricanteSede fab = session.load(FabricanteSede.class, 1);
            System.out.println("Fabricante: " + fab.getNombre());
            System.out.println("Sede: " + fab.getSede().toString());
        } catch (org.hibernate.ObjectNotFoundException e) {
            System.out.println("El valor clave no se ha encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void updateFabricante() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        try {
            FabricanteSede fab = session.get(FabricanteSede.class, 1);  //Obtenemos el fabricante
            System.out.println("Fabricante: " + fab.getNombre());

            fab.setNombre("REALME"); //Modificamos su nombre

            // Obtenemos y modificamos su sede
            Sede sede = fab.getSede();
            sede.setPais("China");
            sede.setLocalidad("Shenzen");
            sede.setCpostal("32560");

            fab.setSede(sede);//Le añadimos al fabricante la nueva sede creada

            session.update(fab);//Actualizamos el fabricante
        } catch (NullPointerException e) {
            System.out.println("El identificador no ha sido encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void deleteFabricante() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        //Obtenemos el objeto, imprimimos su nombre y lo borramos
        try {
            FabricanteSede fab = session.get(FabricanteSede.class, 1);
            System.out.println("Fabricante:" + fab.getNombre());
            session.delete(fab);
        } catch (NullPointerException e) {
            System.out.println("ID no encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void saveOrUpdateFabricante() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        FabricanteSede fab = new FabricanteSede(2, "BQ", "2002", null);

        // Creamos y añadimos la sede al fabricante
        Sede sede = new Sede(fab.getId(), "SPAIN", "MADRID", "400392");
        fab.setSede(sede);//Le añadimos al fabricante la nueva sede creada

        session.saveOrUpdate(fab);

        session.getTransaction().commit();
        session.close();
    }

    /*public static void queryClass() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT p FROM Profesor p");
        List<Profesor> profesores = query.list();
        for (Profesor profesor : profesores) {
            System.out.println(profesor.getNombre());
        }

        session.close();
        sessionFactory.close();
    }*/
}
