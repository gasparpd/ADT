package ejemplo01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;

public class App3 {
    public static void main(String[] args) {
        //guardarProfesor();
        //leerProfesor();
        //leerProfesorLoad();
        //modificarProfesor();
        //borrarProfesor();
        guardarOActualizarProfesor();
    }

    private static void guardarProfesor() {
        try {
            //Obtenemos el SessionFactory

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            //Abrimos la sesión mediante el SessionFactory
            Session session = sessionFactory.openSession();

            //Creamos el objeto
            Profesor3 profesor = new Profesor3(101, "Alberto", "Perez", "García");
            Transaction tx = session.beginTransaction();

            session.save(profesor);//Aquí guardamos el objeto en la base de datos.

            tx.commit();
            session.close();
            sessionFactory.close();
        } catch (PersistenceException e) {
            System.out.println("Clave primaria duplicada.");
        }
    }

    public static void leerProfesor() {
        //Obtenemos el SessionFactory

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //Abrimos la sesión mediante el SessionFactory

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Profesor3 profesor = session.get(Profesor3.class, 102);
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
            Profesor3 profesor = session.load(Profesor3.class, 102);
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
            Profesor3 profesor = session.get(Profesor3.class, 101);
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
            Profesor3 profesor = session.get(Profesor3.class, 101);
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
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        //Creamos el objeto
        Direccion3 direccion = new Direccion3(1, "Plaza del ayuntamiento", 8, "Xativa", "Valencia");
        Profesor3 profesor = new Profesor3(1, "Juan", "Perez", "García");
        profesor.setDireccion(direccion);
        //Guardamos el profesor
        session.saveOrUpdate(profesor);

        tx.commit();
        session.close();
        sessionFactory.close();
    }
}