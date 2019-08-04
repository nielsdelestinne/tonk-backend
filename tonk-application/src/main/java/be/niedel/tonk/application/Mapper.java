package be.niedel.tonk.application;

public interface Mapper<REQUEST, RESPONSE, DOMAIN> {

    DOMAIN toDomain(REQUEST request);
    RESPONSE toResponse(DOMAIN domain);

}
