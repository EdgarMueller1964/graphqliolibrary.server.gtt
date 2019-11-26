package com.thinkenterprise.graphqlio.server.gtt.types;

import java.util.UUID;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

public class GttUuidType extends GraphQLScalarType {

	private static final String DEFAULT_NAME = "UUID";

	public GttUuidType() {
		this(DEFAULT_NAME);
	}

	public GttUuidType(final String name) {
		super(name, DEFAULT_NAME + " type", new Coercing<UUID, String>() {

			@Override
			public UUID parseLiteral(Object arg0) throws CoercingParseLiteralException {
				if (arg0 instanceof StringValue) {
					try {
						StringValue inst = (StringValue) arg0;
						return UUID.fromString(inst.getValue());
					} catch (Exception e) {
						throw new CoercingParseLiteralException(e);
					}
				} else {
					throw new CoercingParseLiteralException(
							"parseLiteral: Expected a 'StringValue' but was '" + (arg0.getClass()) + "'.");
				}
			}

			@Override
			public UUID parseValue(Object arg0) throws CoercingParseValueException {
				if (arg0 instanceof String) {
					try {
						return UUID.fromString((String) arg0);
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
				if (arg0 instanceof UUID) {
					return arg0.toString();
				} else {
					throw new CoercingSerializeException(
							"serialize: Expected a 'UUID' or 'String' but was '" + (arg0.getClass()) + "'.");
				}
			}

		});
	}

}
