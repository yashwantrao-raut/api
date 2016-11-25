package com.api;

import com.api.auth.BasicAuthenticator;
import com.api.auth.UserPrincipal;
import com.api.config.ApiConfig;
import com.api.converter.NoteToAndFromDomainConverter;
import com.api.converter.UserToAndFromDomainConverter;
import com.api.dao.NoteDao;
import com.api.dao.UserDao;
import com.api.domain.Note;
import com.api.domain.User;
import com.api.provider.java.sql.HibernateConstraintViolationExceptionMapper;
import com.api.resource.NoteResource;
import com.api.resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
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
        UserDao userDao = new UserDao(hibernate.getSessionFactory());
        AuthDynamicFeature authDynamicFeature = new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<UserPrincipal>()
                        .setAuthenticator(new BasicAuthenticator(userDao))
                        .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter());
        environment.jersey().register(authDynamicFeature);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(UserPrincipal.class));
        environment.jersey().register(new HibernateConstraintViolationExceptionMapper());

        environment.jersey().register(new NoteResource(new NoteToAndFromDomainConverter(),new NoteDao(hibernate.getSessionFactory())));
        environment.jersey().register(new UserResource(userDao,new UserToAndFromDomainConverter()));
    }


    @Override
    public void initialize(Bootstrap<ApiConfig> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

}
