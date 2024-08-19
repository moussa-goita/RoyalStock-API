package com.test.entities;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class Messagerie {

  private final JavaMailSender javaMailSender;

  public Messagerie(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public String envoiesMessage(String to, String subject, String text) {
    try {
      SimpleMailMessage mailMessage = new SimpleMailMessage();

      mailMessage.setTo(to);
      mailMessage.setSubject(subject);
      mailMessage.setText(text);

      javaMailSender.send(mailMessage);
      return "Mail envoyer";
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
