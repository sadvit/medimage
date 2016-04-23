package com.sadvit.converters;

import com.sadvit.models.User;
import com.sadvit.to.UserTO;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by sadvit on 4/22/16.
 */
public class UserConverter implements Converter<UserTO, User> {

    @Override
    public User convert(UserTO userTO) {
        User user = new User();
        BeanUtils.copyProperties(userTO, user);
        return user;
    }

}
