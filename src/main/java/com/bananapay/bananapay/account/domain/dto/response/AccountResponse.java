package com.bananapay.bananapay.account.domain.dto.response;

import java.util.UUID;

public record AccountResponse(UUID id, String ownerName, String ownerCpf) {
}
