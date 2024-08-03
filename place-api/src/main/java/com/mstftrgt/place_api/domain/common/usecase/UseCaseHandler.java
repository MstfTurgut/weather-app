package com.mstftrgt.place_api.domain.common.usecase;

import com.mstftrgt.place_api.domain.common.model.UseCase;

public interface UseCaseHandler<E, T extends UseCase> {

    E handle(T useCase);
}