import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509TrustManager;

public class DisableCertificateValidation {
    public static void main(String[] args) {
        // Отключаеч проверку SSL сертификата
        disableCertificateValidation();

        // Наш код посылающий HTTPS запросы
    }

    public static void disableCertificateValidation() {
        try {
            // Создайте trust manager, который не проверяет цепочки сертификатов
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {}
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            // Устанавливаем trust manager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}