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
import com.google.gson.JsonNull;
import java.util.Objects;

/**
 * A code snippet to process data bindings to get value.
 *
 * @since 0.1
 */
public final class Code implements Value {
    /**
     * The code snippet.
     */
    private final String snippet;

    /**
     * Constructor.
     * @param code The code snippet in some programming language
     */
    public Code(final String code) {
        this.snippet = code;
    }

    /**
     * Returns the code.
     * @return The text of code as string
     */
    public String getValue() {
        return this.snippet;
    }

    @Override
    public String toJsonString() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this.toJson());
    }

    @Override
    public JsonElement toJson() {
        return JsonNull.INSTANCE;
    }

    @Override
    public boolean equals(final Object obj) {
        final Code code;
        boolean equal = false;
        if (obj instanceof Code) {
            code = (Code) obj;
            if (this.snippet.equals(code.getValue())) {
                equal = true;
            }
        }
        return equal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.snippet);
    }
}
