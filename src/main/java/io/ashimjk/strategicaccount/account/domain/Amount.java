package io.ashimjk.strategicaccount.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Amount {

    public static Amount ZERO = Amount.of(BigDecimal.ZERO);

    @NonNull private final BigDecimal value;

    public static Amount of(BigDecimal value) {
        return new Amount(value);
    }

    public static Amount of(double value) {
        return new Amount(BigDecimal.valueOf(value));
    }

    public static Amount add(final Amount sourceAmount, final Amount targetAmount) {
        return new Amount(sourceAmount.value.add(targetAmount.value));
    }

    public static Amount subtract(final Amount source, final Amount target) {
        return new Amount(source.value.subtract(target.value));
    }

    public Amount negate() {
        return new Amount(this.value.negate());
    }

}
