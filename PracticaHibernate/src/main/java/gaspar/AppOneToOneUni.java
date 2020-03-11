package gaspar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;

public class AppOneToOneUni {

    public static void main(String[] args) {
        //guardarFabricante();
        leerFabricante();
        /*leerFabricanteLoad();
        modificarFabricante();
        borrarFabricante();*/
        //guardarOActualizarFabricante();
        //queryClass();
    }

    private static void guardarFabricante() {
        try {
            //Obtenemos el SessionFactory
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            //Abrimos la sesión mediante el SessionFactory
            Session session = sessionFactory.openSession();

            //Creamos el objeto FabricanteSede y le pasamos su sede
            FabricanteSede fab = new FabricanteSede(1, "Apple", "1997", null);
            Transaction tx = session.beginTransaction();

            Sede sede = new Sede( 1, "España", "Madrid", "21012");
            fab.setSede(sede);

            session.save(fab);//Aquí guardamos el objeto en la base de datos.

            tx.commit();
            session.close();
            sessionFactory.close();
        } catch (PersistenceException e) {
            System.out.println("Clave primaria duplicada.");
        }
    }

    public static void leerFabricante() {
        //Obtenemos el SessionFactory

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //Abrimos la sesión mediante el SessionFactory

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            FabricanteSede fab = session.get(FabricanteSede.class, 1);
            System.out.println("Fabricante: " + fab.getNombre());
            System.out.println("Sede: " +fab.getSede().toString());
        } catch (NullPointerException e) {
            System.out.println("El identificador no ha sido encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void leerFabricanteLoad() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        try {
            FabricanteSede fab = session.load(FabricanteSede.class, 102);
            System.out.println("Fabricante: " + fab.getNombre());
        } catch (org.hibernate.ObjectNotFoundException e) {
            System.out.println("El valor clave no se ha encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void modificarFabricante() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        try {
            FabricanteSede fab = session.get(FabricanteSede.class, 101);
            System.out.println("Fabricante: " + fab.getNombre());
            fab.setNombre("OPPO");
            session.update(fab);
        } catch (NullPointerException e) {
            System.out.println("El identificador no ha sido encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void borrarFabricante() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        try {
            FabricanteSede fab = session.get(FabricanteSede.class, 101);
            System.out.println("Fabricante:" + fab.getNombre());
            session.delete(fab);
        } catch (NullPointerException e) {
            System.out.println("ID no encontrado en la base de datos.");
        }
        tx.commit();
        session.close();
        sessionFactory.close();
    }

    /*public static void guardarOActualizarFabricante() {

        FabricanteSede fab = new FabricanteSede(7, "Sara", "Barrrera", "Salas");
        List<CorreoElectronico6> correosElectronicos = new ArrayList<CorreoElectronico6>();
        correosElectronicos.add(new CorreoElectronico6(3, "sara@yahoo.com", fab));
        correosElectronicos.add(new CorreoElectronico6(2, "sara@hotmail.com", fab));
        correosElectronicos.add(new CorreoElectronico6(1, "sara@gmail.com", fab));

        fab.setCorreosElectronicos(correosElectronicos);

        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(fab);

        session.getTransaction().commit();
        session.close();
    }*/

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
