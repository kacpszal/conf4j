/*
 * MIT License
 *
 * Copyright 2017 Sabre GLBL Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.sabre.oss.conf4j.converter;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * This class converts {@link Integer} to/from string.
 * <p>
 * It supports {@value #FORMAT} and {@value #LOCALE} meta-attributes, for more details see {@link AbstractNumberConverter}.
 */
public class IntegerConverter extends AbstractNumberConverter<Integer> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isApplicable(Type type, Map<String, String> attributes) {
        return isApplicable(type, Integer.class, Integer.TYPE);
    }

    @Override
    protected Integer parseWithoutFormat(String value) {
        return Integer.valueOf(value);
    }

    @Override
    protected Integer convertResult(Number value) {
        long longValue = value.longValue();
        if (longValue > Integer.MAX_VALUE || longValue < Integer.MIN_VALUE) {
            throw new IllegalArgumentException(String.format("Provided value: %d is out of Integer type range.", longValue));
        }
        return value.intValue();
    }
}