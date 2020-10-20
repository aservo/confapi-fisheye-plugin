package de.aservo.confapi.fisheye.rest.api;

import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.model.ErrorCollection;
import de.aservo.confapi.fisheye.model.SettingsHttpBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface SettingsHttpResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = { ConfAPI.SETTINGS },
            summary = "Get the http server settings",
            responses = {
                    @ApiResponse(
                            responseCode = "200", content = @Content(schema = @Schema(implementation = SettingsHttpBean.class)),
                            description = "Returns the server settings"
                    ),
                    @ApiResponse(
                            responseCode = "default", content = @Content(schema = @Schema(implementation = ErrorCollection.class)),
                            description = "Returns a list of error messages."
                    ),
            }
    )
    Response getSettings();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = { ConfAPI.SETTINGS },
            summary = "Set the http server settings",
            responses = {
                    @ApiResponse(
                            responseCode = "200", content = @Content(schema = @Schema(implementation = SettingsHttpBean.class)),
                            description = "Returns the server settings"
                    ),
                    @ApiResponse(
                            responseCode = "default", content = @Content(schema = @Schema(implementation = ErrorCollection.class)),
                            description = "Returns a list of error messages."
                    ),
            }
    )
    Response setSettings(
            @NotNull final SettingsHttpBean bean);
}
