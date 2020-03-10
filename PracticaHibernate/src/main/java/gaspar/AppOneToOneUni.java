package gaspar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;

public class AppOneToOneUni {

    public static void main(String[] args) {
        //guardarProfesor();
        //leerProfesor();
        //leerProfesorLoad();
        //modificarProfesor();
        //borrarProfesor();
        //guardarOActualizarProfesor();
        //queryClass();
    }

    private static void guardarProfesor() {
        try {
            //Obtenemos el SessionFactory
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            //Abrimos la sesión mediante el SessionFactory
            Session session = sessionFactory.openSession();

            //Creamos el objeto FabricanteSede
            FabricanteSede fab = new FabricanteSede(1, "Apple", "1997", 0);
            Transaction tx = session.beginTransaction();

            session.save(fab);//Aquí guardamos el objeto en la base de datos.

            tx.commit();
            session.close();
            sessionFactory.close();
        } catch (PersistenceException e) {
            System.out.println("Clave primaria duplicada.");
        }
    }

    /*public static void leerProfesor() {
        //Obtenemos el SessionFactory

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //Abrimos la sesión mediante el SessionFactory

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Profesor4 profesor = session.get(Profesor4.class, 102);
            System.out.println("Profesor: " + profesor.getNombre());
        } catch (NullPointerException e) {
            System.out.println("El identificador no ha sido encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void leerProfesorLoad() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        try {
            Profesor4 profesor = session.load(Profesor4.class, 102);
            System.out.println("Profesor: " + profesor.getNombre());
        } catch (org.hibernate.ObjectNotFoundException e) {
            System.out.println("El valor clave no se ha encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void modificarProfesor() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        try {
            Profesor4 profesor = session.get(Profesor4.class, 101);
            System.out.println("Profesor: " + profesor.getNombre());
            System.out.println("Profesor: " + profesor.getNombre());
            profesor.setNombre("Pedro");
            session.update(profesor);
        } catch (NullPointerException e) {
            System.out.println("El identificador no ha sido encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void borrarProfesor() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        try {
            Profesor4 profesor = session.get(Profesor4.class, 101);
            System.out.println("Profesor:" + profesor.getNombre());
            session.delete(profesor);
        } catch (NullPointerException e) {
            System.out.println("ID no encontrado en la base de datos.");
        }
        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void guardarOActualizarProfesor() {

        Profesor6 profesor = new Profesor6(7, "Sara", "Barrrera", "Salas");
        List<CorreoElectronico6> correosElectronicos = new ArrayList<CorreoElectronico6>();
        correosElectronicos.add(new CorreoElectronico6(3, "sara@yahoo.com", profesor));
        correosElectronicos.add(new CorreoElectronico6(2, "sara@hotmail.com", profesor));
        correosElectronicos.add(new CorreoElectronico6(1, "sara@gmail.com", profesor));

        profesor.setCorreosElectronicos(correosElectronicos);

        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(profesor);

        session.getTransaction().commit();
        session.close();
    }

    public static void queryClass() {
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
