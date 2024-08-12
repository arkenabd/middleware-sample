package ibk.main.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MainRoute extends RouteBuilder {

	@SuppressWarnings("unchecked")
	@Override
	public void configure() throws Exception {

		// MAIN REST
		restConfiguration().host("{{server.address}}").port("{{conf.port}}").component("netty-http").scheme("http");
		rest("/service/data")
			.get()
			.to("direct:dummyApi");
		
		from("direct:dummyApi")
			.log("Request Message : ${body}")
			.setBody(simple("<ResponseMessage>DUMMY RESPONSE</ResponseMessage>"))
			.log("Response Message : ${body}");

	}

}
