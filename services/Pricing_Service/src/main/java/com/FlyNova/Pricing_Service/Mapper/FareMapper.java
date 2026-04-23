package com.FlyNova.Pricing_Service.Mapper;

import com.FlyNova.Pricing_Service.Model.Fare;
import com.FlyNova.embeddable.*;
import com.FlyNova.payload.request.FareRequest;
import com.FlyNova.payload.response.FareResponse;

public class FareMapper {

    public static Fare toEntity(FareRequest request) {
        if (request == null) return null;

        SeatBenefits seatBenefits = SeatBenefits.builder()
                .extraSeatSpace(bool(request.getExtraSeatSpace()))
                .preferredSeatsChoice(bool(request.getPreferredSeatChoice()))
                .advanceSeatSelection(bool(request.getAdvanceSeatSelection()))
                .guaranteedSeatTogether(bool(request.getGuaranteedSeatTogether()))
                .build();

        BoardingBenefits boardingBenefits = BoardingBenefits.builder()
                .priorityBoarding(bool(request.getPriorityBoarding()))
                .priorityCheckin(bool(request.getPriorityCheckin()))
                .fastTrackSecurity(bool(request.getFastTrackSecurity()))
                .build();

        InFlightBenefits inFlightBenefits = InFlightBenefits.builder()
                .complimentaryMeals(bool(request.getComplimentaryMeals()))
                .premuimMealChoice(bool(request.getPremiumMealChoice()))
                .inFlightInternet(bool(request.getInFlightInternet()))
                .inFlightEntertainment(bool(request.getInFlightEntertainment()))
                .complimentaryBeverages(bool(request.getComplimentaryBeverages()))
                .build();

        FlexibilityBenefits flexibilityBenefits = FlexibilityBenefits.builder()
                .freeDataChange(bool(request.getFreeDateChange()))
                .partilalRefund(bool(request.getPartialRefund()))
                .fullRefund(bool(request.getFullRefund()))
                .build();

        PremuimServiceBenefits premiumServiceBenefits = PremuimServiceBenefits.builder()
                .loungeAccess(bool(request.getLoungeAccess()))
                .airportTransfer(bool(request.getAirportTransfer()))
                .build();

        Double calculatedPrice = request.getCurrentPrice();
        if (calculatedPrice == null) {
            calculatedPrice = request.getBaseFare()
                    + (request.getTaxesAndFees() != null ? request.getTaxesAndFees() : 0.0)
                    + (request.getAirlineFees() != null ? request.getAirlineFees() : 0.0);
        }

        return Fare.builder()
                .name(request.getName())
                .rbdCode(request.getRbdCode())
                .flightId(request.getFlightId())
                .cabinClassId(request.getCabinClassId())

                .baseFare(request.getBaseFare())
                .taxesAndFees(request.getTaxesAndFees())
                .airlineFees(request.getAirlineFees())
                .currentPrice(calculatedPrice)
                .fareLabel(request.getFareLabel())
                .seatBenefits(seatBenefits)
                .boardingBenefits(boardingBenefits)
                .inFlightBenefits(inFlightBenefits)
                .flexibilityBenefits(flexibilityBenefits)
                .premuimServiceBenefits(premiumServiceBenefits)
                .build();
    }

    public static FareResponse toResponse(Fare fare) {
        if (fare == null) return null;
        return FareResponse.builder()
                .id(fare.getId())
                .name(fare.getName())
                .rbdCode(fare.getRbdCode())
                .flightId(fare.getFlightId())
                .cabinClassId(fare.getCabinClassId())
                .cabinClass(fare.getCabinClass())
                .baseFare(fare.getBaseFare())
                .taxesAndFees(fare.getTaxesAndFees())
                .airlineFees(fare.getAirlineFees())
                .currentPrice(fare.getCurrentPrice())
                .totalPrice(fare.getTotalPrice())
                .fareLabel(fare.getFareLabel())
                //todo:watch fare Rule
                //.fareRulesId(fare.getFareRules() != null ? fare.getFareRules().getId() : null)
                // Seat benefits
                .extraSeatSpace(fare.getSeatBenefits() != null ? fare.getSeatBenefits().getExtraSeatSpace() : false)
                .preferredSeatChoice(fare.getSeatBenefits() != null ? fare.getSeatBenefits().getPreferredSeatsChoice() : false)
                .advanceSeatSelection(fare.getSeatBenefits() != null ? fare.getSeatBenefits().getAdvanceSeatSelection() : false)
                .guaranteedSeatTogether(fare.getSeatBenefits() != null ? fare.getSeatBenefits().getGuaranteedSeatTogether() : false)
                // Boarding benefits
                .priorityBoarding(fare.getBoardingBenefits() != null ? fare.getBoardingBenefits().getPriorityBoarding() : false)
                .priorityCheckin(fare.getBoardingBenefits() != null ? fare.getBoardingBenefits().getPriorityCheckin() : false)
                .fastTrackSecurity(fare.getBoardingBenefits() != null ? fare.getBoardingBenefits().getFastTrackSecurity() : false)
                // In-flight benefits
                .complimentaryMeals(fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getComplimentaryMeals() : false)
                .premiumMealChoice(fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getPremuimMealChoice() : false)
                .inFlightInternet(fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getInFlightInternet() : false)
                .inFlightEntertainment(fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getInFlightEntertainment() : false)
                .complimentaryBeverages(fare.getInFlightBenefits() != null ? fare.getInFlightBenefits().getComplimentaryBeverages() : false)
                // Flexibility benefits
                .freeDateChange(fare.getFlexibilityBenefits() != null ? fare.getFlexibilityBenefits().getFreeDataChange() : false)
                .partialRefund(fare.getFlexibilityBenefits() != null ? fare.getFlexibilityBenefits().getPartilalRefund() : false)
                .fullRefund(fare.getFlexibilityBenefits() != null ? fare.getFlexibilityBenefits().getFullRefund() : false)
                // Premium service benefits
                .loungeAccess(fare.getPremuimServiceBenefits() != null ? fare.getPremuimServiceBenefits().getLoungeAccess() : false)
                .airportTransfer(fare.getPremuimServiceBenefits() != null ? fare.getPremuimServiceBenefits().getAirportTransfer() : false)
                // Nested responses
                //Watch Fair Rule and Baggage Policy
                //.fareRules(fare.getFareRules() != null ? FareRulesMapper.toResponse(fare.getFareRules()) : null)
                //.baggagePolicy(fare.getBaggagePolicy() != null ? BaggagePolicyMapper.toResponse(fare.getBaggagePolicy()) : null)
                .createdAt(fare.getCreatedAt())
                .updatedAt(fare.getUpdatedAt())
                .build();
    }

    public static void updateEntity(FareRequest request, Fare existing) {
        if (request == null || existing == null) return;
        if (request.getName() != null) existing.setName(request.getName());
        if (request.getRbdCode() != null) existing.setRbdCode(request.getRbdCode());
        if (request.getFlightId() != null) existing.setFlightId(request.getFlightId());
        if (request.getCabinClassId() != null) existing.setCabinClassId(request.getCabinClassId());

        if (request.getBaseFare() != null) existing.setBaseFare(request.getBaseFare());
        if (request.getTaxesAndFees() != null) existing.setTaxesAndFees(request.getTaxesAndFees());
        if (request.getAirlineFees() != null) existing.setAirlineFees(request.getAirlineFees());
        if (request.getCurrentPrice() != null) existing.setCurrentPrice(request.getCurrentPrice());
        if (request.getFareLabel() != null) existing.setFareLabel(request.getFareLabel());

        // Update embedded benefits
        SeatBenefits sb = existing.getSeatBenefits();
        if (request.getExtraSeatSpace() != null) sb.setExtraSeatSpace(request.getExtraSeatSpace());
        if (request.getPreferredSeatChoice() != null) sb.setPreferredSeatsChoice(request.getPreferredSeatChoice());
        if (request.getAdvanceSeatSelection() != null) sb.setAdvanceSeatSelection(request.getAdvanceSeatSelection());
        if (request.getGuaranteedSeatTogether() != null) sb.setGuaranteedSeatTogether(request.getGuaranteedSeatTogether());

        BoardingBenefits bb = existing.getBoardingBenefits();
        if (request.getPriorityBoarding() != null) bb.setPriorityBoarding(request.getPriorityBoarding());
        if (request.getPriorityCheckin() != null) bb.setPriorityCheckin(request.getPriorityCheckin());
        if (request.getFastTrackSecurity() != null) bb.setFastTrackSecurity(request.getFastTrackSecurity());

        InFlightBenefits ifb = existing.getInFlightBenefits();
        if (request.getComplimentaryMeals() != null) ifb.setComplimentaryMeals(request.getComplimentaryMeals());
        if (request.getPremiumMealChoice() != null) ifb.setPremuimMealChoice(request.getPremiumMealChoice());
        if (request.getInFlightInternet() != null) ifb.setInFlightInternet(request.getInFlightInternet());
        if (request.getInFlightEntertainment() != null) ifb.setInFlightEntertainment(request.getInFlightEntertainment());
        if (request.getComplimentaryBeverages() != null) ifb.setComplimentaryBeverages(request.getComplimentaryBeverages());

        FlexibilityBenefits fb = existing.getFlexibilityBenefits();
        if (request.getFreeDateChange() != null) fb.setFreeDataChange(request.getFreeDateChange());
        if (request.getPartialRefund() != null) fb.setPartilalRefund(request.getPartialRefund());
        if (request.getFullRefund() != null) fb.setFullRefund(request.getFullRefund());

        PremuimServiceBenefits psb = existing.getPremuimServiceBenefits();
        if (request.getLoungeAccess() != null) psb.setLoungeAccess(request.getLoungeAccess());
        if (request.getAirportTransfer() != null) psb.setAirportTransfer(request.getAirportTransfer());
    }
    private static boolean bool(Boolean value) {
        return value != null ? value : false;
    }

}
