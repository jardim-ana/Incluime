package incluime.conectamais.client;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;


public class S3Provider {

    private final S3Client s3Client;

    public S3Provider() {
        this.s3Client = S3Client.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    public S3Client getClient() {
        return s3Client;
    }
}
