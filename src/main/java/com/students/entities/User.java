package com.students.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "name")
  private String name;

  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "surname")
  private String surname;

  @Column(name = "age")
  private Long age;

  @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
  Collection<Group> groups;

  public User() {
  }

  public User(@NotNull @Size(min = 1, max = 50) String name,
              @NotNull @Size(min = 1, max = 100) String surname,
              long age) {
    this.name = name;
    this.surname = surname;
    this.age = age;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }

  public Collection<Group> getGroups() {
    return groups;
  }

  public void setGroups(Collection<Group> groups) {
    this.groups = groups;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return userId.equals(user.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }

  @Override
  public String toString() {
    return "User{" +
            "userId=" + userId +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", age=" + age +
            ", groups=" + groups +
            '}';
  }
}
