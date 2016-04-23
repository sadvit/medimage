package com.sadvit.converters;

import com.sadvit.models.User;
import com.sadvit.to.UserTO;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by sadvit on 4/22/16.
 */
public class UserTOConverter implements Converter<User, UserTO> {

    @Override
    public UserTO convert(User user) {
        UserTO userTO = new UserTO();
        BeanUtils.copyProperties(user, userTO);
        return userTO;
    }

}
