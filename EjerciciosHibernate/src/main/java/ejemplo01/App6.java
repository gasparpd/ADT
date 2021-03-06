package ejemplo01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class App6 {
    public static void main(String[] args) {
        //saveProfesor();   //Hecho
        //getProfesor();    //Hecho
        //loadProfesor();
        //modificarProfesor();
        //borrarProfesor(); //Hecho
        //guardarOActualizarProfesor();
        queryClass();
    }

    private static void saveProfesor() {
        try {
            //Obtenemos el SessionFactory

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            //Abrimos la sesión mediante el SessionFactory
            Session session = sessionFactory.openSession();

            Transaction tx = session.beginTransaction();
            //Creamos el objeto
            Profesor6 profesor = new Profesor6(1, "Sara", "Perez", "García");
            List<CorreoElectronico6> mails = new ArrayList<CorreoElectronico6>();
            CorreoElectronico6 correo = new CorreoElectronico6(1, "sara@gmail.com", profesor);
            mails.add(correo);
            correo = new CorreoElectronico6(2, "sara@hotmail.com", profesor);
            mails.add(correo);
            correo = new CorreoElectronico6(3, "sara@outlook.com", profesor);
            mails.add(correo);

            profesor.setCorreosElectronicos(mails);

            session.save(profesor);//Aquí guardamos el objeto en la base de datos.

            tx.commit();
            session.close();
            sessionFactory.close();
        } catch (PersistenceException e) {
            System.out.println("Clave primaria duplicada.");
            e.printStackTrace();
        }
    }

    public static void getProfesor() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Profesor6 profesor = session.get(Profesor6.class, 1);
            System.out.println(profesor.toString());
            List<CorreoElectronico6> mails = profesor.getCorreosElectronicos();

            for (CorreoElectronico6 m : mails) {
                System.out.println(m.toString());
            }
        } catch (NullPointerException e) {
            System.out.println("El identificador no ha sido encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void loadProfesor() {
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
            Profesor6 profesor = session.get(Profesor6.class, 1);
            System.out.println(profesor.toString());

            List<CorreoElectronico6> mails = profesor.getCorreosElectronicos();
            for (CorreoElectronico6 m : mails) {
                System.out.println(m.toString());
            }

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

        /*
        //Ejercicio 22
        Query query = session.createQuery("SELECT p FROM Profesor6 p");
        List<Profesor6> profesores = query.list();
        for (Profesor6 profesor : profesores) {
            System.out.println(profesor.toString());
        }

        //Ejercicio 23
        query = session.createQuery("SELECT p.id,p.nombre FROM Profesor6 p");
        List<Object[]> listDatos = query.list();
        for (Object[] datos : listDatos) {
            System.out.println(datos[0] + "--" + datos[1]);
        }

        //Ejercicio 24
        query = session.createQuery("SELECT p.nombre FROM Profesor6 p");
        List<Object> listaDatos = query.list();
        for (Object datos : listaDatos) {
            System.out.println(datos);
        }

        //Ejercicio 25
        Profesor6 profesor = (Profesor6) session.createQuery("SELECT p FROM Profesor6 p WHERE id=1").uniqueResult();
        System.out.println("Profesor con Id 1 = " + profesor.getNombre());


        //Ejercicio 26
        int tamanyoPagina = 10;
        int paginaAMostrar = 0;

        Query query = session.createQuery("SELECT p FROM Profesor6 p Order By p.id");
        query.setMaxResults(tamanyoPagina);
        query.setFirstResult(paginaAMostrar * tamanyoPagina);
        List<Profesor6> profesores = query.list();

        for (Profesor6 profesor : profesores) {
            System.out.println(profesor.getNombre());
        }
        */

        //Ejercicio 27
        Query query = session.getNamedQuery("findAllProfesores");
        List<Profesor6> profesores = query.list();
        for (Profesor6 profesor : profesores) {
            System.out.println(profesor.toString());
        }

        session.close();
        sessionFactory.close();
    }
}