//tag::packageandimports[]
package example.micronaut.bookrecommendation

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.Client
import io.reactivex.Flowable

//end::packageandimports[]

/*
//tag::harcoded[]
@Client("http://localhost:8081") // <1>
//end::harcoded[]
*/
//tag::consul[]
@Client(id = "bookcatalogue") // <1>
//end::consul[]
//tag::clazz[]
interface BookCatalogueClient : BookCatalogueOperations {

    @Get("/books")
    override fun findAll(): Flowable<Book>
}
//end::clazz[]
