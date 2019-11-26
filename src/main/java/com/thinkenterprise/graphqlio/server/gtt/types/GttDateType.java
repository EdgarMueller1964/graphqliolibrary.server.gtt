package com.thinkenterprise.graphqlio.server.gtt.types;

import java.text.SimpleDateFormat;
import java.util.Date;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

public class GttDateType extends GraphQLScalarType {

	private static final String DEFAULT_NAME = "Date";

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public GttDateType() {
		this(DEFAULT_NAME);
	}

	public GttDateType(final String name) {
		super(name, DEFAULT_NAME + " type", new Coercing<Date, String>() {

			@Override
			public Date parseLiteral(Object arg0) throws CoercingParseLiteralException {
				if (arg0 instanceof StringValue) {
					try {
						StringValue inst = (StringValue) arg0;
						return new SimpleDateFormat(DATE_FORMAT).parse(inst.getValue());
					} catch (Exception e) {
						throw new CoercingParseLiteralException(e);
					}
				} else {
					throw new CoercingParseLiteralException(
							"parseLiteral: Expected a 'StringValue' but was '" + (arg0.getClass()) + "'.");
				}
			}

			@Override
			public Date parseValue(Object arg0) throws CoercingParseValueException {
				if (arg0 instanceof String) {
					try {
						return new SimpleDateFormat(DATE_FORMAT).parse((String) arg0);
					} catch (Exception e) {
						throw new CoercingParseValueException(e);
					}
				} else {
					throw new CoercingParseValueException(
							"parseValue: Expected a 'String' but was '" + (arg0.getClass()) + "'.");
				}
			}

			@Override
			public String serialize(Object arg0) throws CoercingSerializeException {
				if (arg0 instanceof Date) {
					try {
						return new SimpleDateFormat(DATE_FORMAT).format((Date) arg0);
					} catch (Exception e) {
						throw new CoercingSerializeException(e);
					}
				} else {
					throw new CoercingSerializeException(
							"serialize: Expected a 'Date' but was '" + (arg0.getClass()) + "'.");
				}
			}

		});
	}

}
