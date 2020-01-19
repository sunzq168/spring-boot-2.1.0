package com.sun.zq.mail;

import com.sun.zq.model.User;

import java.util.List;

public interface SendMailService {

    boolean sendMail(List<User> users);
}
