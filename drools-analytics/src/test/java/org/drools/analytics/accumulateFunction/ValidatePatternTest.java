package org.drools.analytics.accumulateFunction;

import java.math.BigDecimal;

import junit.framework.TestCase;

public class ValidatePatternTest extends TestCase {

	public void testfindSumPattern() {
		// Sum +2 missing number 4
		assertTrue(FindMissingNumber.findSumPattern(
				new BigDecimal[] { BigDecimal.valueOf(2),
						BigDecimal.valueOf(6), BigDecimal.valueOf(8),
						BigDecimal.valueOf(10) }).doubleValue() == 4);
		// +10 missing number 50
		assertTrue(FindMissingNumber.findSumPattern(
				new BigDecimal[] { BigDecimal.valueOf(10),
						BigDecimal.valueOf(20), BigDecimal.valueOf(30),
						BigDecimal.valueOf(40), BigDecimal.valueOf(60),
						BigDecimal.valueOf(70) }).doubleValue() == 50);
		// +66 missing number 308
		assertTrue(FindMissingNumber.findSumPattern(
				new BigDecimal[] { BigDecimal.valueOf(110),
						BigDecimal.valueOf(176), BigDecimal.valueOf(242),
						BigDecimal.valueOf(374) }).doubleValue() == 308);

		// Deduction -2 missing number 8
		assertTrue(FindMissingNumber.findSumPattern(
				new BigDecimal[] { BigDecimal.valueOf(10),
						BigDecimal.valueOf(6), BigDecimal.valueOf(4),
						BigDecimal.valueOf(2) }).doubleValue() == 8);
		// -337 missing number -11
		assertTrue(FindMissingNumber.findSumPattern(
				new BigDecimal[] { BigDecimal.valueOf(663),
						BigDecimal.valueOf(326), BigDecimal.valueOf(-348),
						BigDecimal.valueOf(-685) }).doubleValue() == -11);
		// -31 missing number 4350
		assertTrue(FindMissingNumber.findSumPattern(
				new BigDecimal[] { BigDecimal.valueOf(4443),
						BigDecimal.valueOf(4412), BigDecimal.valueOf(4381),
						BigDecimal.valueOf(4319) }).doubleValue() == 4350);

		// Not valid
		// Not in pattern.
		assertTrue(FindMissingNumber.findSumPattern(new BigDecimal[] {
				BigDecimal.valueOf(1), BigDecimal.valueOf(2),
				BigDecimal.valueOf(4), BigDecimal.valueOf(6),
				BigDecimal.valueOf(8), BigDecimal.valueOf(11) }) == null);
		assertTrue(FindMissingNumber.findSumPattern(new BigDecimal[] {
				BigDecimal.valueOf(5), BigDecimal.valueOf(3),
				BigDecimal.valueOf(54353), BigDecimal.valueOf(54554),
				BigDecimal.valueOf(232), BigDecimal.valueOf(123) }) == null);
		// No missing values.
		assertTrue(FindMissingNumber.findSumPattern(new BigDecimal[] {
				BigDecimal.valueOf(2), BigDecimal.valueOf(4),
				BigDecimal.valueOf(6), BigDecimal.valueOf(8),
				BigDecimal.valueOf(10), BigDecimal.valueOf(12),
				BigDecimal.valueOf(14) }) == null);
		assertTrue(FindMissingNumber.findSumPattern(new BigDecimal[] {
				BigDecimal.valueOf(10), BigDecimal.valueOf(20),
				BigDecimal.valueOf(30), BigDecimal.valueOf(40),
				BigDecimal.valueOf(50), BigDecimal.valueOf(60) }) == null);
		assertTrue(FindMissingNumber.findSumPattern(new BigDecimal[] {
				BigDecimal.valueOf(-15), BigDecimal.valueOf(-10),
				BigDecimal.valueOf(-5), BigDecimal.valueOf(0),
				BigDecimal.valueOf(5), BigDecimal.valueOf(10),
				BigDecimal.valueOf(15) }) == null);
		// Under 4 values always returns null.
		assertTrue(FindMissingNumber.findSumPattern(new BigDecimal[] {
				BigDecimal.valueOf(2), BigDecimal.valueOf(4),
				BigDecimal.valueOf(6) }) == null);
		assertTrue(FindMissingNumber.findSumPattern(new BigDecimal[] {
				BigDecimal.valueOf(2), BigDecimal.valueOf(4) }) == null);
		assertTrue(FindMissingNumber.findSumPattern(new BigDecimal[] { BigDecimal
				.valueOf(2) }) == null);
	}

	public void testFindMultiplicationPattern() {

		// Multiplication
		// *2 missing number 4
		assertTrue(FindMissingNumber.findMultiplicationPattern(
				new BigDecimal[] { BigDecimal.valueOf(2),
						BigDecimal.valueOf(8), BigDecimal.valueOf(16),
						BigDecimal.valueOf(32), BigDecimal.valueOf(64) })
				.doubleValue() == 4);
		// *17 missing number 383214
		assertTrue(FindMissingNumber.findMultiplicationPattern(
				new BigDecimal[] { BigDecimal.valueOf(78),
						BigDecimal.valueOf(1326), BigDecimal.valueOf(22542),
						BigDecimal.valueOf(6514638) }).doubleValue() == 383214);
		// *1,23 missing number 2016.6957
		assertTrue(FindMissingNumber.findMultiplicationPattern(
				new BigDecimal[] { BigDecimal.valueOf(1333),
						BigDecimal.valueOf(1639.59),
						BigDecimal.valueOf(2480.535711),
						BigDecimal.valueOf(3051.05892453) }).doubleValue() == 2016.6957);

		// Division
		// /2 (*0.5) missing number 128
		assertTrue(FindMissingNumber.findMultiplicationPattern(
				new BigDecimal[] { BigDecimal.valueOf(256),
						BigDecimal.valueOf(64), BigDecimal.valueOf(32),
						BigDecimal.valueOf(16), BigDecimal.valueOf(8),
						BigDecimal.valueOf(4), BigDecimal.valueOf(2) })
				.doubleValue() == 128);
		// /10 (*0.1) missing number 1
		assertTrue(FindMissingNumber.findMultiplicationPattern(
				new BigDecimal[] { BigDecimal.valueOf(10000),
						BigDecimal.valueOf(1000), BigDecimal.valueOf(100),
						BigDecimal.valueOf(10), BigDecimal.valueOf(0.1),
						BigDecimal.valueOf(0.01) }).doubleValue() == 1);

		// Not valid
		// Not in pattern.
		assertTrue(FindMissingNumber.findMultiplicationPattern(new BigDecimal[] {
				BigDecimal.valueOf(111.2), BigDecimal.valueOf(3323),
				BigDecimal.valueOf(234.434), BigDecimal.valueOf(44343),
				BigDecimal.valueOf(434) }) == null);
		assertTrue(FindMissingNumber.findMultiplicationPattern(new BigDecimal[] {
				BigDecimal.valueOf(1), BigDecimal.valueOf(2),
				BigDecimal.valueOf(3), BigDecimal.valueOf(4),
				BigDecimal.valueOf(5), BigDecimal.valueOf(6),
				BigDecimal.valueOf(7), BigDecimal.valueOf(5),
				BigDecimal.valueOf(4), BigDecimal.valueOf(3),
				BigDecimal.valueOf(2), BigDecimal.valueOf(1),
				BigDecimal.valueOf(1), BigDecimal.valueOf(1) }) == null);
	}
}
