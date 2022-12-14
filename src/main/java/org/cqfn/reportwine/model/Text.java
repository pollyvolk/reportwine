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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.Objects;

/**
 * A text data.
 *
 * @since 0.1
 */
public final class Text implements Value {
    /**
     * The text.
     */
    private final String value;

    /**
     * Constructor.
     * @param value The text as string
     */
    public Text(final String value) {
        this.value = value;
    }

    /**
     * Returns the text value.
     * @return The text
     */
    public String getValue() {
        return this.value;
    }

    @Override
    public String toJsonString() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this.toJson());
    }

    @Override
    public JsonElement toJson() {
        JsonPrimitive primitive;
        try {
            final int numeric = Integer.parseInt(this.value);
            primitive = new JsonPrimitive(numeric);
        } catch (final NumberFormatException ignored) {
            try {
                final double numeric = Double.parseDouble(this.value);
                primitive = new JsonPrimitive(numeric);
            } catch (final NumberFormatException exception) {
                primitive = new JsonPrimitive(this.value);
            }
        }
        return primitive;
    }

    @Override
    public boolean equals(final Object obj) {
        final Text text;
        boolean equal = false;
        if (obj instanceof Text) {
            text = (Text) obj;
            if (this.value.equals(text.getValue())) {
                equal = true;
            }
        }
        return equal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }
}
