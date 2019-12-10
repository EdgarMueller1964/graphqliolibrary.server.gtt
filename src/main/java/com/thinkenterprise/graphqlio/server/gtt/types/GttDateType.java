/*******************************************************************************
 * *
 * **  Design and Development by msg Applied Technology Research
 * **  Copyright (c) 2019-2020 msg systems ag (http://www.msg-systems.com/)
 * **  All Rights Reserved.
 * ** 
 * **  Permission is hereby granted, free of charge, to any person obtaining
 * **  a copy of this software and associated documentation files (the
 * **  "Software"), to deal in the Software without restriction, including
 * **  without limitation the rights to use, copy, modify, merge, publish,
 * **  distribute, sublicense, and/or sell copies of the Software, and to
 * **  permit persons to whom the Software is furnished to do so, subject to
 * **  the following conditions:
 * **
 * **  The above copyright notice and this permission notice shall be included
 * **  in all copies or substantial portions of the Software.
 * **
 * **  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * **  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * **  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * **  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * **  CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * **  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * **  SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * *
 ******************************************************************************/
package com.thinkenterprise.graphqlio.server.gtt.types;

import java.text.SimpleDateFormat;
import java.util.Date;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

/**
 * Class used to implement Date for graphql scalar types
 * format yyyy-MM-dd HH:mm:ss is used here
 *
 * @author Michael Schäfer
 * @author Torsten Kühnert
 */

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
