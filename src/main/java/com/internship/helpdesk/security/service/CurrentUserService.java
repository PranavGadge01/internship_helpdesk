package com.internship.helpdesk.security.service;

import com.internship.helpdesk.entity.User;

public interface CurrentUserService {

    User getCurrentUser();

    Long getCurrentUserId();

}