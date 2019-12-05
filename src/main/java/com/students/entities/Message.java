package com.students.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "messages")
@NamedQueries(
        @NamedQuery(
                name = "Messages.findLastNMessages",
                query = "SELECT m FROM Message m ORDER BY time_of_message DESC"
        )
)
public class Message {

  @Id
  // TODO: 06.12.2019 change generation type to table
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_generator")
  @SequenceGenerator(name = "message_id_generator", sequenceName = "message_id_seq", allocationSize = 1)
  @Column(name = "message_id")
  private long messageId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

  @NotNull
  @Size(min = 1, max = 200)
  @Column(name = "text")
  private String text;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "time_of_message")
  private Date timeOfMessage;

  public Message() {
  }

  public Message(User user, @NotNull @Size(min = 1, max = 200) String text) {
    this.user = user;
    this.text = text;
    this.timeOfMessage = new Date();
  }

  public long getMessageId() {
    return messageId;
  }

  public void setMessageId(long messageId) {
    this.messageId = messageId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getTimeOfMessage() {
    return timeOfMessage;
  }

  public void setTimeOfMessage(Date timeOfMessage) {
    this.timeOfMessage = timeOfMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Message message = (Message) o;
    return messageId == message.messageId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageId);
  }

  @Override
  public String toString() {
    return "Message{" +
            "messageId=" + messageId +
            ", text='" + text + '\'' +
            ", timeOfMessage=" + timeOfMessage +
            '}';
  }

}
