package  com.viniciussantos.SpringRestAPI.interfaces.incoming.mapping

import com.viniciussantos.SpringRestAPI.domain.TravelRequest
import com.viniciussantos.SpringRestAPI.domain.PassengerRepository
import com.viniciussantos.SpringRestAPI.interfaces.incoming.PassengerAPI
import com.viniciussantos.SpringRestAPI.interfaces.incoming.TravelRequestInput
import com.viniciussantos.SpringRestAPI.interfaces.incoming.TravelRequestOutput
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

// A anotação @Component indica que esta classe é um bean gerenciado pelo Spring
@Component
class TravelRequestMapper(val passengersRepository: PassengerRepository) {

    // Esta função mapeia um TravelRequestInput para um TravelRequest
    fun map(input: TravelRequestInput): TravelRequest {
        // Busca o passageiro pelo ID. Se não encontrar, lança uma exceção
        val passenger =
            passengersRepository
                .findById(input.passengerId)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        // Retorna um novo TravelRequest com os dados do input e o passageiro encontrado
        return TravelRequest(
            passenger = passenger,
            origin = input.origin,
            destination = input.destination
        )
    }

    // Esta função mapeia um TravelRequest para um TravelRequestOutput
    fun map(travelRequest: TravelRequest): TravelRequestOutput {
        // Retorna um novo TravelRequestOutput com os dados do TravelRequest
        return TravelRequestOutput(
            id = travelRequest.id!!,
            origin = travelRequest.origin,
            destination = travelRequest.destination,
            status = travelRequest.status,
            creationDate = travelRequest.creationDate
        )
    }

    // Esta função constrói um EntityModel de TravelRequestOutput com um link para o passageiro
    fun buildOutputModel(travelRequest: TravelRequest, output: TravelRequestOutput): EntityModel<TravelRequestOutput> {
        // Cria um link para o passageiro
        val passengerLink = WebMvcLinkBuilder
            .linkTo(PassengerAPI::class.java)
            .slash(travelRequest.passenger.id)
            .withRel("passenger")
            .withTitle(travelRequest.passenger.name)

        // Retorna um EntityModel com o output e o link para o passageiro
        return EntityModel.of(output, passengerLink)
    }

    fun buildOutputModel(requests: List<TravelRequest>) =
        requests.map {
            buildOutputModel(it, map(it))
        }
}
//```
//
//A classe `TravelRequestMapper` é anotada com `@Component`, o que significa que é um bean gerenciado pelo Spring e pode ser injetada automaticamente onde for necessário. Ela tem uma dependência no `PassengerRepository`, que é usada para buscar passageiros pelo ID.
//
//A classe tem três funções principais:
//
//- `map(input: TravelRequestInput): TravelRequest`: Esta função aceita um `TravelRequestInput` e retorna um `TravelRequest`. Ela busca o passageiro pelo ID fornecido no input e lança uma exceção se o passageiro não for encontrado.
//
//- `map(travelRequest: TravelRequest): TravelRequestOutput`: Esta função aceita um `TravelRequest` e retorna um `TravelRequestOutput`. Ela simplesmente copia os dados do `TravelRequest` para um novo `TravelRequestOutput`.
//
//- `buildOutputModel(travelRequest: TravelRequest, output: TravelRequestOutput) :EntityModel<TravelRequestOutput>`: Esta função aceita um `TravelRequest` e um `TravelRequestOutput`, e retorna um `EntityModel` de `TravelRequestOutput`. Ela adiciona um link para o passageiro ao `EntityModel`.