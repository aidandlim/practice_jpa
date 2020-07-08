package practice_jpa;

import practice_jpa.entity.Member;
import practice_jpa.entity.MemberType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String args[]) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice_jpa");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
//            member.setId(1L);
            member.setName("hellojpa");
            member.setMemberType(MemberType.USER);

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        System.out.println("Done!");

        emf.close();
    }
}
