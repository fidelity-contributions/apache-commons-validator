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
package org.apache.commons.validator.routines;

import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * <p><strong>Long Validation</strong> and Conversion routines ({@code java.lang.Long}).</p>
 *
 * <p>This validator provides a number of methods for
 *    validating/converting a {@link String} value to
 *    a {@code Long} using {@link NumberFormat}
 *    to parse either:</p>
 *    <ul>
 *       <li>using the default format for the default {@link Locale}</li>
 *       <li>using a specified pattern with the default {@link Locale}</li>
 *       <li>using the default format for a specified {@link Locale}</li>
 *       <li>using a specified pattern with a specified {@link Locale}</li>
 *    </ul>
 *
 * <p>Use one of the {@code isValid()} methods to just validate or
 *    one of the {@code validate()} methods to validate and receive a
 *    <em>converted</em> {@code Long} value.</p>
 *
 * <p>Once a value has been successfully converted the following
 *    methods can be used to perform minimum, maximum and range checks:</p>
 *    <ul>
 *       <li>{@code minValue()} checks whether the value is greater
 *           than or equal to a specified minimum.</li>
 *       <li>{@code maxValue()} checks whether the value is less
 *           than or equal to a specified maximum.</li>
 *       <li>{@code isInRange()} checks whether the value is within
 *           a specified range of values.</li>
 *    </ul>
 *
 * <p>So that the same mechanism used for parsing an <em>input</em> value
 *    for validation can be used to format <em>output</em>, corresponding
 *    {@code format()} methods are also provided. That is you can
 *    format either:</p>
 *    <ul>
 *       <li>using a specified pattern</li>
 *       <li>using the format for a specified {@link Locale}</li>
 *       <li>using the format for the <em>default</em> {@link Locale}</li>
 *    </ul>
 *
 * @since 1.3.0
 */
public class LongValidator extends AbstractNumberValidator {

    private static final long serialVersionUID = -5117231731027866098L;

    private static final LongValidator VALIDATOR = new LongValidator();

    /**
     * Gets the singleton instance of this validator.
     * @return A singleton instance of the LongValidator.
     */
    public static LongValidator getInstance() {
        return VALIDATOR;
    }

    /**
     * Constructs a <em>strict</em> instance.
     */
    public LongValidator() {
        this(true, STANDARD_FORMAT);
    }

    /**
     * <p>Construct an instance with the specified strict setting
     *    and format type.</p>
     *
     * <p>The {@code formatType} specified what type of
     *    {@code NumberFormat} is created - valid types
     *    are:</p>
     *    <ul>
     *       <li>AbstractNumberValidator.STANDARD_FORMAT -to create
     *           <em>standard</em> number formats (the default).</li>
     *       <li>AbstractNumberValidator.CURRENCY_FORMAT -to create
     *           <em>currency</em> number formats.</li>
     *       <li>AbstractNumberValidator.PERCENT_FORMAT -to create
     *           <em>percent</em> number formats (the default).</li>
     *    </ul>
     *
     * @param strict {@code true} if strict
     *        {@code Format} parsing should be used.
     * @param formatType The {@code NumberFormat} type to
     *        create for validation, default is STANDARD_FORMAT.
     */
    public LongValidator(final boolean strict, final int formatType) {
        super(strict, formatType, false);
    }

    /**
     * Check if the value is within a specified range.
     *
     * @param value The {@code Number} value to check.
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return {@code true} if the value is within the
     *         specified range.
     */
    public boolean isInRange(final long value, final long min, final long max) {
        return value >= min && value <= max;
    }

    /**
     * Check if the value is within a specified range.
     *
     * @param value The {@code Number} value to check.
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return {@code true} if the value is within the
     *         specified range.
     */
    public boolean isInRange(final Long value, final long min, final long max) {
        return isInRange(value.longValue(), min, max);
    }

    /**
     * Check if the value is less than or equal to a maximum.
     *
     * @param value The value validation is being performed on.
     * @param max The maximum value.
     * @return {@code true} if the value is less than
     *         or equal to the maximum.
     */
    public boolean maxValue(final long value, final long max) {
        return value <= max;
    }

    /**
     * Check if the value is less than or equal to a maximum.
     *
     * @param value The value validation is being performed on.
     * @param max The maximum value.
     * @return {@code true} if the value is less than
     *         or equal to the maximum.
     */
    public boolean maxValue(final Long value, final long max) {
        return maxValue(value.longValue(), max);
    }

    /**
     * Check if the value is greater than or equal to a minimum.
     *
     * @param value The value validation is being performed on.
     * @param min The minimum value.
     * @return {@code true} if the value is greater than
     *         or equal to the minimum.
     */
    public boolean minValue(final long value, final long min) {
        return value >= min;
    }

    /**
     * Check if the value is greater than or equal to a minimum.
     *
     * @param value The value validation is being performed on.
     * @param min The minimum value.
     * @return {@code true} if the value is greater than
     *         or equal to the minimum.
     */
    public boolean minValue(final Long value, final long min) {
        return minValue(value.longValue(), min);
    }

    /**
     * Convert the parsed value to a {@code Long}.
     *
     * @param value The parsed {@code Number} object created.
     * @param formatter The Format used to parse the value with.
     * @return The parsed {@code Number} converted to a
     *         {@code Long}.
     */
    @Override
    protected Object processParsedValue(final Object value, final Format formatter) {

        // Parsed value will be Long if it fits in a long and is not fractional
        if (value instanceof Long) {
            return value;
        }
        return null;

    }

    /**
     * <p>Validate/convert a {@code Long} using the default
     *    {@link Locale}.
     *
     * @param value The value validation is being performed on.
     * @return The parsed {@code Long} if valid or {@code null}
     *  if invalid.
     */
    public Long validate(final String value) {
        return (Long) parse(value, (String) null, (Locale) null);
    }

    /**
     * <p>Validate/convert a {@code Long} using the
     *    specified {@link Locale}.
     *
     * @param value The value validation is being performed on.
     * @param locale The locale to use for the number format, system default if null.
     * @return The parsed {@code Long} if valid or {@code null} if invalid.
     */
    public Long validate(final String value, final Locale locale) {
        return (Long) parse(value, (String) null, locale);
    }

    /**
     * <p>Validate/convert a {@code Long} using the
     *    specified <em>pattern</em>.
     *
     * @param value The value validation is being performed on.
     * @param pattern The pattern used to validate the value against.
     * @return The parsed {@code Long} if valid or {@code null} if invalid.
     */
    public Long validate(final String value, final String pattern) {
        return (Long) parse(value, pattern, (Locale) null);
    }

    /**
     * <p>Validate/convert a {@code Long} using the
     *    specified pattern and/ or {@link Locale}.
     *
     * @param value The value validation is being performed on.
     * @param pattern The pattern used to validate the value against, or the
     *        default for the {@link Locale} if {@code null}.
     * @param locale The locale to use for the date format, system default if null.
     * @return The parsed {@code Long} if valid or {@code null} if invalid.
     */
    public Long validate(final String value, final String pattern, final Locale locale) {
        return (Long) parse(value, pattern, locale);
    }
}
