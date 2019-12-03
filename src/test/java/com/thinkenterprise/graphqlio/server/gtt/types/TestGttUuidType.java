package com.thinkenterprise.graphqlio.server.gtt.types;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.thinkenterprise.graphqlio.server.gtt.types.GttUuidType;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestGttUuidType {

	private static final GttUuidType gttUuidType = new GttUuidType();
	private static final Coercing coercing = gttUuidType.getCoercing();

	private static final String UUID_STRING = "3b241101-e2bb-4255-8caf-4136c566a964";

	/*
	 * testing parseLiteral
	 * 
	 * String is wrong input type
	 */
	@Test
	public void test01() {
		Assertions.assertThrows(CoercingParseLiteralException.class, () -> {
			Object result = coercing.parseLiteral("hallo test");
		});
	}

	/*
	 * testing parseLiteral
	 * 
	 * StringValue is correct input type, but wrong input value format: no uuid
	 * value format
	 */
	@Test
	public void test02() {
		Assertions.assertThrows(CoercingParseLiteralException.class, () -> {
			Object result = coercing.parseLiteral(new StringValue("123"));
		});
	}

	/*
	 * testing parseLiteral
	 * 
	 * wrong input value format: no uuid value format
	 */
	@Test
	public void test03() {
		Assertions.assertThrows(CoercingParseLiteralException.class, () -> {
			Object result = coercing.parseLiteral(new StringValue("abc-123-xyz-789"));
		});
	}

	/*
	 * testing parseLiteral
	 * 
	 * correct input type, correct input value format
	 */
	@Test
	public void test04() {
		Object result = coercing.parseLiteral(new StringValue(UUID_STRING));
		assertEquals(UUID.fromString(UUID_STRING), result);
	}

	/*
	 * testing parseValue
	 * 
	 * StringValue is wrong input type
	 */
	@Test
	public void test11() {
		Assertions.assertThrows(CoercingParseValueException.class, () -> {
			Object result = coercing.parseValue(new StringValue(UUID_STRING));
		});
	}

	/*
	 * testing parseValue
	 * 
	 * StringValue is correct input type, but wrong input value format: no uuid
	 * value format
	 */
	@Test
	public void test12() {
		Assertions.assertThrows(CoercingParseValueException.class, () -> {
			Object result = coercing.parseValue("abc");
		});
	}

	/*
	 * testing parseValue
	 * 
	 * wrong input value format: no uuid value format
	 */
	@Test
	public void test13() {
		Assertions.assertThrows(CoercingParseValueException.class, () -> {
			Object result = coercing.parseValue("abc-123-3456-xyzz");
		});
	}

	/*
	 * testing parseValue
	 * 
	 * correct input type, correct input value format
	 */
	@Test
	public void test14() {
		Object result = coercing.parseValue(UUID_STRING);
		assertEquals(UUID.fromString(UUID_STRING), result);
	}

	/*
	 * testing serialize
	 * 
	 * StringValue is wrong input type
	 */
	@Test
	public void test21() {
		Assertions.assertThrows(CoercingParseValueException.class, () -> {
			Object result = coercing.parseValue("abc-123-3456-xyzz");
		});
	}

	/*
	 * testing serialize
	 * 
	 * String is wrong input type
	 */
	@Test
	public void test22() {
		Assertions.assertThrows(CoercingSerializeException.class, () -> {
			Object result = coercing.serialize(UUID_STRING);
		});
	}

	/*
	 * testing serialize
	 * 
	 * correct input, correct value format
	 */
	@Test
	public void test23() {
		Object result = coercing.serialize(UUID.fromString(UUID_STRING));
		assertEquals(UUID_STRING, result);
	}

}
