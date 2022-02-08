package com.objectmentor.utilities.args.tests;

import java.text.ParseException;

import com.objectmentor.utilities.args.Args;

import junit.framework.TestCase;

public class ArgsTest extends TestCase {
	
	public void testCreateWithNoSchemaOrArguments() throws Exception {
		Args args = new Args("", new String[0]);
		assertEquals(0, args.cardinality());
	}
	
	public void testWithNoSchemaButWithOneArgument() throws Exception {
		Args args = new Args("", new String[] {"-x"});
		assertEquals("Argument(s) -x unexpected.", args.errorMessage());
	}
	
	public void testWithNoSchemaButWithMultipleArguments() throws Exception {
		Args args = new Args("", new String[] {"-x", "-y"});
		assertEquals("Argument(s) -xy unexpected.", args.errorMessage());
	}
	
	public void testNonLetterSchema() throws Exception {
		try {
			new Args("*", new String[] {});
			fail("Args constructor should have throw exception");
		} catch (ParseException e) {
			assertEquals("Dad character:* in Args format: *", e.getMessage());
		}
	}
	
	public void testInvalidArgumentFormat() throws Exception {
		try {
			new Args("f~", new String[] {});
			fail("Args constructor should have throw exception");
		} catch (ParseException e) {
			assertEquals("Argument: f has invalid format: ~.", e.getMessage());
		}
	}
	
	public void testSimpleBooleanPresent() throws Exception {
		Args args = new Args("x", new String[] {"-x"});
		assertEquals(true, args.getBoolean('x'));
	}
	
	public void testSimpleStringPresent() throws Exception {
		Args args = new Args("x*", new String[] {"-x", "param"});
		assertTrue(args.has('x'));
		assertEquals("param", args.getString('x'));
	}
	
	public void testMissingStringArgument() throws Exception {
		Args args = new Args("x*", new String[] {"-x"});
		assertEquals("Could not find string parameter for -x.", args.errorMessage());
	}
	
	
	public void testSpacesInFormat() throws Exception {
		Args args = new Args("x, y", new String[] {"-xy"});
		assertTrue(args.has('x'));
		assertTrue(args.has('y'));
	}
	
	public void testSimpleIntPresent() throws Exception {
		Args args = new Args("x#", new String[] {"-x", "42"});
		assertTrue(args.has('x'));
		assertEquals(42, args.getInt('x'));
	}
	
	public void testInvalidInteger() throws Exception {
		Args args = new Args("x#", new String[] {"-x", "Forty two"});
		assertEquals("Argument -x expects an integer but was 'Forty two'.", args.errorMessage());
		assertEquals('x', args.errorArgumentId);
		assertEquals("Forty two", args.errorParameter);
	}
	
	public void testMissingInteger() throws Exception {
		Args args = new Args("x#", new String[] {"-x"});
		assertEquals("Could not find integer parameter for -x.", args.errorMessage());
		assertEquals('x', args.errorArgumentId);
	}
}
