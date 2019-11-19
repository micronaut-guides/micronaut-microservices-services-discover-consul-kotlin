package example.micronaut.bookrecommendation

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxStreamingHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object BookControllerSpec : Spek({
    describe("BookController Suite") {
        var embeddedServer: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        var client: RxStreamingHttpClient = embeddedServer.applicationContext.createBean(RxStreamingHttpClient::class.java, embeddedServer.url)

        it("books can be retrieved") {
            val req = HttpRequest.GET<Any>("/books")
            val books = client.jsonStream(req, BookRecommendation::class.java)
            assertEquals(1, books.toList().blockingGet().size)
            assertEquals("Building Microservices", books.toList().blockingGet()[0].name)
        }

        afterGroup {
            client.close()
            embeddedServer.close()
        }
    }

})