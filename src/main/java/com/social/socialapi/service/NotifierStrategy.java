package com.social.socialapi.service;

import java.util.List;

public interface NotifierStrategy {

    List<Integer> getNotifiers(Object entity);

}
