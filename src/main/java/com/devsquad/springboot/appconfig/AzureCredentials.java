package com.devsquad.springboot.appconfig;

import com.azure.core.credential.TokenCredential;
import com.azure.identity.EnvironmentCredentialBuilder;
import com.azure.spring.cloud.config.AppConfigurationCredentialProvider;
import com.azure.spring.cloud.config.KeyVaultCredentialProvider;
import com.azure.identity.DefaultAzureCredentialBuilder;

public class AzureCredentials implements KeyVaultCredentialProvider, AppConfigurationCredentialProvider {

    private AzureCredentialsMethod azureCredentialsMethod;
    private TokenCredential tokenCredential;

    public AzureCredentials(AzureCredentialsMethod azureCredentialsMethod){
        this.azureCredentialsMethod = azureCredentialsMethod;
    }

    @Override
    public TokenCredential getKeyVaultCredential(String uri) {
        return getTokenCredential();
    }

    @Override
    public TokenCredential getAppConfigCredential(String uri) {
        return getTokenCredential();
    }

    private TokenCredential getTokenCredential() {
        if (tokenCredential != null)
            return tokenCredential;

        if (azureCredentialsMethod == AzureCredentialsMethod.ENVIRONMENT) {
            tokenCredential = new EnvironmentCredentialBuilder().build();
        } else {
            tokenCredential = new DefaultAzureCredentialBuilder().build();
        }
        return tokenCredential;
    }
}
