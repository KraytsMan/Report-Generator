package com.softjourn.report.generator;

import com.softjourn.report.generator.model.ReportSettings;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.IOException;
import java.util.List;

import static com.softjourn.report.generator.util.Constants.print;

public class Launcher {

    private WebResource webResource;

    public Launcher(String link) {
        Client client = Client.create();
        webResource = client.resource(link);
    }

    public static void main(String[] args) throws IOException {
        Launcher launcher = new Launcher(args[0]);
        ReportSettings settings = ReportTypeReader.uploadReportSettings(args[1]);
        ReportGenerator generator = new ReportGenerator();
        launcher.pushToElastic(generator.generate(settings, Long.valueOf(args[2])));
    }

    private void pushToElastic(List<String> reports) {
        print("Pushing reports into ElasticSearch");
        for (String report : reports) {
            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, report);
            if (response.getStatus() != 201) {
                print("Generated report was not pushed into ElasticSearch");
            }
        }
    }

}
