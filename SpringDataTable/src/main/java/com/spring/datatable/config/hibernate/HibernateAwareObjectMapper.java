package com.spring.datatable.config.hibernate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 4152600582756689770L;

	public HibernateAwareObjectMapper() {
        registerModule(new Hibernate5Module());
    }
}
