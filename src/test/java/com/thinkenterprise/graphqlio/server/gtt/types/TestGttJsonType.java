/*
**  Design and Development by msg Applied Technology Research
**  Copyright (c) 2019-2020 msg systems ag (http://www.msg-systems.com/)
**  All Rights Reserved.
** 
**  Permission is hereby granted, free of charge, to any person obtaining
**  a copy of this software and associated documentation files (the
**  "Software"), to deal in the Software without restriction, including
**  without limitation the rights to use, copy, modify, merge, publish,
**  distribute, sublicense, and/or sell copies of the Software, and to
**  permit persons to whom the Software is furnished to do so, subject to
**  the following conditions:
**
**  The above copyright notice and this permission notice shall be included
**  in all copies or substantial portions of the Software.
**
**  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
**  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
**  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
**  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
**  CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
**  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
**  SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.thinkenterprise.graphqlio.server.gtt.types;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

/**
 * Class for testing library graphql scalar type JSON
 *
 * @author Michael Schäfer
 * @author Torsten Kühnert
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestGttJsonType {

	private static final GttJsonType gttJsonType = new GttJsonType();
	private static final Coercing coercing = gttJsonType.getCoercing();

	/*
	 * testing parseLiteral
	 * 
	 * String is wrong input type
	 */
	@Test
	public void test01() {
		Assertions.assertThrows(CoercingParseLiteralException.class, () -> {
			Object result = coercing.parseLiteral("2005-05-05 05:05:05");
		});
	}

	/*
	 * testing parseLiteral
	 * 
	 * StringValue is correct input type, but wrong input value format: no json
	 */
	@Test
	public void test02() {
		Assertions.assertThrows(CoercingParseLiteralException.class, () -> {
			Object result = coercing.parseLiteral(new StringValue("2005-05-05"));
		});
	}

	/*
	 * testing parseLiteral
	 * 
	 * wrong input value format: no correct json
	 */
	@Test
	public void test03() {
		Assertions.assertThrows(CoercingParseLiteralException.class, () -> {
			Object result = coercing.parseLiteral(new StringValue("{ a 3 x }"));
		});
	}

	/*
	 * testing parseLiteral
	 * 
	 * correct input type, correct input value format
	 */
	@Test
	public void test04() {
		Object result = coercing.parseLiteral(new StringValue("{ a: 34, x: [ 3, 4 ] }"));
		assertEquals("{\"a\":34,\"x\":[3,4]}", result);
	}

	/*
	 * testing parseLiteral
	 * 
	 * correct input type, correct input value format
	 */
	@Test
	public void test05() {
		Object result = coercing.parseLiteral(new StringValue("[]"));
		assertEquals("[]", result);
	}

	/*
	 * testing parseLiteral
	 * 
	 * correct input type, correct input value format
	 */
	@Test
	public void test06() {
		Object result = coercing.parseLiteral(new StringValue("[ 2, {}, 4 ]"));
		assertEquals("[2,{},4]", result);
	}

	/*
	 * testing parseValue
	 * 
	 * StringValue is wrong input type
	 */
	@Test
	public void test11() {
		Assertions.assertThrows(CoercingParseValueException.class, () -> {
			Object result = coercing.parseValue(new StringValue("abcabcabcabc"));
		});
	}

	/*
	 * testing parseValue
	 * 
	 * correct input type, wrong input value format: no json
	 */
	@Test
	public void test12() {
		Assertions.assertThrows(CoercingParseValueException.class, () -> {
			Object result = coercing.parseValue("123123123");
		});
	}

	/*
	 * testing parseValue
	 * 
	 * wrong input value format: no json
	 */
	@Test
	public void test13() {
		Assertions.assertThrows(CoercingParseValueException.class, () -> {
			Object result = coercing.parseValue("{ 1 2 3 }");
		});
	}

	/*
	 * testing parseValue
	 * 
	 * correct input type, correct input value format
	 */
	@Test
	public void test14() {
		Object result = coercing.parseValue("{ a: 123, b: [ 1, 2, 3 ] }");
		assertEquals("{\"a\":123,\"b\":[1,2,3]}", result);
	}

	/*
	 * testing parseValue
	 * 
	 * correct input type, correct input value format
	 */
	@Test
	public void test15() {
		Object result = coercing.parseValue("[ 2, 3, 4 ]");
		assertEquals("[2,3,4]", result);
	}

	/*
	 * testing parseValue
	 * 
	 * correct input type, correct input value format
	 */
	@Test
	public void test16() {
		Object result = coercing.parseValue("[ 2, {aa: { x: \"a\" }}, 4 ]");
		assertEquals("[2,{\"aa\":{\"x\":\"a\"}},4]", result);
	}

	/*
	 * testing serialize
	 * 
	 * int is wrong input type
	 */
	@Test
	public void test21() {
		Assertions.assertThrows(CoercingSerializeException.class, () -> {
			Object result = coercing.serialize(123456789);
		});
	}

	/*
	 * testing serialize
	 * 
	 * correct input type, input value: no json format
	 */
	@Test
	public void test22() {
		Assertions.assertThrows(CoercingSerializeException.class, () -> {
			Object result = coercing.serialize("2005-05-05 05:05:05");
		});
	}

	/*
	 * testing serialize
	 * 
	 * correct input, correct value
	 */
	@Test
	public void test23() {
		Object result = coercing.serialize("{ abc: 123 }");
		assertEquals("{\"abc\":123}", result);
	}

	/*
	 * testing serialize
	 * 
	 * correct input, correct value
	 */
	@Test
	public void test24() {
		Date input = new Date();
		Object result = coercing.serialize("[ { abc: 123 }, { def: 456 }, { xyz: 789 } ]");
		assertEquals("[{\"abc\":123},{\"def\":456},{\"xyz\":789}]", result);
	}

	/*
	 * testing serialize
	 * 
	 * correct input, correct value
	 */
	@Test
	public void test25() {
		Object result = coercing.serialize("{ abc: {} }");
		assertEquals("{\"abc\":{}}", result);
	}

}
