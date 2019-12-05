package com.students.repositories;

import com.students.entities.Message;
import com.students.hibernate.EntityManagerConfiguration;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

public class MessageRepository extends AbstractRepository<Message> {

    public MessageRepository() {
        super(Message.class);
    }

    public Collection<Message> getLastNMessages(int n){
        EntityManager entityManager = EntityManagerConfiguration.getInstance().getEntityManager();

        return entityManager.createNamedQuery("Messages.findLastNMessages", Message.class)
                .setMaxResults(n)
                .getResultList();
    }

}
