# java-spring-boot-app-config

This is a very simple JAVA 17 using Spring-Boot 3 to explorer app configuration.

Using app configuration files help us targeting different environment when testing, Spring Boot
offers a really nice feature to compose configurations, like .NET ConfigurationManager.
Allowing us to define a file with the common application properties and override/augment for the specific environments. For that we can use the `application.yml` and its variants `application-<env>.yml`

Setting the environment can be done by changing the env var `spring_profiles_active`, there is
also a system property that is mentioned at the docs `Dspring.profiles.active=test`, but it
didn't work for me.

## Azure App Configuration

Later on this app I added the option to work with the [Azure App Configuration](https://learn.microsoft.com/en-us/azure/azure-app-configuration/overview), in order to setup it usage and being able to turn off I had to add another configuration file `bootstrap.yml`, this file will be load before the `application.yml`.

To start using it add in your dependency the library `azure-spring-cloud-appconfiguration-config`

``` xml
<dependency>
    <groupId>com.azure.spring</groupId>
    <artifactId>azure-spring-cloud-appconfiguration-config</artifactId>
    <version>2.10.0</version>
</dependency>
```

By default I setup the [bootstrap](/src/main/resources/bootstrap.yml) to use the Azure App Configuration, and for that
it is necessary to define the following Env Vars:

- APP_CONFIG_URI: The service URI and **NOT** the connection string, check notes.
- APP_CONFIG_FILTER_KEY: Prefix that will be used to filter the app configuration
- APP_CONFIG_FILTER_LABEL: Label that will be used to filter the app configuration

>note: By default I want to use the pod identity to access the service, so there is no need to have the
service connection string.

To test the application locally, there is no need to use any external dependency so I'm disabling the App configuration using [bootstrap-local](/src/main/resources/bootstrap-local.yml)

If there is a necessity to test the app connecting to the App Configuration we can create a new bootstrap file called `bootstrap-local-appconfig` using the [template](/src/main/resources/bootstrap-appconfig-template.yml)

Setting the same Env Var `spring_profiles_active` will help setting the files will be used to setup the application.

### Setting up a Azure App Configuration

I do recommend creating an Azure App Configuration Store to start playing with it, to do that follow these
 steps defined on [Create an Azure App Configuration Store](https://learn.microsoft.com/en-us/azure/azure-app-configuration/scripts/cli-create-service)


Creates one key to test the app

``` bash
az appconfig kv set --name $appConfigName --key "springboottest:hello-controller/another-message" --value "another-message-from-app-config" 
```

Change the values on the `bootstrap-local-appconfig.yml`

``` bash
make run-local-appconfig
```