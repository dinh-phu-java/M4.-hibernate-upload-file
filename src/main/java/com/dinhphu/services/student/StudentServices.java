package com.dinhphu.services.student;

import com.dinhphu.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.ArrayList;
import java.util.List;

public class StudentServices implements IStudentServices{
    @Override
    public List<Student> findAll() {
        SessionFactory myfac=new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
        Session session=null;
        ArrayList<Student> list= new ArrayList<>();
        try{
            session=myfac.getCurrentSession();
            session.beginTransaction();

            list.addAll(session.createQuery("from Student").getResultList());

            session.getTransaction().commit();

        }
        finally{
            session.close();
            myfac.close();
        }
        return list;
    }

    @Override
    public Student findById(int id) {
        SessionFactory myfac=new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
        Session session=null;
        Student student= null;
        try{
            session=myfac.getCurrentSession();
            session.beginTransaction();

            student=session.get(Student.class,id);

            session.getTransaction().commit();

        }
        finally{
            session.close();
            myfac.close();
        }
        return student;
    }

    @Override
    public void save(Student model) {
        SessionFactory myfac=new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
        Session session=null;

        try{
            session=myfac.getCurrentSession();
            session.beginTransaction();

            session.save(model);

            session.getTransaction().commit();

        }
        finally{
            session.close();
            myfac.close();
        }
    }

    @Override
    public void update(int id, Student model) {
        SessionFactory myfac=new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
        Session session=null;

        try{
            session=myfac.getCurrentSession();
            session.beginTransaction();

            Student editStudent=session.get(Student.class,id);
            if (editStudent!=null){
                editStudent.setName(model.getName());
                editStudent.setAvatar(model.getAvatar());
            }
            session.getTransaction().commit();
        }
        finally{
            session.close();
            myfac.close();
        }
    }

    @Override
    public void remove(int id) {
        SessionFactory myfac=new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
        Session session=null;

        try{
            session=myfac.getCurrentSession();
            session.beginTransaction();

            Student deleteStudent=session.get(Student.class,id);
            if (deleteStudent!=null){
                session.delete(deleteStudent);
            }


            session.getTransaction().commit();

        }
        finally{
            session.close();
            myfac.close();
        }

    }
}
