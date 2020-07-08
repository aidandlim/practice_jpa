package practice_jpa;

import practice_jpa.entity.Member;
import practice_jpa.entity.MemberType;
import practice_jpa.entity.Team;

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
            Team team = new Team();
            team.setName("team");

            em.persist(team);

            Member member = new Member();
            member.setName("member");

            // RDB 방식
//            member.setTeamId(team.getId());

            // 단방향 매핑 & 양방향 매핑
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            // RDB 방식
//            Member findMember = em.find(Member.class, member.getId());
//            Long teamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, teamId);

            // 단방향 매핑
//            Member findMember = em.find(Member.class, member.getId());
//            Team findTeam = findMember.getTeam();
//            findTeam.getName();

            // 양방향 매핑
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            findTeam.getMembers();

           tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
