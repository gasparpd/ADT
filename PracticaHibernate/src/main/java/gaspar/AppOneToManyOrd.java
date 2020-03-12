package gaspar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.Set;

public class AppOneToManyOrd {

    public static void main(String[] args) {
        //saveFabricante();     //Hecho
        //getFabricante();      //Hecho
        //loadFabricante();     //No implementado
        //updateFabricante();   //Hecho
        //deleteFabricante();   //Hecho
        //saveOrUpdateFabricante();   //Hecho
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
            Set<SmartphoneD> smartphones = new HashSet<>();
            SmartphoneD smart = new SmartphoneD(1, fab, "M5", "5", 250);
            smartphones.add(smart);
            smart = new SmartphoneD(2, fab, "X2 PRO", "5,6", 350);
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
            Set<SmartphoneD> smartphones = fab.getSmartphones();

            System.out.println(fab.toString());

            for (SmartphoneD s : smartphones) {
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
            Set<SmartphoneD> smartphones = new HashSet<>();

            SmartphoneD smart = new SmartphoneD(1, fab, "6 PRO", "6,4", 250);
            smartphones.add(smart);
            smart = new SmartphoneD(2, fab, "X2 PRO", "6,5", 350);
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

            Set<SmartphoneD> smartphones = fab.getSmartphones();
            for (SmartphoneD s : smartphones) {
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

        FabricanteSmart fab = new FabricanteSmart(1, "REALME", "2019", null);

        // Creamos un nuevo HashSet de smartphones
        Set<SmartphoneD> smartphones = new HashSet<>();

        SmartphoneD smart = new SmartphoneD(1, fab, "6 PRO", "6.6", 279);
        smartphones.add(smart);
        smart = new SmartphoneD(2, fab, "X50 PRO", "6.4", 599);
        smartphones.add(smart);

        fab.setSmartphones(smartphones);//Le añadimos al fabricante el nuevo HashSet de smartphones

        session.saveOrUpdate(fab);//Guardamos o actualizamos el fabricante

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
