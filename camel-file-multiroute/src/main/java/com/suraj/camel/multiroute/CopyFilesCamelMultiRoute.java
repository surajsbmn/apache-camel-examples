package com.suraj.camel.multiroute;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CopyFilesCamelMultiRoute {
	public static void main(String[] args) {

		// Create the Camel context
		try (CamelContext context = new DefaultCamelContext()) {

			context.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					// Camel route to copy files from data/input directory to data/output directory
					from("file:data/input_1?noop=true").to("log:?level=INFO&showBody=true&showHeaders=true")
							.to("file:data/output_1");

					from("file:data/input_2?noop=true").to("log:?level=INFO&showBody=true&showHeaders=true")
							.to("file:data/output_2").to("file:data/output_3");
				}
			});

			// Start the Camel context
			context.start();

			// Wait for camel to finish the operation
			Thread.sleep(5000);

			// Stop the Camel context
			context.stop();

		} catch (Exception e) {
			System.out.println("Exception :: " + e);
			e.printStackTrace();
		}
	}
}
