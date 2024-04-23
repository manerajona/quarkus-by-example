import Keycloak from "keycloak-js";

class KeycloackService {

    constructor() {
        this.keycloak = new Keycloak({
            url: 'http://smartbar-keycloak:8080',
            realm: 'sbo',
            clientId: 'smartbar-backoffice'
        })
    }

    async authenticate(callback){
        try {
            console.log('Authenticating')
            const authenticated = await this.keycloak.init({
                checkLoginIframe: false,
                onLoad: 'login-required'
            });
            console.log(`User is ${authenticated ? 'authenticated' : 'not authenticated'}`);
            if(authenticated) {
                callback()
            }

        } catch (error) {
            console.error('Failed to initialize adapter:', error);
        }
    }

}

export default KeycloackService
