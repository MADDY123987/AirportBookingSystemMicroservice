package com.FlyNova.Pricing_Service.Mapper;

import com.FlyNova.Pricing_Service.Model.Fare;
import com.FlyNova.Pricing_Service.Model.FareRules;
import com.FlyNova.payload.request.FareRulesRequest;
import com.FlyNova.payload.response.FareRulesResponse;

public class FareRulesMapper {
    public static FareRules toEntity(FareRulesRequest request, Fare fare) {
        if (request == null) return null;
        return FareRules.builder()
                .ruleName(request.getRuleName())
                .fare(fare)
                .airlineId(request.getAirlineId())
                .isRefundable(request.getIsRefundable())
                .changeFee(request.getChangeFee())
                .cancellationFee(request.getCancellationFee())
                .refundDeadLineDays(request.getRefundDeadLineDays())
                .ChangeDeadlineHours(request.getChangeDeadlineHours())
                .isChangeable(request.getIsChangeable() != null ? request.getIsChangeable() : false)
                .build();
    }

    public static FareRulesResponse toResponse(FareRules fareRules) {
        if (fareRules == null) return null;
        return FareRulesResponse.builder()
                .id(fareRules.getId())
                .ruleName(fareRules.getRuleName())
                .fareId(fareRules.getFare() != null ? fareRules.getFare().getId() : null)
                .airlineId(fareRules.getAirlineId())
                .isRefundable(fareRules.getIsRefundable())
                .changeFee(fareRules.getChangeFee())
                .cancellationFee(fareRules.getCancellationFee())
                .refundDeadLineDays(fareRules.getRefundDeadLineDays())
                .ChangeDeadlineHours(fareRules.getChangeDeadlineHours())
                .isChangeable(fareRules.getIsChangeable())
                .createdAt(fareRules.getCreatedAt())
                .UpdatedAt(fareRules.getUpdatedAt())
                .build();
    }

    public static void updateEntity(FareRulesRequest request, FareRules existing) {
        if (request == null || existing == null) return;
        if (request.getRuleName() != null) existing.setRuleName(request.getRuleName());
        if (request.getAirlineId() != null) existing.setAirlineId(request.getAirlineId());
        if (request.getIsRefundable() != null) existing.setIsRefundable(request.getIsRefundable());
        if (request.getChangeFee() != null) existing.setChangeFee(request.getChangeFee());
        if (request.getCancellationFee() != null) existing.setCancellationFee(request.getCancellationFee());
        if (request.getRefundDeadLineDays() != null) existing.setRefundDeadLineDays(request.getRefundDeadLineDays());
        if (request.getChangeDeadlineHours() != null) existing.setChangeDeadlineHours(request.getChangeDeadlineHours());
        if (request.getIsChangeable() != null) existing.setIsChangeable(request.getIsChangeable());
    }
}
