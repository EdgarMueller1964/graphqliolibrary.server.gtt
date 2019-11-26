package com.thinkenterprise.graphqlio.server.gtt.types;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

public class GttJsonType extends GraphQLScalarType {

	private static final String DEFAULT_NAME = "JSON";

	public GttJsonType() {
		this(DEFAULT_NAME);
	}

	public GttJsonType(final String name) {
		super(name, DEFAULT_NAME + " type", new Coercing<String, String>() {

			@Override
			public String parseLiteral(Object arg0) throws CoercingParseLiteralException {
				if (arg0 instanceof StringValue) {
					try {
						String value = ((StringValue) arg0).getValue();
						return testAndConvertJson2String(value);
					} catch (Exception e) {
						throw new CoercingParseLiteralException(e);
					}
				} else {
					throw new CoercingParseLiteralException(
							"parseLiteral: Expected a 'StringValue' but was '" + (arg0.getClass()) + "'.");
				}
			}

			@Override
			public String parseValue(Object arg0) throws CoercingParseValueException {
				if (arg0 instanceof String) {
					try {
						String value = (String) arg0;
						return testAndConvertJson2String(value);
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
				if (arg0 instanceof String) {
					try {
						String value = (String) arg0;
						return testAndConvertJson2String(value);
					} catch (Exception e) {
						throw new CoercingSerializeException(e);
					}
				} else {
					throw new CoercingSerializeException(
							"serialize: Expected a 'String' but was '" + (arg0.getClass()) + "'.");
				}
			}

			private String testAndConvertJson2String(String value) throws Exception {
				try {
					// 1. try JSONObject
					JSONObject obj = new JSONObject(value);
					return obj.toString();

				} catch (Exception e1) {
					String eMsg = e1.getMessage();

					try {
						// 2. try JSONArray
						JSONArray obj = new JSONArray(value);
						return obj.toString();

					} catch (Exception e2) {
						eMsg += " and " + e2.getMessage();
						throw new Exception(eMsg);
					}
				}
			}

		});
	}

}
