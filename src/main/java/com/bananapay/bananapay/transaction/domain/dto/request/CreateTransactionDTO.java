package com.bananapay.bananapay.transaction.domain.dto.request;

import com.bananapay.bananapay.transaction.domain.model.PaymentTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record CreateTransactionDTO(
        @NotNull UUID originId,
        @NotNull UUID receiverId,
        @NotNull @Positive Integer quantity,
        @NotNull PaymentTypeEnum paymentType
) {
}
