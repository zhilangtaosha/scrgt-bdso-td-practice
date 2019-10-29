package gov.dhs.uscis.bdso.celebrity.config;

import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import gov.dhs.uscis.bdso.celebrity.property.AWSProperties;

@Configuration
@EnableConfigurationProperties(AWSProperties.class)
public class AwsConfiguration {
    @Inject
    private AWSProperties awsProperties;

    @Bean
    @ConditionalOnProperty(prefix = "aws", name = "use-credentials", havingValue = "true", matchIfMissing = true)
    public AmazonS3 s3Client() {
        return AmazonS3ClientBuilder.standard().withCredentials(credentialProvider())
                .withClientConfiguration(clientConfiguration()).withRegion(awsProperties.getRegion()).build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "aws", name = "use-credentials", havingValue = "false")
    public AmazonS3 s3Client1() {
        return AmazonS3ClientBuilder.standard().withClientConfiguration(clientConfiguration())
                .withRegion(awsProperties.getRegion()).build();

    }

    private AWSCredentialsProvider credentialProvider() {
        AWSCredentialsProvider provider = null;

        if (StringUtils.isNotEmpty(awsProperties.getAccessKey())
                && StringUtils.isNotEmpty(awsProperties.getSecretKey())) {
            AWSCredentials credentials =
                    new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey());
            provider = new AWSStaticCredentialsProvider(credentials);
        } else {
            provider = new DefaultAWSCredentialsProviderChain();
        }

        return provider;
    }
    
    private ClientConfiguration clientConfiguration() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();

        clientConfiguration.setMaxConnections(awsProperties.getMaxConnections());
        clientConfiguration.setConnectionTimeout(awsProperties.getTimeout());
        clientConfiguration.setConnectionTTL(awsProperties.getTtl());
        clientConfiguration.setMaxErrorRetry(awsProperties.getMaxRetry());

        return clientConfiguration;
    }
}
