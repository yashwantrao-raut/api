package com.api;

import com.api.config.ApiConfig;
import com.api.resource.NoteResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class Main extends Application<ApiConfig> {

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public void run(ApiConfig configuration, Environment environment) throws Exception {
        environment.jersey().register(new NoteResource());
    }
}
