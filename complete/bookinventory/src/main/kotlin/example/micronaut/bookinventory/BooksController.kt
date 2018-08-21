package example.micronaut.bookinventory

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.validation.Validated
import javax.validation.constraints.NotBlank
import java.util.Optional

@Validated // <1>
@Controller("/books") // <2>
open class BooksController {

    @Produces(MediaType.TEXT_PLAIN) // <3>
    @Get("/stock/{isbn}") // <4>
    open fun stock(@NotBlank isbn: String): Boolean? {
        return bookInventoryByIsbn(isbn).map { (_, stock) -> stock > 0 }.orElse(null)
    }

    private fun bookInventoryByIsbn(isbn: String): Optional<BookInventory> {
        if (isbn == "1491950358") {
            return Optional.of(BookInventory(isbn, 4))

        } else if (isbn == "1680502395") {
            return Optional.of(BookInventory(isbn, 0))
        }
        return Optional.empty()
    }
}
