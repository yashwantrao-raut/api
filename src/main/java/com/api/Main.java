package com.api;

import com.api.config.ApiConfig;
import com.api.domain.Note;
import com.api.domain.User;
import com.api.resource.NoteResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Main extends Application<ApiConfig> {

    private final HibernateBundle<ApiConfig> hibernate = new HibernateBundle<ApiConfig>(User.class, Note.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ApiConfig configuration) {
            return configuration.getDatabase();
        }
    };


    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public void run(ApiConfig configuration, Environment environment) throws Exception {
        environment.jersey().register(new NoteResource());
    }


    @Override
    public void initialize(Bootstrap<ApiConfig> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

}
