/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 Polina Volkhontseva
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.cqfn.reportwine.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.cqfn.reportwine.converters.IrToYargConverter;
import org.cqfn.reportwine.exceptions.BaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link Array} class.
 *
 * @since 0.1
 */
class ArrayTest {
    /**
     * Test case: a valid text array.
     */
    @Test
    void testValidTextArray() {
        final List<Value> values = new LinkedList<>();
        values.add(new Text("1"));
        values.add(new Text("2"));
        values.add(new Text("3"));
        final Array array = new Array(values);
        Assertions.assertTrue(array.isTextArray());
    }

    /**
     * Test case: a valid list of arrays.
     */
    @Test
    void testValidArrayList() {
        final List<Value> first = new LinkedList<>();
        first.add(new Text("11"));
        first.add(new Text("22"));
        final List<Value> second = new LinkedList<>();
        second.add(new Text("33"));
        second.add(new Text("44"));
        final Array array = new Array(
            Arrays.asList(
                new Array(first),
                new Array(second)
            )
        );
        Assertions.assertTrue(array.isArrayList());
    }

    /**
     * Test case: a valid array of pairs.
     */
    @Test
    void testValidPairArray() {
        final Pair first = new Pair("one");
        first.setValue(new Text("1"));
        final Pair second = new Pair("two");
        first.setValue(new Text("2"));
        final Array array = new Array(
            Arrays.asList(
                first,
                second
            )
        );
        Assertions.assertTrue(array.isPairArray());
    }

    /**
     * Test case: array of text values is expected, but other object is found.
     */
    @Test
    void testExpectedTextArrayException() {
        final List<Value> values = new LinkedList<>();
        values.add(new Text("111"));
        values.add(new Text("222"));
        final Pair nested = new Pair("nested");
        nested.setValue(new Text("object"));
        values.add(nested);
        final Array array = new Array(values);
        final Pair pair = new Pair("proj", array);
        boolean oops = false;
        try {
            new IrToYargConverter(pair).convert();
        } catch (final BaseException exception) {
            oops = true;
        }
        Assertions.assertTrue(oops);
    }

    /**
     * Test case: array of array values is expected, but other object is found.
     */
    @Test
    void testExpectedArrayListException() {
        final List<Value> first = new LinkedList<>();
        first.add(new Text("11"));
        first.add(new Text("22"));
        final List<Value> second = new LinkedList<>();
        second.add(new Text("33"));
        second.add(new Text("44"));
        final Array array = new Array(
            Arrays.asList(
                new Array(first),
                new Array(second),
                new Text("55")
            )
        );
        final Pair pair = new Pair("report", array);
        boolean oops = false;
        try {
            new IrToYargConverter(pair).convert();
        } catch (final BaseException exception) {
            oops = true;
        }
        Assertions.assertTrue(oops);
    }

    /**
     * Test case: array of array values is expected, but other object is found.
     */
    @Test
    void testExpectedPairArrayException() {
        final Pair first = new Pair("one");
        first.setValue(new Text("1"));
        final Pair second = new Pair("two");
        second.setValue(new Text("2"));
        final Array array = new Array(
            Arrays.asList(
                first,
                second,
                new Text("text")
            )
        );
        final Pair pair = new Pair("doc", array);
        boolean oops = false;
        try {
            new IrToYargConverter(pair).convert();
        } catch (final BaseException exception) {
            oops = true;
        }
        Assertions.assertTrue(oops);
    }
}
