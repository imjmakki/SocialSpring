package app.social.spring.Utility.JWT;

public class JwtProperties {

    public static final String SECRET = "Social-Login";
    public static final Integer EXPIRATION_TIME = 864_000_000;
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
}
