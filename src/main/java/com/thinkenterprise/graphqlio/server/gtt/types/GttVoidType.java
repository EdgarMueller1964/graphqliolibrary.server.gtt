package com.thinkenterprise.graphqlio.server.gtt.types;

import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

public class GttVoidType extends GraphQLScalarType {

	private static final String DEFAULT_NAME = "Void";

	public GttVoidType() {
		this(DEFAULT_NAME);
	}

	public GttVoidType(final String name) {
		super(name, DEFAULT_NAME + " type", new Coercing<Object, Object>() {

			@Override
			public Object parseLiteral(Object arg0) throws CoercingParseLiteralException {
				return null;
			}

			@Override
			public Object parseValue(Object arg0) throws CoercingParseValueException {
				return null;
			}

			@Override
			public Object serialize(Object arg0) throws CoercingSerializeException {
				return null;
			}

		});
	}

}
