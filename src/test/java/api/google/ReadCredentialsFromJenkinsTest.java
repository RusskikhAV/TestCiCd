package api.google;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Api")
public class ReadCredentialsFromJenkinsTest {
    private static final String EXPECTED_ACCESS_KEY = "admin";
    private static final String EXPECTED_SECRET_KEY = "admin2";

    @Test
    public void firstTest() {
        String accessKey = System.getenv("login");
        String secretKey = System.getenv("password");

        boolean isValid = validateCredentials(accessKey, secretKey);

        assertTrue(isValid, "Credentials validation failed: Invalid or missing values.");
    }

    private boolean validateCredentials(String accessKey, String secretKey) {
        if (accessKey == null || secretKey == null) {
            System.out.println("❌ ERROR: AWS credentials are missing!");
            return false;
        }
        if (!accessKey.equals(EXPECTED_ACCESS_KEY) || !secretKey.equals(EXPECTED_SECRET_KEY)) {
            System.out.println("❌ ERROR: AWS credentials are incorrect!");
            return false;
        }
        System.out.println("✅ AWS credentials are valid.");
        return true;
    }
}
