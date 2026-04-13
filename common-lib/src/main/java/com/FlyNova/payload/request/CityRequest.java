package com.FlyNova.payload.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityRequest {

    @NotBlank(message = "City Name is Required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "City Code is Requried")
    @Size(max = 10)
    private String cityCode;

    @NotBlank(message = "Country Code is Required")
    @Size(max = 5)
    private String countryCode;


    @NotBlank(message = "Country Name is Required")
    @Size(max = 100)
    private String countryName;


    @Size
    private String regionCode;

    @Size
    private String timeZoneOffSet;
}
