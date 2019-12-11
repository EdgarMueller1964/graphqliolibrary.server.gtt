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

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingSerializeException;

/**
 * Class for testing library graphql scalar type Void
 *
 * @author Michael Schäfer
 * @author Torsten Kühnert
 */

@Tag("annotations")
@Tag("junit5")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestGttVoidType {

	private static final GttVoidType gttVoidType = new GttVoidType();
	private static final Coercing coercing = gttVoidType.getCoercing();

	/*
	 * testing parseLiteral
	 * 
	 * every input is ok, result is always null
	 */
	@Test
	public void test01() {
		Object result = coercing.parseLiteral("2005-05-05 05:05:05");
		assertEquals(null, result);
	}

	/*
	 * testing parseLiteral
	 * 
	 * every input is ok, result is always null
	 */
	@Test
	public void test02() {
		Object result = coercing.parseLiteral(new StringValue("2005-05-05"));
		assertEquals(null, result);
	}

	/*
	 * testing parseLiteral
	 * 
	 * every input is ok, result is always null
	 */
	@Test
	public void test03() {
		Object result = coercing.parseLiteral(1235);
		assertEquals(null, result);
	}

	/*
	 * testing parseLiteral
	 * 
	 * every input is ok, result is always null
	 */
	@Test
	public void test04() throws ParseException {
		Object result = coercing.parseLiteral(new Date());
		assertEquals(null, result);
	}

	/*
	 * testing parseLiteral
	 * 
	 * every input is ok, result is always null
	 */
	@Test
	public void test05() throws ParseException {
		Object result = coercing.parseLiteral(null);
		assertEquals(null, result);
	}

	/*
	 * testing parseValue
	 * 
	 * every input is ok, result is always null
	 */
	@Test
	public void test11() {
		Object result = coercing.parseValue(new StringValue("2005-05-05 05:05:05"));
		assertEquals(null, result);
	}

	/*
	 * testing parseValue
	 * 
	 * every input is ok, result is always null
	 */
	@Test
	public void test12() {
		Object result = coercing.parseValue("2005-05-05");
		assertEquals(null, result);
	}

	/*
	 * testing parseValue
	 * 
	 * every input is ok, result is always null
	 */
	@Test
	public void test13() {
		Object result = coercing.parseValue(1354);
		assertEquals(null, result);
	}

	/*
	 * testing parseValue
	 * 
	 * every input is ok, result is always null
	 */
	@Test
	public void test14() throws ParseException {
		Object result = coercing.parseValue(new Date());
		assertEquals(null, result);
	}

	/*
	 * testing serialize
	 * 
	 * every input is ok, result is always null
	 */
	@Test
	public void test21() {
		Object result = coercing.serialize("2005-05-05 05:05:05");
		assertEquals(null, result);
	}

	/*
	 * testing serialize
	 * 
	 * every input is ok, result is always null
	 */
	@Test
	public void test22() throws CoercingSerializeException, ParseException {
		Object result = coercing.serialize(new Date());
		assertEquals(null, result);
	}

	/*
	 * testing serialize
	 * 
	 * every input is ok, result is always null
	 */
	@Test
	public void test23() {
		Object result = coercing.serialize(1354);
		assertEquals(null, result);
	}

	/*
	 * testing serialize
	 * 
	 * every input is ok, result is always null
	 */
	@Test
	public void test24() throws ParseException {
		Object result = coercing.serialize(true);
		assertEquals(null, result);
	}

}
