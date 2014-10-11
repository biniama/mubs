package com.kaufda.mubs.model

import org.springframework.context.MessageSourceResolvable;

/**
 * Created by biniam on 10/11/2014.
 */

/**
    Gender is an Enumeration (Enum) because the value is constant
    and I do not need to store it in the database
*/
enum GenderTypeEnum implements MessageSourceResolvable {

	MALE,
	FEMALE

    @Override
    String[] getCodes() {
        return new String[0]
    }

    @Override
    Object[] getArguments() {
        return new Object[0]
    }

    @Override
    String getDefaultMessage() {
        return null
    }
}
