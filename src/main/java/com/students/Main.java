package com.students;

import com.students.entities.Group;
import com.students.entities.Message;
import com.students.entities.User;
import com.students.hibernate.EntityManagerConfiguration;
import com.students.repositories.GroupRepository;
import com.students.repositories.MessageRepository;
import com.students.repositories.UserRepository;

import javax.persistence.EntityManager;
import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        showUserRepository();
        showGroupRepository();
        showMessageRepository();

        EntityManagerConfiguration.getInstance().releaseResourcesAndClose();
    }

    private static void showUserRepository(){
        //find existed user
        UserRepository userRepository = new UserRepository();
        User user = userRepository.find(1l);
        //output User{userId=1, name='Alex', surname='Shevtsov', age=25, groups=[Groups{groupId=1, name='ADMIN'}, Groups{groupId=2, name='USER'}]}
        System.out.println(user);

        //create new user
        user = new User("Vasya", "Pupkin", 27);
        userRepository.create(user);
        //output User{userId=3, name='Vasya', surname='Pupkin', age=27, groups=null}
        System.out.println(user);
    }

    private static void showGroupRepository() {
        //find existed group
        GroupRepository groupRepository = new GroupRepository();
        Group group = groupRepository.find(1l);
        //Groups{groupId=1, name='ADMIN'}
        System.out.println(group);

        //get all users from group
        group = groupRepository.find(2l);
        //Group : USER
        //User{userId=1, name='Alex', surname='Shevtsov', age=25, groups=[Groups{groupId=1, name='ADMIN'}, Groups{groupId=2, name='USER'}]}
        //User{userId=2, name='Sergey', surname='Kozlov', age=null, groups=[Groups{groupId=2, name='USER'}]}
        System.out.println("Group : " + group.getName());
        for (User user : group.getUsers()) {
            System.out.println(user);
        }

        //update existed group
        group.setName("SUPER_ADMIN");
        groupRepository.save(group);
        //Groups{groupId=1, name='SUPER_ADMIN'}
        System.out.println(group);
    }

    private static void showMessageRepository() {
        //find existed message
        MessageRepository messageRepository = new MessageRepository();
        Message message = messageRepository.find(1l);
        System.out.println(message);
        //Alex said "Hello, Sergey!"
        System.out.println(message.getUser().getName() + " said \"" + message.getText() + "\"");

        //add new message
        message = new Message(message.getUser(), "Hello, students!");
        messageRepository.save(message);

        //find existed message
        Collection<Message> lastNMessages = messageRepository.getLastNMessages(2);
        //02:20:58 - Shevtsov : Hello, students!
        //02:20:55 - Kozlov : Hello, Alex!
        for (Message messageIterator : lastNMessages) {
            System.out.printf("%tT - %s : %s%n",
                    messageIterator.getTimeOfMessage(),
                    messageIterator.getUser().getSurname(),
                    messageIterator.getText()
                );
        }


    }
}
