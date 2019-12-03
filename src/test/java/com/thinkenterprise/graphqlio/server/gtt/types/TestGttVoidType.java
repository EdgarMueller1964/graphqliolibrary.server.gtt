package com.thinkenterprise.graphqlio.server.gtt.types;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingSerializeException;

@RunWith(SpringRunner.class)
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
