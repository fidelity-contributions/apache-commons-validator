/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.validator.routines.checkdigit;

/**
 * Modulus 10 <strong>CUSIP</strong> (North American Securities) Check Digit calculation/validation.
 *
 * <p>
 * CUSIP Numbers are 9 character alphanumeric codes used
 * to identify North American Securities.
 * </p>
 *
 * <p>
 * Check digit calculation uses the <em>Modulus 10 Double Add Double</em> technique
 * with every second digit being weighted by 2. Alphabetic characters are
 * converted to numbers by their position in the alphabet starting with A being 10.
 * Weighted numbers greater than ten are treated as two separate numbers.
 * </p>
 *
 * <p>
 * See <a href="https://en.wikipedia.org/wiki/CUSIP">Wikipedia - CUSIP</a>
 * for more details.
 * </p>
 *
 * @since 1.4
 */
public final class CUSIPCheckDigit extends ModulusCheckDigit {

    private static final long serialVersionUID = 666941918490152456L;

    /** Singleton CUSIP Check Digit instance */
    public static final CheckDigit CUSIP_CHECK_DIGIT = new CUSIPCheckDigit();

    /** Weighting given to digits depending on their right position */
    private static final int[] POSITION_WEIGHT = {2, 1};

    /**
     * Constructs a CUSIP Identifier Check Digit routine.
     */
    public CUSIPCheckDigit() {
    }

    /**
     * Convert a character at a specified position to an integer value.
     *
     * @param character The character to convert
     * @param leftPos The position of the character in the code, counting from left to right
     * @param rightPos The position of the character in the code, counting from right to left
     * @return The integer value of the character
     * @throws CheckDigitException if the character is not alphanumeric
     */
    @Override
    protected int toInt(final char character, final int leftPos, final int rightPos)
            throws CheckDigitException {
        final int charValue = Character.getNumericValue(character);
        // the final character is only allowed to reach 9
        final int charMax = rightPos == 1 ? 9 : 35;  // CHECKSTYLE IGNORE MagicNumber
        if (charValue < 0 || charValue > charMax) {
            throw new CheckDigitException("Invalid Character[" +
                    leftPos + "," + rightPos + "] = '" + charValue + "' out of range 0 to " + charMax);
        }
        return charValue;
    }

    /**
     * <p>Calculates the <em>weighted</em> value of a character in the
     * code at a specified position.</p>
     *
     * <p>For CUSIP (from right to left) <strong>odd</strong> digits are weighted
     * with a factor of <strong>one</strong> and <strong>even</strong> digits with a factor
     * of <strong>two</strong>. Weighted values &gt; 9, have 9 subtracted</p>
     *
     * @param charValue The numeric value of the character.
     * @param leftPos The position of the character in the code, counting from left to right
     * @param rightPos The position of the character in the code, counting from right to left
     * @return The weighted value of the character.
     */
    @Override
    protected int weightedValue(final int charValue, final int leftPos, final int rightPos) {
        final int weight = POSITION_WEIGHT[rightPos % 2];
        final int weightedValue = charValue * weight;
        return sumDigits(weightedValue);
    }
}
