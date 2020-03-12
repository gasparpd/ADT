package gaspar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.Set;

public class AppOneToManyD {

    public static void main(String[] args) {
        //saveFabricante();     //Hecho
        //getFabricante();      //Hecho
        //loadFabricante();     //No implementado
        //updateFabricante();   //Hecho
        //deleteFabricante();   //Hecho
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

        //Obtenemos el objeto con el método get y lo imprimimos (con sus smartphones)
        try {
            FabricanteSmart fab = session.get(FabricanteSmart.class, 1);
            Set<Smartphone> smartphones = fab.getSmartphones();

            System.out.println(fab.toString());

            for (Smartphone s : smartphones) {
                System.out.println(s.toString());
            }
        } catch (NullPointerException e) {
            System.out.println("El identificador no ha sido encontrado en la base de datos.");
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
            FabricanteSmart fab = session.get(FabricanteSmart.class, 1);  //Obtenemos el fabricante
            System.out.println(fab.toString());

            fab.setNombre("REALME"); //Modificamos su nombre

            // Creamos un nuevo HashSet de smartphones
            Set<Smartphone> smartphones = new HashSet<>();

            Smartphone smart = new Smartphone(1, fab, "6 PRO", "6,4", 250);
            smartphones.add(smart);
            smart = new Smartphone(2, fab, "X2 PRO", "6,5", 350);
            smartphones.add(smart);

            fab.setSmartphones(smartphones);//Le añadimos al fabricante el nuevo HashSet de smartphones

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
            FabricanteSmart fab = session.get(FabricanteSmart.class, 1);
            System.out.println(fab.toString());

            Set<Smartphone> smartphones = fab.getSmartphones();
            for (Smartphone s : smartphones) {
                System.out.println(s.toString());
            }

            session.delete(fab);//Eliminamos el fabricante
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
