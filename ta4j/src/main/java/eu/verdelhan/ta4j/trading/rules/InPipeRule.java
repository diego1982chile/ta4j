/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 Marc de Verdelhan & respective authors (see AUTHORS)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eu.verdelhan.ta4j.trading.rules;

import eu.verdelhan.ta4j.Decimal;
import eu.verdelhan.ta4j.Indicator;
import eu.verdelhan.ta4j.TradingRecord;
import eu.verdelhan.ta4j.indicators.simple.ConstantIndicator;

/**
 * Indicator-between-indicators rule.
 * <p>
 * Satisfied when the value of the {@link Indicator indicator} is between the values of the boundary (up/down) indicators.
 */
public class InPipeRule extends AbstractRule {

    /** The upper indicator */
    private Indicator<Decimal> upper;
    /** The lower indicator */
    private Indicator<Decimal> lower;
    /** The evaluated indicator */
    private Indicator<Decimal> ref;

    /**
     * Constructor.
     * @param ref the reference indicator
     * @param upper the upper threshold
     * @param lower the lower threshold
     */
    public InPipeRule(Indicator<Decimal> ref, Decimal upper, Decimal lower) {
        this(ref, new ConstantIndicator<Decimal>(upper), new ConstantIndicator<Decimal>(lower));
    }

    /**
     * Constructor.
     * @param ref the reference indicator
     * @param upper the upper indicator
     * @param lower the lower indicator
     */
    public InPipeRule(Indicator<Decimal> ref, Indicator<Decimal> upper, Indicator<Decimal> lower) {
        this.upper = upper;
        this.lower = lower;
        this.ref = ref;
    }

    @Override
    public boolean isSatisfied(int index, TradingRecord tradingRecord) {
        final boolean satisfied = ref.getValue(index).isLessThanOrEqual(upper.getValue(index))
                && ref.getValue(index).isGreaterThanOrEqual(lower.getValue(index));
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }
}
