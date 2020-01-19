package com.suraj.camel.file;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CopyFilesCamel {

	public static void main(String[] args) {

		// Create the Camel context
		CamelContext context = new DefaultCamelContext();

		try {
			context.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					// Camel route to copy files from data/input directory to data/output directory
					from("file:data/input?noop=true").to("log:?level=INFO&showBody=true&showHeaders=true")
							.to("file:data/output");
				}
			});

			// Start the Camel context
			context.start();
			
			// Wait for camel to finish the operation
			Thread.sleep(5000);

			// Stop the Camel context
			context.stop();
			context.close();

		} catch (Exception e) {
			System.out.println("Exception :: " + e);
			e.printStackTrace();
		}
	}
}
