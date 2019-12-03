package com.thinkenterprise.graphqlio.server.gtt.types;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.thinkenterprise.graphqlio.server.gtt.types.GttDateType;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestGttDateType {

	private static final GttDateType gttDateType = new GttDateType();
	private static final Coercing coercing = gttDateType.getCoercing();

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_FORMAT);

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
	 * StringValue is correct input type, but wrong input value format: no time
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
	 * wrong input value format: no seconds
	 */
	@Test
	public void test03() {
		Assertions.assertThrows(CoercingParseLiteralException.class, () -> {
			Object result = coercing.parseLiteral(new StringValue("2005-05-05 05:05"));
		});
	}

	/*
	 * testing parseLiteral
	 * 
	 * correct input type, correct input value format
	 */
	@Test
	public void test04() throws ParseException {
		Object result = coercing.parseLiteral(new StringValue("2005-05-05 05:05:05"));
		assertEquals(SDF.parse("2005-05-05 05:05:05"), result);
	}

	/*
	 * testing parseValue
	 * 
	 * StringValue is wrong input type
	 */
	@Test
	public void test11() {
		Assertions.assertThrows(CoercingParseValueException.class, () -> {
			Object result = coercing.parseValue(new StringValue("2005-05-05 05:05:05"));
		});
	}

	/*
	 * testing parseValue
	 * 
	 * StringValue is correct input type, but wrong input value format: no time
	 */
	@Test
	public void test12() {
		Assertions.assertThrows(CoercingParseValueException.class, () -> {
			Object result = coercing.parseValue("2005-05-05");
		});
	}

	/*
	 * testing parseValue
	 * 
	 * wrong input value format: no seconds
	 */
	@Test
	public void test13() {
		Assertions.assertThrows(CoercingParseValueException.class, () -> {
			Object result = coercing.parseValue("2005-05-05 05:05");
		});
	}

	/*
	 * testing parseValue
	 * 
	 * correct input type, correct input value format
	 */
	@Test
	public void test14() throws ParseException {
		Object result = coercing.parseValue("2005-05-05 05:05:05");
		assertEquals(SDF.parse("2005-05-05 05:05:05"), result);
	}

	/*
	 * testing serialize
	 * 
	 * String is wrong input type
	 */
	@Test
	public void test21() {
		Assertions.assertThrows(CoercingSerializeException.class, () -> {
			Object result = coercing.serialize("2005-05-05 05:05:05");
		});
	}

	/*
	 * testing serialize
	 * 
	 * Date is correct input type
	 */
	@Test
	public void test22() throws CoercingSerializeException, ParseException {
		Object result = coercing.serialize(new SimpleDateFormat("yyyy-MM-dd").parse("2005-05-05"));
		assertEquals("2005-05-05 00:00:00", result);
	}

	/*
	 * testing serialize
	 * 
	 * correct input, compare date formatted to string
	 */
	@Test
	public void test23() {
		Date input = new Date();
		Object result = coercing.serialize(input);
		assertEquals(SDF.format(input), result);
	}

	/*
	 * testing serialize
	 * 
	 * correct input, compare date formatted to string
	 */
	@Test
	public void test24() throws ParseException {
		Object result = coercing.serialize(SDF.parse("2005-05-05 05:05:05"));
		assertEquals("2005-05-05 05:05:05", result);
	}

}
