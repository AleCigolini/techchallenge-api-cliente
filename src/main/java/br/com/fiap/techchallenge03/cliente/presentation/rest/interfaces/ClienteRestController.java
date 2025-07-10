package br.com.fiap.techchallenge03.cliente.presentation.rest.interfaces;

import br.com.fiap.techchallenge03.cliente.common.domain.dto.request.ClienteRequestDto;
import br.com.fiap.techchallenge03.cliente.common.domain.dto.request.IdentificarClienteDto;
import br.com.fiap.techchallenge03.cliente.common.domain.dto.response.ClienteResponseDto;
import br.com.fiap.techchallenge03.core.utils.validators.cpf.Cpf;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "${tag.swagger.cliente.name}", description = "${tag.swagger.cliente.description}")
public interface ClienteRestController {

    /**
     * Busca o cliente a partir de seu CPF.
     *
     * @param cpf String do cpf do cliente
     * @return {@link ResponseEntity<ClienteResponseDto>}
     */
    @Operation(summary = "Buscar cliente a partir de seu cpf",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Encontrado mais de um cliente para o mesmo cpf/Erros de validação",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Cliente não encntrado",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    br.com.fiap.techchallenge03.cliente.common.domain.dto.response.ClienteResponseDto buscarClientePorCpf(@Parameter(description = "CPF válido do cliente", example = "52932609017", required = true)  @Valid @Cpf String cpf);

    /**
     * Busca o cliente a partir de seu e-mail.
     *
     * @param email String do e-mail do cliente
     * @return {@link ResponseEntity<ClienteResponseDto>}
     */
    @Operation(summary = "Buscar cliente a partir de seu e-mail",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Encontrado mais de um cliente para o mesmo e-mail/Erros de validação",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Cliente não encntrado",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    ClienteResponseDto buscarClientePorEmail(@Parameter(description = "E-mail válido do cliente", example = "email@email.com", required = true) @Valid String email);

    /**
     * Busca o cliente a partir de seu id.
     *
     * @param id UUID do id do cliente
     * @return {@link ResponseEntity<ClienteResponseDto>}
     */
    @Operation(summary = "Buscar cliente a partir de seu id",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "404", description = "Cliente não encntrado",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    ClienteResponseDto buscarClientePorId(@Parameter(description = "ID do cliente", example = "123e4567-e89b-12d3-a456-426655440000", required = true) UUID id);

    /**
     * Cadastrar um novo cliente.
     *
     * @param clienteRequestDto objeto contendo os dados para cadastro do cliente, possuindo cpf ou e-mail obrigatóriamente e de maneira válida
     * @return {@link ResponseEntity<ClienteResponseDto>}
     */
    @Operation(summary = "Cadastrar cliente",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Erro no cadastro do cliente/Erros de validação",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
    })
    ClienteResponseDto cadastrarCliente(@Valid ClienteRequestDto clienteRequestDto);

    /**
     * Identificar um cliente a partir de seu CPF ou e-mail.
     *
     * @param identificarClienteDto objeto contendo os dados para a identificação do cliente, possuindo cpf obrigatóriamente e de maneira válida
     * @return {@link ResponseEntity<ClienteResponseDto>}
     */
    @Operation(summary = "Identificar cliente",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Erro na identificação do cliente/Erros de validação",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
            })
    ClienteResponseDto identificarCliente(@Valid IdentificarClienteDto identificarClienteDto);

}
