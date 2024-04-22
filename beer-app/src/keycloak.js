import Keycloak from "keycloak-js";
const keycloak = new Keycloak({
 url: "http://localhost:8181/",
 realm: "beer-microservices-realm",
 clientId: "spring-cloud-client",
 checkLoginIframe: false,


});



export default keycloak;